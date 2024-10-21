package org.evomaster.core.output.service

import com.google.inject.Inject
import org.evomaster.core.output.Lines
import org.evomaster.core.problem.graphql.GraphQLAction
import org.evomaster.core.problem.graphql.GraphQLIndividual
import org.evomaster.core.problem.graphql.GraphQLUtils
import org.evomaster.core.problem.graphql.GraphQlCallResult
import org.evomaster.core.problem.graphql.service.GraphQLFitness
import org.evomaster.core.problem.httpws.HttpWsAction
import org.evomaster.core.problem.httpws.HttpWsCallResult
import org.evomaster.core.search.action.Action
import org.evomaster.core.search.action.ActionResult
import org.evomaster.core.search.EvaluatedIndividual
import org.evomaster.core.search.gene.utils.GeneUtils
import org.slf4j.LoggerFactory
import java.nio.file.Path

import java.io.File
import java.io.IOException
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.FileWriter
import java.io.PrintWriter
import java.io.BufferedWriter

class GraphQLTestCaseWriter : HttpWsTestCaseWriter() {

    companion object {
        private val log = LoggerFactory.getLogger(GraphQLTestCaseWriter::class.java)
    }

    @Inject
    protected lateinit var fitness: GraphQLFitness

    override fun handleActionCalls(lines: Lines, baseUrlOfSut: String, ind: EvaluatedIndividual<*>, insertionVars: MutableList<Pair<String, String>>, testCaseName: String, testSuitePath: Path?){
        if (ind.individual is GraphQLIndividual) {
            ind.evaluatedMainActions().forEachIndexed { index,  a ->
                handleSingleCall(a, index, ind.fitness, lines, testCaseName, testSuitePath, baseUrlOfSut)
            }
        }
    }

    override fun addActionLinesPerType(action: Action, index: Int, testCaseName: String, lines: Lines, result: ActionResult, testSuitePath: Path?, baseUrlOfSut: String) {
        addGraphQlCallLines(action as GraphQLAction, lines, result as GraphQlCallResult, baseUrlOfSut)
    }

    private fun addGraphQlCallLines(call: GraphQLAction, lines: Lines, result: GraphQlCallResult, baseUrlOfSut: String) {

        val responseVariableName = makeHttpCall(call, lines, result, baseUrlOfSut)
        handleResponseAfterTheCall(call, result, responseVariableName, lines)
    }

    override fun handleBody(call: HttpWsAction, lines: Lines) {

        /*
            TODO: when/if we are going to deal with GET, then we will need to update/refactor this code
         */

        when {
            format.isJavaOrKotlin() -> lines.add(".contentType(\"application/json\")")
            format.isJavaScript() -> lines.add(".set('Content-Type','application/json')")
            format.isPython() -> lines.add("headers[\"content-type\"] = \"application/json\"")
           // format.isCsharp() -> lines.add("Client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue(\"application/json\"));")
        }


        val gql = call as GraphQLAction

        val body = GraphQLUtils.generateGQLBodyEntity(gql, format)
        try {
            // Fileオブジェクトを作成
            val file = FileWriter("queries.txt")
            val pw = PrintWriter(BufferedWriter(file))

            pw.println(body!!.entity)

            println(body!!.entity)
            pw.close()
            val content = body?.toString() ?: throw IllegalArgumentException("Body is null")

            // ファイルに文字列を書き込む
            // file.writeText(body!!.entity)

            // println("Successfully wrote to the file.")
        } catch (e: IOException) {
            println("An error occurred while writing to the file: ${e.message}")
        } catch (e: Exception) {
            println("An unexpected error occurred: ${e.message}")
        }

        // val coverageData = runGraphQLInspector()
        // if (coverageData != null) {
        //     val typesCoverage = coverageData["Types covered"]?.removeSuffix("%")?.toFloatOrNull()?.div(100.0)
        //     println("Types Coverage: $typesCoverage")
        // }
        printSendJsonBody(body!!.entity, lines)
    }

    // fun runGraphQLInspector(): Map<String, String>? {
    //     try {
    //         // プロセスビルダーを使用してコマンドを実行
    //         val processBuilder = ProcessBuilder(
    //             "graphql-inspector", "coverage", "queries.graphql", "spacex_schema.graphql"
    //         )
    //         processBuilder.redirectErrorStream(true)

    //         val process = processBuilder.start()

    //         // コマンドの出力を読み取る
    //         val reader = BufferedReader(InputStreamReader(process.inputStream))
    //         val output = StringBuilder()
    //         var line: String? = null

    //         while (reader.readLine().also { line = it } != null) {
    //             output.append(line).append("\n")
    //         }

    //         val exitCode = process.waitFor()

    //         if (exitCode != 0) {
    //             println("Error running graphql-inspector: $output")
    //             return null
    //         }

    //         // 出力を解析してマップに変換
    //         return parseTable(output.toString())

    //     } catch (e: Exception) {
    //         println("Exception occurred: ${e.message}")
    //         return null
    //     }
    // }

    // fun parseTable(inputString: String): Map<String, String> {
    //     val pattern = Regex("""│\s*(.*?)\s*│\s*(.*?)\s*│""")
    //     val matches = pattern.findAll(inputString)

    //     val result = mutableMapOf<String, String>()
    //     for (match in matches) {
    //         val key = match.groupValues[1].trim()
    //         val value = match.groupValues[2].trim()
    //         result[key] = value
    //     }

    //     return result
    // }

    override fun getAcceptHeader(call: HttpWsAction, res: HttpWsCallResult): String {

        val accept = openAcceptHeader()

        /**
         * GQL services typically respond using JSON
         */
        var result =  "$accept\"application/json\""
        result = closeAcceptHeader(result)
        return result
    }


    override fun handleLastStatementComment(res: HttpWsCallResult, lines: Lines){

        super.handleLastStatementComment(res, lines)

        val code = res.getStatusCode()

        /*
            if last line has already been added due to 500, no point in adding again
         */

        val gql = res as GraphQlCallResult

        if (code != 500 && gql.hasLastStatementWhenGQLError()) {
            lines.appendSingleCommentLine(gql.getLastStatementWhenGQLErrors() ?: "")
        }
    }

    override fun handleVerbEndpoint(baseUrlOfSut: String, _call: HttpWsAction, lines: Lines) {

        // TODO maybe in future might want to have GET for QUERY types
        val verb = "post"
        lines.add(".$verb(")

        if(config.blackBox){
            /*
                in BB, the baseUrl is actually the full endpoint
             */

            when {
                format.isKotlin() -> lines.append("\"\${$baseUrlOfSut}\"")
                format.isPython() -> lines.append("self.$baseUrlOfSut")
                else -> lines.append("$baseUrlOfSut")
            }
        } else {

            if (format.isKotlin()) {
                lines.append("\"\${$baseUrlOfSut}")
            } else {
                lines.append("$baseUrlOfSut + \"")
            }

           val path= fitness.infoDto.graphQLProblem.endpoint

              // infoDto.graphQLProblem?.endpoint

            lines.append("${path?.let { GeneUtils.applyEscapes(it, mode = GeneUtils.EscapeMode.NONE, format = format) }}\"")
        }

        if (format.isPython()) {
            handlePythonVerbEndpoint(_call as GraphQLAction, lines) {
                lines.append(", data=body")
            }
        }

        lines.append(")")
    }

}

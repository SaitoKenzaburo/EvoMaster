package org.evomaster.e2etests.spring.rest.bb.datapool

import com.foo.rest.examples.bb.datapool.BBDataPoolController
import org.evomaster.core.output.OutputFormat
import org.evomaster.core.problem.rest.HttpVerb
import org.evomaster.e2etests.spring.rest.bb.SpringTestBase
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class BBDataPoolEMTest : SpringTestBase() {

    companion object {
        @BeforeAll
        @JvmStatic
        fun init() {
            initClass(BBDataPoolController())
        }
    }


    @Test
    fun testJavaScript(){

        val folderName = "datapool"

        runTestForJS(folderName, 200, 3){ args: MutableList<String> ->

            setOption(args, "useResponseDataPool", "true")

            val solution = initAndRun(args)

            Assertions.assertTrue(solution.individuals.size >= 1)
            assertHasAtLeastOne(solution, HttpVerb.GET, 200, "/api/bbdatapool/users/{id}", null)
        }

        runNpmTests(relativePath(folderName))
    }

    @Test
    fun testRunEMOk() {
        runTestHandlingFlakyAndCompilation(
                "BBDataPoolEM",
                200
        ) { args: MutableList<String> ->

            addBlackBoxOptions(args, OutputFormat.KOTLIN_JUNIT_5)
            setOption(args, "useResponseDataPool", "true")

            val solution = initAndRun(args)

            Assertions.assertTrue(solution.individuals.size >= 1)
            assertHasAtLeastOne(solution, HttpVerb.GET, 200, "/api/bbdatapool/users", null)

            assertHasAtLeastOne(solution, HttpVerb.GET, 404, "/api/bbdatapool/users/{id}", null)
            assertHasAtLeastOne(solution, HttpVerb.PUT, 404, "/api/bbdatapool/users/{id}", null)
            assertHasAtLeastOne(solution, HttpVerb.PATCH, 404, "/api/bbdatapool/users/{id}", null)
            assertHasAtLeastOne(solution, HttpVerb.DELETE, 404, "/api/bbdatapool/users/{id}", null)

            //with data pool, should be possible to handle the GET
            assertHasAtLeastOne(solution, HttpVerb.GET, 200, "/api/bbdatapool/users/{id}", null)

            //but MUST NOT do it for write operations
            assertNone(solution, HttpVerb.PUT, 200, "/api/bbdatapool/users/{id}", null)
            assertNone(solution, HttpVerb.PATCH, 200, "/api/bbdatapool/users/{id}", null)
            assertNone(solution, HttpVerb.DELETE, 200, "/api/bbdatapool/users/{id}", null)
        }
    }


}
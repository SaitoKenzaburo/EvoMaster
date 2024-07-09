package org.evomaster.e2etests.spring.openapi.v3

import com.foo.rest.examples.spring.openapi.v3.taintkotlinequal.TaintKotlinEqualController
import org.evomaster.client.java.controller.InstrumentedSutStarter
import org.evomaster.client.java.instrumentation.InputProperties
import org.evomaster.client.java.instrumentation.InstrumentingAgent
import org.evomaster.core.EMConfig
import org.evomaster.core.output.OutputFormat
import org.evomaster.e2etests.utils.RestTestBase
import org.junit.jupiter.api.BeforeAll

/**
 * Created by arcuri82 on 03-Mar-20.
 */
abstract class SpringTestBase : RestTestBase(){

    companion object {
        @BeforeAll
        @JvmStatic
        fun initAgent() {
            /*
                needed because kotlin.jvm.internal.Intrinsics gets loaded in
                TaintKotlinEqualController before agent is initialized
             */
            System.setProperty(InputProperties.REPLACEMENT_CATEGORIES, "BASE,SQL,EXT_0")
            InstrumentedSutStarter.loadAgent()
            InstrumentingAgent.changePackagesToInstrument("com.foo.")
        }
    }


    protected fun addBlackBoxOptions(
        args: MutableList<String>,
        outputFormat: OutputFormat
    ){
        setOption(args, "blackBox", "true")
        setOption(args, "bbTargetUrl", baseUrlOfSut)
        setOption(args, "bbSwaggerUrl", "$baseUrlOfSut/v3/api-docs")
        setOption(args, "problemType", "REST")
        setOption(args, "outputFormat", outputFormat.toString())
    }
}
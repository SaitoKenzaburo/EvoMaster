package org.evomaster.core.problem.external.service

import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.common.Metadata.metadata
import org.evomaster.core.problem.external.service.param.ResponseParam
import org.evomaster.core.search.Action
import org.evomaster.core.search.StructuralElement
import org.evomaster.core.search.gene.Gene

/**
 * Action to execute the external service related need
 * to handle the external service calls.
 *
 * Typically, handle WireMock responses
 */
class ExternalServiceAction(

    /**
     * Received request to the respective WireMock instance
     *
     * TODO: Need to expand the properties further in future
     *  depending on the need
     */
    val request: ExternalServiceRequest,

    /**
     * currently, we support response with json format
     * then use ObjectGene now,
     * might extend it later
     */
    val response: ResponseParam = ResponseParam(),

    /**
     * WireMock server which received the request
     */
    val externalService: ExternalService,
    active : Boolean = false,
    used : Boolean = false,
    private val id: Long,
    localId : String
) : Action(listOf(response), localId) {


    /**
     * it represents whether the external service is instanced before executing the corresponding API call
     *
     * Note that it should be always re-assigned based on [used] before fitness evaluation, see [resetActive]
     */
    var active : Boolean = active
        private set


    /**
     * it represents whether the external service is used when executing the corresponding API call
     *
     * Note that it should be updated after the fitness evaluation based on whether the external service is used during the API execution
     */
    var used : Boolean = used
        private set

    companion object {
        private fun buildResponse(template: String): ResponseParam {
            // TODO: refactor later
            return ResponseParam()
        }
    }

    constructor(request: ExternalServiceRequest, template: String, externalService: ExternalService, id: Long, localId: String = NONE_ACTION_COMPONENT_ID) :
            this(request, buildResponse(template), externalService, id = id, localId = localId)

    init {
        // TODO: This is not the correct way to do this, but for now
        //  to test concept, this is triggered here.
        this.buildResponse()
    }

    /**
     * UUID generated by WireMock is used under ExternalServiceRequest
     * is used as ID for action.
     *
     * TODO: After the ID refactor, this needs to be changed.
     */
    override fun getName(): String {
        return request.id.toString()
    }

    override fun seeTopGenes(): List<out Gene> {
        return response.genes
    }

    override fun shouldCountForFitnessEvaluations(): Boolean {
        return false
    }

    /**
     * Each external service will have a WireMock instance representing that
     * so when the ExternalServiceAction is copied, same instance will be passed
     * into the copy too. Otherwise, we have to manage multiple instances for the
     * same external service.
     */
    override fun copyContent(): StructuralElement {
        return ExternalServiceAction(
            request,
            response.copy() as ResponseParam,
            externalService,
            active,
            used,
            id,
            localId = getLocalId()
        )
    }

    /**
     * reset active based on [used]
     * it should be used before fitness evaluation
     */
    fun resetActive() {
        this.active = this.used
    }

    /**
     * based on the feedback from the mocked service,
     * the external service is confirmed that it is used during the API execution
     */
    fun confirmUsed()  {
        this.used = true
    }

    /**
     * based on the feedback from the mocked service,
     * the external service is confirmed that it is not used during the API execution
     */
    fun confirmNotUsed() {
        this.used = false
    }

    /**
     * Experimental implementation of WireMock stub generation
     *
     * Method should randomize the response code
     *
     * TODO: This has to moved separetly to have extensive features
     *  in future.
     */
    fun buildResponse() {
        if (externalService.getWireMockServer().findStubMappingsByMetadata(matchingJsonPath("$.url", containing(request.url)))
                .isEmpty()
        ) {
            externalService.getWireMockServer().stubFor(
                get(urlMatching(request.url))
                    .atPriority(1)
                    .willReturn(
                        aResponse()
                            .withStatus(viewStatus())
                            .withBody(viewResponse())
                    )
                    .withMetadata(
                        metadata()
                            .attr("url", request.url)
                    )
            )
        }
    }

    private fun viewStatus(): Int {
        return response.status.getValueAsRawString().toInt()
    }

    private fun viewResponse(): String {
        // TODO: This needs to be refactored
        return "{}"
    }

}
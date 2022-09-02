package org.evomaster.core.search.gene.network

import org.evomaster.core.logging.LoggingUtil
import org.evomaster.core.output.OutputFormat
import org.evomaster.core.search.gene.*
import org.evomaster.core.search.service.AdaptiveParameterControl
import org.evomaster.core.search.service.Randomness
import org.evomaster.core.search.service.mutator.genemutation.AdditionalGeneMutationInfo
import org.evomaster.core.search.service.mutator.genemutation.SubsetGeneSelectionStrategy
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class InetGene(
        name: String,
        private val octets: List<IntegerGene> = List(INET_SIZE)
        { i -> IntegerGene("b$i", min = 0, max = 255) }
) : CompositeFixedGene(name, octets.toMutableList()) {

    companion object {
        const val INET_SIZE = 4
        val log: Logger = LoggerFactory.getLogger(InetGene::class.java)
    }

    override fun isLocallyValid() : Boolean{
        return getViewOfChildren().all { it.isLocallyValid() }
    }

    override fun randomize(randomness: Randomness, tryToForceNewValue: Boolean) {
        octets.forEach { it.randomize(randomness, tryToForceNewValue) }
    }

    override fun candidatesInternalGenes(
            randomness: Randomness,
            apc: AdaptiveParameterControl,
            selectionStrategy: SubsetGeneSelectionStrategy,
            enableAdaptiveGeneMutation: Boolean,
            additionalGeneMutationInfo: AdditionalGeneMutationInfo?
    ): List<Gene> {
        return octets.toList()
    }

    override fun getValueAsPrintableString(
            previousGenes: List<Gene>,
            mode: GeneUtils.EscapeMode?,
            targetFormat: OutputFormat?,
            extraCheck: Boolean
    ): String = "\"${getValueAsRawString()}\""

    override fun getValueAsRawString() = this.octets
            .map { it.value }
            .joinToString(".")



    override fun innerGene(): List<Gene> = octets.toList()

    override fun bindValueBasedOn(gene: Gene): Boolean {
        return when {
            gene is InetGene -> {
                var result = true
                repeat(octets.size) {
                    result = result && octets[it].bindValueBasedOn(gene.octets[it])
                }
                result
            }
            else -> {
                LoggingUtil.uniqueWarn(log, "cannot bind MacAddrGene with ${gene::class.java.simpleName}")
                false
            }
        }
    }

    override fun copyValueFrom(other: Gene) {
        if (other !is InetGene) {
            throw IllegalArgumentException("Invalid gene type ${other.javaClass}")
        }
        if (octets.size != other.octets.size) {
            throw IllegalArgumentException(
                    "cannot bind MacAddrGene${octets.size} with MacAddrGene${other.octets.size}"
            )
        }
        repeat(octets.size) {
            octets[it].copyValueFrom(other.octets[it])
        }
    }

    override fun containsSameValueAs(other: Gene): Boolean {
        if (other !is InetGene) {
            throw IllegalArgumentException("Invalid gene type ${other.javaClass}")
        }
        if (octets.size != other.octets.size) {
            return false
        }
        var result = true
        repeat(octets.size) {
            result = result && octets[it].containsSameValueAs(other.octets[it])
        }
        return result
    }

    override fun copyContent() = InetGene(name, octets.map { it.copy() as IntegerGene }.toList())

}
package org.evomaster.core.search.gene.sql

import org.evomaster.core.logging.LoggingUtil
import org.evomaster.core.output.OutputFormat
import org.evomaster.core.search.gene.CompositeFixedGene
import org.evomaster.core.search.gene.Gene
import org.evomaster.core.search.gene.GeneUtils
import org.evomaster.core.search.gene.ObjectGene
import org.evomaster.core.search.impact.impactinfocollection.sql.SqlXmlGeneImpact
import org.evomaster.core.search.service.AdaptiveParameterControl
import org.evomaster.core.search.service.Randomness
import org.evomaster.core.search.service.mutator.MutationWeightControl
import org.evomaster.core.search.service.mutator.genemutation.AdditionalGeneMutationInfo
import org.evomaster.core.search.service.mutator.genemutation.SubsetGeneSelectionStrategy
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SqlXMLGene(name: String,
                 val objectGene: ObjectGene = ObjectGene(name, fields = listOf())
) : CompositeFixedGene(name, mutableListOf(objectGene)) {

    companion object{
        private val log: Logger = LoggerFactory.getLogger(SqlXMLGene::class.java)
    }

    override fun isLocallyValid() : Boolean{
        return getViewOfChildren().all { it.isLocallyValid() }
    }

    override fun copyContent(): Gene = SqlXMLGene(
            name,
            objectGene = this.objectGene.copy() as ObjectGene)


    override fun randomize(randomness: Randomness, tryToForceNewValue: Boolean, allGenes: List<Gene>) {
        objectGene.randomize(randomness, tryToForceNewValue, allGenes)
    }


    override fun candidatesInternalGenes(randomness: Randomness, apc: AdaptiveParameterControl, allGenes: List<Gene>, selectionStrategy: SubsetGeneSelectionStrategy, enableAdaptiveGeneMutation: Boolean, additionalGeneMutationInfo: AdditionalGeneMutationInfo?): List<Gene> {
        return if (objectGene.isMutable()) listOf(objectGene) else emptyList()
    }

    override fun adaptiveSelectSubset(randomness: Randomness, internalGenes: List<Gene>, mwc: MutationWeightControl, additionalGeneMutationInfo: AdditionalGeneMutationInfo): List<Pair<Gene, AdditionalGeneMutationInfo?>> {
        if (additionalGeneMutationInfo.impact != null && additionalGeneMutationInfo.impact is SqlXmlGeneImpact){
            if (internalGenes.size != 1 || !internalGenes.contains(objectGene))
                throw IllegalStateException("mismatched input: the internalGenes should only contain objectGene")
            return listOf(objectGene to additionalGeneMutationInfo.copyFoInnerGene(additionalGeneMutationInfo.impact.geneImpact, objectGene))
        }
        throw IllegalArgumentException("impact is null or not SqlXmlGeneImpact")
    }

    override fun shallowMutate(randomness: Randomness, apc: AdaptiveParameterControl, mwc: MutationWeightControl, allGenes: List<Gene>, selectionStrategy: SubsetGeneSelectionStrategy, enableAdaptiveGeneMutation: Boolean, additionalGeneMutationInfo: AdditionalGeneMutationInfo?): Boolean {
        // do nothing since the objectGene is not mutable
        return true
    }

    override fun getValueAsPrintableString(previousGenes: List<Gene>, mode: GeneUtils.EscapeMode?, targetFormat: OutputFormat?, extraCheck: Boolean): String {
        val rawValue = objectGene.getValueAsPrintableString(previousGenes, GeneUtils.EscapeMode.XML , targetFormat)
        when {
            // TODO: refactor with StringGene.getValueAsPrintableString(()
            (targetFormat == null) -> return "\"$rawValue\""
            targetFormat.isKotlin() -> return "\"$rawValue\""
                    .replace("\\", "\\\\")
                    .replace("$", "\\$")
            else -> return "\"$rawValue\""
                    .replace("\\", "\\\\")
        }

    }

    override fun copyValueFrom(other: Gene) {
        if (other !is SqlXMLGene) {
            throw IllegalArgumentException("Invalid gene type ${other.javaClass}")
        }
        this.objectGene.copyValueFrom(other.objectGene)
    }

    /**
     * Genes might contain a value that is also stored
     * in another gene of the same type.
     */
    override fun containsSameValueAs(other: Gene): Boolean {
        if (other !is SqlXMLGene) {
            throw IllegalArgumentException("Invalid gene type ${other.javaClass}")
        }
        return this.objectGene.containsSameValueAs(other.objectGene)
    }




    override fun mutationWeight(): Double {
        return  objectGene.mutationWeight()
    }

    override fun innerGene(): List<Gene> = listOf(objectGene)

    override fun bindValueBasedOn(gene: Gene): Boolean {
        return when(gene){
            is SqlXMLGene -> objectGene.bindValueBasedOn(gene.objectGene)
            is SqlJSONGene -> objectGene.bindValueBasedOn(gene.objectGene)
            is ObjectGene -> objectGene.bindValueBasedOn(gene)
            else->{
                LoggingUtil.uniqueWarn(log, "cannot bind SqlXMLGene with ${gene::class.java.simpleName}")
                false
            }
        }
    }


}
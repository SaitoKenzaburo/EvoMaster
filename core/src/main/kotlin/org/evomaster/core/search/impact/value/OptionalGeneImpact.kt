package org.evomaster.core.search.impact.value

import org.evomaster.core.search.gene.Gene
import org.evomaster.core.search.gene.OptionalGene
import org.evomaster.core.search.impact.*
import org.evomaster.core.search.impact.value.numeric.BinaryGeneImpact

/**
 * created by manzh on 2019-09-09
 */
class OptionalGeneImpact  (
        sharedImpactInfo: SharedImpactInfo,
        specificImpactInfo: SpecificImpactInfo,
        val activeImpact : BinaryGeneImpact,
        val geneImpact: GeneImpact
) : GeneImpact(sharedImpactInfo, specificImpactInfo){

    constructor(
            id : String,
            degree: Double = 0.0,
            timesToManipulate : Int = 0,
            timesOfNoImpacts : Int = 0,
            timesOfNoImpactWithTargets : MutableMap<Int, Int> = mutableMapOf(),
            timesOfImpact : MutableMap<Int, Int> = mutableMapOf(),
            noImpactFromImpact : MutableMap<Int, Int> = mutableMapOf(),
            noImprovement : MutableMap<Int, Int> = mutableMapOf(),
            activeImpact : BinaryGeneImpact = BinaryGeneImpact("isActive"),
            geneImpact: GeneImpact

    ) : this(
            SharedImpactInfo(id, degree, timesToManipulate, timesOfNoImpacts, timesOfNoImpactWithTargets, timesOfImpact),
            SpecificImpactInfo(noImpactFromImpact, noImprovement),
            activeImpact,
            geneImpact
    )

    constructor(id : String, optionalGene: OptionalGene) : this(id, geneImpact = ImpactUtils.createGeneImpact(optionalGene.gene, id))

    override fun copy(): OptionalGeneImpact {
        return OptionalGeneImpact(
                shared.copy(),
                specific.copy(),
                activeImpact = activeImpact.copy(),
                geneImpact = geneImpact.copy() as GeneImpact)
    }

    override fun clone(): OptionalGeneImpact {
        return OptionalGeneImpact(
                shared.clone(),
                specific.clone(),
                activeImpact = activeImpact.clone(),
                geneImpact = geneImpact.clone())
    }

    override fun countImpactWithMutatedGeneWithContext(gc: MutatedGeneWithContext, noImpactTargets: Set<Int>, impactTargets: Set<Int>, improvedTargets: Set<Int>, onlyManipulation: Boolean) {
        countImpactAndPerformance(noImpactTargets = noImpactTargets, impactTargets = impactTargets, improvedTargets = improvedTargets, onlyManipulation = onlyManipulation)

        if (gc.current !is OptionalGene)
            throw IllegalStateException("gc.current(${gc.current::class.java.simpleName}) should be OptionalGene")

        if (gc.previous != null && gc.previous !is OptionalGene)
            throw IllegalStateException("gc.pervious (${gc.previous::class.java.simpleName}) should be OptionalGene")

        if (gc.previous == null || (gc.previous as OptionalGene).isActive != gc.current.isActive){
            activeImpact.countImpactAndPerformance(noImpactTargets = noImpactTargets, impactTargets = impactTargets, improvedTargets = improvedTargets, onlyManipulation = onlyManipulation)
            if (gc.current.isActive)
                activeImpact.trueValue.countImpactAndPerformance(noImpactTargets = noImpactTargets, impactTargets = impactTargets, improvedTargets = improvedTargets, onlyManipulation = onlyManipulation)
            else
                activeImpact.falseValue.countImpactAndPerformance(noImpactTargets = noImpactTargets, impactTargets = impactTargets, improvedTargets = improvedTargets, onlyManipulation = onlyManipulation)

            if (gc.previous != null){
                return
            }
        }

        if (gc.previous == null && impactTargets.isNotEmpty()) return

        if (gc.current.isActive){
            val mutatedGeneWithContext = MutatedGeneWithContext(
                    previous = if (gc.previous==null) null else (gc.previous as OptionalGene).gene,
                    current = gc.current.gene
            )
            geneImpact.countImpactWithMutatedGeneWithContext(mutatedGeneWithContext, noImpactTargets = noImpactTargets, impactTargets = impactTargets, improvedTargets = improvedTargets, onlyManipulation = onlyManipulation)
        }

    }


    override fun validate(gene: Gene): Boolean = gene is OptionalGene

    override fun flatViewInnerImpact(): Map<String, Impact> {
        return mutableMapOf(
                "${getId()}-activeImpact" to activeImpact
        ).plus(activeImpact.flatViewInnerImpact()).plus("${getId()}-geneImpact" to geneImpact).plus(geneImpact.flatViewInnerImpact())
    }
}
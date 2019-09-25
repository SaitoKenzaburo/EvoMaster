package org.evomaster.experiments.archiveMutation.stringProblem.partialIndepStable

import com.google.inject.Inject
import org.evomaster.core.search.gene.StringGene
import org.evomaster.core.search.service.mutator.geneMutation.CharPool
import org.evomaster.core.search.service.Randomness
import org.evomaster.experiments.archiveMutation.stringProblem.StringIndividual
import org.evomaster.experiments.archiveMutation.stringProblem.StringProblemDefinition

/**
 * created by manzh on 2019-09-16
 */
class PIndepStableStringProblemDefinition : StringProblemDefinition() {

    var optima: MutableList<String> = mutableListOf()

    override fun init(n : Int, sLength : Int, maxLength: Int){
        nTargets = n
        nGenes = n * 3 // only 1/3 genes contributes to fitness
        specifiedLength = sLength
        this.maxLength = maxLength
        optima.clear()
        (0 until nTargets).forEach { _ ->
            optima.add(randomString())
        }
    }

    override fun distance(individual: StringIndividual): Map<Int, Double> {
        return distance(individual.seeGenes().toMutableList())
    }

    private fun distance(list: MutableList<StringGene>) : Map<Int, Double>{
        assert(list.size * 3 == optima.size)
        val result = mutableMapOf<Int, Double>()
        (0 until nTargets).forEach{ index ->
            result[index] = leftDistance(optima[index], list[index * 3].value )
        }
        return result
    }
}
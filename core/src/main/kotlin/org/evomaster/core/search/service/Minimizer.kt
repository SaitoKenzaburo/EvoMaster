package org.evomaster.core.search.service

import com.google.inject.Inject
import org.evomaster.core.EMConfig
import org.evomaster.core.logging.LoggingUtil
import org.evomaster.core.problem.gui.GuiIndividual
import org.evomaster.core.search.GroupsOfChildren
import org.evomaster.core.search.Individual
import org.evomaster.core.search.service.mutator.StructureMutator
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Reduce/simplify the final test outputs
 */
class Minimizer<T: Individual> {

    companion object{
        private val log : Logger = LoggerFactory.getLogger(Minimizer::class.java)
    }

    @Inject
    private lateinit var archive: Archive<T>

    @Inject
    private lateinit var fitness : FitnessFunction<T>

    @Inject
    private lateinit var config: EMConfig

    @Inject
    private lateinit var mutator: StructureMutator


    private var startTimer : Long = -1


    fun doStartTheTimer(){
        startTimer = System.currentTimeMillis()
    }

    fun passedTimeInSecond() : Int {
        if(startTimer < 0){
            throw IllegalStateException("Timer was not started")
        }
        return ((System.currentTimeMillis() - startTimer) / 1000).toInt()
    }

    fun checkHasTimedout() : Boolean{
        if(startTimer < 0){
            throw IllegalStateException("Timer was not started")
        }
        if(config.minimizeTimeout < 0){
            return false
        }
        if(config.minimizeTimeout == 0){
            return true
        }
        val current = System.currentTimeMillis()
        val passed = (current - startTimer) / (1000 * 60.0)
        return passed >- config.minimizeTimeout
    }

    fun pruneNonNeededDatabaseActions(){
        //TODO
    }

    /**
     * eg, removed un-needed optional parameters
     */
    fun simplifyActions(){
        //TODO
    }

    /**
     * Based on the tests in the archive, update the archive by having, for each target T,
     * the minimum number of actions needed to cover it.
     *
     * Using the same/similar kind of algorithm as explained in:
     *
     * "EvoSuite: On The Challenges of Test Case Generation in the Real World"
     */
    fun minimizeMainActionsPerCoveredTargetInArchive() {

        if(checkHasTimedout()){
           LoggingUtil.getInfoLogger().warn("Minimization phase has timed-out. You can use --minimizeTimeout to increase it.")
           return
        }

        LoggingUtil.getInfoLogger().info("Starting to apply minimization phase")

        recomputeArchiveWithFullCoverageInfo()

        val current = archive.getCopyOfUniqueCoveringIndividuals()
            .filter {
                //it.size() > 1 // FIXME, see issue described below
                it.groupsView()!!.sizeOfGroup(GroupsOfChildren.MAIN) > 1
            } //can't minimize below 1

        LoggingUtil.getInfoLogger().info("Analyzing ${current.size} tests with size greater than 1")

        val n = current.size
        var k = 0;

        if(config.showProgress){
            println()
            printProgress(k,n)
        }

        current.forEach{

            if(checkHasTimedout()){
                LoggingUtil.getInfoLogger().warn("Minimization phase has timed-out. You can use --minimizeTimeout to increase it.")
                return
            }

            k++

            if(it !is GuiIndividual) { //doesn't make sense for GUI sequences, as strongly dependent
                val singles = splitIntoSingleCalls(it)
                singles.forEach { s ->
                    fitness.computeWholeAchievedCoverageForPostProcessing(s)?.run { archive.addIfNeeded(this) }
                }
            }

            /*
                Above works well if each action is independent.
                To handle cases in which there is need of more than 1 action together, we need to remove each
                action one at a time, and see if there is any improvement. For example, having size N,
                remove from 0 to N, one at a time. If any of those is successful (ie addIfNeeded returns true), then
                re-apply recursively to all the successful copies with length N-1.

                TODO implement such algorithm
             */

            printProgress(k,n)
        }
    }

    private fun splitIntoSingleCalls(ind: T) : List<T>{

        val n = ind.groupsView()!!.sizeOfGroup(GroupsOfChildren.MAIN)
        //val n = ind.size()
        /*
            FIXME: we currently have a rather major limitation, has in REST we have group of calls
            related to same resources, and cannot currently delete single calls with messing up lot of things...
            We need to do some major refactoring.

            Man comments:
            there might be two options:
            1) re-construct  RestResourceCalls , e.g., three actions, A-B-C, remove B, then construct the resources with A and C,
            2) remove the resource only if all actions are reductant
            there might be a problematic regarding value binding, then you can remove all binding before the minimization phase, since it is last one
            see replaceResourceCall  in RestIndividual , it can use for option 1.  removeResourceCall  can be used to remove resource, eg, option 2
            replaceResourceCall  and removeResourceCall  have handled the binding, should be fine.
        */
        if(n <= 1){
            throw IllegalArgumentException("Need at least 2 actions to apply split")
        }

        val copy =  ind.copy()

        return (0 until n)
            .map {index ->  (copy.copy() as T)
                                .apply { removeAllMainActionsButIndex(this,index) }
            }
    }

    private fun removeAllMainActionsButIndex(ind: T, index: Int){

        val n = ind.size()

        for(i in n-1 downTo index+1){
            ind.removeMainExecutableAction(i)
        }
        for(i in 0 until index){
            ind.removeMainExecutableAction(0)
        }
    }

    private fun printProgress(k: Int, n: Int){
        if(! config.showProgress){
            return
        }

        //TODO check if need a time-delta for updates, as in SearchStatusUpdater

        SearchStatusUpdater.upLineAndErase()
        println("Minimization progress: $k/$n")
    }

    private fun recomputeArchiveWithFullCoverageInfo(){
        val current = archive.getCopyOfUniqueCoveringIndividuals()

        LoggingUtil.getInfoLogger().info("Recomputing full coverage for ${current.size} tests")

        val beforeCovered = archive.numberOfCoveredTargets()

        /*
            Previously evaluated individual only had partial info, due to performance issues.
            Need to make sure to fetch all coverage info.
         */
        val population = current.mapNotNull {
            val ei = fitness.computeWholeAchievedCoverageForPostProcessing(it)
            if(ei == null){
                log.warn("Failed to re-evaluate individual during minimization")
            }
            ei
        }

        archive.clearPopulations()

        population.forEach{archive.addIfNeeded(it)}

        val afterCovered = archive.numberOfCoveredTargets()

        if(afterCovered < beforeCovered){
            //could happen if background threads, for example
            LoggingUtil.getInfoLogger().warn("Recomputing coverage did lose some targets: from $beforeCovered to $afterCovered" +
                    ", i.e., lost ${beforeCovered-afterCovered}")
            assert(false)//shouldn't really happen in the E2E...
        }
    }
}
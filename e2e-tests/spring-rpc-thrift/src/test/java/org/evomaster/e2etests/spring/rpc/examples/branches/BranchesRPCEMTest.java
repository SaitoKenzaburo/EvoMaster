package org.evomaster.e2etests.spring.rpc.examples.branches;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foo.rpc.examples.spring.branches.BranchesRPCController;
import com.foo.rpc.examples.spring.branches.BranchesResponseDto;
import org.evomaster.core.problem.rpc.RPCIndividual;
import org.evomaster.core.search.Solution;
import org.evomaster.e2etests.spring.rpc.examples.SpringRPCTestBase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BranchesRPCEMTest extends SpringRPCTestBase {

    @BeforeAll
    public static void initClass() throws Exception {

        BranchesRPCController controller = new BranchesRPCController();
        SpringRPCTestBase.initClass(controller);
    }

    @Test
    public void testRunEM() throws Throwable {

        runTestHandlingFlakyAndCompilation(
                "BranchesRPCEM",
                "org.foo.BranchesRPCEM",
                5000,
                (args) -> {

                    Solution<RPCIndividual> solution = initAndRun(args);

                    assertTrue(solution.getIndividuals().size() >= 1);
                });
    }
}

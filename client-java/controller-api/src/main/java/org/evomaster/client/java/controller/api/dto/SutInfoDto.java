package org.evomaster.client.java.controller.api.dto;

import org.evomaster.client.java.controller.api.dto.database.schema.DbSchemaDto;
import org.evomaster.client.java.controller.api.dto.problem.RPCProblemDto;
import org.evomaster.client.java.controller.api.dto.problem.RestProblemDto;
import org.evomaster.client.java.controller.api.dto.problem.GraphQLProblemDto;

import java.util.List;
import java.util.Set;

public class SutInfoDto {

    /**
     * If the SUT is a RESTful API, here there will be the info
     * on how to interact with it
     */
    public RestProblemDto restProblem;

    /**
     * If the SUT is a GraphQL API, here there will be the info
     * on how to interact with it
     */
    public GraphQLProblemDto graphQLProblem;

    /**
     * If the SUT is a PRC-based service, here there will be the info
     * on how to interact with it
     */
    public RPCProblemDto rpcProblem;

    /**
     * Whether the SUT is running or not
     */
    public Boolean isSutRunning;

    /*
        Note: this enum must be kept in sync with what declared in
        org.evomaster.core.output.OutputFormat
     */
    public enum OutputFormat {
        JAVA_JUNIT_5,
        JAVA_JUNIT_4,
        KOTLIN_JUNIT_4,
        KOTLIN_JUNIT_5,
        JS_JEST,
        CSHARP_XUNIT
    }

    /**
     * When generating test cases for this SUT, specify the default
     * preferred output format (eg JUnit 4 in Java)
     */
    public OutputFormat defaultOutputFormat;

    /**
     * The base URL of the running SUT (if any).
     * E.g., "http://localhost:8080"
     * It should only contain the protocol and the hostname/port
     */
    public String baseUrlOfSUT;

    /**
     * There is no way a testing system can guess passwords, even
     * if given full access to the database storing them (ie, reversing
     * hash values).
     * As such, the SUT might need to provide a set of valid credentials
     */
    public List<AuthenticationDto> infoForAuthentication;


    /**
     * If the application is using a SQL database, then we need to
     * know its schema to be able to do operations on it.
     */
    public DbSchemaDto sqlSchemaDto;


    /**
     * Information about the "units" in the SUT.
     */
    public UnitsInfoDto unitsInfoDto;

    /**
     * Information about the external services used inside the SUT
     */
    public Set<ExternalServiceInfoDto> externalServicesDto;
}

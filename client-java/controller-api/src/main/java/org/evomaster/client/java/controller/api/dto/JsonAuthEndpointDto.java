package org.evomaster.client.java.controller.api.dto;

/**
 * process auth setting with endpoints in interfaces
 */
public class JsonAuthEndpointDto {

    /**
     * The id representing this user that is going to login
     */
    public String userId;

    /**
     * The interface which contains the endpoint
     */
    public String interfaceName;

    /**
     * The endpoint where to process the auth setting
     */
    public String endpointName;

    /**
     * The payload to send, as stringified JSON object
     */
    public String jsonPayload;

    /**
     * the class name of the json object
     */
    public String className;

    /**
     * specify the auth if it is only applicable to specified endpoints with corresponding annotation
     * Note that it is nullable indicating that the auth could be applied for all endpoints (eg, global auth)
     */
    public String annotationOnEndpoint;

}

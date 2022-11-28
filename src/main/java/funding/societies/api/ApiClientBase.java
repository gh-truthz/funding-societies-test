package funding.societies.api;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.specification.RequestSpecification;

public abstract class ApiClientBase {
    abstract RequestSpecification getURL();
    abstract RequestSpecification getURLWithPathParameters(String path);

    protected RequestSpecification getBaseRequestSpecification(String baseUrl, String basePath) {
        assert baseUrl != null : "The baseUrl is required for RequestSpecification";
        assert basePath != null : "The baseUrl is required for RequestSpecification";

        if (basePath.startsWith("/")) {
            basePath = basePath.substring(1);
        }

        LogConfig logConfig = LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails();
        return RestAssured.given()
                .config(RestAssured.config().logConfig(logConfig))
                .basePath(basePath)
                .baseUri(baseUrl);
    }

//    public Response get(String path, String value) {
//        return getURLWithPathParameters()
//                .header("accept", "*/*")
//                .pathParam(path, value)
//                .get();
//    }
//
//    public Response get(Map<String, Object> parameters) {
//        return getURL()
//                .header("accept", "*/*")
//                .queryParams(parameters)
//                .get();
//    }
}

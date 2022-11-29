package funding.societies.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class LoginApiClient extends ApiClientBase {
    //region Override configuration
    @Override
    RequestSpecification getURL() {
        return getBaseRequestSpecification("https://reqres.in/api", "/login");
    }
    //end region
    //------------------------------------------------------------------------------------------------------------------
    // GET LIST RESOURCES
    //------------------------------------------------------------------------------------------------------------------
    public Response login(Map<String, Object> body) {
        return getURL()
                .contentType(ContentType.JSON)
                .body(body)
                .post();
    }
}

package funding.societies.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class UserApiClient extends ApiClientBase {
    //region Override configuration
    @Override
    RequestSpecification getURL() {
        return getBaseRequestSpecification("https://reqres.in/api", "/users");
    }

    @Override
    RequestSpecification getURLWithPathParameters(String path) {
        return getBaseRequestSpecification("https://reqres.in/api", "/users/" + path);

    }
    //end region
    //------------------------------------------------------------------------------------------------------------------
    // GET LIST USERS
    //------------------------------------------------------------------------------------------------------------------
    public Response getList(int page, int perPage) {
        return getURL()
                .queryParam("page", page)
                .queryParam("per_page", perPage)
                .get();
    }
    //------------------------------------------------------------------------------------------------------------------
    // GET LIST USERS WITH DELAY
    //------------------------------------------------------------------------------------------------------------------
    public Response getListWithDelay(int delay) {
        return getURL()
                .queryParam("delay", delay)
                .get();
    }
    //------------------------------------------------------------------------------------------------------------------
    // GET SINGLE USER
    //------------------------------------------------------------------------------------------------------------------
    public Response get(int id) {
        return getURL()
                .pathParam("id", id)
                .get("{id}");
    }
    //------------------------------------------------------------------------------------------------------------------
    // CREATE USER
    //------------------------------------------------------------------------------------------------------------------
    public Response create(Object user) {
        return getURL()
                .contentType(ContentType.JSON)
                .body(user)
                .post();
    }
    //------------------------------------------------------------------------------------------------------------------
    // UPDATE USER WITH PUT
    //------------------------------------------------------------------------------------------------------------------
    public Response updateWithPut(Integer id, Map<String, Object> updatedFields) {
        return getURL()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(updatedFields)
                .put("{id}");
    }
    //------------------------------------------------------------------------------------------------------------------
    // UPDATE USER WITH PATCH
    //------------------------------------------------------------------------------------------------------------------
    public Response updateWithPatch(Integer id, Map<String, Object> updatedFields) {
        return getURL()
                .contentType(ContentType.JSON)
                .body(updatedFields)
                .pathParam("id", id)
                .patch("{id}");
    }
    //------------------------------------------------------------------------------------------------------------------
    // DELETE USER WITH PATCH
    //------------------------------------------------------------------------------------------------------------------
    public Response delete(int id) {
        return getURL()
                .pathParam("id", id)
                .delete("{id}");
    }
}

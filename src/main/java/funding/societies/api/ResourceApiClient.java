package funding.societies.api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ResourceApiClient extends ApiClientBase{
    //region Override configuration
    @Override
    RequestSpecification getURL() {
        return getBaseRequestSpecification("https://reqres.in/api", "/unknown");
    }
    //end region
    //------------------------------------------------------------------------------------------------------------------
    // GET LIST RESOURCES
    //------------------------------------------------------------------------------------------------------------------
    public Response getList(int page, int perPage) {
        return getURL()
                .queryParam("page", page)
                .queryParam("per_page", perPage)
                .get();
    }
    //------------------------------------------------------------------------------------------------------------------
    // GET SINGLE RESOURCE
    //------------------------------------------------------------------------------------------------------------------
    public Response get(int id) {
        return getURL()
                .pathParam("id", id)
                .get("{id}");
    }
}

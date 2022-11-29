package funding.societies.test.apis;

import funding.societies.TestBase;
import funding.societies.api.content.Resource;
import funding.societies.api.enums.StatusCode;
import funding.societies.api.TestingAPIs;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

public class ResourceApiTests extends TestBase {
    public static int exists_id_1 = 1;
    public static int exists_id_2 = 2;
    public static int non_exists_id = 23;
    public static int number_of_data_in_db = 12;

    @DataProvider(name = "resources")
    public Iterator<Object[]> getSingleUser() {
        List<Object[]> testCases = new ArrayList<>();

        testCases.add(new Object[]{"1. Get Valid Resource with ID", exists_id_1, getResourceById(exists_id_1), StatusCode.OK});
        testCases.add(new Object[]{"2. Get Valid Resource with ID", exists_id_2, getResourceById(exists_id_2), StatusCode.OK});
        testCases.add(new Object[]{"4. Get Invalid Resource with ID", non_exists_id, getResourceById(non_exists_id), StatusCode.RESOURCE_NOT_FOUND});

        return testCases.iterator();
    }


    @Test(dataProvider = "resources")
    public void testGetSingleTests(String test, Integer id, Resource expectedData, StatusCode expectedStatusCode) {
        Response response = TestingAPIs.resourceClient.get(id)
                .then().statusCode(expectedStatusCode.value())
                .extract().response();

        if (StatusCode.OK.equals(expectedStatusCode)) {
            Resource resource = response.getBody().jsonPath().getObject("data", Resource.class);
            verifyResource(resource, expectedData);
            verifyTheSupportPart(response);
            verifyResponseTime(response_thread_hold_time_in_ms, response);
        } else {
            Assertions.assertThat(response.getBody().jsonPath().getString("data")).isNull();
            Assertions.assertThat(response.getBody().jsonPath().getString("support")).isNull();
        }
    }


    @DataProvider(name = "resourceList")
    public Iterator<Object[]> getUserListTests() {
        List<Object[]> testCases = new ArrayList<>();

        testCases.add(new Object[]{"1. Get List with valid range page", 1, 3, StatusCode.OK, getResourceList(1, 3)});
        testCases.add(new Object[]{"2. Get List with valid range page but no data", 2, 20, StatusCode.OK, new ArrayList<>()});
        testCases.add(new Object[]{"3. Get List with invalid range page", -1, -1, StatusCode.OK, new ArrayList<>()});
        //=> This test is failed because the total_page should not be negatived
        testCases.add(new Object[]{"4. Get List with full data", 1, 9999, StatusCode.OK, getResourceList(1, 9999)});

        return testCases.iterator();
    }

    @Test(dataProvider = "resourceList")
    public void testGetList(String test, int page, int perPage, StatusCode expectedStatusCode, List<Resource> expectedList) {
        Response response = TestingAPIs.resourceClient.getList(page, perPage)
                .then().statusCode(expectedStatusCode.value())
                .extract().response();

        Assertions.assertThat(response.getBody().jsonPath().getInt("page")).isEqualTo(page);
        Assertions.assertThat(response.getBody().jsonPath().getInt("per_page")).isEqualTo(perPage);
        Assertions.assertThat(response.getBody().jsonPath().getInt("total")).isEqualTo(number_of_data_in_db);
        Assertions.assertThat(response.getBody().jsonPath().getInt("total_pages")).isEqualTo(Math.max(number_of_data_in_db/perPage, 1));
        List<Resource> data = response.getBody().jsonPath().getList("data", Resource.class);
        Assertions.assertThat(data.size()).isEqualTo(expectedList.size());
        for (int i = 0; i < data.size(); i++) {
            Resource resource = data.get(i);
            Resource expectedData = expectedList.get(i);
            verifyResource(resource, expectedData);
        }
        verifyTheSupportPart(response);
        verifyResponseTime(response_thread_hold_time_in_ms, response);
    }

//    @DataProvider(name = "creates")
//    public Iterator<Object[]> getCreateTests() {
//        List<Object[]> testCases = new ArrayList<>();
//
//        testCases.add(new Object[]{"1. Create new record", non_exists_id, StatusCode.RESOURCE_CREATED});
//        testCases.add(new Object[]{"2. Create record for used ID", exists_id_1, StatusCode.BAD_REQUEST});
//        // => This test failed because the API should create data for exists id and inform error
//
//        return testCases.iterator();
//    }
//    @Test(dataProvider = "creates")
//    public void testCreateUser(String test, int id, StatusCode expectedStatusCode) {
//        User newUser = new User().setId(id)
//                .setAvatar("fake avatar")
//                .setEmail("faked email")
//                .setFirst_name("faked first_name")
//                .setLast_name("faked last_name");
//
//        Response response = TestingAPIs.resourceClient.create(newUser)
//                .then().statusCode(expectedStatusCode.value())
//                .extract().response();
//        User createdUser = response.getBody().as(User.class);
//        verifyUser(createdUser, newUser);
//        verifyResponseTime(response_thread_hold_time_in_ms, response);
//    }
//
//    @DataProvider(name = "updates")
//    public Iterator<Object[]> getUpdateTests() {
//        List<Object[]> testCases = new ArrayList<>();
//
//        testCases.add(new Object[]{"1. Update First name", "first_name", "new first_name", 1, StatusCode.OK});
//        testCases.add(new Object[]{"2. Update Last name", "last_name", "new last_name", 1, StatusCode.OK});
//        testCases.add(new Object[]{"3. Update new field", "job", "new data", 1, StatusCode.OK});
//        testCases.add(new Object[]{"4. Update field non-exist record", "job", "new data", 23, StatusCode.RESOURCE_NOT_FOUND});
//        // => This test failed because The update function should inform that there is no corresponding data
//
//        return testCases.iterator();
//    }
//    @Test(dataProvider = "updates")
//    public void testUpdateUserWithPut(String test, String updatedField, String updatedValue, int updateRecordId, StatusCode expectedStatusCode) {
//        Map<String, Object> updatedContent = new HashMap<>();
//        updatedContent.put(updatedField, updatedValue);
//
//        Response response = TestingAPIs.userClient.updateWithPut(updateRecordId, updatedContent)
//                .then().statusCode(expectedStatusCode.value())
//                .extract().response();
//        Assertions.assertThat(response.getBody().jsonPath().getString(updatedField)).isEqualTo(updatedValue);
//        Assertions.assertThat(response.getBody().jsonPath().getString("updatedAt")).isNotNull();
//        verifyResponseTime(response_thread_hold_time_in_ms, response);
//    }
//
//    @Test(dataProvider = "updates")
//    public void testUpdateUserWithPatch(String test, String updatedField, String updatedValue, int updateRecordId, StatusCode expectedStatusCode) {
//        Map<String, Object> updatedContent = new HashMap<>();
//        updatedContent.put(updatedField, updatedValue);
//
//        Response response = TestingAPIs.userClient.updateWithPatch(updateRecordId, updatedContent)
//                .then().statusCode(expectedStatusCode.value())
//                .extract().response();
//        Assertions.assertThat(response.getBody().jsonPath().getString(updatedField)).isEqualTo(updatedValue);
//        Assertions.assertThat(response.getBody().jsonPath().getString("updatedAt")).isNotNull();
//        verifyResponseTime(response_thread_hold_time_in_ms, response);
//    }
//
//    @DataProvider(name = "deletes")
//    public Iterator<Object[]> getDeleteTests() {
//        List<Object[]> testCases = new ArrayList<>();
//
//        testCases.add(new Object[]{"1. Delete new record", non_exists_id, StatusCode.NO_CONTENT});
//        testCases.add(new Object[]{"2. Delete record for used ID", exists_id_1, StatusCode.NO_CONTENT});
//
//        return testCases.iterator();
//    }
//    @Test(dataProvider = "deletes")
//    public void testDeleteUser(String test, int id, StatusCode expectedStatusCode) {
//        Response response = TestingAPIs.userClient.delete(id)
//                .then().statusCode(expectedStatusCode.value())
//                .extract().response();
//
//        verifyResponseTime(response_thread_hold_time_in_ms, response);
//    }
}

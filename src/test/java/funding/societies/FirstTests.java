package funding.societies;

import funding.societies.api.content.User;
import funding.societies.api.enums.StatusCode;
import funding.societies.api.enums.TestingAPIs;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

public class FirstTests extends TestBase {
    public static int exists_id_1 = 1;
    public static int exists_id_2 = 2;
    public static int non_exists_id = 23;
    public static int number_of_data_in_db = 12;
    public static int response_thread_hold_time_in_ms = 1000;


    @DataProvider(name = "users")
    public Iterator<Object[]> getSingleUser() {
        List<Object[]> testCases = new ArrayList<>();

        testCases.add(new Object[]{"1. Get Valid User with ID", exists_id_1, getUserById(exists_id_1), StatusCode.OK});
        testCases.add(new Object[]{"2. Get Valid User with ID", exists_id_2, getUserById(exists_id_2), StatusCode.OK});
        testCases.add(new Object[]{"4. Get Invalid User with ID", non_exists_id, getUserById(non_exists_id), StatusCode.RESOURCE_NOT_FOUND});

        return testCases.iterator();
    }


    @Test(dataProvider = "users")
    public void testGetSingleTests(String test, Integer id, User expectedData, StatusCode expectedStatusCode) {
        Response response = TestingAPIs.userClient.get(id)
                .then().statusCode(expectedStatusCode.value())
                .extract().response();

        if (StatusCode.OK.equals(expectedStatusCode)) {
            User user = response.getBody().jsonPath().getObject("data", User.class);
            verifyUser(user, expectedData);
            verifyTheSupportPart(response);
            verifyResponseTime(response_thread_hold_time_in_ms, response);
        } else {
            Assertions.assertThat(response.getBody().jsonPath().getString("data")).isNull();
            Assertions.assertThat(response.getBody().jsonPath().getString("support")).isNull();
        }
    }


    @DataProvider(name = "userList")
    public Iterator<Object[]> getUserListTests() {
        List<Object[]> testCases = new ArrayList<>();

        testCases.add(new Object[]{"1. Get List with valid range page", 1, 3, StatusCode.OK, getListUser(1, 3)});
        testCases.add(new Object[]{"2. Get List with valid range page but no data", 2, 20, StatusCode.OK, new ArrayList<>()});
        testCases.add(new Object[]{"3. Get List with invalid range page", -1, -1, StatusCode.OK, new ArrayList<>()});
        //=> This test is failed because the total_page should not be negatived
        testCases.add(new Object[]{"4. Get List with full data", 1, 9999, StatusCode.OK, getListUser(1, 9999)});

        return testCases.iterator();
    }

    @Test(dataProvider = "userList")
    public void testGetList(String test, int page, int perPage, StatusCode expectedStatusCode, List<User> expectedList) {
        Response response = TestingAPIs.userClient.getList(page, perPage)
                .then().statusCode(expectedStatusCode.value())
                .extract().response();

        Assertions.assertThat(response.getBody().jsonPath().getInt("page")).isEqualTo(page);
        Assertions.assertThat(response.getBody().jsonPath().getInt("per_page")).isEqualTo(perPage);
        Assertions.assertThat(response.getBody().jsonPath().getInt("total")).isEqualTo(number_of_data_in_db);
        Assertions.assertThat(response.getBody().jsonPath().getInt("total_pages")).isEqualTo(Math.max(number_of_data_in_db/perPage, 1));
        List<User> data = response.getBody().jsonPath().getList("data", User.class);
        Assertions.assertThat(data.size()).isEqualTo(expectedList.size());
        for (int i = 0; i < data.size(); i++) {
            User user = data.get(i);
            User expectedData = expectedList.get(i);
            verifyUser(user, expectedData);
        }
        verifyTheSupportPart(response);
        verifyResponseTime(response_thread_hold_time_in_ms, response);
    }

    @DataProvider(name = "creates")
    public Iterator<Object[]> getCreateTests() {
        List<Object[]> testCases = new ArrayList<>();

        testCases.add(new Object[]{"1. Create new record", non_exists_id, StatusCode.RESOURCE_CREATED});
        testCases.add(new Object[]{"2. Create record for used ID", exists_id_1, StatusCode.BAD_REQUEST});
        // => This test failed because the API should create data for exists id and inform error

        return testCases.iterator();
    }
    @Test(dataProvider = "creates")
    public void testCreateUser(String test, int id, StatusCode expectedStatusCode) {
        User newUser = new User().setId(id)
                .setAvatar("fake avatar")
                .setEmail("faked email")
                .setFirst_name("faked first_name")
                .setLast_name("faked last_name");

        Response response = TestingAPIs.userClient.create(newUser)
                .then().statusCode(expectedStatusCode.value())
                .extract().response();
        User createdUser = response.getBody().as(User.class);
        verifyUser(createdUser, newUser);
        verifyResponseTime(response_thread_hold_time_in_ms, response);
    }

    @DataProvider(name = "updates")
    public Iterator<Object[]> getUpdateTests() {
        List<Object[]> testCases = new ArrayList<>();

        testCases.add(new Object[]{"1. Update First name", "first_name", "new first_name", 1, StatusCode.OK});
        testCases.add(new Object[]{"2. Update Last name", "last_name", "new last_name", 1, StatusCode.OK});
        testCases.add(new Object[]{"3. Update new field", "job", "new data", 1, StatusCode.OK});
        testCases.add(new Object[]{"4. Update field non-exist record", "job", "new data", 23, StatusCode.RESOURCE_NOT_FOUND});
        // => This test failed because The update function should inform that there is no corresponding data

        return testCases.iterator();
    }
    @Test(dataProvider = "updates")
    public void testUpdateUserWithPut(String test, String updatedField, String updatedValue, int updateRecordId, StatusCode expectedStatusCode) {
        Map<String, Object> updatedContent = new HashMap<>();
        updatedContent.put(updatedField, updatedValue);

        Response response = TestingAPIs.userClient.updateWithPut(updateRecordId, updatedContent)
                .then().statusCode(expectedStatusCode.value())
                .extract().response();
        Assertions.assertThat(response.getBody().jsonPath().getString(updatedField)).isEqualTo(updatedValue);
        Assertions.assertThat(response.getBody().jsonPath().getString("updatedAt")).isNotNull();
        verifyResponseTime(response_thread_hold_time_in_ms, response);
    }

    @Test(dataProvider = "updates")
    public void testUpdateUserWithPatch(String test, String updatedField, String updatedValue, int updateRecordId, StatusCode expectedStatusCode) {
        Map<String, Object> updatedContent = new HashMap<>();
        updatedContent.put(updatedField, updatedValue);

        Response response = TestingAPIs.userClient.updateWithPatch(updateRecordId, updatedContent)
                .then().statusCode(expectedStatusCode.value())
                .extract().response();
        Assertions.assertThat(response.getBody().jsonPath().getString(updatedField)).isEqualTo(updatedValue);
        Assertions.assertThat(response.getBody().jsonPath().getString("updatedAt")).isNotNull();
        verifyResponseTime(response_thread_hold_time_in_ms, response);
    }

    @DataProvider(name = "deletes")
    public Iterator<Object[]> getDeleteTests() {
        List<Object[]> testCases = new ArrayList<>();

        testCases.add(new Object[]{"1. Delete new record", non_exists_id, StatusCode.NO_CONTENT});
        testCases.add(new Object[]{"2. Delete record for used ID", exists_id_1, StatusCode.NO_CONTENT});

        return testCases.iterator();
    }
    @Test(dataProvider = "deletes")
    public void testDeleteUser(String test, int id, StatusCode expectedStatusCode) {
        Response response = TestingAPIs.userClient.delete(id)
                .then().statusCode(expectedStatusCode.value())
                .extract().response();

        verifyResponseTime(response_thread_hold_time_in_ms, response);
    }

    @Test
    public void testDelay() {
        Response response = TestingAPIs.userClient.getListWithDelay(3)
                .then().statusCode(StatusCode.OK.value())
                .extract().response();

        verifyResponseTime(response_thread_hold_time_in_ms, response);

    }
}

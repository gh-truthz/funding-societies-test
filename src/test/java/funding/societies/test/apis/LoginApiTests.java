package funding.societies.test.apis;

import funding.societies.TestBase;
import funding.societies.api.TestingAPIs;
import funding.societies.api.enums.StatusCode;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LoginApiTests extends TestBase {
    @DataProvider(name = "logins")
    public Iterator<Object[]> getLoginTests() {
        List<Object[]> data = new ArrayList<>();

        data.add(new Object[]{"1. Success Login",
                Map.of(
                        "email", "eve.holt@reqres.in",
                        "password", "password"),
                StatusCode.OK, null
        });

        data.add(new Object[]{"2. Failed Login",
                Map.of("email", "eve.holt@reqres.in"),
                StatusCode.BAD_REQUEST, "Missing password"
        });

        data.add(new Object[]{"3. Non-exists Email Login",
                Map.of(
                        "email", "faked@email.com",
                        "password", "password"),
                StatusCode.BAD_REQUEST, "user not found"
        });

        return data.iterator();
    }
    @Test(dataProvider = "logins")
    public void testLogin(String test, Map<String, Object> requestBody, StatusCode expectedStatusCode, String expectedMessage) {
        Response response = TestingAPIs.loginClient.login(requestBody)
                .then().statusCode(expectedStatusCode.value())
                .extract().response();
        verifyResponseTime(response_thread_hold_time_in_ms, response);

        if (expectedMessage == null) {
            Assertions.assertThat(response.getBody().jsonPath().getString("token")).isNotNull();
            Assertions.assertThat(response.getBody().jsonPath().getString("error")).isNull();
        } else {
            Assertions.assertThat(response.getBody().jsonPath().getString("error")).isEqualTo(expectedMessage);
        }
    }
}

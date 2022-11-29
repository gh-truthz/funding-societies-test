package funding.societies.test.apis;

import funding.societies.TestBase;
import funding.societies.api.TestingAPIs;
import funding.societies.api.enums.StatusCode;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

public class RegisterApiTests extends TestBase {
    @DataProvider(name = "registers")
    public Iterator<Object[]> getRegisterTests() {
        List<Object[]> data = new ArrayList<>();

        data.add(new Object[]{"1. Success Register",
                Map.of(
                "email", "eve.holt@reqres.in",
                "password", "password"),
                StatusCode.OK, null
        });

        data.add(new Object[]{"2. Failed Register",
                Map.of("email", "eve.holt@reqres.in"),
                StatusCode.BAD_REQUEST, "Missing password"
        });

        data.add(new Object[]{"3. Unaccepted Email Register",
                Map.of(
                        "email", "faked@email.com",
                        "password", "password"),
                StatusCode.BAD_REQUEST, "Note: Only defined users succeed registration"
        });

        return data.iterator();
    }
    @Test(dataProvider = "registers")
    public void testRegister(String test, Map<String, Object> requestBody, StatusCode expectedStatusCode, String expectedMessage) {
        Response response = TestingAPIs.registerClient.register(requestBody)
                .then().statusCode(expectedStatusCode.value())
                .extract().response();
        verifyResponseTime(response_thread_hold_time_in_ms, response);

        if (expectedMessage == null) {
            Assertions.assertThat(response.getBody().jsonPath().getInt("id")).isNotNull();
            Assertions.assertThat(response.getBody().jsonPath().getString("token")).isNotNull();
            Assertions.assertThat(response.getBody().jsonPath().getString("error")).isNull();
        } else {
            Assertions.assertThat(response.getBody().jsonPath().getString("error")).isEqualTo(expectedMessage);
        }
    }
}

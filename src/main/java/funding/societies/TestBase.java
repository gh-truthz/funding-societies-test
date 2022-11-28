package funding.societies;

import funding.societies.api.content.User;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestBase {

    protected void verifyUser(User actualUser, User expectedData) {
        Assertions.assertThat(actualUser.getEmail()).isEqualTo(expectedData.getEmail());
        Assertions.assertThat(actualUser.getAvatar()).isEqualTo(expectedData.getAvatar());
        Assertions.assertThat(actualUser.getFirst_name()).isEqualTo(expectedData.getFirst_name());
        Assertions.assertThat(actualUser.getLast_name()).isEqualTo(expectedData.getLast_name());
    }
    protected void verifyTheSupportPart(Response response) {
        String url = response.getBody().jsonPath().getString("support.url");
        String text = response.getBody().jsonPath().getString("support.text");

        Assertions.assertThat(url).isEqualTo("https://reqres.in/#support-heading");
        Assertions.assertThat(text).isEqualTo("To keep ReqRes free, contributions towards server costs are appreciated!");
    }

    protected void verifyResponseTime(long threadHoldInMS, Response response) {
        long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
        Assertions.assertThat(responseTime).isLessThan(threadHoldInMS);
    }

    protected User getUserById(int id) {
        switch (id) {
            case 1: return new User().setId(1)
                    .setEmail("george.bluth@reqres.in")
                    .setFirst_name("George").setLast_name("Bluth")
                    .setAvatar("https://reqres.in/img/faces/1-image.jpg");
            case 2: return new User().setId(2)
                    .setEmail("janet.weaver@reqres.in")
                    .setFirst_name("Janet").setLast_name("Weaver")
                    .setAvatar("https://reqres.in/img/faces/2-image.jpg");
            case 3: return new User().setId(3)
                    .setEmail("emma.wong@reqres.in")
                    .setFirst_name("Emma").setLast_name("Wong")
                    .setAvatar("https://reqres.in/img/faces/3-image.jpg");
            case 4: return new User().setId(4)
                    .setEmail("eve.holt@reqres.in")
                    .setFirst_name("Eve").setLast_name("Holt")
                    .setAvatar("https://reqres.in/img/faces/4-image.jpg");
            case 5: return new User().setId(5)
                    .setEmail("charles.morris@reqres.in")
                    .setFirst_name("Charles").setLast_name("Morris")
                    .setAvatar("https://reqres.in/img/faces/5-image.jpg");
            case 6: return new User().setId(6)
                    .setEmail("tracey.ramos@reqres.in")
                    .setFirst_name("Tracey").setLast_name("Ramos")
                    .setAvatar("https://reqres.in/img/faces/6-image.jpg");
            case 7: return new User().setId(7)
                    .setEmail("michael.lawson@reqres.in")
                    .setFirst_name("Michael").setLast_name("Lawson")
                    .setAvatar("https://reqres.in/img/faces/7-image.jpg");
            case 8: return new User().setId(8)
                    .setEmail("lindsay.ferguson@reqres.in")
                    .setFirst_name("Lindsay").setLast_name("Ferguson")
                    .setAvatar("https://reqres.in/img/faces/8-image.jpg");
            case 9: return new User().setId(9)
                    .setEmail("tobias.funke@reqres.in")
                    .setFirst_name("Tobias").setLast_name("Funke")
                    .setAvatar("https://reqres.in/img/faces/9-image.jpg");
            case 10: return new User().setId(10)
                    .setEmail("byron.fields@reqres.in")
                    .setFirst_name("Byron").setLast_name("Fields")
                    .setAvatar("https://reqres.in/img/faces/10-image.jpg");
            case 11: return new User().setId(11)
                    .setEmail("george.edwards@reqres.in")
                    .setFirst_name("George").setLast_name("Edwards")
                    .setAvatar("https://reqres.in/img/faces/11-image.jpg");
            case 12: return new User().setId(12)
                    .setEmail("rachel.howell@reqres.in")
                    .setFirst_name("Rachel").setLast_name("Howell")
                    .setAvatar("https://reqres.in/img/faces/12-image.jpg");
            default: return null;
        }
    }

    protected List<User> getListUser(int fromId, int toId) {
        return IntStream.rangeClosed(fromId, toId)
                .boxed()
                .map(id -> getUserById(id))
                .filter(item -> item != null)
                .collect(Collectors.toList());
    }


}

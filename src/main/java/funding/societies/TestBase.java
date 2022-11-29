package funding.societies;

import funding.societies.api.content.Resource;
import funding.societies.api.content.User;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestBase {
    public static int response_thread_hold_time_in_ms = 1000;

    protected void verifyUser(User actualUser, User expectedData) {
        Assertions.assertThat(actualUser.getId()).isEqualTo(expectedData.getId());
        Assertions.assertThat(actualUser.getEmail()).isEqualTo(expectedData.getEmail());
        Assertions.assertThat(actualUser.getAvatar()).isEqualTo(expectedData.getAvatar());
        Assertions.assertThat(actualUser.getFirst_name()).isEqualTo(expectedData.getFirst_name());
        Assertions.assertThat(actualUser.getLast_name()).isEqualTo(expectedData.getLast_name());
    }

    protected void verifyResource(Resource actualUser, Resource expectedData) {
        Assertions.assertThat(actualUser.getId()).isEqualTo(expectedData.getId());
        Assertions.assertThat(actualUser.getName()).isEqualTo(expectedData.getName());
        Assertions.assertThat(actualUser.getColor()).isEqualTo(expectedData.getColor());
        Assertions.assertThat(actualUser.getYear()).isEqualTo(expectedData.getYear());
        Assertions.assertThat(actualUser.getPantone_value()).isEqualTo(expectedData.getPantone_value());
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

    protected Resource getResourceById(int id) {
        switch (id) {
            case 1: return new Resource().setId(1)
                    .setName("cerulean")
                    .setColor("#98B2D1")
                    .setYear(2000)
                    .setPantone_value("15-4020");
            case 2: return new Resource().setId(2)
                    .setName("fuchsia rose")
                    .setColor("#C74375")
                    .setYear(2001)
                    .setPantone_value("17-2031");
            case 3: return new Resource().setId(3)
                    .setName("true red")
                    .setColor("#BF1932")
                    .setYear(2002)
                    .setPantone_value("19-1664");
            case 4: return new Resource().setId(4)
                    .setName("aqua sky")
                    .setColor("#7BC4C4")
                    .setYear(2003)
                    .setPantone_value("14-4811");
            case 5: return new Resource().setId(5)
                    .setName("tigerlily")
                    .setColor("#E2583E")
                    .setYear(2004)
                    .setPantone_value("17-1456");
            case 6: return new Resource().setId(6)
                    .setName("blue turquoise")
                    .setColor("#53B0AE")
                    .setYear(2005)
                    .setPantone_value("15-5217");
            case 7: return new Resource().setId(7)
                    .setName("sand dollar")
                    .setColor("#DECDBE")
                    .setYear(2006)
                    .setPantone_value("13-1106");
            case 8: return new Resource().setId(8)
                    .setName("chili pepper")
                    .setColor("#9B1B30")
                    .setYear(2007)
                    .setPantone_value("19-1557");
            case 9: return new Resource().setId(9)
                    .setName("blue iris")
                    .setColor("#5A5B9F")
                    .setYear(2008)
                    .setPantone_value("18-3943");
            case 10: return new Resource().setId(10)
                    .setName("mimosa")
                    .setColor("#F0C05A")
                    .setYear(2009)
                    .setPantone_value("14-0848");
            case 11: return new Resource().setId(11)
                    .setName("turquoise")
                    .setColor("#45B5AA")
                    .setYear(2010)
                    .setPantone_value("15-5519");
            case 12: return new Resource().setId(12)
                    .setName("honeysuckle")
                    .setColor("#D94F70")
                    .setYear(2011)
                    .setPantone_value("18-2120");
            default: return null;
        }
    }

    protected List<Resource> getResourceList(int fromId, int toId) {
        return IntStream.rangeClosed(fromId, toId)
                .boxed()
                .map(id -> getResourceById(id))
                .filter(item -> item != null)
                .collect(Collectors.toList());
    }
}

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.given;
public class DataGenerator {
    public static final Faker faker = new Faker(new Locale("en"));

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    static void sendRequest(RegistrationInfo user) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then();
    }

    public static String getLogin() {
        String login = faker.name().username();
        return login;
    }

    public static String getPassword() {
        String password = faker.internet().password();
        return password;
    }

    public static class Registration {
        private Registration() {
        }

        public static RegistrationInfo getRandomUser(String status) {
            RegistrationInfo randomUser = new RegistrationInfo(
                    getLogin(),
                    getPassword(),
                    status);
            return randomUser;
        }

        public static RegistrationInfo getRegisteredUser(String status) {
            RegistrationInfo registeredUser = getRandomUser(status);
            sendRequest(registeredUser);
            return registeredUser;
        }
    }
}

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldLoginIfUserRegisteredActive() {
        RegistrationInfo user = DataGenerator.Registration.getRegisteredUser("active");
        $("[data-test-id='login'] input").setValue(user.getLogin());
        $("[data-test-id='password'] input").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $("[id='root']").shouldBe(exactText("Личный кабинет"));
    }

    @Test
    void shouldNotLoginUserIfNonRegisteredActive() {
        RegistrationInfo user = DataGenerator.Registration.getRandomUser("active");
        $("[data-test-id='login'] input").setValue(user.getLogin());
        $("[data-test-id='password'] input").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldBe(exactText("Ошибка Ошибка! Неверно указан логин или пароль"));
    }
    @Test
    void shouldNotLoginUserIfRegisteredBlocked() {
        RegistrationInfo user = DataGenerator.Registration.getRegisteredUser("blocked");
        $("[data-test-id='login'] input").setValue(user.getLogin());
        $("[data-test-id='password'] input").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldBe(exactText("Ошибка Ошибка! Пользователь заблокирован"));
    }

    @Test
    void shouldNotLoginIfUserRegisteredActiveWrongLogin() {
        RegistrationInfo user = DataGenerator.Registration.getRegisteredUser("active");
        String login = DataGenerator.getLogin();
        $("[data-test-id='login'] input").setValue(login);
        $("[data-test-id='password'] input").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldBe(exactText("Ошибка Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldNotLoginIfUserRegisteredActiveWrongPassword() {
        RegistrationInfo user = DataGenerator.Registration.getRegisteredUser("active");
        String password = DataGenerator.getPassword();
        $("[data-test-id='login'] input").setValue(user.getLogin());
        $("[data-test-id='password'] input").setValue(password);
        $("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldBe(exactText("Ошибка Ошибка! Неверно указан логин или пароль"));
    }
}


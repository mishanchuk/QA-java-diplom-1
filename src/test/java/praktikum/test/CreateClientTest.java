package praktikum.test;


import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import net.datafaker.Faker;
import org.junit.Before;
import org.junit.Test;
import praktikum.model.Client;
import praktikum.steps.ClientSteps;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Тесты для создания курьеров")
public class CreateClientTest extends TestBase{
    private final ClientSteps clientSteps = new ClientSteps();
    private Client client;


    @Before
    @Step("Подготовка тестовых данных")
    public void setUpClient(){
        Faker faker = new Faker();
        client = new Client()
                .setEmail(faker.internet().emailAddress())
                .setPassword(faker.internet().password())
                .setName(faker.name().firstName());

    }
    @Test
    @DisplayName("Создать уникального пользователя")
    @Description("Позитивный тест")
    public void successfulclientCreationTest(){
        clientSteps.createClient(client)
                .assertThat()
                .body("success", equalTo(true));
    }
    @Test
    @DisplayName("создать пользователя, который уже зарегистрирован")
    @Description("Негативный тест")
    public void duplicateCourierCreationTest(){
        clientSteps.createClient(client);
        clientSteps.createClient(client)
                .assertThat()
                .statusCode(SC_FORBIDDEN)
                .body("success",equalTo(false))
                .body("message", equalTo("User already exists"));

    }
    @Test
    @DisplayName("создать пользователя и не заполнить пароль")
    @Description("Негативный кейс")
    public void noPasswordClientCreationTest(){
        client.setPassword(null);
        clientSteps.createClient(client)
                .assertThat()
                .statusCode(SC_FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));

    }
    @Test
    @DisplayName("создать пользователя и не заполнить email")
    @Description("Негативный кейс")
    public void noEmailClientCreationTest(){
        client.setEmail(null);
        clientSteps.createClient(client)
                .assertThat()
                .statusCode(SC_FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));

    }
    @Test
    @DisplayName("создать пользователя и не заполнить имя")
    @Description("Негативный кейс")
    public void noNameClientCreationTest(){
        client.setName(null);
        clientSteps.createClient(client)
                .assertThat()
                .statusCode(SC_FORBIDDEN)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));

    }
}

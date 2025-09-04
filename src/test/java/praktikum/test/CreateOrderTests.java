package praktikum.test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.datafaker.Faker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.model.Client;
import praktikum.model.Order;
import praktikum.steps.ClientSteps;
import praktikum.steps.OrderSteps;

import java.util.*;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;



@DisplayName("Тесты по созданию заказа")
public class CreateOrderTests extends TestBase{

    private OrderSteps orderSteps;
    private Order order;
    private ClientSteps clientSteps;
    private Client validClient;
    private List<String> randomIngredients;

    @Before
    @Step("Подготовка тестовых данных")
    public void setUp() {
        clientSteps = new ClientSteps();
        orderSteps = new OrderSteps();
        order = new Order();
        Faker faker = new Faker();
        validClient = new Client()
                .setEmail(faker.internet().emailAddress())
                .setPassword(faker.internet().password())
                .setName(faker.name().firstName());
        clientSteps.createClient(validClient);
        randomIngredients = orderSteps.getRandomIngredientIds(3);

    }

    @Test
    @DisplayName("Создание заказа авторизованным пользователем")
    @Description("Позитивный кейс")
    public void testCreateOrderWithRandomIngredients() {
        clientSteps.loginClient(validClient);

        // Создаем заказ с полученными ингредиентам
        order.setIngredients(randomIngredients);
        orderSteps.createOrder(order)
                .body("success",equalTo(true));

    }

    @Test
    @DisplayName("Создание заказа без авторизации")
    @Description("Негативный кейс")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateOrderWithoutAuth() {
        order.setIngredients(randomIngredients);
        orderSteps.createOrder(order)
                .statusCode(SC_BAD_REQUEST)
                .body("success",equalTo(false));

    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    @Description("Негативный кейс")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateOrderWithoutIngredients() {
        clientSteps.loginClient(validClient);
        order.setIngredients(null);
        orderSteps.createOrder(order)
                .statusCode(SC_BAD_REQUEST)
                .body("success",equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }
    @Test
    @DisplayName("Создание заказа с некорректным хешом")
    @Description("Негативный кейс")
    @Severity(SeverityLevel.NORMAL)
    public void testCreateOrderWrongIngredients() {
        clientSteps.loginClient(validClient);
        order.setIngredients(Collections.singletonList("invalid-id-123"));
        orderSteps.createOrder(order)
                .statusCode(SC_INTERNAL_SERVER_ERROR);
    }
}
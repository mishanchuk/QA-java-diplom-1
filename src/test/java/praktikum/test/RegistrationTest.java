package praktikum.test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.pages.RegistrationPage;
import com.github.javafaker.Faker;

import static org.junit.Assert.assertTrue;

public class RegistrationTest {
    @Rule
    public DriverFactory driverFactory = new DriverFactory();


    @Test
    public void perfectRegistrationTest() throws InterruptedException{
        WebDriver driver = driverFactory.getDriver();
        Faker faker = new Faker();
        String email = faker.internet().safeEmailAddress();
        String password = faker.internet().password(8, 16, true, true, true);
        String name = faker.name().fullName();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openRegisterPage();
        registrationPage.nameInput(name);
        registrationPage.emailInput(email);
        registrationPage.passwordInput(password);
        registrationPage.clickRegisterButton();
        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Ожидаем перенаправление на страницу Авторизации", loginPage.loginPageLoaded());

    }
    @Test
    public void registrationInvalidPassword() throws InterruptedException {
        WebDriver driver = driverFactory.getDriver();
        Faker faker = new Faker();
        String email = faker.internet().safeEmailAddress();
        String name = faker.name().fullName();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openRegisterPage();
        registrationPage.nameInput(name);
        registrationPage.emailInput(email);
        registrationPage.passwordInput("qazx1");
        registrationPage.clickRegisterButton();
        registrationPage.checkErrorRegister();
        Assert.assertTrue("Ожидаемая ошибка не появилась", registrationPage.checkErrorRegister());
    }

}

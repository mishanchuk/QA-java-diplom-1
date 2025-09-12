package praktikum.test;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;

import static org.junit.Assert.assertTrue;

import praktikum.pages.RegistrationPage;
import praktikum.utill.Config;

public class LoginTest {
    @Rule
    public DriverFactory driverFactory = new DriverFactory();

    @Test
    public void loginFromPersonalAccountTest()  {
        WebDriver driver = driverFactory.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.personalAccountButtonPageClick();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.emailInput(Config.TEST_EMAIL);
        loginPage.passwordInput(Config.TEST_PASSWORD);
        loginPage.loginButtonClick();
        assertTrue("Ожидаем успешную авторизацию", mainPage.mainPageLoaded());
    }
    @Test
    public void loginFromMainButtonTest()  {
        WebDriver driver = driverFactory.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.loginInMainPageClick();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.emailInput(Config.TEST_EMAIL);
        loginPage.passwordInput(Config.TEST_PASSWORD);
        loginPage.loginButtonClick();
        assertTrue("Ожидаем успешную авторизацию", mainPage.mainPageLoaded());
    }
    @Test
    public void loginFromForgotPasswordFormTest() {
        WebDriver driver = driverFactory.getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        loginPage.linkRepairPasswordPageClick();
        loginPage.loginLinkClick();
        loginPage.emailInput(Config.TEST_EMAIL);
        loginPage.passwordInput(Config.TEST_PASSWORD);
        loginPage.loginButtonClick();
        MainPage mainPage = new MainPage(driver);
        assertTrue("Ожидаем успешную авторизацию", mainPage.mainPageLoaded());
    }
    @Test
    public void loginFromRegistrationForm() {
        WebDriver driver = driverFactory.getDriver();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.openRegisterPage();
        registrationPage.clickLoginLink();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.emailInput(Config.TEST_EMAIL);
        loginPage.passwordInput(Config.TEST_PASSWORD);
        loginPage.loginButtonClick();
        MainPage mainPage = new MainPage(driver);
        assertTrue("Ожидаем успешную авторизацию", mainPage.mainPageLoaded());
    }

}

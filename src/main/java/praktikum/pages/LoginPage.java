package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.utill.Config;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By linkRepairPasswordPage = By.cssSelector("div > p:nth-child(2) > a");
    private final By linkToRegisterPage = By.cssSelector("#root > div > main > div > div > p.undefined.text.text_type_main-default.text_color_inactive.mb-4 > a");
    private final By email = By.cssSelector("fieldset:nth-child(1) > div > div > input");
    private final By password = By.cssSelector("fieldset:nth-child(2) > div > div > input");
    private final By loginButton = By.cssSelector("#root > div > main > div > form > button");
    private final By loginLink = By.cssSelector("#root > div > main > div > div > p > a");
    private final By loginPage = By.xpath("//button[contains(@class, 'button_button_type_primary') and text()='Войти']");
    private final By lastLocate = By.tagName("script");



    public void emailInput(String emailField){
        driver.findElement(email).sendKeys(emailField);
    }
    public void passwordInput(String passwordField){
        driver.findElement(password).sendKeys(passwordField);
    }
    public void loginButtonClick(){
        driver.findElement(loginButton).click();
    }

    public void linkRepairPasswordPageClick(){
        driver.findElement(linkRepairPasswordPage).click();
    }
    public void linkToRegisterPageClick(){
        driver.findElement(linkToRegisterPage).click();
    }
    public void loginLinkClick(){
        driver.findElement(loginLink).click();
    }
    public boolean loginPageLoaded() {
        return driver.findElement(loginPage).isDisplayed();
    }
    public void openLoginPage(){
        driver.get(Config.LOGIN_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Config.IMLICITY_TIMOUT));
        wait.until(ExpectedConditions.presenceOfElementLocated(lastLocate));
    }

}




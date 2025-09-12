package praktikum.pages;


import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.utill.Config;

import java.time.Duration;


public class RegistrationPage {

    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By name = By.xpath("/html/body/div/div/main/div/form/fieldset[1]/div/div/input");
    private final By email = By.xpath("/html/body/div/div/main/div/form/fieldset[2]/div/div/input");
    private final By password = By.xpath("/html/body/div/div/main/div/form/fieldset[3]/div/div/input");
    private final By registerButton = By.cssSelector("main > div > form > button");
    private final By loginLink = By.cssSelector("#root > div > main > div > div > p > a");
    private final By inputError = By.cssSelector(".input__error.text_type_main-default");
    private final By lastLocate = By.tagName("script");

    public void openRegisterPage(){
        driver.get(Config.REGISTER_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Config.IMLICITY_TIMOUT));
        wait.until(ExpectedConditions.presenceOfElementLocated(lastLocate));
    }
    public void nameInput(String nameField) {
        driver.findElement(name).sendKeys(nameField);
    }

    public void emailInput(String emailField){
        driver.findElement(email).sendKeys(emailField);
    }
   public void passwordInput(String passwordField){
        driver.findElement(password).sendKeys(passwordField);
   }
   public void clickRegisterButton(){
        driver.findElement(registerButton).click();
   }
   public void clickLoginLink(){
        driver.findElement(loginLink).click();
   }
    public boolean checkErrorRegister() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Config.EXPICITY_TIMOUT));
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(inputError));
            return errorElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

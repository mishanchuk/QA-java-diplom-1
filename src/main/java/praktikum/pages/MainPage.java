package praktikum.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.utill.Config;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By constructorButton = By.cssSelector("li:nth-child(1) > a");
    private final By breadsTab = By.cssSelector("section.BurgerIngredients_ingredients__1N8v2 > div:nth-child(2) > div:nth-child(1)");
    private final By sauceTab = By.cssSelector("section.BurgerIngredients_ingredients__1N8v2 > div:nth-child(2) > div:nth-child(2)");
    private final By toppingsTab = By.cssSelector("section.BurgerIngredients_ingredients__1N8v2 > div:nth-child(2) > div:nth-child(3)");
    private final By breadsAnchor = By.xpath("//h2[@class='text text_type_main-medium mb-6 mt-10' and normalize-space()='Булки']");
    private final By sauceAnchor = By.xpath("//h2[@class='text text_type_main-medium mb-6 mt-10' and normalize-space()='Соусы']");
    private final By toppingsAnchor = By.xpath("//h2[@class='text text_type_main-medium mb-6 mt-10' and normalize-space()='Начинки']");
    private final By lastLocate = By.tagName("script");
    private final By personalAccountButtonPage = By.cssSelector("a.AppHeader_header__link__3D_hX[href='/account']");
    private final By mainPageLocator = By.xpath("//h1[@class='text text_type_main-large mb-5 mt-10' and text()='Соберите бургер']");
    private final By loginInMainPage = By.cssSelector("section.BurgerConstructor_basket__29Cd7.mt-25 > div > button");


    public void openMainPage() {
        driver.get(Config.BASE_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Config.IMLICITY_TIMOUT));
        wait.until(ExpectedConditions.presenceOfElementLocated(lastLocate));
    }

    public void loginInMainPageClick() {
        driver.findElement(loginInMainPage).click();
    }



    public boolean mainPageLoaded() {
        return driver.findElement(mainPageLocator).isDisplayed();
    }

    public void personalAccountButtonPageClick() {
        driver.findElement(personalAccountButtonPage).click();
    }
    public void clickBreadsTab() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPICITY_TIMOUT))
                .until(ExpectedConditions.elementToBeClickable(breadsTab))
                .click();

    }

    public WebElement  findBreadsAnchor() {
        WebElement breadsAnchorLocator = driver.findElement(breadsAnchor);
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPICITY_TIMOUT))
                .until(ExpectedConditions.visibilityOf(breadsAnchorLocator));
        return  breadsAnchorLocator;
    }
    public boolean isBreadsAnchorInViewport() {
        WebElement element = findBreadsAnchor();
        return element.getLocation().getY() < 300;
    }


    public void clickSauceTab() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPICITY_TIMOUT))
                .until(ExpectedConditions.elementToBeClickable(sauceTab))
                .click();
    }
    public WebElement findSauceAnchor() {
        WebElement sauceAnchorLocator = driver.findElement(sauceAnchor);
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPICITY_TIMOUT))
                .until(ExpectedConditions.visibilityOf(sauceAnchorLocator));
        return sauceAnchorLocator;
    }
    public boolean isSauceAnchorInViewport() {
        WebElement element = findSauceAnchor();
        return element.getLocation().getY() < 300;
    }

    public void clickToppingsTab() {
        driver.findElement(toppingsTab).click();

    }
    public WebElement findToppingsAnchor() {
        WebElement toppingsAnchorLocator = driver.findElement(toppingsAnchor);
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPICITY_TIMOUT))
                .until(ExpectedConditions.visibilityOf(toppingsAnchorLocator));
        return toppingsAnchorLocator;
    }
    public boolean isToppingsAnchorInViewport() {
        WebElement element = findToppingsAnchor();
        return element.getLocation().getY() < 300;
    }

    public void clickConstructorButton(){
        driver.findElement(constructorButton).click();
    }
}


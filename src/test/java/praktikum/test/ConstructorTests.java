package praktikum.test;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.pages.LoginPage;
import praktikum.pages.MainPage;
import praktikum.utill.Config;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class ConstructorTests {
    @Rule
    public DriverFactory driverFactory = new DriverFactory();

    @Test
    public void constructorTabBreadTest() throws InterruptedException {
        WebDriver driver = driverFactory.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickSauceTab();
        mainPage.clickBreadsTab();
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPICITY_TIMOUT))
                .until(d -> mainPage.isBreadsAnchorInViewport());
        mainPage.findBreadsAnchor();
        Assert.assertTrue(
                "Элемент не прокрутился в видимую область",
                mainPage.isBreadsAnchorInViewport());

    }
    @Test
    public void constructorTabSauceTest() throws InterruptedException {
        WebDriver driver = driverFactory.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickToppingsTab();
        mainPage.clickSauceTab();
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPICITY_TIMOUT))
                .until(d -> mainPage.isSauceAnchorInViewport());
        mainPage.findSauceAnchor();
        Assert.assertTrue(
                "Элемент не прокрутился в видимую область",
                mainPage.isSauceAnchorInViewport());


    }
    @Test
    public void constructorTabToppingsTest() throws InterruptedException {
        WebDriver driver = driverFactory.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickSauceTab();
        mainPage.clickToppingsTab();
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPICITY_TIMOUT))
                .until(d -> mainPage.isToppingsAnchorInViewport());
        mainPage.findToppingsAnchor();
        Assert.assertTrue(
                "Элемент не прокрутился в видимую область",
                mainPage.isToppingsAnchorInViewport());


    }
}

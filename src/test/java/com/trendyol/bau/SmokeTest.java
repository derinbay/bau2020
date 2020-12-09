package com.trendyol.bau;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.TestException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SmokeTest {

    WebDriver driver = null;

    //Open the website
    //assert if im in the homepage
    @BeforeMethod
    public void startUp() {
        System.setProperty("webdriver.chrome.driver", getPath());
        driver = new ChromeDriver();
    }

    public String getPath() {
        String os = System.getProperty("os.name");
        return switch (os) {
            case "Mac OS X" -> System.getProperty("user.dir") + "/src/main/resources/chromedriver";
            case "Windows" -> System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe";
            case "Linux" -> System.getProperty("user.dir") + "/src/main/resources/chromedriver_linux";
            default -> throw new TestException("No valid os!");
        };
    }

    public void clickTo(By byLocator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(byLocator))
                .click();
    }

    //Presence: presence in the dom but maybe not visible
    //Visible: presence in the dom, also it's visible

    public void typeTo(By byLocator, int timeoutInSeconds, String text) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(byLocator))
                .sendKeys(text);
    }

    public String getText(By byLocator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator))
                .getText();
    }

    @Test
    public void shouldOpenHomepage() throws InterruptedException {
        driver.get("http://www.trendyol.com");

        clickTo(By.className("fancybox-close"), 15);
        waitForTitle(10, "En Trend Ürünler Türkiye'nin Online Alışveriş Sitesi Trendyol'da");

        typeTo(By.className("search-box"), 15, "samsung");
        //we have to wait for value has filled with keyword
        waitForValueToFilled(10, "samsung");
        clickTo(By.className("search-icon"), 10);

        String result = getText(By.className("dscrptn"), 10);
        Assert.assertTrue(result.contains("samsung"));
    }

    public void waitForValueToFilled(int timeoutInSeconds, String keyword) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.attributeContains(By.className("search-box"), "Value", keyword));
    }

    public void waitForTitle(int timeoutInSeconds, String title) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.titleIs(title));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

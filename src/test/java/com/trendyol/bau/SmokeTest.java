package com.trendyol.bau;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SmokeTest {

    WebDriver driver = null;

    //Open the website
    //assert if im in the homepage
    @BeforeMethod
    public void startUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/taylan.derinbay/Downloads/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void shouldOpenHomepage() throws InterruptedException {
        driver.get("http://www.trendyol.com");
        Thread.sleep(1000);
        WebElement closeButton = driver.findElement(By.className("fancybox-close"));
        closeButton.click();

        String title = driver.getTitle();
        Assert.assertEquals(title, "En Trend Ürünler Türkiye'nin Online Alışveriş Sitesi Trendyol'da");

        WebElement searchBox = driver.findElement(By.className("search-box"));
        searchBox.click();
        searchBox.sendKeys("samsung");

        WebElement searchButton = driver.findElement(By.className("search-icon"));
        Thread.sleep(1000);
        searchButton.click();

        WebElement resultText = driver.findElement(By.className("dscrptn"));
        String result = resultText.getText();
        Assert.assertTrue(result.contains("samsung"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

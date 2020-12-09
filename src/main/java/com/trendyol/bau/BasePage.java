package com.trendyol.bau;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    WebDriver driver;

    By searchBox = By.className("search-box");
    By searchButton = By.className("search-icon");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickTo(By byLocator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(byLocator))
                .click();
    }

    public void typeTo(By byLocator, int timeoutInSeconds, String text) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(byLocator))
                .sendKeys(text);
    }

    public SearchResultPage search(String keyword) {
        typeTo(searchBox, 15, keyword);
        waitForValueToFilled(15, keyword);
        clickTo(searchButton, 15);
        return new SearchResultPage(driver);
    }

    public void waitForValueToFilled(int timeoutInSeconds, String keyword) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.attributeContains(searchBox, "Value", keyword));
    }

    public String getText(By byLocator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator))
                .getText();
    }

    public void waitForTitle(int timeoutInSeconds, String title) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.titleIs(title));
    }
}

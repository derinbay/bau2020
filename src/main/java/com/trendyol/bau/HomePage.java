package com.trendyol.bau;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    By closeButton = By.className("fancybox-close");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void redirectHere() {
        driver.get("http://www.trendyol.com");
    }

    public void closeEntryPopup() {
        clickTo(closeButton, 15);
    }
}

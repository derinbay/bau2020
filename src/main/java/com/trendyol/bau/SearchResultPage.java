package com.trendyol.bau;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultPage extends BasePage {

    By descriptionText = By.className("dscrptn");

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public String getDescriptionText() {
        return getText(descriptionText, 10);
    }
}

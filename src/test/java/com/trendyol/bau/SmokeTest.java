package com.trendyol.bau;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest extends BaseTest {

    //Presence: presence in the dom but maybe not visible
    //Visible: presence in the dom, also it's visible

    @Test
    public void shouldOpenHomepage() {
        String keyword = "iphone";
        HomePage homePage = new HomePage(driver);
        homePage.redirectHere();
        homePage.closeEntryPopup();
        homePage.waitForTitle(10, "En Trend Ürünler Türkiye'nin Online Alışveriş Sitesi Trendyol'da");

        SearchResultPage searchResultPage = homePage.search(keyword);
        String descriptionText = searchResultPage.getDescriptionText();
        Assert.assertTrue(descriptionText.contains(keyword));
    }

    @Test
    public void shouldLogin() {

    }

    @Test
    public void shouldRegister() {

    }
}

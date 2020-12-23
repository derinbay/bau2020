package com.trendyol.bau;

import com.trendyol.bau.objects.User;
import com.trendyol.bau.objects.UserPool;
import com.trendyol.bau.pages.HomePage;
import com.trendyol.bau.pages.LoginPage;
import com.trendyol.bau.pages.SearchResultPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.trendyol.bau.objects.UserPool.buyerWithInvalidatedEmail;
import static org.testng.Assert.assertEquals;

public class SmokeTest extends BaseTest {

    //Presence: presence in the dom but maybe not visible
    //Visible: presence in the dom, also it's visible

    @Test
    public void shouldSearch() {
        String keyword = "iphone";
        HomePage homePage = new HomePage(driver);
        homePage.redirectHere();

        homePage.waitForTitle(10, "En Trend Ürünler Türkiye'nin Online Alışveriş Sitesi Trendyol'da");

        SearchResultPage searchResultPage = homePage.search(keyword);
        String descriptionText = searchResultPage.getDescriptionText();
        Assert.assertTrue(descriptionText.contains(keyword));
    }

    @Test
    public void shouldLogin() {
        //trendyol'a git
        //login sayfasina git
        //fill email and password then click login button
        //assert:
        //1- anasayfaya redirect
        //2- menude hesabim gelecek
        User user = UserPool.buyer();
        HomePage homePage = new HomePage(driver);
        homePage.redirectHere();

        LoginPage loginPage = homePage.clickLogin();
        loginPage.login(user);

        String myAccountText = homePage.getText(By.cssSelector("#logged-in-container > span"), 10);
        assertEquals(myAccountText, "Hesabım");
    }

    @Test
    public void shouldNotLoginWithIncorrectPassword() {
        User user = UserPool.buyerWithIncorrectPassword();

        HomePage homePage = new HomePage(driver);
        homePage.redirectHere();

        LoginPage loginPage = homePage.clickLogin();
        loginPage.login(user);

        String errorMessage = loginPage.getErrorText();
        assertEquals(errorMessage, "E-posta adresiniz ve/veya şifreniz hatalı.");
    }

    @Test
    public void shouldNotLoginWithIncorrectEmail() {
        User user = UserPool.buyerWithIncorrectEmail();

        HomePage homePage = new HomePage(driver);
        homePage.redirectHere();

        LoginPage loginPage = homePage.clickLogin();
        loginPage.login(user);

        String errorMessage = loginPage.getErrorText();
        assertEquals(errorMessage, "E-posta adresiniz ve/veya şifreniz hatalı.");
    }

    @Test
    public void shouldNotLoginWithEmptyPassword() {
        User user = UserPool.buyerWithEmptyPassword();

        HomePage homePage = new HomePage(driver);
        homePage.redirectHere();

        LoginPage loginPage = homePage.clickLogin();
        loginPage.login(user);

        String errorMessage = loginPage.getErrorText();
        assertEquals(errorMessage, "Lütfen şifrenizi giriniz.");
    }

    @Test
    public void shouldNotLoginWithEmptyEmail() {
        User user = UserPool.buyerWithEmptyEmail();
        
        HomePage homePage = new HomePage(driver);
        homePage.redirectHere();

        LoginPage loginPage = homePage.clickLogin();
        loginPage.login(user);

        String errorMessage = loginPage.getErrorText();
        assertEquals(errorMessage, "Lütfen geçerli bir e-posta adresi giriniz.");
    }

    @Test
    public void shouldNotLoginWithInvalidatedEmail() {
        User user = buyerWithInvalidatedEmail();

        HomePage homePage = new HomePage(driver);
        homePage.redirectHere();

        LoginPage loginPage = homePage.clickLogin();
        loginPage.login(user);

        String errorMessage = loginPage.getErrorText();
        assertEquals(errorMessage, "Lütfen geçerli bir e-posta adresi giriniz.");
    }

    @Test
    public void shouldRegister() {

    }
}

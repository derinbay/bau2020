package com.trendyol.bau.pages;

import com.trendyol.bau.objects.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(User user) {
        typeTo(By.id("login-email"), 15, user.getEmail());
        typeTo(By.id("login-password-input"), 15, user.getPassword());
        clickTo(By.className("submit"), 15);
    }

    public String getErrorText() {
        return getText(By.cssSelector("#error-box-wrapper .message"), 15);
    }
}

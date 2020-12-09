package com.trendyol.bau;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.TestException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    WebDriver driver = null;

    @BeforeMethod
    public void startUp() {
        System.setProperty("webdriver.chrome.driver", getPath());
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
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
}

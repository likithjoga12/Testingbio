
package com.rbs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    // Locators
    By username = By.id("username");

    By password = By.id("password");

    By loginButton = By.cssSelector("button[type='submit']");

    By message = By.id("flash");

    public LoginPage(WebDriver driver) {

        this.driver = driver;
    }

    // Enter username
    public void enterUsername(String user) {

        driver.findElement(username).sendKeys(user);
    }

    // Enter password
    public void enterPassword(String pass) {

        driver.findElement(password).sendKeys(pass);
    }

    // Click login
    public void clickLogin() {

        driver.findElement(loginButton).click();
    }

    // Get login message
    public String getMessage() {

        return driver.findElement(message).getText();
    }
    public void clearLoginFields() {

        driver.findElement(username).clear();

        driver.findElement(password).clear();
    }

    // Get current page URL
    public String getCurrentURL() {

        return driver.getCurrentUrl();
    }
}


package com.rbs.tests;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.rbs.base.BaseTest;
import com.rbs.pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {

        LoginPage login = new LoginPage(driver);

        login.enterUsername("tomsmith");
        login.enterPassword("SuperSecretPassword!");
        login.clickLogin();

        String message = login.getMessage();

        Assert.assertTrue(
            message.contains("You logged into a secure area!"),
            "Valid login failed!"
        );
    }

    @Test
    public void invalidUsernameTest() {

        LoginPage login = new LoginPage(driver);

        login.enterUsername("wronguser");
        login.enterPassword("Wrong123");
        login.clickLogin();

        String message = login.getMessage();

        Assert.assertTrue(
            message.contains("Your username is invalid!"),
            "Invalid username validation failed!"
        );
    }

    @Test
    public void invalidPasswordTest() {

        LoginPage login = new LoginPage(driver);

        login.enterUsername("tomsmith");
        login.enterPassword("Wrong123");
        login.clickLogin();

        String message = login.getMessage();

        Assert.assertTrue(
            message.contains("Your password is invalid!"),
            "Invalid password validation failed!"
        );
    }

    @Test
    public void emptyCredentialsTest() {

        LoginPage login = new LoginPage(driver);

        login.clickLogin();

        String message = login.getMessage();

        Assert.assertTrue(
            message.contains("Your username is invalid!"),
            "Empty credentials validation failed!"
        );
    }

@DataProvider(name = "invalidLoginData")
public Object[][] getLoginData() {

    return new Object[][] {

        {"wronguser", "Wrong123", "Your username is invalid!"},

        {"tomsmith", "Wrong123", "Your password is invalid!"},

        {"", "", "Your username is invalid!"}

    };
}

@Test(dataProvider = "invalidLoginData")
public void loginWithMultipleData(
        String username,
        String password,
        String expectedMessage) {

    LoginPage login = new LoginPage(driver);

    login.enterUsername(username);

    login.enterPassword(password);

    login.clickLogin();

    String actualMessage = login.getMessage();

    Assert.assertTrue(
        actualMessage.contains(expectedMessage),
        "Login validation failed for username: " + username
    );

    System.out.println("Test passed for: " + username);
}

}

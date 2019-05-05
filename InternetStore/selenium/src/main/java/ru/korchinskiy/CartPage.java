package ru.korchinskiy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    private static final int TIMEOUT_SECONDS = 10;
    private static final String LOGIN_BTN_ID = "login-btn";
    private static final String LOGIN_INPUT_ID = "modalLoginInput";
    private static final String PASSWORD_INPUT_ID = "modalPasswordInput";
    private static final String LOGIN = "johnDoe@gmail.com";
    private static final String PASSWORD = "johnDoe";
    private static final String LOGIN_SUBMIT_BTN_ID = "login-submit-btn";
    private static final String PROFILE_LINK_ID = "profile-link";

    public String login(WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, TIMEOUT_SECONDS);
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id(LOGIN_BTN_ID)));
        loginBtn.click();
        WebElement loginInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LOGIN_INPUT_ID)));
        loginInput.sendKeys(LOGIN);
        WebElement passInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PASSWORD_INPUT_ID)));
        passInput.sendKeys(PASSWORD);
        WebElement loginSubmitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id(LOGIN_SUBMIT_BTN_ID)));
        loginSubmitBtn.click();
        WebElement profileLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PROFILE_LINK_ID)));
        return profileLink.getText();
    }
}

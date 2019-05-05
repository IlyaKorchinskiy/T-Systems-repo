package ru.korchinskiy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CatalogPage {
    private static final int TIMEOUT_SECONDS = 10;
    private static final String PRODUCT_LINK_CLASS = "card";

    public String goToProductPage(WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, TIMEOUT_SECONDS);
        WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.className(PRODUCT_LINK_CLASS)));
        productLink.click();
        return webDriver.getTitle();
    }
}

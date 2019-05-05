package ru.korchinskiy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IndexPage {
    private static final int TIMEOUT_SECONDS = 10;
    private static final String CATEGORY_LINK_CLASS = "category";

    public String goToCatalogPage(WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, TIMEOUT_SECONDS);
        WebElement categoryLink = wait.until(ExpectedConditions.elementToBeClickable(By.className(CATEGORY_LINK_CLASS)));
        categoryLink.click();
        return webDriver.getTitle();
    }
}

package ru.korchinskiy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    private static final int TIMEOUT_SECONDS = 10;
    private static final String ADD_PRODUCT_BTN_ID = "add-product-btn";
    private static final String CONTINUE_SHOPPING_BTN_ID = "continueShoppingBtn";
    private static final String CART_BADGE_ID = "cart-badge";
    private static final String CART_LINK_ID = "cart-link";

    public String addProductToCart(WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, TIMEOUT_SECONDS);
        WebElement addProductBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id(ADD_PRODUCT_BTN_ID)));
        addProductBtn.click();
        WebElement continueShoppingBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id(CONTINUE_SHOPPING_BTN_ID)));
        continueShoppingBtn.click();
        wait.until(ExpectedConditions.textToBe(By.id(CART_BADGE_ID), "1"));
        return webDriver.findElement(By.id(CART_BADGE_ID)).getText();
    }

    public String goToCartPage(WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, TIMEOUT_SECONDS);
        WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(By.id(CART_LINK_ID)));
        cartLink.click();
        return webDriver.getTitle();
    }
}

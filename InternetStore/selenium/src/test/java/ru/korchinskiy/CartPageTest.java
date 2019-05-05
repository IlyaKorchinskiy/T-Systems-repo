package ru.korchinskiy;

import org.junit.Assert;
import org.junit.Test;

public class CartPageTest extends WebTestBase {

    private CartPage cartPage = new CartPage();

    @Test
    public void login() {
        webDriver.get("http://192.168.99.100:8189/store/cart");
        String profileLink = cartPage.login(webDriver);
        Assert.assertEquals("Profile John", profileLink);
    }
}

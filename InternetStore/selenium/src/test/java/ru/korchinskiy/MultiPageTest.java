package ru.korchinskiy;

import org.junit.Assert;
import org.junit.Test;

public class MultiPageTest extends WebTestBase {

    private IndexPage indexPage = new IndexPage();
    private CatalogPage catalogPage = new CatalogPage();
    private ProductPage productPage = new ProductPage();
    private CartPage cartPage = new CartPage();

    @Test
    public void buySomeProduct() {
        webDriver.get("http://192.168.99.100:8189/store");
        indexPage.goToCatalogPage(webDriver);
        catalogPage.goToProductPage(webDriver);
        productPage.addProductToCart(webDriver);
        productPage.goToCartPage(webDriver);
        cartPage.login(webDriver);
        String orderSuccess = cartPage.confirmOrderWithDeliveryAndCash(webDriver);
        Assert.assertEquals("Order success", orderSuccess);
    }

    @Test
    public void goToProductPageAndAddToCart() {
        webDriver.get("http://192.168.99.100:8189/store");
        indexPage.goToCatalogPage(webDriver);
        catalogPage.goToProductPage(webDriver);
        String badge = productPage.addProductToCart(webDriver);
        Assert.assertEquals("1", badge);
    }
}

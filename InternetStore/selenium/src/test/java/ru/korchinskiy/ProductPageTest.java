package ru.korchinskiy;

import org.junit.Assert;
import org.junit.Test;

public class ProductPageTest extends WebTestBase {

    private ProductPage productPage = new ProductPage();

    @Test
    public void addProductToCartFromProductPage() {
        webDriver.get("http://192.168.99.100:8189/store/catalog/product/2");
        String badge = productPage.addProductToCart(webDriver);
        Assert.assertEquals("1", badge);
    }
}

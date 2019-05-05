package ru.korchinskiy;

import org.junit.Assert;
import org.junit.Test;

public class CatalogPageTest extends WebTestBase {

    private CatalogPage catalogPage = new CatalogPage();

    @Test
    public void goToProductPage() {
        webDriver.get("http://192.168.99.100:8189/store/catalog?id=1");
        String nextPage = catalogPage.goToProductPage(webDriver);
        Assert.assertEquals("Product", nextPage.split("\\s")[0]);
    }
}

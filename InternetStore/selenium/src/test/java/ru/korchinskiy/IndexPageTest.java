package ru.korchinskiy;

import org.junit.Assert;
import org.junit.Test;

public class IndexPageTest extends WebTestBase {

    private IndexPage indexPage = new IndexPage();

    @Test
    public void goToCatalogPage() {
        webDriver.get("http://192.168.99.100:8189/store");
        String nextPage = indexPage.goToCatalogPage(webDriver);
        Assert.assertEquals("Catalog", nextPage.split("\\s")[0]);
    }
}

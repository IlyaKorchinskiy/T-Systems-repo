package ru.korchinskiy;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTestBase {
    WebDriver webDriver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Goodwin\\Development\\Java\\Projects\\InternetStore\\chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    @After
    public void close() {
        webDriver.close();
    }
}

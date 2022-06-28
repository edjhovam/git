package com.devskiller.selenium;

import java.util.Random;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class BaseSeleniumTest {

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(8089);

  protected WebDriver webDriver;

  @BeforeClass
  public static void prepareWebDriver() {
    WebDriverManager.chromedriver().setup();
  }

  @Before
  public void setUp() {
    createWebDriver();
  }

  private void createWebDriver() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--disable-gpu");
    options.addArguments("--no-sandbox");
    options.addArguments("--window-size=1920,1200");

    webDriver = new ChromeDriver(options);
  }

  @After
  public void afterEachBaseSeleniumTest() {
    if (webDriver != null) {
      webDriver.quit();
    }
  }

  protected String mockLinkResponse() {
    int randomDelay = new Random().nextInt((8 + 1) * 1000);
    String linkForTest = "http://" + System.currentTimeMillis() + ".com/";
    stubFor(
            get(urlEqualTo("/get-link"))
                    .willReturn(aResponse().withBody(linkForTest).withFixedDelay(randomDelay))
    );

    return linkForTest;
  }
}

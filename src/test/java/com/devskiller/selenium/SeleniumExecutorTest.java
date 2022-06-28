package com.devskiller.selenium;

import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static org.junit.Assert.assertEquals;

public class SeleniumExecutorTest extends BaseSeleniumTest {

  @Test
  public void shouldExtractHelloWorldText() {
    webDriver.get("http://localhost:8089/index.html");
    SeleniumExecutor executor = new SeleniumExecutor(webDriver);
    assertEquals("Hello world!", executor.extractHeader());
  }

  @Test
  public void shouldClickAndExtractLink() {
    String linkForTest = mockLinkResponse();
    webDriver.get("http://localhost:8089/index.html");
    SeleniumExecutor executor = new SeleniumExecutor(webDriver);

    String extractedLink = executor.clickTheButtonAndExtractLink();

    wireMockRule.verify(getRequestedFor(urlMatching("/get-link")));
    assertEquals(linkForTest, extractedLink);
  }

  @Test
  public void shouldFindInvisibleText() {
    webDriver.get("http://localhost:8089/index.html");
    SeleniumExecutor executor = new SeleniumExecutor(webDriver);

    String extractedParagraph = executor.extractParagraph();

    assertEquals("Don't forget about me!", extractedParagraph);
  }
}
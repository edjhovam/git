package com.devskiller.selenium;

import org.openqa.selenium.WebDriver;

public class SeleniumExecutor {

  private final WebDriver driver;

  public SeleniumExecutor(WebDriver driver) {
    this.driver = driver;
  }

  public String extractHeader() {
    return null;
  }
public String getText(WebElement element){
     return element.getText();
public String getText(By locator) {
     return driver.findElement(locator).getText();

Public void click(By locator){
  driver.findElement(locator).click();
public void type(String inputText, By locator){
     driver.findElement(locator).sendKeys(inputText);
  public String clickTheButtonAndExtractLink() {
    return null;
  }

  public String extractParagraph() { return null; }
}

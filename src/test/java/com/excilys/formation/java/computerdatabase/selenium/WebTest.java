package com.excilys.formation.java.computerdatabase.selenium;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class WebTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  //private Selenium selenium;

  @Before public void   setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    //selenium = new WebDriverBackedSelenium(driver, baseUrl);
  }

  @Test public void testWeb() throws Exception {
    driver.get(baseUrl + "computerdatabase");
    driver.findElement(By.linkText("Wang OIS")).click();
    driver.findElement(By.id("computerName")).clear();
    driver.findElement(By.id("computerName")).sendKeys("wcw");
    new Select(driver.findElement(By.id("companyId"))).selectByVisibleText("Canon");
    driver.findElement(By.name("action")).click();
    driver.findElement(By.cssSelector("a.navbar-brand")).click();
    driver.findElement(By.linkText("Cray-1")).click();
    driver.findElement(By.id("computerName")).clear();
    driver.findElement(By.id("computerName")).sendKeys("wcc");
    driver.findElement(By.name("action")).click();
    driver.findElement(By.cssSelector("a.navbar-brand")).click();
    driver.findElement(By.linkText("»")).click();
    driver.findElement(By.linkText("5")).click();
    driver.findElement(By.linkText("7")).click();
    driver.findElement(By.linkText("9")).click();
    driver.findElement(By.linkText("10")).click();
    driver.findElement(By.linkText("12")).click();
    driver.findElement(By.linkText("»")).click();
  }

  @After public void tearDown() throws Exception {
    //driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

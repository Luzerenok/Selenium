package com.exadel.automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 */
public class Page {
  /*
   * Constructor injecting the WebDriver interface
   * 
   * @param webDriver
   */
  protected WebDriver driver;
  protected Logger logger;

  public Page(WebDriver driver) {
    this.driver = driver;
    this.logger = LoggerFactory.getLogger(this.getClass());
    //PageFactory.initElements(driver, this);
  }


  @Step("Navigate to HomeOnlinerPage page")
  public HomeOnlinerPage navigateToHomeOnlinerPage(String url){
    logger.info("Navigate to HomeOnlinerPage page");
    driver.get(url);
    return new HomeOnlinerPage(driver);
  }

  @Step("Navigate to MobilePage page")
  public MobilePage navigateToMobilePage(String url){
    logger.info("Navigate to MobilePage page");
    driver.get(url);
    return new MobilePage(driver);
  }

}

package com.exadel.automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;

public class HomeOnlinerPage extends Page{

    private static final String PageSelector = "//ul[@class='b-main-navigation']/li[1]/a";
    private static final String HomePageUrl = "https://www.onliner.by/";

    public HomeOnlinerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath=PageSelector)
    public WebElement catalogSection;


    public String getHomePageUrl(){
        return HomePageUrl;
    }

    public void clickOnCatalogPage(){
        catalogSection.click();
    }

}

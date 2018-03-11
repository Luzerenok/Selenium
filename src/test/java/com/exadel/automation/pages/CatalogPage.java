package com.exadel.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatalogPage extends Page{

    private static final String CategorySelector = "//li[@class='catalog-navigation-classifier__item '][@data-id='1']";
    private static final String SubCategorySelector = "//div[@class='catalog-navigation-list__aside-list']/div[1]";
    private static final String ProductSelector = "//div[@class='catalog-navigation-list__aside-list']/div[1]/div[@class='catalog-navigation-list__dropdown']/div[1]/a";

    public CatalogPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath=CategorySelector)
    public WebElement categorySection;

    @FindBy(xpath=SubCategorySelector)
    public WebElement subCategorySection;

    @FindBy(xpath=ProductSelector)
    public WebElement productSection;

    public void clickOnMobilePage(){
        categorySection.click();
        subCategorySection.click();
        productSection.click();
    }

}

package com.exadel.automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MobilePage extends Page{
//
//    private static final String PageSelector = "//ul[@class='b-main-navigation']/li[1]/a";
//    private static final String CategorySelector = "//li[@class='catalog-navigation-classifier__item '][@data-id='1']";
//    private static final String SubCategorySelector = "//div[@class='catalog-navigation-list__aside-list']/div[1]";
//    private static final String ProductSelector = "//div[@class='catalog-navigation-list__aside-list']/div[1]/div[@class='catalog-navigation-list__dropdown']/div[1]/a";
    private static final String ThirdPhoneImageSelector = "//div[@id='schema-products']/div[@class='schema-product__group'][3]/div/div[@class='schema-product__part schema-product__part_1']/div[@class='schema-product__image']/a/img";
    private static final String FirstPhonePriceSelector = "//div[@id='schema-products']/div[@class='schema-product__group'][1]//div[@class='schema-product__price']/a/span";
    private static final String AllPhonesPriceSelector = "//div[@id='schema-products']/div[@class='schema-product__group']//div[@class='schema-product__price']/a[@class='schema-product__price-value schema-product__price-value_primary']/span";
    private static final String FilterParametersSelector = "//span[@class='schema-tags__text']";

    public MobilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

//    @FindBy(xpath=PageSelector)
//    public WebElement catalogSection;
//
//    @FindBy(xpath=CategorySelector)
//    public WebElement categorySection;
//
//    @FindBy(xpath=SubCategorySelector)
//    public WebElement subCategorySection;
//
//    @FindBy(xpath=ProductSelector)
//    public WebElement productSection;

    @FindBy(xpath=ThirdPhoneImageSelector)
    public WebElement thirdPhoneImageSection;

    @FindBy(xpath=FirstPhonePriceSelector)
    public WebElement firstPhonePrice;

    @FindBy(xpath=AllPhonesPriceSelector)
    public List<WebElement> allPhonesPriceSelector;

    @FindBy(xpath=FilterParametersSelector)
    public WebElement filterParametersSection;



//    public void goToMobilePage(){
//        catalogSection.click();
//        categorySection.click();
//        subCategorySection.click();
//        productSection.click();
//    }

    public String getPriceOfTheFirstPhone(){
        return firstPhonePrice.getText();
    }

    public Double getAverageOfPriceOfThePhones(){
        Double price = 0.0;
        String str = "";
        int mobilesCount = 0;
        for(WebElement phonePrice : allPhonesPriceSelector){
            str =  phonePrice.getText().replaceAll("\\s(\\S+)","").replaceAll(",",".");
            price += Double.valueOf(str);
            mobilesCount++;
        }

        Double averagePrice = price/mobilesCount;
        return averagePrice;
    }

    public void selectDiagonal(String minValue, String maxValue){
        WebDriverWait waitFor = new WebDriverWait(driver, 15);

        Select selectByMinValue = new Select(driver.findElement(By.cssSelector(".schema-filter__group .schema-filter-control.schema-filter-control_select:first-child select.schema-filter-control__item")));
        selectByMinValue.selectByVisibleText(minValue);
      //  waitFor.until(ExpectedConditions.stalenessOf(filterParametersSection));//deleted from DOM
        waitFor.until(ExpectedConditions.presenceOfElementLocated(By.xpath(FilterParametersSelector))); //waiting existing element

        Select selectByMaxValue = new Select(driver.findElement(By.cssSelector(".schema-filter__group .schema-filter-control.schema-filter-control_select:last-child select.schema-filter-control__item")));
        selectByMaxValue.selectByVisibleText(maxValue);
        //waitFor.until(ExpectedConditions.stalenessOf(filterParametersSection));
        waitFor.until(ExpectedConditions.presenceOfElementLocated(By.xpath(FilterParametersSelector)));
    }

    public String getImageOfTheThirdPhone(){
        return thirdPhoneImageSection.getAttribute("src");
    }

}

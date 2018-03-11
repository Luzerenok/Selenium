package com.exadel.automation;

import com.exadel.automation.pages.CatalogPage;
import com.exadel.automation.pages.HomeOnlinerPage;
import com.exadel.automation.pages.MobilePage;
import com.exadel.automation.pages.Page;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestOnliner extends TestBase{

    @DataProvider(name="getDataDiagonalList")
    public Object[][] getDataDiagonalList(){
        Object[][] obj = new Object[][]{{"1\"","1\""},{"1\"","1.77\""},{"1.4\"","1.44\""},{"1.5\"","2.2\""},{"2\"","2.2\""}};
        return obj;
    }

    @Test(description = "Verify opening https://www.onliner.by/")
    public void testOpeningOnliner() {
        HomeOnlinerPage homeOnlinerPage = new HomeOnlinerPage(driver);
        homeOnlinerPage.navigateToHomeOnlinerPage(homeOnlinerPage.getHomePageUrl());
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle,"Onliner.by");
    }

    @Test(description = "Verify Navigation by Каталог->Электроника->Мобильные телефоны")
    public void testNavigationOnlinerViaClasses() {
        HomeOnlinerPage homeOnlinerPage = new HomeOnlinerPage(driver);
        homeOnlinerPage.navigateToHomeOnlinerPage(homeOnlinerPage.getHomePageUrl());
        homeOnlinerPage.clickOnCatalogPage();

        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.clickOnMobilePage();

        MobilePage mobilePage = new MobilePage(driver);
        String pageUrl = driver.getCurrentUrl();
        Assert.assertEquals(pageUrl,"https://catalog.onliner.by/mobile");
    }

//    @Test(description = "Verify Navigation by Каталог->Электроника->Мобильные телефоны")
//    public void testNavigationOnOnliner() {
//        HomeOnlinerPage homeOnlinerPage = new HomeOnlinerPage(driver);
//        homeOnlinerPage.navigateToHomeOnlinerPage(homeOnlinerPage.getHomePageUrl());
//
//        MobilePage mobilePage = homeOnlinerPage.navigateToMobilePage("https://www.onliner.by/");
//        mobilePage.goToMobilePage();
//        String pageUrl = driver.getCurrentUrl();
//        Assert.assertEquals(pageUrl,"https://catalog.onliner.by/mobile");
//    }

    @Test(description = "Founding the image of the third mobile")
    public void testFoundingThirdImageOfMobile() {
//        Page page = new Page(driver);
//        MobilePage mobilePage = page.navigateToMobilePage("https://www.onliner.by/");
//        mobilePage.goToMobilePage();
        HomeOnlinerPage homeOnlinerPage = new HomeOnlinerPage(driver);
        homeOnlinerPage.navigateToHomeOnlinerPage(homeOnlinerPage.getHomePageUrl());
        homeOnlinerPage.clickOnCatalogPage();

        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.clickOnMobilePage();
        MobilePage mobilePage = new MobilePage(driver);

        System.out.println("The image src of the third phone is " + mobilePage.getImageOfTheThirdPhone());
        Response responce = RestAssured.get(mobilePage.getImageOfTheThirdPhone());
        System.out.println("Status: " + responce.statusCode());

        int statusCode = responce.statusCode();
        Assert.assertEquals(statusCode,200);
    }

    @Test(description = "Founding the price of the first mobile")
    public void testFoundingThePriceOfTheFirstMobile() {
//        Page page = new Page(driver);
//        MobilePage mobilePage = page.navigateToMobilePage("https://www.onliner.by/");
//        mobilePage.goToMobilePage();

        HomeOnlinerPage homeOnlinerPage = new HomeOnlinerPage(driver);
        homeOnlinerPage.navigateToHomeOnlinerPage(homeOnlinerPage.getHomePageUrl());
        homeOnlinerPage.clickOnCatalogPage();

        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.clickOnMobilePage();
        MobilePage mobilePage = new MobilePage(driver);

        System.out.println("The price of the first phone is " + mobilePage.getPriceOfTheFirstPhone());
        String price = mobilePage.getPriceOfTheFirstPhone();
        Assert.assertFalse(price == null || price.isEmpty());
    }

    @Test(description = "Founding the average price of the all mobiles on the page")
    public void testFoundingTheAveragePriceOfTheMobiles() {
//        Page page = new Page(driver);
//        MobilePage mobilePage = page.navigateToMobilePage("https://www.onliner.by/");
//        mobilePage.goToMobilePage();

        HomeOnlinerPage homeOnlinerPage = new HomeOnlinerPage(driver);
        homeOnlinerPage.navigateToHomeOnlinerPage(homeOnlinerPage.getHomePageUrl());
        homeOnlinerPage.clickOnCatalogPage();

        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.clickOnMobilePage();
        MobilePage mobilePage = new MobilePage(driver);

        System.out.println("The average price of the all phones on the page is " + mobilePage.getAverageOfPriceOfThePhones());
        double averageOfPriceOfThePhones = mobilePage.getAverageOfPriceOfThePhones();
        Assert.assertTrue(averageOfPriceOfThePhones > 0.0);
    }

    @Test(description = "Founding the mobiles with the diagonal 1-1.77")
    public void testFoundingTheMobilesWithSetDiagonal() {
        String minValue = "1\"";
        String maxValue = "1.77\"";
//        Page page = new Page(driver);
//        MobilePage mobilePage = page.navigateToMobilePage("https://catalog.onliner.by/mobile");

        HomeOnlinerPage homeOnlinerPage = new HomeOnlinerPage(driver);
        homeOnlinerPage.navigateToHomeOnlinerPage(homeOnlinerPage.getHomePageUrl());
        homeOnlinerPage.clickOnCatalogPage();

        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.clickOnMobilePage();
        MobilePage mobilePage = new MobilePage(driver);

        mobilePage.selectDiagonal(minValue,maxValue);
        WebDriverWait waitFor = new WebDriverWait(driver, 10);
        waitFor.until(ExpectedConditions.visibilityOf(mobilePage.filterParametersSection));

        String value =  mobilePage.filterParametersSection.getText();
        Assert.assertEquals(value,"1\" — 1.77\"");

        //the following string has been added to make sure that the mobiles with 1" — 1.77" diagonal has been loaded.
        //System.out.println("The price of the first phone is " + mobilePage.getPriceOfTheFirstPhone());
    }


    @Test(description = "Founding the mobiles with the different diagonal values", dataProvider="getDataDiagonalList")
    public void testFoundingTheMobilesWithDifferentDiagonalValues(String minValue, String maxValue){
        WebDriverWait waitFor = new WebDriverWait(driver, 15);

//        Page page = new Page(driver);
//        MobilePage mobilePage = page.navigateToMobilePage("https://catalog.onliner.by/mobile");

        HomeOnlinerPage homeOnlinerPage = new HomeOnlinerPage(driver);
        homeOnlinerPage.navigateToHomeOnlinerPage(homeOnlinerPage.getHomePageUrl());
        homeOnlinerPage.clickOnCatalogPage();

        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.clickOnMobilePage();
        MobilePage mobilePage = new MobilePage(driver);

        mobilePage.selectDiagonal(minValue,maxValue);

        waitFor.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='schema-tags__text']")));
        String value =  driver.findElement(By.xpath("//span[@class='schema-tags__text']")).getText();

        //the following string has been added to make sure that the mobiles with different diagonal values has been loaded.
        System.out.println("The diapason of screen sizes for founded mobiles is " + mobilePage.filterParametersSection);

        String comparableValue = minValue + " — " + maxValue;

        if (minValue != maxValue){
            Assert.assertEquals(value, comparableValue);
        }
        else{
            Assert.assertEquals(value,minValue);
        }
    }
}

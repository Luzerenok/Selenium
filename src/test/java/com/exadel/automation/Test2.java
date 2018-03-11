package com.exadel.automation;

import org.testng.annotations.Test;

public class Test2 extends TestBase {


    @Test(description = "Verify login with correct username and password, TC-1")
    public void testSuccessfulLogin() {
        driver.getCurrentUrl();
    }


}

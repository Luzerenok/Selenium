package com.exadel.automation;

import org.testng.annotations.Test;

/**
 * Created by sboreisha on 2/26/2018.
 */
public class Test1 extends TestBase {
    @Test
    public void testChromeStart() {
        driver.getCurrentUrl();
    }
}

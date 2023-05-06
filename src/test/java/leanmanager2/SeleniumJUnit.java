package leanmanager2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SeleniumJUnit {

    // Fields
    private static SeleniumConfig config;
    private static final String url = "localhost:8080";
    private static WebDriver driver;

    // Test preparation
    @BeforeClass
    public static void setUp() {
        config = new SeleniumConfig();
        driver = config.getDriver();
        driver.get(url);
    }

    @AfterClass
    public static void tearDown() {
        driver.close();
    }

    // Tests
    @Test
    public void titleIsAsExpected() {
        String expectedTitle = "Please sign in";
        String actualTitle = driver.getTitle();
     
        assertNotNull(actualTitle);
        assertEquals(expectedTitle, actualTitle);
    }
}

package leanmanager2;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumConfig {

    private WebDriver driver;
    
    public SeleniumConfig() {
        //Capabilities capabilities = DesiredCapabilities.firefox();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("marionette", true);
        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    
    static {
        System.setProperty("webdriver.gecko.driver", findFile("geckodriver.exe"));
    }
    
    static private String findFile(String filename) {
       String paths[] = {"", "bin/", "target/test-classes/"};
       for (String path : paths) {
          if (new File(path + filename).exists())
              return path + filename;
       }
       return "";
    }

    public WebDriver getDriver() {
        return driver;
    }
}

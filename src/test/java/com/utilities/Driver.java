package com.utilities;

import com.utilities.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
    //Driver class driver instance'i başlatmak için kullanılır.(Singleton Driver)
    //İhtihacımız olduğunda driverı kurmak ve başlatmak için kullanılır
    //Driver null olduğunda create edip başlatılır.
    //Driver classı farklı browserlar ile kullanılmasını kolaylaştırır
    //driver instance olurtur
    private Driver(){
        //başka bir object oluştururlmasın diye içi boş private bir construtor create ediyoruz
    }
    static WebDriver driver;
    //driveri başlatmak için statik bir method oluştur
    public static WebDriver getDriver(){
        if (driver==null){
            switch (ConfigReader.getProperty("browser")){
                case "chrome" :
                    WebDriverManager.chromedriver().setup();
                   ChromeOptions options = new ChromeOptions();
                    options.addArguments("headless");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-setuid-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    driver = new ChromeDriver(options);
                    //driver = new ChromeDriver();
                    break;
                case "firefox" :
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "ie" :
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                case "safari" :
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driver = new SafariDriver();
                    break;
                case "headless-chrome" :
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;
            }

        }
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
    public static void closeDriver(){
        if (driver!=null){
            driver.quit();
            driver=null; //driveri tekrar başlatabilmek için null olduğundan emin olmak için bu yapılır
            //Eğer bunu yapmazsak multi browser test yaparken sıkıntı yaşarız
        }
    }
}

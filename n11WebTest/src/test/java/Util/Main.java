package Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    /**
     * Yiğithan KADIOĞLU
     */
    //region Driver
    public WebDriver setUpDriver() {
        System.setProperty("webdriver.chrome.driver","//Users/elektronetuser/Desktop/chromedriver_/chromedriver");
        WebDriver driver = new ChromeDriver();
        return driver;
    }
    //endregion
}

package Header;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Util.Main;

public class Login {
    /**
     * Yiğithan KADIOĞLU
     */

    //region @BeforeMethod , @Test , @AfterMethod
    Main main = new Main();
    public static  WebDriver driver;

    @BeforeMethod
    public void setUp() {
        //driver = main.setUpDriver();
    }

    @Test
    @Parameters({"email","password"})
    public void Login(String email, String password){
        driver = main.setUpDriver();
        String baseUrl = "http://www.n11.com";
        String expectedTitle = "n11.com - Alışverişin Uğurlu Adresi";
        String actualTitle = "";

        //Direct url address
        System.out.println("loading url...");
        driver.get(baseUrl);

        //Fullscreen browser window
        System.out.println("Maximizing window");
        driver.manage().window().maximize();

        System.out.println("Waiting...");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"logo\"]")));

        actualTitle = driver.getTitle();

        //Getting title of website
        System.out.println(actualTitle);
        Assert.assertEquals(actualTitle, expectedTitle);
        System.out.println("clicking signin button");
        driver.findElement(By.cssSelector("[class=\"btnSignIn\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"fieldsetContainer\"]")));
        System.out.println("entering email and password");
        driver.findElement(By.cssSelector("[name=\"email\"]")).sendKeys(email);
        driver.findElement(By.cssSelector("[name=\"password\"]")).sendKeys(password);
        driver.findElement(By.cssSelector("[name=\"email\"]")).submit();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"menuLink user\"]")));
        String actualName = driver.findElement(By.cssSelector("[class=\"menuLink user\"]")).getText();

        //Verification to login process
        Assert.assertEquals("Yiğithan Kadioglu", actualName);
    }


    @AfterMethod
    public void tearDown() {

        //driver.close();
    }

    //endregion

}
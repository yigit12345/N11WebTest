package Header;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Util.Main;

public class Search extends Login{
    /**
     * Yiğithan KADIOĞLU
     */
    //region @Test
    public static WebDriver driver;

    @Test
    public void Search() {
        //Login process
        driver = Login.driver;
        //Searching samsung
        driver.findElement(By.cssSelector("[id=\"searchData\"]")).sendKeys("samsung");
        driver.findElement(By.cssSelector("[class=\"icon iconSearch\"]")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"resultText \"]")));
        String actualName = driver.findElement(By.cssSelector("[class=\"resultText \"]")).getText().substring(0, 7);
        System.out.println(actualName);

        //Verification to search key
        Assert.assertEquals("Samsung", actualName);

        //Next page
        driver.findElement(By.cssSelector("[class=\"next navigation\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"resultText \"]")));
        String page = driver.findElement(By.cssSelector("[class=\"active \"]")).getText();
        System.out.println(page);

        //Verification to second page
        Assert.assertEquals("2", page);

        //Clicking follow button
        driver.findElements(By.cssSelector("[class=\"textImg followBtn\"]")).get(2).click();

        //Getting search list's item name
        String itemNameExpected = driver.findElements(By.cssSelector("[class=\"productName bold\"]")).get(2).getText();
        System.out.println(itemNameExpected);

        //Entering favorite list
        driver.findElement(By.cssSelector(".myAccount")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".active")));
        driver.findElement(By.cssSelector("#myAccount > div.accMenu > div.accMenu-cover > div.accNav > ul > li:nth-child(6) > a")).click();
        String ItemCount = driver.findElement(By.cssSelector("[class=\"listItemTitle\"]")).getText().substring(13, 14);

        driver.findElement(By.cssSelector("[class=\"listItemTitle\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"productName \"]")));

        //Getting favorite list list's item name
        String itemNameActual = driver.findElements(By.cssSelector("[class=\"productName \"]")).get(0).getText();

        //Checking search item and favorite item equals each other
        Assert.assertEquals(itemNameExpected, itemNameActual);

        //Deleting item
        driver.findElement(By.cssSelector("[class=\"deleteProFromFavorites\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"btn btnBlack confirm\"]")));
        driver.findElement(By.cssSelector("[class=\"btn btnBlack confirm\"]")).click();

        //Going to previous page
        driver.navigate().back();

        //Checking favorite item list number
        String ItemCountEmpty = driver.findElement(By.cssSelector("[class=\"listItemTitle\"]")).getText().substring(13, 14);
        System.out.println(ItemCountEmpty);
        Assert.assertEquals(Integer.parseInt(ItemCount)-1, Integer.parseInt(ItemCountEmpty));
        driver.close();

    }

    //endregion

}
package ua.com.maklai;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.KnickerbockerNewYorkPage;
import java.util.concurrent.TimeUnit;
import static java.lang.Thread.sleep;

public class KnickerbockerTest {

    private static WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://knickerbocker-hotel-new-york.nochi.com/?btest=119");
    }

    @Test
    public void firstTest() throws InterruptedException{
        KnickerbockerNewYorkPage knickerbockerNewYorkPage = new KnickerbockerNewYorkPage(driver);
        driver.findElement(KnickerbockerNewYorkPage.OVERVIEW).click();
        sleep(1000);
        knickerbockerNewYorkPage.makeScreenshot("Screenshot OVERVIEW");
        driver.findElement(KnickerbockerNewYorkPage.FACILITIES).click();
        knickerbockerNewYorkPage.makeScreenshot("Screenshot FACILITIES");
        driver.findElement(KnickerbockerNewYorkPage.ROOMS).click();
        knickerbockerNewYorkPage.makeScreenshot("Screenshot ROOMS");
        driver.findElement(KnickerbockerNewYorkPage.LOCATION).click();
        sleep(2000);
        knickerbockerNewYorkPage.makeScreenshot("Screenshot LOCATION");
        driver.findElement(KnickerbockerNewYorkPage.REVIEWS).click();
        sleep(2000);
        knickerbockerNewYorkPage.makeScreenshot("Screenshot REVIEWS");
        knickerbockerNewYorkPage.setCheckIn();
        knickerbockerNewYorkPage.setDate("Январь", "2019", "24");
        knickerbockerNewYorkPage.setDate("Январь", "2019", "25");
        sleep(1000);
        knickerbockerNewYorkPage.openRoomsAndGuests();
        knickerbockerNewYorkPage.addAdult();
        knickerbockerNewYorkPage.addChild();
        knickerbockerNewYorkPage.addChild();
        knickerbockerNewYorkPage.fillAgeOfChild(1, "2");
        knickerbockerNewYorkPage.fillAgeOfChild(2, "10");
        Assert.assertEquals("verify amount of guests", "5", driver.findElement(KnickerbockerNewYorkPage.AMOUNT_GUESTS).getText());
        Assert.assertEquals("verify amount of rooms", "1", driver.findElement(KnickerbockerNewYorkPage.AMOUNT_ROOMS).getText());
        knickerbockerNewYorkPage.viewPrices();
        sleep(5000);
        knickerbockerNewYorkPage.switchTab(2);
        knickerbockerNewYorkPage.selectBookingRoom();
        sleep(8000);
        knickerbockerNewYorkPage.makeScreenshot("Booking page");
        Assert.assertEquals("verify date of check in", "2019-01-24", driver.findElement(KnickerbockerNewYorkPage.DATE_CHECK_IN).getText());
        Assert.assertEquals("verify date of check out", "2019-01-25", driver.findElement(KnickerbockerNewYorkPage.DATE_CHECK_OUT).getText());
        Assert.assertEquals("verify count of guests", "3 Взрослых,\n" + "2 Детей", driver.findElement(KnickerbockerNewYorkPage.COUNT_GUESTS).getText());
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
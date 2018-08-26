package pages;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KnickerbockerNewYorkPage {

    private static WebDriver driver;
    public KnickerbockerNewYorkPage(WebDriver driver) {
        this.driver = driver;
    }

    public static final By OVERVIEW = By.xpath("//*[@id=\"container\"]/div/div/div/div[4]/ul/li[1]/a");
    public static final By FACILITIES = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[1]/div/div/ul/li[2]/a");
    public static final By ROOMS = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[1]/div/div/ul/li[3]/a");
    public static final By LOCATION = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[1]/div/div/ul/li[4]/a");
    public static final By REVIEWS = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[1]/div/div/ul/li[5]/a");
    public static final By AMOUNT_GUESTS = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[1]/div[3]/div[3]/div[1]/span[1]");
    public static final By AMOUNT_ROOMS = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[1]/div[3]/div[3]/div[1]/span[2]/span[2]");
    public static final By DATE_CHECK_IN = By.xpath("//*[@id=\"container\"]/div[3]/div[1]/div[1]/div[3]/div[1]/div[1]/span[2]");
    public static final By DATE_CHECK_OUT = By.xpath("//*[@id=\"container\"]/div[3]/div[1]/div[1]/div[3]/div[1]/div[2]/span[2]");
    public static final By COUNT_GUESTS = By.xpath("//*[@id=\"container\"]/div[3]/div[1]/div[1]/div[3]/div[2]/div[1]/span[2]");

    private static final By CHECK_IN = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[1]/div[3]/div[1]/div/span/span[3]");
    private static final By NEXT_MONTH = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[2]/div[2]/table[2]/thead/tr[1]/th[3]/span");
    private static final By MONTH = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[2]/div[2]/table[1]/thead/tr[1]/th[2]/div[1]");
    private static final By YEAR = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[2]/div[2]/table[1]/thead/tr[1]/th[2]/div[2]");
    private static final By DAYS = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[2]/div[2]/table[1]/tbody");
    private static final By ROOMS_AND_GUESTS = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[1]/div[3]/div[3]/div[1]/span[2]");
    private static final By INCREMENT_ADULT = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[1]/div[3]/div[3]/div[2]/div/div[1]/div/div[2]/div[1]/div[1]/div[2]/div[3]");
    private static final By INCREMENT_CHILD = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[1]/div[3]/div[3]/div[2]/div/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[3]");
    private static final By AGE_OF_ANY_CHILD = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[1]/div[3]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div[2]");
    private static final By PRICES_BTN = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[1]/div[2]/div/div[2]/button[1]");
    private static final By BOOKING_OF_ROOM_BTN = By.xpath("//*[@id=\"hotel-608662\"]/div[3]/div[2]/div/div/div/div[2]/div[2]/div[4]/button");

    public void makeScreenshot(String eventName) throws InterruptedException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            String fileName = "./target/screenshots/" + eventName +"." + FilenameUtils.getExtension(scrFile.getName());
            Thread.sleep(2000);
            FileUtils.copyFile(scrFile, new File(fileName));
        } catch (IOException e) {
            // TODO: LOG error message that screenshot was not saved
        }
    }
    public void setCheckIn(){
        driver.findElement(CHECK_IN).click();
    }
    public void setDate(String searchMonth, String searchYear, String searchDay) {
        while (!driver.findElement(MONTH).getText().equals(searchMonth) && !driver.findElement(YEAR).getText().equals(searchYear)){
            driver.findElement(NEXT_MONTH).click();
        }
        List<WebElement> columns = driver.findElement(DAYS).findElements(By.tagName("td"));
        for (WebElement cell: columns) {
            if (cell.getText().equals(searchDay)) {
                cell.click();
                break;
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void openRoomsAndGuests(){
        driver.findElement(ROOMS_AND_GUESTS).click();
    }
    public void addAdult(){
        driver.findElement(INCREMENT_ADULT).click();
    }
    public void addChild(){
        driver.findElement(INCREMENT_CHILD).click();
    }
    public void fillAgeOfChild(int numberOfChild, String ageOfChild){
        Select ageSelect = new Select(driver.findElement(AGE_OF_ANY_CHILD).findElements(By.tagName("select")).get(numberOfChild - 1));
        ageSelect.selectByValue(ageOfChild);
    }
    public void viewPrices(){
        driver.findElement(PRICES_BTN).click();
    }
    public void selectBookingRoom(){
        if(driver.findElement(By.xpath("//*[@id=\"hotel-608662\"]/div[3]/div[2]/div/div/div/div")).isDisplayed()){
            driver.findElement(BOOKING_OF_ROOM_BTN).click();
        }
    }
    public void switchTab(int numberOfTab) throws InterruptedException {
        Thread.sleep(2000);
        List<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(numberOfTab-1));
        Thread.sleep(2000);
    }
}

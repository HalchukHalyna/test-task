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

    public By overview = By.xpath("//*[@id=\"container\"]/div/div/div/div[4]/ul/li[1]/a");
    public By facilities = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[1]/div/div/ul/li[2]/a");
    public By rooms = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[1]/div/div/ul/li[3]/a");
    public By location = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[1]/div/div/ul/li[4]/a");
    public By reviews = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[1]/div/div/ul/li[5]/a");

    private By checkIn = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[1]/div[3]/div[1]/div/span/span[3]");
    private By checkOut = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[1]/div[3]/div[2]/div/span/span[3]");
    private By nextMonth = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[2]/div[2]/table[2]/thead/tr[1]/th[3]/span");
    private By month = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[2]/div[2]/table[1]/thead/tr[1]/th[2]/div[1]");
    private By year = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[2]/div[2]/table[1]/thead/tr[1]/th[2]/div[2]");
    private By days = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[2]/div[2]/table[1]/tbody");
    private By roomsAndGuests = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[1]/div[3]/div[3]/div[1]/span[2]");
    private By incrAdult = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[1]/div[3]/div[3]/div[2]/div/div[1]/div/div[2]/div[1]/div[1]/div[2]/div[3]");
    private By incrChild = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[1]/div[3]/div[3]/div[2]/div/div[1]/div/div[2]/div[1]/div[2]/div[2]/div[3]");
    private By ageOfAnyChild = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[1]/div[3]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div[2]");
    private By pricesBtn = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[1]/div[2]/div/div[2]/button[1]");
    private By bookingRoomBtn = By.xpath("//*[@id=\"hotel-608662\"]/div[3]/div[2]/div/div/div/div[2]/div[2]/div[4]/button");

    public By numberGuests = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[1]/div[3]/div[3]/div[1]/span[1]");
    public By numberRooms = By.xpath("//*[@id=\"container\"]/div/div/div/div[5]/div[3]/div[2]/div/form/div[1]/div[3]/div[3]/div[1]/span[2]/span[2]");
    public By dateChechIn = By.xpath("//*[@id=\"container\"]/div[3]/div[1]/div[1]/div[3]/div[1]/div[1]/span[2]");
    public By dateCheckOut = By.xpath("//*[@id=\"container\"]/div[3]/div[1]/div[1]/div[3]/div[1]/div[2]/span[2]");
    public By countGuests = By.xpath("//*[@id=\"container\"]/div[3]/div[1]/div[1]/div[3]/div[2]/div[1]/span[2]");

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
        driver.findElement(checkIn).click();
    }
    public void setCheckOut(){
        driver.findElement(checkOut).click();
    }
    public void setDate(String searchMonth, String searchYear, String searchDay) {
        while (!driver.findElement(month).getText().equals(searchMonth) && !driver.findElement(year).getText().equals(searchYear)){
            driver.findElement(nextMonth).click();
        }
        List<WebElement> columns = driver.findElement(days).findElements(By.tagName("td"));
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
        driver.findElement(roomsAndGuests).click();
    }
    public void addAdult(){
        driver.findElement(incrAdult).click();
    }
    public void addChild(){
        driver.findElement(incrChild).click();
    }
    public void fillAgeOfChild(int numberOfChild, String ageOfChild){
        Select ageSelect = new Select(driver.findElement(ageOfAnyChild).findElements(By.tagName("select")).get(numberOfChild - 1));
        ageSelect.selectByValue(ageOfChild);
    }
    public void viewPrices(){
        driver.findElement(pricesBtn).click();
    }
    public void selectBookingRoom(){
        if(driver.findElement(By.xpath("//*[@id=\"hotel-608662\"]/div[3]/div[2]/div/div/div/div")).isDisplayed()){
            driver.findElement(bookingRoomBtn).click();
        }
    }
    public void switchTab(int numberOfTab) throws InterruptedException {
        Thread.sleep(2000);
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(numberOfTab-1));
        Thread.sleep(2000);
    }
}

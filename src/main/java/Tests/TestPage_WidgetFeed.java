package Tests;

import io.appium.java_client.MobileElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPage_WidgetFeed extends BaseTest{

    @BeforeEach
    public void initTheTest() throws MalformedURLException {
        //go to the right page
        driver.findElement(By.id("std_mid_article_with_feed_lnr")).click();
        //scroll down to find Taboola feed
        while(driver.findElements(By.id("taboola-pl1")).size()==0) {
            verticalSwipeByPercentages(0.6, 0.3, 0.5);
        }
    }


    @DisplayName("TestMoreThanOneItem")
    @Test
    public void TestMoreThanOneItem() throws InterruptedException {
        int count=0;
        int i=1;
        while(driver.findElements(By.id("taboola-pl"+i)).size()>0){
            count++;
            if(count>1){
                break;
            }
        }
        assertTrue(count>1, "TestMoreThanOneItem failed - no more than 1 item is found");

    }


    @DisplayName("FindTheFourthElement")
    @Test
    public void FindTheFourthElement() throws InterruptedException {
        while(driver.findElements(By.id("taboola-pl4")).size()>0){
            verticalSwipeByPercentages(0.6, 0.3, 0.5);
            driver.findElement(By.id("taboola-pl4")).click();
        }
        //close the window
        driver.navigate().back();
        assertTrue(driver.findElement(By.id("taboola-pl4")).isDisplayed(), "FindTheFourthElement - the page wasn't open or closed properly");

    }


    @DisplayName("clickOnTheAdchoicec")
    @Test
    public void clickOnTheAdchoicec() throws InterruptedException {
        driver.context("WEBVIEW_com.taboola.android.sdksamples");
        List<MobileElement> windows = driver.findElements(By.xpath("//*[starts-with(@class,'branding-inner')]"));
        windows.get(4).click();
        System.out.println("Clicked on the forth adchoicec");

        //close the window
        driver.context("NATIVE_APP");
        driver.navigate().back();

        assertTrue(driver.findElement(By.id("taboola-pl4")).isDisplayed(), "clickOnTheAdchoicec - the page wasn't open or closed properly");

    }


}

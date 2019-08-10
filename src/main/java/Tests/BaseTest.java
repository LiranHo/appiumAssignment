package Tests;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import sun.applet.Main;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class BaseTest {

    protected AppiumDriver driver = null;
    protected DesiredCapabilities dc = new DesiredCapabilities();
    protected static String deviceName;
    final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
    static File appDir = new File(System.getProperty("user.dir"), "src/main/resources");
    static File app;


    @BeforeAll
    public static void Init() throws MalformedURLException {
        System.out.println("Before All Tests for device");
        app = new File(appDir, "taboolasamples-debug.apk");
        //TODO:
        deviceName = "Android Emulator";
    }

    @BeforeEach
    public void setUp() throws MalformedURLException {
        //createDriver
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        //TODO: change the application
        dc.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
//        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.taboola.android.sdksamples");
//        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");
        dc.setCapability("newCommandTimeout",30000);

        driver = new AndroidDriver<>(new URL(URL_STRING), dc);


    }

    @AfterEach
    public void tearDown() {
        driver.quit();

    }



    //Vertical Swipe
    public void verticalSwipeByPercentages(double startPercentage, double endPercentage, double anchorPercentage) {
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.width * anchorPercentage);
        int startPoint = (int) (size.height * startPercentage);
        int endPoint = (int) (size.height * endPercentage);

        new TouchAction(driver)
                .press(point(anchor, startPoint))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(anchor, endPoint))
                .release().perform();
    }

}

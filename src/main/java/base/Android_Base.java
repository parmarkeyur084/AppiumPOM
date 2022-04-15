package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import static io.appium.java_client.touch.offset.PointOption.point;

public class Android_Base extends GlobalBase {

	public static AndroidDriver<AndroidElement> driver;

	public Android_Base(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/config/AndroidTest.properties");
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setUp_And(String noReset) throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		if(env.equals("local")) {
			caps.setCapability("deviceName", prop.getProperty("devicename"));
			caps.setCapability("deviceId", prop.getProperty("deviceid"));
			caps.setCapability("BROWSER", prop.getProperty("BROWSER"));
			caps.setCapability("platformName", prop.getProperty("platformname"));
			caps.setCapability("platformVersion", prop.getProperty("platformversion"));
			caps.setCapability("app", System.getProperty("user.dir") + prop.getProperty("appPath"));
			caps.setCapability("autoGrantPermissions", "true");
			//caps.setCapability("appActivity", "com.planetx.shopifymobileapp.ui.storeUrl.StoreUrlActivity");
			caps.setCapability("noReset", noReset);
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AndroidDriver<>(url, caps);
		}else {
			caps.setCapability("browserstack.user", "biniv_kE9mAP");
			caps.setCapability("browserstack.key", "spXmxZAo7C7zTxNRSET7");
			caps.setCapability("app", "bs://671f35d21ae6f71c28212685e7bcfa2e57141b97");
			caps.setCapability("device", "OnePlus OnePlus 9");
			caps.setCapability("os_version", "11.0");
			caps.setCapability("project", "First - Android");
			caps.setCapability("build", "First-android-build-1");
			caps.setCapability("autoGrantPermissions", "true");
			caps.setCapability("browserstack.enableCameraImageInjection", "true");
			URL url = new URL("http://hub.browserstack.com/wd/hub");
			driver = new AndroidDriver<>(url, caps);
		}
	}

	public void scroll(){
		System.out.println("Scrolling to get elements...");
		Dimension size = driver.manage().window().getSize();
		int startX = (int) ((size.getWidth()) * 0.80);
		int startY = (int) ((size.getWidth()) * 0.80);
		int endX = (int) ((size.getWidth()) * 0.20);
		int endY = (int) ((size.getWidth()) * 0.20);
		try {
			new TouchAction(driver).press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
					.moveTo(PointOption.point(endX, endY)).release().perform();
		} catch (Exception e) {
			System.out.println("unable to swipe");
		}
	}
}

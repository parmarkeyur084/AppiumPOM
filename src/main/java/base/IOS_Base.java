package base;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class IOS_Base extends GlobalBase{

	public static IOSDriver<IOSElement> driver;

	public IOS_Base(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/config/IOSTest.properties");
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setUp_iOS(boolean noReset) throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		if (env.equals("local")) {
			caps.setCapability("platformVersion", prop.getProperty("platformversion"));
			caps.setCapability("deviceName", prop.getProperty("devicename"));
			caps.setCapability("platformName", prop.getProperty("platformname"));
			caps.setCapability("udid", prop.getProperty("deviceid"));
			caps.setCapability("automationName", prop.getProperty("automationname"));
			caps.setCapability("autoAcceptAlerts", "true");
			caps.setCapability("autoGrantPermissions", "true");
			caps.setCapability("noReset", noReset);
			caps.setCapability("app", System.getProperty("user.dir") + prop.getProperty("appPath"));
			driver = new IOSDriver<IOSElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		} else {
			caps.setCapability("browserstack.user", "riwedi_F3cMqY");
			caps.setCapability("browserstack.key", "qZ1nGyFx1Mzyqa2Cghzr");
			caps.setCapability("app", "bs://47b7a4010f8ba161ff0105d1ce6de7f010013836");
			caps.setCapability("device", "iPhone 13 Pro");
			caps.setCapability("os_version", "15");
			caps.setCapability("project", "First Java Project");
			caps.setCapability("build", "browserstack-build-1");
			caps.setCapability("name", "first_test");
			caps.setCapability("autoAcceptAlerts", "true");
			driver = new IOSDriver<>(new URL("http://hub-cloud.browserstack.com/wd/hub"), caps);
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

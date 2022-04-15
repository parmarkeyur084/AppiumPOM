package base;

import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class GlobalBase {
    public static Properties prop;
    public static String Apppackage = "";
    public static int timeStamp = (int) (new Date().getTime()/1000);
    public static int jobID;
    public static String env="local"; //local; browserstack
}

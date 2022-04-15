package Pages;

import base.Android_Base;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.IReporter;
import org.testng.ITestResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FailedCaseListeners extends Android_Base implements IReporter {
    public static String fileName = new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date());

    public void tearDown(ITestResult result){
        if(ITestResult.FAILURE==result.getStatus()){
            try{
                TakesScreenshot ts=(TakesScreenshot)driver;
                File source=ts.getScreenshotAs(OutputType.FILE);
                try{
                    FileHandler.copy(source, new File("./Screenshots/"+result.getName()+"_"+timeStamp+".png"));
                    System.out.println("Screenshot taken");
                }catch (Exception e){
                    //e.printStackTrace();
                    System.out.println("Unable to take screenshot");
                }
            }
            catch (Exception e){

                System.out.println("Exception while taking screenshot "+e.getMessage());
            }
        }
    }
}

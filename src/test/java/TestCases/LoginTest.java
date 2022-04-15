package TestCases;

import Pages.FailedCaseListeners;
import Pages.LoginPage;
import base.Android_Base;
import base.GlobalBase;
import base.IOS_Base;
import org.json.simple.parser.ParseException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.TestUtil;

import java.net.MalformedURLException;

public class LoginTest extends GlobalBase {
    Android_Base android_base;
    IOS_Base ios_base;
    TestUtil testUtil;
    FailedCaseListeners failedcaselisteners;
    LoginPage loginPage;

    @Parameters({ "platform" })
    @BeforeClass
    public void setUpMethod(String platform) throws MalformedURLException, ParseException {
        if(platform.equals("Android")){
            android_base = new Android_Base();
            android_base.setUp_And("false");
        }else {
            ios_base = new IOS_Base();
            ios_base.setUp_iOS(false);
        }
        loginPage = new LoginPage();
        testUtil = new TestUtil();
        failedcaselisteners = new FailedCaseListeners();
    }

    @Parameters({ "platform" })
    @Test(priority=1)
    public void loginCheck(String platform) throws InterruptedException {
        if(platform.equals("Android")) {
            loginPage.login_Android();
        }else {
            loginPage.login_IOS();
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        failedcaselisteners.tearDown(result);
    }
}

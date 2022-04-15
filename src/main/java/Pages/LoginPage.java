package Pages;

import base.Android_Base;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends Android_Base {
    public static WebDriverWait wait_ele = new WebDriverWait(driver, 20);

    public LoginPage(){
        PageFactory.initElements(driver, this);
    }

    public void login_Android(){

    }

    public void login_IOS(){

    }
}

package appTests;

import base.BaseTest;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

import java.io.IOException;

public class LoginTest extends BaseTest {
    LoginScreen loginScreenObj;
    NotificationScreen notificationScreenObj;
    HomeScreen homeScreenObj;
    OnboardingScreen onBoardingScreenObj;
    CommonScreen commonScreenObj;

    @BeforeMethod
    public void beforeMethod(){

        //call action utils [relaunch app]

        //driver=	setup("Android");
        loginScreenObj = new LoginScreen(driver);
        notificationScreenObj = new NotificationScreen(driver);
        homeScreenObj = new HomeScreen(driver);
        onBoardingScreenObj = new OnboardingScreen(driver);
        commonScreenObj = new CommonScreen(driver);
    }

    @Test
    public void testValidLogin(){
        Allure.step("=======Start test Login========");
        String nationalID = prop.getProperty("nationalID");
        String password = prop.getProperty("password");

        commonScreenObj.allowNotifications();
        onBoardingScreenObj.changeLanguageToAR();
        onBoardingScreenObj.skipIntroScreen();
    }
}

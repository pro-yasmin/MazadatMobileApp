package appTests;

import base.BaseTest;
import io.qameta.allure.Allure;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.HomeScreen;
import screens.LoginScreen;
import screens.NotificationScreen;
import screens.OnboardingScreen;

import java.io.IOException;

public class LoginTest extends BaseTest {
    LoginScreen loginScreenObj;
    NotificationScreen notificationScreenObj;
    HomeScreen homeScreenObj;
    OnboardingScreen onBoardingScreenObj;

    @BeforeMethod
    public void beforeMethod() {

        //call action utils [relaunch app]

        //driver=	setup("Android");
        loginScreenObj = new LoginScreen(driver);
        notificationScreenObj = new NotificationScreen(driver);
        homeScreenObj = new HomeScreen(driver);
        onBoardingScreenObj = new OnboardingScreen(driver);
    }

    @Test
    public void testLogin(){
        Allure.step("=======Start test Login========");
        String nationalID = prop.getProperty("nationalID");
        String password = prop.getProperty("password");

        notificationScreenObj.allowNotifications();
        onBoardingScreenObj.changeLanguageToAR();
        onBoardingScreenObj.skipIntroScreen();


    }
}

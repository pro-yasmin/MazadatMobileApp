package appTests;


import java.io.ByteArrayInputStream;
import java.io.IOException;

import base.BaseTest;
//import io.qameta.allure.Allure;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.HomeScreen;
import screens.LoginScreen;
import screens.NotificationScreen;
import screens.OnboardingScreen;


public class OnboardingTest extends BaseTest {
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
	public void testOnBoarding(){
		Allure.step("=======Start test onBoarding========");
          String scanQrTitle= prop.getProperty("scanQRTitle");
          String biddingTitle= prop.getProperty("biddingTitle");

          notificationScreenObj.allowNotifications();
          onBoardingScreenObj.changeLanguageToAR();
        Assert.assertTrue(onBoardingScreenObj.checkOnboarding(scanQrTitle,biddingTitle));

        //  homeScreenObj.iconsTooltip();
        //  loginScreenObj.loginWithUserName(nationalID, password);
	Allure.step("=======End test onBoarding========");
	}


	
	@AfterMethod
	public void afterMethod(){
	   Allure.addAttachment("ScreenShot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

	//	driver.closeApp();
	}
	
}

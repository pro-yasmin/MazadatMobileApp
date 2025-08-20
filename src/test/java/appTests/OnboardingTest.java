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
import screens.*;
import utils.Constants;


public class OnboardingTest extends BaseTest {
    LoginScreen loginScreenObj;
    NotificationScreen notificationScreenObj;
    HomeScreen homeScreenObj;
    OnboardingScreen onBoardingScreenObj;
    CommonScreen commonScreenObj;

	@BeforeMethod
	public void beforeMethod() {

        //call action utils [relaunch app]
        //driver=	setup("Android");
    loginScreenObj = new LoginScreen(driver);
    notificationScreenObj = new NotificationScreen(driver);
    homeScreenObj = new HomeScreen(driver);
    onBoardingScreenObj = new OnboardingScreen(driver);
    commonScreenObj = new CommonScreen(driver);
			}
	
	@Test
	public void testOnBoarding(){
		Allure.step("=======Start test onBoarding========");
          String startTitleAr= prop.getProperty("welcometitleAr");
          String scanQrTitleAr= prop.getProperty("scanQRTitleAr");
          String biddingTitleAr= prop.getProperty("biddingTitleAr");
          String platformType= Constants.ANDROIDPLATFORM;

          if(platformType.equalsIgnoreCase(Constants.ANDROIDPLATFORM)){
              commonScreenObj.allowNotifications();
          }

          Assert.assertTrue(onBoardingScreenObj.validateOnboardScreens(scanQrTitleAr,biddingTitleAr));

	   Allure.step("=======End test onBoarding========");
	}

    @Test
    public void testHomeTour(){
        Allure.step("=======Start test home tour========");
          String homeTourAr= prop.getProperty("homeInfoAr");
          String cardsTourAr=prop.getProperty("cardsInfoAr");
          String qaCodeTourAr= prop.getProperty("scanQRCodeInfoAr");
          String auctionTourAr= prop.getProperty("myAuctionsInfoAr");
          String settingTourAr= prop.getProperty("settingInfoAr");

         Assert.assertTrue(homeScreenObj.validateHomeTour(homeTourAr,cardsTourAr,qaCodeTourAr,auctionTourAr,settingTourAr));

        Allure.step("=======End test home tour========");
    }


	
	@AfterMethod
	public void afterMethod(){
	   Allure.addAttachment("ScreenShot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		//driver.closeApp();
	}
	
}

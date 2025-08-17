package appTests;


import java.io.ByteArrayInputStream;
import java.io.IOException;

import base.BaseTest;
//import io.qameta.allure.Allure;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.HomeScreen;
import screens.LoginScreen;
import screens.NotificationScreen;


public class StaysTest extends BaseTest {
    LoginScreen loginScreenObj;
    NotificationScreen notificationScreenObj;
    HomeScreen homeScreenObj;

	@BeforeMethod
	public void beforeMethod() {


        //call action utils [relaunch app]


	//driver=	setup("Android");
    loginScreenObj = new LoginScreen(driver);
    notificationScreenObj = new NotificationScreen(driver);
    homeScreenObj = new HomeScreen(driver);
			}
	
	@Test
	public void testLoginWithMail() throws InterruptedException, IOException {
		Allure.step("=======Start test login with mail=======");
          String nationalID= prop.getProperty("nationalID");
          String password = prop.getProperty("password");

          notificationScreenObj.allowNotifications();
          homeScreenObj.skipIntroScreen();
          homeScreenObj.iconsTooltip();
          loginScreenObj.loginWithUserName(nationalID, password);
	Allure.step("=======End test login with mail=======");
	}

    @Test
    public void testtwo() throws InterruptedException, IOException {
        Allure.step("=======test two======");
    }
	/*
	@Test
	public void testFilterWithVeryGood() throws InterruptedException {
		  Allure.step("=======Start test filter with five star=======");

		//  driver.manage().timeouts().implicitlyWait(80,TimeUnit.SECONDS);
	         String mail=prop.getProperty("email");
	         String password=prop.getProperty("pw");
	         String destination=prop.getProperty("destination");
	         String tripType=prop.getProperty("tripType");
	         
	       //  notificationScreenObj.notAllowNotifications();
			  Allure.step("close notification page");

			 // signInScreenObj.ignoreLogin();
			  Allure.step("Ignore login");
			//signInScreenObj.signInWithVerificationLink(mail);

			//launchGmailApp();
			//inboxScreenObj.VerifySignInMail();
			
			//driver.activateApp("com.booking");
			
	 		//Assert.assertTrue(staysScreenObj.checkSignInProfile());
			//Allure.step("Login done successfully");
		    //	 signInScreenObj.ignoreLogin();

			staysScreenObj.searchWithDestination(destination, tripType);
			Allure.step("Search with the destination ="+ destination);

			Assert.assertTrue(staysScreenObj.filterSearchResult(Constants.VERYGOOD));
			Allure.step("Search results filtered with Very Good");
			
			  Allure.step("=======End test filter with very good=======");
	}    
	
	@Test
	public void testReserveStayWithFreeCancelationAndLowCost() throws InterruptedException, IOException {
		 Allure.step("=======Start reserve stay with free cancelation and low cost=======");
         String mail=prop.getProperty("email");
         String password=prop.getProperty("pw");
         String destination=prop.getProperty("destination");    
         String tripType=prop.getProperty("tripType");    
         
		notificationScreenObj.notAllowNotifications();
		 Allure.step("close notification page");

		 signInScreenObj.ignoreLogin();
		  Allure.step("Ignore login");
				
		 //signInScreenObj.signInWithVerificationLink(mail);

		//Assert.assertTrue(staysScreenObj.checkSignInProfile());
		//Allure.step("Login done successfully");

		staysScreenObj.searchWithDestination(destination, tripType);
		Allure.step("Search with the destination ="+destination);

		Assert.assertTrue(staysScreenObj.filterSearchResult(Constants.FREECANCELATIONFILTER));
		Allure.step("Search results filtered with free cancelation filter");

		staysScreenObj.sortSearchResult();
		Allure.step("Search result sorted by price from lowest to ");
		
		Assert.assertTrue(staysScreenObj.reservceATrip(), "Reserve button displayed successfully");	
		Allure.step("Reserve button displayed successfully");
		
		 Allure.step("=======End reserve stay with free cancelation and low cost=======");
	}
	
	@Test
	public void testSavedToFavouriteItem() throws InterruptedException{
		 Allure.step("=======Start saved to favourite item========");
         String mail=prop.getProperty("email");
         String password=prop.getProperty("pw");
         String destination=prop.getProperty("destination");
         String tripType=prop.getProperty("tripType");
         
         notificationScreenObj.notAllowNotifications();
		 Allure.step("close notification page");

		// signInScreenObj.signInWithVerificationLink(mail);
		 signInScreenObj.ignoreLogin();
		  Allure.step("Ignore login");
 				
 		staysScreenObj.searchWithDestination(destination, tripType); 
		Allure.step("Search with the destination ="+destination);

 		staysScreenObj.addToFavourite();
		Allure.step("Hotel added to favourite");
 		
 		Assert.assertTrue(savedScreenObj.checkSavedItem(),"Item displayed successfully");
		Allure.step("Hotel displayed at the saved items list successfully");
 		
 		 Allure.step("=======End saved to favourite item=======");
	}
	*/
	
	@AfterMethod
	public static void afterMethod(){		
	   Allure.addAttachment("ScreenShot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

	//	driver.closeApp();
	}
	
}

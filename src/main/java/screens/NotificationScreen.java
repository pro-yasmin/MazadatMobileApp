package screens;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class NotificationScreen{
    AppiumDriver driver;
	
	public NotificationScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
	}

    // Locators for the login page elements
    @AndroidFindBy(uiAutomator ="new UiSelector().resourceId(\"com.android.permissioncontroller:id/permission_deny_button\")")
    //  @iOSXCUITFindBy(accessibility = "username_field")
    private WebElement allowButton;


   public void allowNotifications() throws InterruptedException {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
       wait.until(ExpectedConditions.visibilityOf(allowButton));
       allowButton.click();


   }


}

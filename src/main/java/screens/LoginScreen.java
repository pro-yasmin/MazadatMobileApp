package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class LoginScreen{

   AppiumDriver driver;

    public LoginScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }


    // Locators for the login page elements
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Login\")")
    @iOSXCUITFindBy(accessibility = "username_field")
    private WebElement loginButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(0)")
    //  @iOSXCUITFindBy(accessibility = "username_field")
    private WebElement nationalIdField;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(1)")
    private WebElement passwordField;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(2)")
    private WebElement submitButton;



    public void loginWithUserName(String nationalId, String password) {
     //   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
     //   wait.until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();
        nationalIdField.sendKeys(nationalId);
        passwordField.sendKeys(password);
        submitButton.click();
    }

}

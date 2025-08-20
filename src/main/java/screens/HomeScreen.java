package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import utils.ActionsUtils;
import utils.Constants;
import utils.WaitUtils;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomeScreen {

    AppiumDriver driver;

    public HomeScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    @iOSXCUITFindBy(accessibility = "ابدأ الجولة")
    private WebElement startGuideBtn;

    @iOSXCUITFindBy(accessibility = "تجاوز هذه الجولة")
    private WebElement skipGuideBtn;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id=\"android:id/content\"]/android.view.View/android.view.View")
    private WebElement tourBtn;

    @AndroidFindBy(className = "android.widget.TextView")
    private WebElement tooltip;




    @Step("Validate Home Tour sequence")
    public boolean validateHomeTour(String... expectedTooltips) {
        for (String expected : expectedTooltips) {
            WaitUtils.waitForSeconds(2);
            String actualText = tooltip.getText();
            if (!actualText.equals(expected)) {
                Reporter.log("Expected: " + expected + " but found: " + actualText);
                return false; // stop early on failure
            }
            Reporter.log("Tooltip matched: " + actualText);
            tourBtn.click();
        }
        return true;
    }


    /*public void iconsTooltip(){
        for(int i=5; i>0; i--){
            ActionsUtils.doubleClickElement(driver,tourBtn);
        }
    }*/
}
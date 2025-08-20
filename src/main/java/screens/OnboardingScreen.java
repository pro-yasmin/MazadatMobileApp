package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import utils.ActionsUtils;
import utils.WaitUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class OnboardingScreen {
    AppiumDriver driver;

    public OnboardingScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"عربي\")")
    @iOSXCUITFindBy(accessibility = "عربي")
    private WebElement arLanguageBtn;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"EN\")")
    private WebElement enLanguageBtnEn;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Skip\")")
    @iOSXCUITFindBy(accessibility = "تخطي")
    private WebElement skipButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(2)")
    @iOSXCUITFindBy(accessibility = "ابدأ")
    private WebElement startButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(3)")
    @iOSXCUITFindBy(accessibility = "التالي")
    private WebElement nextButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").instance(0)")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView//XCUIElementTypeStaticText[1]")
    private WebElement onboardingTitle;


   @Step("Change App language to Arabic")
   public void changeLanguageToAR(){
       if(arLanguageBtn.isDisplayed()){
           arLanguageBtn.click();
       }
    }

    @Step("Change App language to English")
    public void changeLanguageToEN(){
       if(enLanguageBtnEn.isDisplayed()){
           enLanguageBtnEn.click();
       }
    }

    @Step("Skip onBoaring")
    public void skipIntroScreen(){
        skipButton.click();
    }

   /* @Step("validate onBoaring screens")
    public boolean checkOnboarding(String startTitle,String scanTitle,String biddingTitle){
        List<Boolean> status = new ArrayList<>();

        //changeLanguageToAR();

        if(onboardingTitle.getText().equals(startTitle)){
            status.add(true);
        }
        startButton.click();
        if (onboardingTitle.getText().equals(scanTitle)){
             status.add(true);
        }
        WaitUtils.waitForElementClickable(nextButton,2,driver);
        nextButton.click();
        if(onboardingTitle.getText().equals(biddingTitle)){
            status.add(true);
        }
        WaitUtils.waitForElementClickable(nextButton,5,driver);
        if(nextButton.isEnabled()){
            nextButton.click();
        }
        return status.stream().allMatch(b -> b);
    }*/

    @Step("Validate onBoarding Screens")
    public boolean validateOnboardScreens(String... expectedTooltips) {
        startButton.click();

        for (String expected : expectedTooltips) {
            WaitUtils.waitForSeconds(2);
            String actualText = onboardingTitle.getText();
            if (!actualText.equals(expected)) {
                Reporter.log("Expected: " + expected + " but found: " + actualText);
                return false; // stop early on failure
            }
            Reporter.log("Tooltip matched: " + actualText);
            nextButton.click();
        }
        return true;
    }


}

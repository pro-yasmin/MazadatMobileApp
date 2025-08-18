package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
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
    private WebElement arLanguageBtn;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Skip\")")
    private WebElement skipButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(2)")
    private WebElement startButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(3)")
    private WebElement nextButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").instance(0)")
    private WebElement onboardingTitle;


   @Step("Change App language to Arabic")
   public void changeLanguageToAR(){
    arLanguageBtn.click();
    }

    @Step("Skip onBoaring")
    public void skipIntroScreen(){
        skipButton.click();
    }

    @Step("validate onBoaring screens")
    public boolean checkOnboarding(String scanTitle,String biddingTitle){
        List<Boolean> status = new ArrayList<>();

        startButton.click();

        if (onboardingTitle.getText().equals(scanTitle)){
             status.add(true);
            Reporter.log("Fisrt tittle:" +onboardingTitle.getText());
        }

        WaitUtils.waitForElementClickable(nextButton,2,driver);
        nextButton.click();

        if(onboardingTitle.getText().equals(biddingTitle)){
            status.add(true);
            Reporter.log("Second tittle:" +biddingTitle);
        }
        WaitUtils.waitForElementClickable(nextButton,2,driver);
        nextButton.click();

        return status.stream().allMatch(b -> b);

    }


}

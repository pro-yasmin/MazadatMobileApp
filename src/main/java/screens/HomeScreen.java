package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.ActionsUtils;


import java.time.Duration;

public class HomeScreen {

    AppiumDriver driver;

    public HomeScreen(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Skip\")")
    private WebElement skipButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id=\"android:id/content\"]/android.view.View/android.view.View")
    private WebElement homeButton;

    @AndroidFindBy(className = "android.widget.TextView")
    private WebElement homeTooltip;



    public void skipIntroScreen() throws InterruptedException {
        skipButton.click();
    }

    public void iconsTooltip(){
        for(int i=5; i>0; i--){
            ActionsUtils.doubleClickElement(driver,homeButton);
        }
    }
}
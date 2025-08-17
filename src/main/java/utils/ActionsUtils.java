package utils;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;


//import io.appium.java_client.MobileElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Collections;


public class ActionsUtils {



    public ActionsUtils() {
    }
//relunch app


    //Long press
    public static void longPress(AppiumDriver driver, WebElement element, int seconds) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence longPress = new Sequence(finger, 1);

        int centerX = element.getRect().getX() + (element.getRect().getWidth() / 2);
        int centerY = element.getRect().getY() + (element.getRect().getHeight() / 2);

        longPress.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY));
        longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        longPress.addAction(finger.createPointerMove(Duration.ofSeconds(seconds), PointerInput.Origin.viewport(), centerX, centerY));
        longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(longPress));
    }

    // Swipe (startX, startY → endX, endY)
    public static void swipe( int startX, int startY, int endX, int endY, int duration,AppiumDriver driver) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        // Move finger to start point
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        // Finger down
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        // Move to end point over given duration
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(duration), PointerInput.Origin.viewport(), endX, endY));
        // Finger up
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    // 🔹 Swipe up
    public void swipeUp(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        swipe(startX, startY, startX, endY, 1000, driver);
    }

    // 🔹 Swipe down
    public void swipeDown(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.2);
        int endY = (int) (size.height * 0.8);
        swipe(startX, startY, startX, endY, 1000, driver);
    }

    // 🔹 Scroll to element by text (Android only)
    public WebElement scrollToElement(String visibleText, AppiumDriver driver) {
        if (driver instanceof AndroidDriver) {
            // ANDROID: Use UiScrollable
            return driver.findElement(
                    AppiumBy.androidUIAutomator(
                            "new UiScrollable(new UiSelector().scrollable(true))" +
                                    ".scrollIntoView(new UiSelector().textContains(\"" + visibleText + "\"))"
                    )
            );
        } else if (driver instanceof IOSDriver) {
            // iOS: Use predicate string
            return driver.findElement(
                    AppiumBy.iOSNsPredicateString("label CONTAINS '" + visibleText + "' OR name CONTAINS '" + visibleText + "'")
            );
        } else {
            throw new UnsupportedOperationException("Unsupported platform: ");
        }
    }

    // 🔹 Hide keyboard
   public void hideKeyboard(AppiumDriver driver) {
       try {
           if (driver instanceof AndroidDriver) {
               ((AndroidDriver) driver).hideKeyboard();
           } else if (driver instanceof IOSDriver) {
               ((IOSDriver) driver).hideKeyboard();
           }
       } catch (Exception e) {
           System.out.println("Keyboard not present or already hidden.");
       }
    }

    public static void doubleClickElement(AppiumDriver driver,WebElement element) {
        // Create an Actions object
        Actions actions = new Actions(driver);

        // Build the sequence of actions:
        // 1. Move to the element
        // 2. Perform the first click (pointerDown followed by pointerUp)
        // 3. Pause for a very short duration (e.g., 50 milliseconds)
        // 4. Perform the second click
        actions.moveToElement(element)
                .click() // First click
                .pause(50) // Pause for a quick double-click action
                .click() // Second click
                .build()
                .perform();
    }
}

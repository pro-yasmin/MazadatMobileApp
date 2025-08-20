package base;

import java.io.*;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Properties;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import io.appium.java_client.android.AndroidDriver;
//import io.qameta.allure.Attachment;
//import jdk.jpackage.internal.util.FileUtils;
//import org.openqa.selenium.OutputType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


public class BaseTest {
    protected static Properties prop;
    protected static FileInputStream inputStream;
    protected static AppiumDriver driver;
    private  static final String APPIUM_URL = "http://127.0.0.1:4723"; // Appium server URL
    public static AppiumDriverLocalService service;


    @BeforeSuite
    public void beforeSuite() {
        loadConfig();
        startAppiumServer();
        setup();
        //login
    }
    @BeforeMethod
    public void beforeMethod() {

    }



    public static void loadConfig() {
        // Load properties file once for the entire suite
        // Load properties file once for the entire suite
        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream("src/main/resources/config/config.properties"),
                StandardCharsets.UTF_8)) {

            prop = new Properties();
            prop.load(reader);

        } catch (FileNotFoundException e) {
            System.err.println("Configuration file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error loading properties file: " + e.getMessage());
        }
    }


    public  static void setup() {
        String platformName = "Android";
        try {
            if (platformName.equalsIgnoreCase("Android")) {
                driver=setupAndroid();
            } else if (platformName.equalsIgnoreCase("iOS")) {
                driver=setupiOS();
            } else {
                throw new IllegalArgumentException("Invalid platform: " + platformName);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("Error initializing driver: " + e.getMessage());
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    private static AndroidDriver setupAndroid() throws MalformedURLException {
        // Read APK path from config
        String appPath = prop.getProperty("androidAppPath");
        File app = new File(appPath);

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName("Pixel7Android15");
        //options.setPlatformVersion("your-android-version");
        options.setAppPackage("sa.elm.mazadat");
        options.setAppActivity("sa.elm.mazadat.android.MainActivity");
        //options.setApp(app.getAbsolutePath());

        return new AndroidDriver(new URL(APPIUM_URL), options);
    }

    private  static IOSDriver setupiOS() throws MalformedURLException {
        String appPath = prop.getProperty("iosAppPath");
        File app = new File(appPath);

        XCUITestOptions options = new XCUITestOptions();
        options.setPlatformName("iOS");
       // options.setDeviceName("your-ios-device-name");
       // options.setPlatformVersion("your-ios-version");
        options.setAutomationName("XCUITest");
        options.setBundleId("sa.elm.mazadat.tameer");
        options.setUdid("00008101-000921421A0A001E");
        options.setCapability("autoAcceptAlerts", true);

        //options.setApp(app.exists() ? app.getAbsolutePath() : null);   // Install fresh if file exists

        return new IOSDriver(new URL(APPIUM_URL), options);
    }

    public static AppiumDriverLocalService startAppiumServer() {

        boolean flag = checkIfServerIsRunnning(4723);
        if (!flag) {
            service = AppiumDriverLocalService.buildDefaultService();
            service.start();
        }
        return service;

    }

    private static boolean checkIfServerIsRunnning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);

            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }

    public void relaunchApp() {
        if (driver == null) {
            throw new IllegalStateException("Driver is not initialized. Please start the driver before calling relaunchApp().");
        }
        try {
            if (driver instanceof AndroidDriver) {
                String appPackage = "sa.elm.mazadat";  // e.g., sa.elm.mazadat
                ((AndroidDriver) driver).terminateApp(appPackage);
                ((AndroidDriver) driver).activateApp(appPackage);
            } else if (driver instanceof IOSDriver) {
                String bundleId = prop.getProperty("bundleId");  // e.g., com.example.iosapp
                ((IOSDriver) driver).terminateApp(bundleId);
                ((IOSDriver) driver).activateApp(bundleId);
            } else {
                throw new UnsupportedOperationException("Relaunch not supported for driver type: " + driver.getClass());
            }

            System.out.println("App relaunched successfully.");

        } catch (Exception e) {
            System.err.println("Failed to relaunch app: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @AfterSuite
    public static void afterSuite() {
        try {
            // Generate the report
            ProcessBuilder generate = new ProcessBuilder(
                    "allure", "generate", "target/allure-results",
                    "-o", "target/allure-report", "--clean"
            );
            generate.inheritIO().start().waitFor();

            // Open the report (non-blocking, exits immediately)
            new ProcessBuilder("allure", "open", "target/allure-report")
                    .inheritIO()
                    .start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}
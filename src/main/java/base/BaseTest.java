package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
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
public void beforeMethod() {}



    public static void loadConfig() {
        // Load properties file once for the entire suite
        try {
            inputStream = new FileInputStream("src/main/resources/config/config.properties");
            prop = new Properties();
            prop.load(inputStream);
        } catch (FileNotFoundException e) {
            System.err.println("Configuration file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error loading properties file: " + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName("Pixel7Android15");
        //options.setPlatformVersion("your-android-version");
        options.setAppPackage("sa.elm.mazadat");
        options.setAppActivity("sa.elm.mazadat.android.MainActivity");
        // options.setApp("/path/to/your/app.apk");

        return new AndroidDriver(new URL(APPIUM_URL), options);
    }

    private  static IOSDriver setupiOS() throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions();
        options.setPlatformName("iOS");
        options.setDeviceName("your-ios-device-name");
        options.setPlatformVersion("your-ios-version");
        options.setBundleId("com.yourcompany.yourapp");
        options.setApp("/path/to/your/app.ipa");

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

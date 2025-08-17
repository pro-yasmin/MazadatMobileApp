package screens;

//import io.appium.java_client.MobileElement;

//import io.qameta.allure.Allure;

public class SignInScreen {
/*	AndroidDriver <MobileElement> driver;
	
       public SignInScreen(AndroidDriver <MobileElement> driver) {
    	   PageFactory.initElements(driver, this);
    	   this.driver=driver;
		}
       
       @FindBy(xpath="//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
       private WebElement ignoreLogin;
	
       @FindBy(xpath = "//*[@text=\"Continue with email\"]")
       private WebElement continueWithMailBtn;
       
       @FindBy(xpath="//android.widget.EditText")
       private WebElement emailAddress;
       
       @FindBy(xpath="//android.widget.Button")
       private WebElement continueBtn;
       
       @FindBy(xpath="//android.widget.EditText[@resource-id=\"com.booking:id/identity_text_input_edit_text\"]")
       private WebElement password;
       
       @FindBy(xpath="//android.widget.TextView[@resource-id=\"com.booking:id/identity_landing_social_button_text\" and @text=\"Sign in\"]")
       private WebElement signBtn;
       
       @FindBy(xpath="//android.widget.TextView[@resource-id=\"com.booking:id/identity_landing_social_button_text\" and @text=\"Sign in via email verification link\"]")
       private WebElement verificationBtn;
       
       @FindBy(id="com.booking:id/bui_input_choice_container_content")
       private WebElement acceptCondition;
       
       @FindBy(xpath="//android.widget.EditText")
       private WebElement addVerificationCode;
       
       @FindBy(xpath="//android.widget.TextView[@text=\"Verify email\"]")
       private WebElement verifyMail;
	
	public void SignInwithEmailAndPw(String mail, String pw){
		WaitUtils.waitUntilElementVisible(continueWithMailBtn,driver);
			this.continueWithMailBtn.click();
			
		WaitUtils.waitUntilElementVisible(emailAddress,driver);
		 Allure.step("Add user mail ="+ mail);
			 this.emailAddress.sendKeys(mail);
			 
			 this.continueBtn.click();
			 
		WaitUtils.waitUntilElementVisible(password,driver);
		Allure.step("Add password ="+pw);
			 this.password.sendKeys(pw); 
			 this.signBtn.click();
	}
	
	public void signInWithVerificationLink(String mail){
		WaitUtils.waitUntilElementVisible(continueWithMailBtn,driver);
		this.continueWithMailBtn.click();
		
		WaitUtils.waitUntilElementVisible(emailAddress,driver);
		 Allure.step("Add user mail ="+ mail);
		 this.emailAddress.sendKeys(mail);
		 this.continueBtn.click();

		WaitUtils.waitUntilElementVisible(verificationBtn,driver);
		 this.verificationBtn.click();
		
	}
	
	public void signInWithVerificationCode(String mail) {
		WaitUtils.waitUntilElementVisible(continueWithMailBtn,driver);
		acceptCondition.click();
		this.continueWithMailBtn.click();
		
		WaitUtils.waitUntilElementVisible(emailAddress,driver);
		 Allure.step("Add user mail ="+ mail);
		 this.emailAddress.sendKeys(mail);
		 this.continueBtn.click();
	}
	
	public void submitVerificationCode(String code) {
		WaitUtils.waitUntilElementVisible(addVerificationCode,driver);
		addVerificationCode.sendKeys(code);
		verifyMail.click();
	}
	
	public void ignoreLogin() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WaitUtils.waitUntilElementVisible(ignoreLogin,driver);
		ignoreLogin.click();
	}
	
	*/
	
}

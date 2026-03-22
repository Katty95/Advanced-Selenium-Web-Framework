package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}
		@FindBy(xpath = "//input[@placeholder='Username']")
		private WebElement UN;
		@FindBy(xpath="//input[@placeholder='Password']")
		private WebElement passWord;
		@FindBy(xpath="//button[@type='submit']")
		private WebElement loginBtn;
		@FindBy(xpath = "//p[normalize-space()='Invalid credentials']")
		private WebElement invalidpassword;
		
		
		
		
		public WebElement getInvalidpassword() {
			return invalidpassword;
		}
		public WebElement getLoginBtn() {
			return loginBtn;
		}
		public WebElement getUN() {
			return UN;
		}
		public WebElement getPassWord() {
			return passWord;
		}
		
		public void Login(String user,String pass) {
			UN.sendKeys(user);
			passWord.sendKeys(pass);
			
		}
		
		public void clickLogin() {
			loginBtn.click();
		}
		
		
		
		
		
	

}

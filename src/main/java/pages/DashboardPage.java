package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
	WebDriver driver;
	WebDriverWait wait;
	public DashboardPage(WebDriver driver) {
		this.driver= driver;
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		PageFactory.initElements( driver,this);
	}
	@FindBy(xpath = "//span[normalize-space()='PIM']")
	private WebElement pim;
	@FindBy(xpath="//p[@class='oxd-userdropdown-name']")
	private WebElement userDropdwon;
	
	@FindBy(xpath="//a[normalize-space()='Logout']")
	private WebElement LogOut;
	
	public WebElement getpim() {
		return pim;
	}
	public WebElement getUserDropdwon() {
		return userDropdwon;
	}

	public WebElement getLogOut() {
		return LogOut;
	}
	
	
	public void logout() {
		wait.until(ExpectedConditions.elementToBeClickable(userDropdwon)).click();
		wait.until(ExpectedConditions.elementToBeClickable(LogOut)).click();
	}
	
}

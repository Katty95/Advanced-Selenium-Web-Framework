package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import genricUtilites.Propertiesfileutility;

public class BaseTest {

	
	public String baseUrl;
	public WebDriver driver;
	public static WebDriver sdriver;
	public WebDriverWait wait;
	
	public Propertiesfileutility pUtil=new Propertiesfileutility();
	 
	
	@BeforeMethod (groups = {"smoke", "regression"})
public void setup() throws Throwable {
		baseUrl= pUtil.toReadDataFrompropertiesfile("url");
		// 1. ChromeOptions ka object banayein
	    ChromeOptions options = new ChromeOptions();
	    
	    // 2. Headless argument add karein
	    options.addArguments("--headless=new"); 
	    
	    // Optional: Screen size set karein (Headless mein zaroori hota hai taaki elements sahi dikhein)
	    options.addArguments("--window-size=1920,1080");
	    options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
	    
	    // 3. Driver ko options ke saath initialize karein
	   //driver = new ChromeDriver(options);
		
		System.out.println("Before Test execute - Launching URL: "+baseUrl );
		driver=new ChromeDriver(options);
		sdriver = driver;
		// Maximize window
		//driver.manage().window().maximize();
		//Open url
		driver.get(baseUrl);
		//global wait 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		
	}
	@AfterMethod
	public void teardown () throws InterruptedException {
//!= → not equal to driver null ke barabar nahi hai
	
		if (driver != null) {
			Thread.sleep(5000);// wait for 5 second before quit
		
		driver.quit();
	}
	}
	}

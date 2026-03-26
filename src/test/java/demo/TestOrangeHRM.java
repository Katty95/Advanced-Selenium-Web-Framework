package demo;

import org.testng.Assert;
import org.testng.annotations.*;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;

public class TestOrangeHRM extends BaseTest {
	// TC_LOGIN_001 (Valid Login)
	@Test(priority = 1, groups = "smoke")
	public void loginTest() throws Throwable

	{
		System.out.println("Login Test running");
		String UN = pUtil.toReadDataFrompropertiesfile("username");
		String PW = pUtil.toReadDataFrompropertiesfile("password");
		LoginPage lp = new LoginPage(driver);
		lp.Login(UN,PW);
		lp.getLoginBtn().click();

		DashboardPage dp = new DashboardPage(driver);

		// Verify the login was successful by checking page title;

		String pageTitle = driver.getTitle();
		System.out.println("Page Title is :" + pageTitle);
		Assert.assertEquals("OrangeHRM", pageTitle, "Title mismatch! Login failed.");

		/*
		 * if(pageTitle.equals("OrangeHRM")) { System.out.println("Login successful");
		 * 
		 * } else { System.out.println("Login Failed"); }
		 */
		// 5. Logout Action
		System.out.println("User is :" + dp.getUserDropdwon().getText());
		dp.logout();
	}

	// TC_LOGIN_002 (Invalid Login)
	@Test(priority = 2, groups = "smoke")
	public void invalidLogin() throws Throwable {

		LoginPage lP = new LoginPage(driver);
		String UN = pUtil.toReadDataFrompropertiesfile("username");
		String PW = pUtil.toReadDataFrompropertiesfile("wrongpassword");
		lP.Login(UN,PW);
		lP.getLoginBtn().click();
		String message_expected = "Invalid credentials";
		String Error = lP.getInvalidpassword().getText();
		Assert.assertEquals(message_expected, Error, "Test fail not matching");
		System.out.println("test Invalid login test pass");
	}

}

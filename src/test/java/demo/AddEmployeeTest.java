package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PIM;

@Listeners(listenerUtility.ListenerImplementation.class)
public class AddEmployeeTest extends BaseTest {

	// Global Variable declare for deleting last employee id;
	//public String captureId;
	//public String captureEmployeeName;

	@DataProvider(name = "employeeData")
	public Object[][] getEmpdata() {
		return new Object[][] { { "Ankit", "Kumar", "869551" }, { "Abhijeet", "Kumar", "8996514" },
				{ "Piku", "chiku", "1335619" }

		};
	}

	@Test(priority = 1, dataProvider = "employeeData", groups = "regression")
	public void verifyAddEmployee(String fName, String lName, String id) throws Throwable {
		String UN = pUtil.toReadDataFrompropertiesfile("username");
		String PW = pUtil.toReadDataFrompropertiesfile("password");
		LoginPage lp = new LoginPage(driver);
		lp.Login(UN,PW);
		lp.getLoginBtn().click();

		DashboardPage dp = new DashboardPage(driver);
		wait.until(ExpectedConditions.elementToBeClickable(dp.getpim())).click();

		PIM pm = new PIM(driver);
		wait.until(ExpectedConditions.elementToBeClickable(pm.getAddEmplyoee())).click();
		pm.getFirstName().sendKeys(fName);
		pm.getLastName().sendKeys(lName);
		pm.getEmployeeId().sendKeys(Keys.CONTROL + "a");
		pm.getEmployeeId().sendKeys(Keys.BACK_SPACE);
		pm.getEmployeeId().sendKeys(id);
		wait.until(ExpectedConditions.elementToBeClickable(pm.getSave())).click();

		// verifyAddEmployee test
		String captureId = pm.GeneratedEmployeeId();
		String captureEmployeeName = pm.GeneratedEmployeeName();
		System.out.println("Employee ID : " + captureId + captureEmployeeName );

		// String actaulMsg = pm.getToastMessage().getText();
		// assertEquals(actaulMsg,"Successfully Saved");

		String currentUrl = driver.getCurrentUrl();
		Boolean isSaved = wait.until(ExpectedConditions.urlContains("viewPersonalDetails"));
		Assert.assertTrue(isSaved, "Employee not saved");
		// Assert.assertTrue(currentUrl.contains("viewPersonalDetails"),"Employee not
		// saved");
		dp.getUserDropdwon().click();
		dp.getLogOut().click();

	}

	@Test(priority = 3, dataProvider = "employeeData", groups = "regression", dependsOnMethods = "verifyAddEmployee")
	public void deleteEmployee(String fName, String lName, String id) throws Throwable {
		// 1. Pehle check karo ID null toh nahi
		/*//No need of null check because i am using data provider
		 * if (captureId == null || captureId.isEmpty()) {
			Assert.fail("Delete skip because Employee ID is null");
		}*/

		// Login
		String UN = pUtil.toReadDataFrompropertiesfile("username");
		String PW = pUtil.toReadDataFrompropertiesfile("password");
		LoginPage lp = new LoginPage(driver);
		lp.Login(UN,PW);
		lp.getLoginBtn().click();

		// Dashboard par click karna

		DashboardPage dp = new DashboardPage(driver);
		dp.getpim().click();

		PIM pm = new PIM(driver);
		pm.getEmployeeId().sendKeys(id);
		pm.getSearch().click();

		System.out.println("Searching for ID to delete:" + id);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(pm.getCheckBox())).click();
		} catch (StaleElementReferenceException e) {
			driver.findElement(By.xpath("//div[@class='oxd-table-card-cell-checkbox']//span")).click();
		}

		

		pm.getTrash().click();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(pm.getYesDelete())).click();
		} catch (StaleElementReferenceException  e) {
			driver.findElement(By.xpath("//button[normalize-space()='Yes, Delete']")).click();
		}
		
		try {
			wait.until(ExpectedConditions.visibilityOf(pm.getNoRecord()));
		} catch (StaleElementReferenceException  e ) {
			driver.findElement(By.xpath("//span[normalize-space()='No Records Found']"));
		}
		
		boolean noRecord = pm.getNoRecord().getText().contains("No Records Found");
		Assert.assertTrue(noRecord, "Verification Failed: 'No Records Found' message NOT found!");
		System.out.println("Verification passed:''No Records Found' message found'");

	}

	@Test(priority = 2 ,dataProvider = "employeeData", groups = "regression",dependsOnMethods = "verifyAddEmployee")
	public void searchEmployee(String fName, String lName, String id)  throws Throwable {
		/* // No need of null check because i am using data provider
		 if (captureEmployeeName  == null  || captureEmployeeName.isEmpty()) {
			Assert.fail("Search skip because Employee Name is null");
			}
		*/
			String UN = pUtil.toReadDataFrompropertiesfile("username");
			String PW = pUtil.toReadDataFrompropertiesfile("password");
			LoginPage lp=new LoginPage(driver);
			lp.Login(UN, PW);
			lp.clickLogin();
			
			DashboardPage dp =new DashboardPage(driver);
			dp.getpim().click();
			
			PIM pi=new PIM(driver);
			pi.getEmpName().sendKeys(fName);
			pi.getEmpName().sendKeys(Keys.ARROW_DOWN);
			pi.getEmpName().sendKeys(Keys.ENTER);
			pi.getSearch().click();
			String employeeMatch = wait.until(ExpectedConditions.visibilityOf(pi.getRecordFound())).getText();
			Assert.assertEquals(employeeMatch, "(1) Record Found");
			
		}
	}


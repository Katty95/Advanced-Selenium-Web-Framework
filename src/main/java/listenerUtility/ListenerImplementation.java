package listenerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.annotations.ITest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.BaseTest;

public class ListenerImplementation implements ITestListener,ISuiteListener {
	public static ExtentReports report;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onStart(ISuite suite) {
		Date d= new Date();
		String time = d.toString().replace(" ", "_").replace(":", "_");
		ExtentSparkReporter spark=new ExtentSparkReporter("./AdvanceReport/Report_"+time+".html");
		spark.config().setDocumentTitle("OrangeHRM Automation Results");
		spark.config().setReportName("Automation Report");
		spark.config().setTheme(Theme.DARK);
		
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Window 11");
		report.setSystemInfo("Browser", "Chrome");
		report.setSystemInfo("QA Name", "Ankit Kumar Singh");
		Reporter.log("=====Report configuration Initialized=====",true);
		
	}
	
	
	@Override
	public void onTestStart(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		//// Extent Test Create karna aur ThreadLocal mein set karna
		ExtentTest t=report.createTest(methodName);
		test.set(t);
		test.get().log(Status.INFO,methodName+" =====STARTED====");
		Reporter.log("====="+methodName+"Execution STARTED====",true);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		test.get().log(Status.PASS, methodName+"====>SUCCESS<=====");
		Reporter.log("===="+methodName+"Success===",true);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.get().log(Status.FAIL, methodName+"===> FAILED <===");
		test.get().fail(result.getThrowable());
			
		try {
		TakesScreenshot ts=(TakesScreenshot)BaseTest.sdriver;
		String base64Path = ts.getScreenshotAs(OutputType.BASE64);
		test.get().addScreenCaptureFromBase64String(base64Path,"Failure_Screenshot_"+methodName);
		}catch (Exception e) {
			test.get().log(Status.WARNING, "Failed to attach screenshot:"+e.getMessage());
		}
		
	
		
		Reporter.log("===="+methodName+"Failure===",true);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		test.get().log(Status.SKIP, methodName+"===> SKIPPED <===");
		Reporter.log("===="+methodName+"SKIPPED====",true);
	}


	@Override
	public void onFinish(ISuite suite) {
		
		// Report save 
		if(report!=null) {
			report.flush();
		}
		Reporter.log("======>Report Generated & Saved in AdvanceReport Folder<=======",true);
	}

	
		
	
	

}

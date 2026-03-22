package listenerUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

public class RetryImplementation implements IRetryAnalyzer{
	
	int count= 0;
	int limit= 2;
	@Override
	public boolean retry(ITestResult result) {
		if (count<limit) {
			count++;
			System.out.println("Retrying test:"+ result.getName()+ " | Attempt: " + count);
			Reporter.log("Retrying test: " + result.getName() + " | Attempt: " + count, true);
			return true;
			
		}
		return false;
	}
	
	
	

}

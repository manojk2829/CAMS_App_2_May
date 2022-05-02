package listenerManager;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class My_ListenerClass implements ITestListener{
	
	public ExtentTest test;	

	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getName() + " - %%%%%%%%%% Test Success PASS %%%%%%%% "+result.getName());
		test = (ExtentTest)result.getTestContext().getAttribute("test");
        test.log(Status.PASS, result.getName() + " - %%%%%%%%%%  Test PASS  %%%%%%%% "+result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
        System.out.println(result.getName() + " - %%%%%%%%%%  Test failed  %%%%%%%% - "+result.getName());
        System.out.println(result.getThrowable().getMessage());
        
        test = (ExtentTest)result.getTestContext().getAttribute("test");
        //Reporter.getCurrentTestResult().getTestContext().setAttribute("criticalFailuer", "Y");
        test.log(Status.FAIL, result.getThrowable().getMessage());
        test.log(Status.FAIL, result.getName() + " - %%%%%%%%%%  Test FAILED  %%%%%%%% "+result.getName());
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println(result.getName() + " - %%%%%%%%%%  Test SKIPPED  %%%%%%% "+result.getName());
		test = (ExtentTest)result.getTestContext().getAttribute("test");
        test.log(Status.SKIP, result.getName() + " - %%%%%%%%%%  Test SKIPPED  %%%%%%%% "+result.getName());
	}

}

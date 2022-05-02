package testBase;

import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import keywords.ApplicationKeyword;
import reportingPackage.ExtentReportingManager;

public class BaseTest {
	public ApplicationKeyword app;
	public ExtentReports rep;
	public ExtentTest test;
	
	@BeforeTest(alwaysRun = true)
	public void beforeTest(ITestContext context) {
		// Initialized properties file and share to Test... 
		app = new ApplicationKeyword();
		context.setAttribute("app", app);		
		// Initialized Reporting file and share to Test...
		
		rep = ExtentReportingManager.getReporting_By_Manoj();
		test = rep.createTest(context.getCurrentXmlTest().getName());	
		
		System.out.println("*********** Before Test *********");		

		test.log(Status.INFO, "Starting the Test... " + context.getCurrentXmlTest().getName());
		app.setReport(test);
		context.setAttribute("report", rep);
		context.setAttribute("test", test);			
	}
	
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(ITestContext context) {		
		System.out.println("*********** Before Method *********");		
		app = (ApplicationKeyword)context.getAttribute("app");		
		rep = (ExtentReports)context.getAttribute("report");
		test = (ExtentTest)context.getAttribute("test");		
		
		String criticalFailure = (String)context.getAttribute("criticalFailure");
		if(criticalFailure !=null && criticalFailure.equals("Y")) {
			app.log_Skip("criticalFailure occured in previous tests. Now skipped the Test");
			throw new SkipException("criticalFailure occured in previous tests. ");
		}		
	}
	
	@AfterTest
	public void tearDown() {
		if(rep!=null) {
			rep.flush();	
		}
		app.assetAll();
		app.quite_Browser_afuter_Wait(2);
		
		
	}
}

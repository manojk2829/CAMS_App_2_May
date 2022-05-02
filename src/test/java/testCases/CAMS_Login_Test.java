package testCases;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import keywords.ApplicationKeyword;
import testBase.BaseTest;

public class CAMS_Login_Test extends BaseTest{
	
	@Test
	public void doing_Login(ITestContext context) {	
		app.log("Going to Login in CAMS catalog admin application....");
		app.openBrowser("chrome");
		app.navigate("url");
		app.type_input("username_name", app.pro.getProperty("username"));
		app.type_input("passweord_xpath",app.pro.getProperty("password"));
		//app.reportFailure("First Fail... Non Critical Error -",false);
		//app.validate_Element_Present("login_xpath");
		app.click("login_xpath");
		app.wait(1);
		app.takeScreenShot();
		app.validate_Element_Present("cms_xpath");
		System.out.println("## Login Done Successfully .... Because Welcome page link is getting displayed on Page. ##");
		app.log_Pass("## Login Successfully Done .... Because Welcome page link is getting displayed on Page. ##");
	}
}

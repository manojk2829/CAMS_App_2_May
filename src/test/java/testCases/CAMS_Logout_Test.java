package testCases;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import testBase.BaseTest;

public class CAMS_Logout_Test extends BaseTest{

	@Test
	public void doing_Logout(ITestContext context) {
		// Logout application....
		System.out.println("Going to Logout......");
		app.log("Going to Logout......"); 
		app.scroll_Down("menu_scroll_bar_xpath");
		app.click("logout_xpath");		
		app.wait(2);
		app.validate_Element_Present("passweord_xpath");
		//app.reportFailure("First Fail... Non Critical Error -",false);
		app.log_Pass("## Logout Done Successfully.... Because Login page is getting displayed on Page. ##");
	}
}

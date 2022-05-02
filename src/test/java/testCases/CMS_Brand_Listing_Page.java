package testCases;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import testBase.BaseTest;

public class CMS_Brand_Listing_Page extends BaseTest{
	
	@Test
	public void brand_listing_page_test(ITestContext context) {	
		app.log("Going to Verify Brand Listing page data in side CAMS catalog admin application....");
		doing_Login(context);
		app.wait(1);
		app.validate_Element_Present("manage_brand_xpath");
		app.click("manage_brand_xpath");
		app.wait(1);
		app.validate_Element_Present("brands_link_xpath");
		app.takeScreenShot();
		app.click("brands_link_xpath");
		app.wait(1);
		app.validate_Element_Present("add_new_brand_button_xpath");
		app.wait(2);
		app.validate_Element_Present_with_text("brand_listing_data_xpath", "Listing data");
		app.validate_Element_Present_with_text("brand_filter_xpath", " Filter Button ");
		app.validate_Element_Present_with_text("brand_Columns_xpath"," Column Button ");
		app.validate_Element_Present_with_text("brand_view_xpath"," View Button ");
		app.validate_Element_Present_with_text("brand_Export_Excel_xpath", "Excel Export");
		app.validate_Element_Present_with_text("brand_Export_CSV_xpath", "CSV Export");
		app.validate_Element_Present_with_text("brand_Reset_filter_link_xpath", "Reset_Filter_link");
		app.wait(1);
		app.validate_Element_Present_with_text("brand_custom_page_link_xpath", "Custom Page link"); //span[text()='Custom records ']
		app.takeScreenShot();
    	System.out.println("## Brand Listing Page Open.... Because Add Brand Button and List of Brand getting Displayed. ##");
		app.log_Pass("## Brand Listing Page Open.... Because Add Brand Button and List of Brand getting Displayed. ##");
	}
	
	
	public void doing_Login(ITestContext context) {	
		app.openBrowser("chrome");
		app.navigate("url");
		app.type_input("username_name", app.pro.getProperty("username"));
		app.type_input("passweord_xpath",app.pro.getProperty("password"));
		app.click("login_xpath");
		app.log_Pass("## Login Successfully Done .... Because Welcome page link is getting displayed on Page. ##");
	}
}

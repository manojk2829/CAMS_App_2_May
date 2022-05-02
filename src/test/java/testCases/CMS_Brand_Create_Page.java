package testCases;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import testBase.BaseTest;

public class CMS_Brand_Create_Page extends BaseTest{	
	
	@Test  // Brand Properties Configuration....
	public void brand_create_page_all_button_validation(ITestContext context) {		
		// ALL Button Validation on Brand Create Page
		app.log("Going to Validation ALL Button on Brand Create Page.");
		doing_Login(context);
		brand_listing_page_test(context);		
		app.wait(1);
		app.validate_Element_Present("add_new_brand_button_xpath");
		app.takeScreenShot();
		app.click("add_new_brand_button_xpath");
		app.verify_Header_Present("create_brand_page_header_xpath", "Create Brand Item");
		app.validate_Element_Present("back_button_xpath");
		app.validate_Element_Present("save_and_continue_button_xpath");
		app.validate_Element_Present("save_button_xpath");
		app.validate_Element_Present("reset_button_xpath");
		app.log_Pass("##  ALL Button present on Create Brand Page ##");
	}
	
	
	@Test
	public void brand_create_test(ITestContext context) {
		app.log("Going to Create New Brand in side CAMS catalog admin application....");
		// Enter Brand - English and Arabic Name
		app.type_input("brand_english_name_xpath", app.pro.getProperty("brand_name")+app.randumNumber());
		System.out.println(app.pro.getProperty("brand_name")+app.randumNumber());
	    app.log(app.pro.getProperty("brand_name")+app.randumNumber());
	    app.type_input("brand_arabic_name_xpath", app.pro.getProperty("brand_name")+app.randumNumber());
	    app.click("gender_xpath");	
	    app.click("select_gender_xpath");
		//Upload Thumbnail Image -- Add file method 
		app.type_input("thumbnail_xpath","imagePath");
	    //app.wait(2);
		app.type_input("brand_position_xpath","0");
		app.click("brand_status_xpath");
		app.click("select_status_xpath");
    }
	
	@Test  // Brand Properties Configuration....
	public void brand_properties_configuration(ITestContext context) {		
		// Add Properties 
		app.verify_Header_Present("brand_properties_header_name_xpath", "Brand Properties");
		app.click("select_2_property_xpath");
		app.click("select_2_property_1_xpath");
		app.wait(1);
		app.click("select_2_property_2_xpath");
		app.wait(1);
		app.click("select_1_property_xpath");
		app.click("select_1_property_1_xpath");
		app.wait(1);
		app.click("select_1_property_2_xpath");
		app.wait(1);
		
		app.type_input("select_date_xpath", app.pro.getProperty("enter_properties_date"));
		app.wait(1);
		app.type_input("text_editor_xpath", app.pro.getProperty("text_editor_data"));
		app.type_input("select_type_field_xpath", app.pro.getProperty("enter_properties_text_box"));
		app.click("select_yes_no_property_xpath");
		app.click("select_yes_property_xpath");		
		app.wait(1);
		app.takeScreenShot();
		
		app.click("save_button_xpath");
		app.log_Pass("## Brand Creation Done with Brand Properties data ##");
	}

	public void doing_Login(ITestContext context) {	
		app.openBrowser("chrome");
		app.navigate("url");
		app.type_input("username_name", app.pro.getProperty("username"));
		app.type_input("passweord_xpath",app.pro.getProperty("password"));
		app.click("login_xpath");
		app.log_Pass("## Login Successfully Done .... Because Welcome page link is getting displayed on Page. ##");
	}

	public void brand_listing_page_test(ITestContext context) {	
		app.click("manage_brand_xpath");
		app.wait(1);
		app.click("brands_link_xpath");
		app.wait(1);
		app.takeScreenShot();
		app.log_Pass("## Brand Listing Page Open.... Because Add Brand Button and List of Brand getting Displayed. ##");
	}

}

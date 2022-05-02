package keywords;

import org.openqa.selenium.support.ui.Select;

public class ValidationKeyword extends GenericKeyword{
	
	public void validate_Link_Text() {
		System.out.println("validate_Link_Text");
		log("validate_Link_Text");
	}
	
	public void validate_Element_Present(String locator) {
		System.out.println("Going to Check and Validate -- Element_Present - " + locator);
		log("Going to Check and Validate -- Element_Present - " + locator);
		boolean result = isElement_Present(locator);
		if(result){
			log_Pass("Element Present on Page.... " + locator);
		}else {
			takeScreenShot();
			reportFailure("Element is not Present on Page.... This is critical Failure. " + locator, true);
		}		
	}
	
	public void validate_Element_Present_with_text(String locator,String text) {
		System.out.println("Going to Check and Validate -- Element_Present - " + locator+" - " + text);
		log("Going to Check and Validate -- Element_Present - " + locator+" - " + text);
		boolean result = isElement_Present(locator);
		if(result){
			log_Pass("Element Present on Page.... " + locator+" - " + text);
		}else {
			takeScreenShot();
			reportFailure("Element is not Present on Page.... This is critical Failure. " + locator+" - " + text, true);
		}		
	}
	
	
	public void verify_Header_Present(String locator, String expected_Header_name) {
		System.out.println("Going to Check and Verify Page Header Present - " + locator);
		log("Going to Check and Verify Page Header Present - " + locator);
		String Actual_Page_Header = dr.findElement(getLocator(locator)).getText();
		if(Actual_Page_Header.equalsIgnoreCase(expected_Header_name)){
			log_Pass("Provided Header Present on Page.... " + Actual_Page_Header);
			takeScreenShot();
		}else {
			takeScreenShot();
			reportFailure("Provided Header is not present on Page.... " + Actual_Page_Header, true);
		}		
	}
	
	public void verify_selected_ValuePresent_In_Dropdown(String locator,String option) {
		System.out.println("Going to Verify selected value is Present in dropdown - " + locator);
		Select s = new Select(getElement(locator));
		String dropdown_text= s.getFirstSelectedOption().getText();
		if(!dropdown_text.equals(option)) {
			reportFailure(option + " option is not present in dropdown "+dropdown_text, true);
		}
	}
	
	public void verify_selected_ValuePresent_NotIn_Dropdown(String locator,String option) {
		System.out.println("Going to Verify selected value is Present in dropdown - " + locator);
		Select s = new Select(getElement(locator));
		String dropdown_text= s.getFirstSelectedOption().getText();
		if(dropdown_text.equals(option)) {
			reportFailure(option + " option is present in dropdown "+dropdown_text + " " + locator, true);
		}
	}
	
	public void validate_Title(String Actual_Title) {
		System.out.println("Going to validate_Title");
		log("Going to validate_Title");
		String page_title=dr.getTitle();
		if(Actual_Title.equalsIgnoreCase(page_title)) {
			log_Pass(Actual_Title + " is present on Page... ");
		}else {
			takeScreenShot();
			reportFailure(Actual_Title +  " is not Present on Page.... This is critical Failure. ", true);
		}		
		
	}

}

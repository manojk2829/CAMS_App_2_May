package keywords;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;
import reportingPackage.ExtentReportingManager;

public class GenericKeyword {

	public WebDriver dr;
	public Properties env_pro;
	public Properties pro;
	public SoftAssert soft;
	public ExtentReports rep;
	public ExtentTest test;
	public Actions act;

	public void openBrowser(String bName) {		
		if(bName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");			
			ChromeOptions ops = new ChromeOptions();
			ops.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			//ops.setPageLoadStrategy(PageLoadStrategy.EAGER);
			ops.setExperimentalOption("useAutomationExtension", false);
			ops.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));			
			ops.addArguments("--ignore-certificate-errors");
			ops.addArguments("--disable-notifications");			
			ops.addArguments("--disable-infobars");
			ops.addArguments("--start-maximized");
			ops.addArguments("--disable-web-security");
			//ops.addArguments("--no-proxy-server");
			// To prevent Password Save popup Alert window don't come up.
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ops.setExperimentalOption("prefs", prefs);
			dr = new ChromeDriver(ops);
			System.out.println("Open Browser -------------- "+ bName);
			log("Browser Initialization done... Chrome Browser opened successfully.... ");
			//implicit_wait(100);
		}else if(bName.equalsIgnoreCase("Firefox")) {
			//System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"log\\firefox.log");
			FirefoxOptions fop=new FirefoxOptions();
			FirefoxProfile fp=new FirefoxProfile();
			fp.setPreference("dom.webnotifications.enabled", false);
			fop.setProfile(fp);
			dr=new FirefoxDriver(fop);
			System.out.println("Open Browser -------------- "+ bName);
			log("Browser Initialization done... Firefox Browser opened successfully.... ");
			implicit_wait(100);
		}else {
			System.setProperty(EdgeDriverService.EDGE_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			EdgeOptions edge_ops=new EdgeOptions();
			edge_ops.addArguments("--disable-notifications");
			edge_ops.addArguments("--disable-infobars");
			edge_ops.addArguments("--start-maximized");	
			edge_ops.setExperimentalOption("useAutomationExtension", false);
			edge_ops.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));			
			dr=new EdgeDriver(edge_ops);
			System.out.println("Open Browser -------------- "+ bName);
			log("Browser Initialization done... Edge Browser opened successfully.... ");
			implicit_wait(10);
		}
	}

	public void implicit_wait(int seconds) {
		dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}

	public void navigate(String url) {		
		System.out.println("navigate URL - " + pro.getProperty(url));
		dr.navigate().to(pro.getProperty(url));
		log("navigate URL - " + pro.getProperty(url));
	}

	public void type_input(String locator,String text) {		
		System.out.println("Type input in location - " + locator +" Enter some text inside -- "+ text);
		getElement(locator).sendKeys(text);
		log("Type input in location - " + locator +" Enter some text inside -- "+ text);
	}

	public void click(String locator) {		
		getElement(locator).click();
		log("Click on locator -  " + locator );
	}

	public void wait(int s) {		
		System.out.println("Wait for - " + s + " Seconds");
		try {
			Thread.sleep(s*1000);
		}catch(Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			System.out.println(ex.getMessage());
		}
	}

	public void quite_Browser_afuter_Wait(int s) {		
		System.out.println("Wait for - " + s + " Seconds");
		try {
			Thread.sleep(s*1000);
			dr.quit();
		}catch(Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			System.out.println(ex.getMessage());
		}
	}



	public String get_page_Header(String locator) {
		String page_Header_Text = dr.findElement(getLocator(locator)).getText();
		System.out.println(page_Header_Text);
		return page_Header_Text;
	}

	public void select_Value(String locator, String text) {		
		System.out.println("Select value from Dropdown - " +locator +" Value -- " +text);
		log("Select value from Dropdown - " +locator +" Value -- " +text);
		Select drop_down_value=new Select(getElement(locator));
		drop_down_value.selectByVisibleText(text);		
	}

	public void getText(String locator) {		
		System.out.println("Get the text from the page location --  " +locator);
		getElement(locator).getText();
		System.out.println(getElement(locator).getText());
		log("Get the text from the page location --  " +locator);
	}

	public WebElement getElement(String locator) {

		if(!isElement_Present(locator)) {
			System.out.println("Element Not Present -- "+"[" + pro.getProperty(locator));
			log_Fail("Element Not Present -- " +"["+ pro.getProperty(locator));
		}		

		if(!isElement_Visible(locator)) {
			System.out.println("Element Not Visible -- "+"[" + pro.getProperty(locator));
			log_Fail("Element Not Visible -- "+"[" + pro.getProperty(locator));
		}else {
			System.out.println("Element is Present and Visible... " + locator);		
		}
		WebElement e=null;
		e=dr.findElement(getLocator(locator));
		return e;

	}

	public boolean isElement_Present(String locator) {
		log("Checking Element Presence " + locator);
		System.out.println("Checking Element Presence " + locator);
		WebDriverWait wait=new WebDriverWait(dr, Duration.ofSeconds(5));
		try{
			wait.until(ExpectedConditions.presenceOfElementLocated(getLocator(locator)));
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println("Element Not Presence -- IsElement_Present_Function_Executed. - " + locator 
					+ " - " + "["+ pro.getProperty(locator));
			return false; 
		}
		return true;
	}

	public boolean isElement_Visible(String locator) {
		log("Checking Element Visibility " + locator);
		WebDriverWait wait=new WebDriverWait(dr, Duration.ofSeconds(10));
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locator)));
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println("Element Not Visible -- IsElement_Visible_Function_Executed. - " 
					+ locator + " - " + "["+ pro.getProperty(locator));
			return false; 
		}
		return true;
	}

	public By getLocator(String locatorKey) {
		By by=null;
		if(locatorKey.endsWith("_id")) {
			by=By.id(pro.getProperty(locatorKey));
		}else if(locatorKey.endsWith("_name")) {
			by=By.name(pro.getProperty(locatorKey));
		}else if(locatorKey.endsWith("_xpath")) {
			by=By.xpath(pro.getProperty(locatorKey));
		}else if(locatorKey.endsWith("_css")) {
			by=By.cssSelector(pro.getProperty(locatorKey));
		} 
		return by;
	}


	public void quite_Browser_after_Wait(int s) {		
		System.out.println("quite_Browser_after_Wait - " + s);
		try {
			Thread.sleep(s*1000);
			dr.quit();
		}catch(Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			System.out.println(ex.getMessage());
		}
	}

	public void takeScreenShot() {
		Date d = new Date();
		String ScreenshotFileName = d.toString().replace(" ", "_").replace(":", "_")+".png";
		String reportFile_location = ExtentReportingManager.Screenshot_FolderPath+"//"+ScreenshotFileName;
		File src= ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(src, new File(reportFile_location));
			test.log(Status.INFO, "Screenshot taken -- > " + test.addScreenCaptureFromPath(reportFile_location));
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	/*
	 public void waitForPageToLoad() {
		 JavascriptExecutor js=(JavascriptExecutor)dr;
		 int i=0;
		 while(i!=10) {
			 String state=(String)js.executeScript("return document.readyState;");
			 System.out.println(state);
		 if(state.equals("complete"))
			 break;
		 else
			 wait(2);
		 i++;
		 }
		 i=0;
		 while(i!=10) {
			 Long d=(Long)js.executeScript("return jquery.active;");
			 System.out.println(d);
			 if(d.longValue()==0) 
				 break;
			 else
				 wait(2);
			 i++;
		 }
	 }
	 */
	public void log(String msg) {
		test.log(Status.INFO, msg);
	}

	public void log_Pass(String msg) {
		test.log(Status.PASS, msg);
	}

	public void log_Fail(String msg) {
		test.log(Status.FAIL, msg);
		takeScreenShot();
	}

	public void log_Skip(String msg) {
		test.log(Status.SKIP, msg);
	}

	public void reportFailure(String failureMessage, boolean stopTest_ReportFail) {
		soft.fail(failureMessage);		
		log_Fail(failureMessage);
		if(stopTest_ReportFail) {
			//Reporter.getCurrentTestResult().getTestContext().setAttribute("criticalFailuer", "Y");
			assetAll();
		}
	}

	public void assetAll() {		
		Reporter.getCurrentTestResult().getTestContext().setAttribute("criticalFailuer", "Y");
		soft.assertAll();		
	}

	public long randumNumber() {
		Random r = new Random();
		long l=r.nextInt(100000);		
		return l;
	}

	public void select_value_action_method(String locator) {
		act = new Actions(dr);
		act.moveToElement(getElement(locator)).click().build().perform();
	}

	public void scroll_Down(String locatorKey) {
		act=new Actions(dr);
		act.moveToElement(getElement(locatorKey)).moveByOffset(0,100).perform();
	}

	public void clear_text_box(String locator) {
		getElement(locator).clear();
	}

	public void select_Date_From_any_Calendar(String locaterKey, String selection_date) {
		Date d=new Date();
		try{
			Date expectedDate = new SimpleDateFormat("d-MMMM-yyyy").parse(selection_date);
			String day = new SimpleDateFormat("d").format(expectedDate);
			String month = new SimpleDateFormat("MMMM").format(expectedDate);
			String year = new SimpleDateFormat("yyyy").format(expectedDate);
			String monthyearToBeSelected=month+" "+year;
			String monthYear_to_be_Displayed = getElement(locaterKey).getText();

			while(!monthyearToBeSelected.equals(monthYear_to_be_Displayed)) {
				click("back_arrow_button");
				monthYear_to_be_Displayed = getElement(locaterKey).getText();
			}
			dr.findElement(By.xpath("")).click();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}		
	}

	public int validate_value_from_table_grid(String tableLocaterKey,String tableData) {
		WebElement table = dr.findElement(By.xpath(tableLocaterKey));
		List<WebElement> rows =dr.findElements(By.tagName("tr"));
		for(int rNumber=0;rNumber<rows.size();rNumber++) {
			WebElement row = rows.get(rNumber);
			List<WebElement> cells =dr.findElements(By.tagName("td"));
			for(int cNumber=0;cNumber<cells.size();cNumber++) {
				WebElement col = cells.get(rNumber);
				System.out.println("Text - " + col.getText());
				if(!col.getText().trim().equals("")) {
					if(tableData.startsWith(col.getText()))
						return (rNumber+1);	
				}
			}
			return -1;
		}
		return 0;
	}



}

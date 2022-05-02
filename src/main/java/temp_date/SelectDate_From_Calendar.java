package temp_date;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelectDate_From_Calendar{
	public WebDriver dr;
	@Test
	public void select_date_method() throws InterruptedException{
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
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		ops.setExperimentalOption("prefs", prefs);
		dr = new ChromeDriver(ops);
		System.out.println("Open Browser -------------- Chrome");
		
		  dr.get("https://cams.boutiqaat.com/");	
		  dr.findElement(By.name("userName")).sendKeys("manoj");
		  dr.findElement(By.name("password")).sendKeys("Kushwaha@1212");
		  dr.findElement(By.name("password")).sendKeys(Keys.ENTER);
		  Thread.sleep(2000);
		  dr.findElement(By.xpath("//a[contains(text(),'Manage Brands')]")).click();
		  Thread.sleep(1000);
		  dr.findElement(By.xpath("//a[text()='Brands']")).click();
		  Thread.sleep(2000);
		  dr.findElement(By.xpath("//*[@id='custom_submit']")).click();
		  Thread.sleep(2000);
		  scroll_Down("//div[@class='bq-main-container ']");
		  dr.findElement(By.xpath("//span[@class='rw-i rw-i-calendar']")).click();
		  Thread.sleep(2000);
		  
		    Date d=new Date();
			System.out.println(d.toString());
			String selection_date = "03/31/2022";
			try{
				Date expectedDate = new SimpleDateFormat("MM/dd/yyyy").parse(selection_date);
			    String day = new SimpleDateFormat("d").format(expectedDate);
			    String month = new SimpleDateFormat("MMMM").format(expectedDate);
			    String year = new SimpleDateFormat("yyyy").format(expectedDate);
			    String monthyearToBeSelected=month+" "+year;
			    System.out.println(monthyearToBeSelected);
			    dr.findElement(By.xpath("//input[@id='rw_7_input']")).sendKeys(selection_date);
			    
			    
			    dr.findElement(By.xpath("//span[@class='rw-i rw-i-calendar']")).click();
				  Thread.sleep(2000);
			    String monthYear_to_be_Displayed = dr.findElement(By.xpath("//button[@id='rw_24_date_calendar_label']")).getText();
			    System.out.println(monthYear_to_be_Displayed);
			    
			    while(!monthyearToBeSelected.equals(monthYear_to_be_Displayed)) {
			    	//Click forward key button...
			    	dr.findElement(By.xpath("//button[@class='rw-calendar-btn-right rw-btn rw-btn-primary']")).click();
			    	monthYear_to_be_Displayed = 
			    	dr.findElement(By.xpath("(//button[normalize-space()='"+monthyearToBeSelected+"'])[1]")).getText();
			    }
			    dr.findElement(By.xpath("//td[@title='July '"+day+"', 2022']")).click();
			    
			}catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  String displayDate= dr.findElement(By.xpath("//button[@id='rw_24_date_calendar_label']")).getText();
		  System.out.println(displayDate);
		  select_Date_From_any_Calendar("//button[@id='rw_24_date_calendar_label']", "15-May-2022");
			System.out.println("Required Date selected successfully.....");
		/*  
		  String flag = "False";
		  while(flag=="False") 
		{		   
		   if(dr.findElements(By.xpath("//div[@class='DayPicker-Day'][contains(@aria-label,'May 10 2022')]")).size()>0) 
		 {
		   dr.findElement(By.xpath("//div[@class='DayPicker-Day'][contains(@aria-label,'May 10 2022')]")).click(); 
		   flag="True";
		   Thread.sleep(5000);
		}		   
		   else {
		       Thread.sleep(5000);
		       dr.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
		   }		      
		  }
		  
		  System.out.println("Test case is passed");
		  Thread.sleep(5000);
		//dr.navigate().to("https://www.makemytrip.com/");
		//dr.findElement(By.xpath("//p[@data-cy='departureDate']")).click();
		//Thread.sleep(2);
		//dr.switchTo().alert();
		String Display_Month=dr.findElement(By.xpath("//div[contains(text(),'March 2022')]")).getText();
		System.out.println(Display_Month);
		
		select_Date_From_any_Calendar("//span[@aria-label='Next Month']", "15-May-2022");
		System.out.println("Required Date selected successfully.....");
		*/
	}
	
	public void select_Date_From_any_Calendar(String locaterKey, String selection_date) {
		
		

		
	}
	
	@AfterTest
	public void close() throws InterruptedException {
		Thread.sleep(15000);
	   dr.close();	
	}
	
	public void scroll_Down(String locatorKey){
		Actions act=new Actions(dr);
		act.moveToElement(dr.findElement(By.xpath(locatorKey))).moveByOffset(0,100).perform();
	}
	

}

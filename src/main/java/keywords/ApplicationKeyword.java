package keywords;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

public class ApplicationKeyword extends ValidationKeyword {
	
	public ApplicationKeyword() {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\run_information.properties";
		env_pro=new Properties();
		pro=new Properties();
		try {
			FileInputStream fs=new FileInputStream(filePath);
			env_pro.load(fs);
			String env=env_pro.getProperty("environment")+".properties";
			filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\"+env;
			fs=new FileInputStream(filePath);
			pro.load(fs);
		}catch(Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			System.out.println(ex.getMessage());
		}
		System.out.println("Properties file initialized.... " + pro.getProperty("url"));
		//Soft Assert Class Initialized. 
		soft=new SoftAssert();
	}

	public void default_Login() {
		navigate("url");
		type_input("username_name", pro.getProperty("username"));
		type_input("passweord_xpath",pro.getProperty("password"));
		click("login_xpath");
		wait(1);
		validate_Element_Present("cms_xpath");
		System.out.println("## Login Done Successfully .... Because Welcome page link is getting displayed on Page. ##");
		takeScreenShot();
		log_Pass("## Login Successfully Done .... Because Welcome page link is getting displayed on Page. ##");
	}
	
	public void select_available_link() {
		System.out.println("select_available_link");
	}
	
	public void select_available_login() {
		System.out.println("select_available_login");
	}
	
	public void setReport(ExtentTest test) {
		this.test=test;
	}
}

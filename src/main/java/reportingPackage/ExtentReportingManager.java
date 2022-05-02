package reportingPackage;

import java.io.File;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportingManager {
	public static ExtentReports report;	
	public static String Screenshot_FolderPath;

	public static ExtentReports getReporting_By_Manoj() {
		if(report==null) {
			report = new ExtentReports();		

			Date d=new Date();
			String ProjectFile_Location = System.getProperty("user.dir")+"//reportFiles//";
			String report_Folder_Name = "Report_"+d.toString().replace(" ","_").replace(":", "-");
			
			Screenshot_FolderPath = ProjectFile_Location+report_Folder_Name+"//Screenshot";
			String Report_File_Path= ProjectFile_Location+report_Folder_Name;
			
 			ExtentSparkReporter spark=new ExtentSparkReporter(Report_File_Path);
 			
			File f = new File(Screenshot_FolderPath);
			f.mkdirs();	
			
			spark.config().setReportName("Automation Reports by Manoj Kushwaha -- " +report_Folder_Name);
			spark.config().setDocumentTitle("Manoj Reporting File - "+report_Folder_Name);
			spark.config().setTheme(Theme.DARK);
			spark.config().setEncoding("utf-8");			
			report.attachReporter(spark);

		}
		return report;
	}
}
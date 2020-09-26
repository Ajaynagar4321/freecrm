package generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {
	public WebDriver driver;
	public Properties p;
	public ExtentReports report;
	public ExtentTest test;
	static {
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivernn/chromedriver.exe");
	}

	@BeforeMethod
	public void openBrowser() throws FileNotFoundException, IOException {
		p = new Properties();
		p.load(new FileInputStream("./p.properties"));
		String url = p.getProperty("url");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(url);

		// to get the report
		report = new ExtentReports("./src/main/resources/reports/freecrm.html", false);
		test = report.startTest("System testing");

	}

	@AfterMethod
	public void closeBrowser(ITestResult res) throws IOException {

		int result = res.getStatus();
		String tc = res.getName();
		// if test get failed it attached screenshot
		if (result == 2) {

			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File dest = new File("./photo/" + tc + ".jpeg");
			FileUtils.copyFile(src, dest);
			String path = dest.getAbsolutePath();
			test.log(LogStatus.FAIL, test.addScreenCapture(path));
		}
		// if test get pass
		else if (result == 1) {
			test.log(LogStatus.PASS, tc + " get passed");
		}

		report.endTest(test);
		report.flush();
	}

}

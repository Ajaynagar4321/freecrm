package generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.LoginPage;

public class BaseClass {

	@FindBy(xpath = "//div[@class='dropdown']")
	private WebElement selectDrop;

	@FindBy(xpath = "//div[@class='dropdown-menu js_usermenu show']/a")
	private List<WebElement> dropdownMenu;

	@FindBy(xpath = "//td/a[text()='freedemo']/../../td/span/a[.='Connect ']")
	private WebElement connectBtn;

	@FindBy(xpath = "//div[.='CRM']/..")
	private WebElement crmClick;

	public WebDriver driver;
	public Properties p;
	public ExtentReports report;
	public ExtentTest test;
	static {
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivernn/chromedriver.exe");
	}

	public static void selectdropDown(List<WebElement> value, String input) {
		for (WebElement webElement : value) {
			System.out.println(webElement.getText());
			if (webElement.getText().equals(input)) {
				webElement.click();
				break;
			}
		}

	}

	@DataProvider(name = "exceluserdata")
	public String[][] data() throws EncryptedDocumentException, FileNotFoundException, IOException {
		Workbook book = WorkbookFactory.create(new FileInputStream("./src/main/resources/data/userdetails.xlsx"));
		Sheet sheet = book.getSheet("userdetails");
		int row = sheet.getLastRowNum();
		int col = sheet.getRow(0).getLastCellNum();
		String[][] data = new String[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
			}
		}
		System.out.println("asdfs" + data);
		return data;
	}

	@BeforeClass
	public void openBrowser() throws FileNotFoundException, IOException, InterruptedException {
		p = new Properties();
		p.load(new FileInputStream("./p.properties"));
		String url = p.getProperty("url");
		driver = new ChromeDriver();
		PageFactory.initElements(driver, this);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
		driver.findElement(By.xpath("//a[text()='Sign in']")).click();
		String user = p.getProperty("userlogin");
		String pass = p.getProperty("userpassword");
		LoginPage lp = new LoginPage(driver);

		lp.login(user, pass);
		Thread.sleep(5000);
		Actions ac = new Actions(driver);
		ac.moveToElement(selectDrop).click().perform();
		BaseClass.selectdropDown(dropdownMenu, "My Databases");
		connectBtn.click();
		lp.login(user, pass);
		crmClick.click();

		/*
		 * // to get the report report = new
		 * ExtentReports("./src/main/resources/reports/freecrm.html", false); test =
		 * report.startTest("System testing");
		 */

	}

	@AfterMethod(enabled = false)
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

package generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
	public WebDriver driver;
	public Properties p;
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

	}

}

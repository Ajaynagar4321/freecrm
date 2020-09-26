package generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;

public class BaseClass {

	@BeforeMethod
	public void openBrowser() throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileInputStream("./p.properties"));
		String url = p.getProperty("url");
		
	}

}

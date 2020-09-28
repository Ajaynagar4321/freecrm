package generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;

public class FwUtils {

	public static void selectAction(List<WebElement> element, WebDriver driver, String search) {
		for (WebElement webElement : element) {
			if (webElement.getText().equals(search)) {
				Actions ac = new Actions(driver);
				ac.moveToElement(webElement).click().perform();
				break;
			}
		}
	}

	public static void select(WebElement element, String search) {
		Select sc = new Select(element);
		sc.selectByVisibleText(search);
	}
}

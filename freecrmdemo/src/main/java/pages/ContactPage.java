package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import generic.FwUtils;

public class ContactPage extends FwUtils {

	@FindBy(xpath = "//button[text()='New']")
	private WebElement newUser;
	@FindBy(name = "first_name")
	private WebElement fname;
	@FindBy(name = "middle_name")
	private WebElement mname;
	@FindBy(name = "last_name")
	private WebElement lname;
	@FindBy(xpath = "//div[@name='company']/input")
	private WebElement company;
	@FindBy(xpath = "//input[@placeholder='Email address']")
	private WebElement email;
	@FindBy(name = "category")
	private WebElement category;
	@FindBy(name = "status")
	private WebElement status;
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement save;
	WebDriver driver;
	@FindBy(xpath = "//div[@name='category']/div/div[@role='option']")
	private List<WebElement> categoryOption;

	@FindBy(xpath = "//div[@name='status']/div/div[@role='option']")
	private List<WebElement> statusOption;

	public ContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public void userForm(String fname, String mname, String lname, String company, String email, String category,
			String status) {
		//driver.findElement(HomePage.contact).click();
		newUser.click();
		this.fname.sendKeys(fname);
		this.mname.sendKeys(mname);
		this.lname.sendKeys(lname);
		this.company.sendKeys(company);
		this.email.sendKeys(email);
		this.category.click();
		for (WebElement webElement : categoryOption) {
			if (webElement.getText().equals(category)) {
				webElement.click();
			}
		}
		this.status.click();
		for (WebElement webElement : statusOption) {
			if (webElement.getText().equals(status)) {
				webElement.click();
			}
		}
		save.click();
	}

}

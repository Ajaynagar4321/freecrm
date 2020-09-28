package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.FwUtils;

public class HomePage {

	@FindBy(xpath = "//li/a[contains(text(),'Sales')]")
	private WebElement sales;
	
	@FindBy(xpath = "//div[@class='dropdown-menu show']/a/span")
	private List< WebElement> salesDropDown;
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void customerClick(WebDriver driver)
	{
		sales.click();
		FwUtils.selectAction(salesDropDown, driver,"Customers");
		
	}
	
	 
}

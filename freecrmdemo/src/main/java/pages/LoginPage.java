package pages;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	@FindBy(name = "email")
	private WebElement username;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(xpath = "//div[text()='Login']")
	private WebElement lgnButton;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void login(String user, String pass) {
		username.sendKeys(user);
		password.sendKeys(pass);
		lgnButton.click();

	}
}

package scripts;

import org.testng.annotations.Test;

import generic.BaseClass;
import pages.ContactPage;
import pages.HomePage;

public class TestCreateUser extends BaseClass {

	@Test
	public void testCreateUser() throws InterruptedException {
		HomePage hp = new HomePage(driver);
		hp.customerClick(driver);
	}

}

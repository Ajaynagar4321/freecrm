package scripts;

import org.testng.annotations.Test;

import generic.BaseClass;
import pages.LoginPage;

public class TestCreateUser extends BaseClass {

	@Test
	public void testCreateUser() {
		//to login into the application
		String user = p.getProperty("userlogin");
		String pass = p.getProperty("userpassword");
		LoginPage lp = new LoginPage(driver);
      
		lp.login(user, pass);
	}

}

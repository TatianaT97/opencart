package testCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegitrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

	@Test(groups= {"regression","master"})
	public void verify_account_registration()

	{

		logger.info("****  Starting TC_001_AccountRegistrationTest  ****");

		try {

			HomePage hp = new HomePage(driver);

			hp.clickMyAccount();
			logger.info("Clicked on my Account Link");

			hp.clickRegister();
			logger.info("Clicked on my Registration Link");

			logger.info("Entering customer details..");
			AccountRegitrationPage regpage = new AccountRegitrationPage(driver);

			regpage.setFirstName(randomeString().toUpperCase());
			regpage.setLastName(randomeString().toUpperCase());
			regpage.setEmail(randomeString() + "@gmail.com");// randomly generated the email
			regpage.setTelephone(randomeNumber());

			String password = randomAlphaNumeric();

			regpage.setPassword(password);
			regpage.setConfirmPassword(password);

			regpage.setPrivacyPolicy();
			regpage.clickContinue();
			logger.info("Clicked on continue.");

			String confmsg = regpage.getConfirmationMsg();

			logger.info("Validating expected result");
			Assert.assertEquals(confmsg, "Your Account Has Been Created!");

		}

		catch (Exception e) {
			logger.error("Test Failed");
			Assert.fail();

		}

		logger.info("****  Finished TC_001_AccountRegistrationTest  ****");

	}

}

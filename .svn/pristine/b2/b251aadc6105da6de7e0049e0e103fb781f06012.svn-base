package testcases;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.DashBoardPage;
import pages.LoginPage;
import testBase.TestBase;

public class LoginPageTest extends TestBase

{

	LoginPage loginPage;
	DashBoardPage dashboard;
	

	boolean aa = false;

	public LoginPageTest() throws IOException 
	{
		super();

	}


	@BeforeMethod
	public void setup() throws IOException 
	{
		initilization();
		loginPage = new LoginPage();
		log.info("Initiating Login Page Object");
		dashboard = new DashBoardPage();
		log.info("Initiating Dashboard Page Object");
	}



	@Test(priority = 1) 
	public void Verify_Login_Page_Title_Test() 
	{ 
		String loginTitle = loginPage.getPageTitle();
		log.info("Getting Login Page title");
		assertEquals(loginTitle, "Sales Suite"); 
		log.info("Asserting Login Title");
	}

	@Test (priority = 5)
	public void Login_To_Sales_Suite_Test() throws IOException, InterruptedException 
	{ 
		dashboard = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		log.info("Logging to the Application");
		boolean status = dashboard.verifyLoginSuccessfull();
		log.info("Verifying if logged in to application");
		Assert.assertTrue(status, "Login Unsuccessful!!!");
	}



	@Test(priority = 2 )
	public void Login_Functionality_For_Invalid_Password() 
	{

		aa = loginPage.loginFunctionalityForInvaildPassword();
		log.info("Checking logging functionality for invalid password");
		if(aa == true) 
		{
			System.out.println("Alert Displayed");
		}
		else 
		{
			System.out.println("Alert not displayed");
		}
	}

	@Test (priority = 3)
	public void Login_Functionality_For_Invalid_Username() 
	{

		aa = loginPage.loginFunctionalityForInvaildUsername();
		log.info("Checking logging functionality for invalid username");
		if(aa == true) 
		{
			System.out.println("Alert Displayed");
		}
		else 
		{
			System.out.println("Alert not displayed");
		}
	}
	
	@Test (priority = 4)
	public void Login_Functionality_For_Invalid_Username_And_Password() 
	{

		aa = loginPage.loginFunctionalityForInvaildUsernameAndPassword();
		log.info("Checking logging functionality for invalid username and password");
		if(aa == true) 
		{
			System.out.println("Alert Displayed");
		}
		else 
		{
			System.out.println("Alert not displayed");
		}
	}
	
	@Test(priority = 6)
	public void verify_alert_Text_Test() 
	{
		String alertText = loginPage.getAlertText();
		log.info("Checking alert text");
		Assert.assertEquals(alertText, "Username and password incorrect");
		
	}


	@AfterMethod
	public void tearDown(ITestResult result) 
	{
		if(ITestResult.FAILURE==result.getStatus())
		{
			try 
			{
				TakesScreenshot ts=(TakesScreenshot)driver;
				log.info("Take screenshot for Failed testcases");
				File source=ts.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(source, new File("./Screenshots/"+result.getName()+".jpg"));
				System.out.println("Screenshot taken");
			} 
			catch (Exception e)
			{
				System.out.println("Exception while taking screenshot "+e.getMessage());
			} 
		}
		
		dashboard.cleanUpDownloadFolder();
		log.info("Cleanup download folder");
		driver.quit();
	}
}

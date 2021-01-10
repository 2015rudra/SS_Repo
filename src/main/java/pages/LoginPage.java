package pages;

import java.io.IOException;

import org.apache.poi.hdgf.chunks.Chunk.Command;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import config.GlobalThings;
import testBase.TestBase;
import utility.TestUtil;

public class LoginPage extends TestBase

{
	TestUtil util;
	WebDriverWait wait;

	@FindBy(name="username")
	WebElement uname;

	
	@FindBy(name="password")
	WebElement pwd;
	
	@FindBy(xpath="//button[@class='btn btn-primary']")
	WebElement loginButton;
	
	By logoutIcon = By.xpath("//*[@role='listbox']");
	By logoutOption = By.xpath("//*[@class='menu transition visible']//a[4]");
	
	@FindBy(xpath="//div[@class='alert alert-danger']")
	WebElement loginAlert;
	
	
	private By loginPanel = By.xpath("(//div[@class='navbar-brand'])[1]");
	//private By loginAlert = By.xpath("//div[@class='alert alert-danger']");
	
	@FindBy(xpath="//div[@class='navbar-brand'])[1]")
	WebElement dashBoardStatus;
	
	static boolean status = false;
	
	public LoginPage() throws IOException 
	{
		super();
		PageFactory.initElements(driver, this);
		util = new TestUtil();
		wait = new WebDriverWait(driver, 30);
	}
	
	
	public String getPageTitle() 
	{
		return driver.getTitle();
	}
	
	public DashBoardPage login(String username, String Password) throws IOException, InterruptedException 
	{

		wait.until(ExpectedConditions.visibilityOfElementLocated (loginPanel));
		uname.clear();
		uname.sendKeys(username);
		pwd.clear();
		pwd.sendKeys(Password);
		loginButton.click();
		Thread.sleep(3000);
		util.waitForAnObject(driver, dashBoardStatus, GlobalThings.microWait);
			
		
		return new DashBoardPage();
	}
	
	public void logout() 
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated (logoutIcon));
		driver.findElement(logoutIcon).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated (logoutOption));
		driver.findElement(logoutOption).click();
	}
	
	
	public boolean loginFunctionalityForInvaildPassword() 
	{
		boolean alerPresent;
		wait.until(ExpectedConditions.visibilityOfElementLocated (loginPanel));
		uname.clear();
		uname.sendKeys("Admin");
		pwd.clear();
		pwd.sendKeys("admin1234");
		loginButton.click();
		util.waitForAnObject(driver, loginAlert, GlobalThings.microWait);
		return alerPresent = loginAlert.isDisplayed();
		
		
	}
	
	public boolean loginFunctionalityForInvaildUsername() 
	{
		boolean alerPresent;
		wait.until(ExpectedConditions.visibilityOfElementLocated (loginPanel));
		uname.clear();
		uname.sendKeys("Administrator");
		pwd.clear();
		pwd.sendKeys("admin123");
		loginButton.click();
		util.waitForAnObject(driver, loginAlert, GlobalThings.microWait);
		return alerPresent = loginAlert.isDisplayed();
		
	}
	
	public boolean loginFunctionalityForInvaildUsernameAndPassword() 
	{
		boolean alerPresent;
		wait.until(ExpectedConditions.visibilityOfElementLocated (loginPanel));
		uname.clear();
		uname.sendKeys("Administrator");
		pwd.clear();
		pwd.sendKeys("admin123456");
		loginButton.click();
		util.waitForAnObject(driver, loginAlert, GlobalThings.microWait);
		return alerPresent = loginAlert.isDisplayed();
		
	}
	
	public String getAlertText() 
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated (loginPanel));
		uname.clear();
		uname.sendKeys("Administrator");
		pwd.clear();
		pwd.sendKeys("admin123456");
		loginButton.click();
		util.waitForAnObject(driver, loginAlert, GlobalThings.microWait);
		String alertText = driver.findElement(By.xpath("//div[@class='alert alert-danger']/p")).getText().trim();
		return alertText;
	}
	
	
	
	
	
	
	

}

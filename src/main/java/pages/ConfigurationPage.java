package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.GlobalThings;
import testBase.TestBase;
import utility.TestUtil;

public class ConfigurationPage extends TestBase
{

	TestUtil util;
	WebDriverWait wait;
	DashBoardPage dashboard;
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	@FindBy(xpath="//div[contains(text(),'Configuration')]")
	WebElement configurationHeader;
	
	@FindBy(xpath="//button[text()='Export']")
	WebElement configurationExportButton;
	
	public ConfigurationPage() throws IOException 
	{
		super();
		PageFactory.initElements(driver, this);
		util = new TestUtil();
		dashboard = new DashBoardPage();
		
	}

	
	public boolean confirmConfiguration() 
	{
		TestUtil.waitForAnObject(driver, configurationHeader, GlobalThings.microWait);
		return configurationHeader.isDisplayed();
	}
	
	public boolean confirmIfConfigurationTableLoaded() 
	{
		WebElement data = driver.findElement(By.xpath("//div[@class='rt-tbody']/div"));
		TestUtil.waitForAnObject(driver, data, GlobalThings.microWait);
		return data.isDisplayed();
	}
	
	public void ExportConfiguration() throws InterruptedException 
	{
		dashboard.cleanUpDownloadFolder();
		TestUtil.waitForAnObject(driver, configurationExportButton, GlobalThings.microWait);
		configurationExportButton.click();
		Thread.sleep(7000);
	}
}

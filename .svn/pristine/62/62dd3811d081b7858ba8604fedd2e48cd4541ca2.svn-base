package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.GlobalThings;
import testBase.TestBase;
import utility.TestUtil;

public class YOYPage extends TestBase
{

	TestUtil util;
	WebDriverWait wait;
	DashBoardPage dashboard;
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	@FindBy(xpath="//div[contains(text(),'YOY')]")
	WebElement yoyHeader;
	
	@FindBy(xpath="//button[text()='Export']")
	WebElement yoyExportButton;
	
	
	@FindBy(xpath="(//div[@class='card-body'])[1]")
	WebElement FinancialYOY;
	
	@FindBy(xpath="(//div[@class='card-body'])[2]")
	WebElement FinancialMonthly;
	
	public YOYPage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
		util = new TestUtil();
		dashboard = new DashBoardPage();
	}
	
	
	public boolean confirmYOY() 
	{
		TestUtil.waitForAnObject(driver, yoyHeader, GlobalThings.microWait);
		return yoyHeader.isDisplayed();
	}
	
	public boolean confirmIfYOYTableLoaded() 
	{
		WebElement data = driver.findElement(By.xpath("//div[@class='rt-tbody']/div"));
		TestUtil.waitForAnObject(driver, data, GlobalThings.microWait);
		return data.isDisplayed();
	}
	
	public ArrayList<String> getYOY_SearchParameters() 
	{
		List<WebElement> links = driver.findElements(By.xpath("//p[@class='card-category']"));
		int cnt = links.size();
		ArrayList<String> searchNames = new ArrayList<String>();
		for(int i=1; i<= cnt; i++) 
		{
			WebElement searchText = driver.findElement(By.xpath("(//p[@class='card-category'])["+i+"]"));
			//js.executeScript("arguments[0].scrollIntoView();", searchText);
			String text = searchText.getText().trim();	
			
			searchNames.add(text);
		}
			
		return searchNames;
		
	}
	
	public ArrayList<String> getRegionDropdownValues() 
	{
		List<WebElement> links = driver.findElements(By.xpath("//p[text()='Region']//parent::div//parent::div//select//option"));
		int cnt = links.size();
		ArrayList<String> regionValues = new  ArrayList<String>();

		for (int i=1; i<=cnt; i++) 
		{
			WebElement text = driver.findElement(By.xpath("//p[text()='Region']//parent::div//parent::div//select//option["+i+"]"));
			regionValues.add(text.getText().trim());
		}

		return regionValues;
	}
	
	public ArrayList<String> getProductStatusDropdownValues() 
	{
		List<WebElement> links = driver.findElements(By.xpath("//p[text()='Product Status']//parent::div//parent::div//select//option"));
		int cnt = links.size();
		ArrayList<String> regionValues = new  ArrayList<String>();

		for (int i=1; i<=cnt; i++) 
		{
			WebElement text = driver.findElement(By.xpath("//p[text()='Product Status']//parent::div//parent::div//select//option["+i+"]"));
			//System.out.println(text.getText().trim());
			regionValues.add(text.getText().trim());
		}

		return regionValues;
	}
	
	public void ExportYOY() throws InterruptedException 
	{
		dashboard.cleanUpDownloadFolder();
		TestUtil.waitForAnObject(driver, yoyExportButton, GlobalThings.microWait);
		yoyExportButton.click();
		Thread.sleep(7000);
	}
	
	
	public Boolean getPresenceOfFinancialYOYGrpah() 
	{
		js.executeScript("arguments[0].scrollIntoView();", FinancialYOY);
		TestUtil.waitForAnObject(driver, FinancialYOY, GlobalThings.microWait);
		Boolean flag = FinancialYOY.isDisplayed();
		return flag;
		
	}
	
	public Boolean getPresenceOfFinancialMonthlyGrpah() 
	{
		js.executeScript("arguments[0].scrollIntoView();", FinancialMonthly);
		TestUtil.waitForAnObject(driver, FinancialMonthly, GlobalThings.microWait);
		Boolean flag = FinancialMonthly.isDisplayed();
		return flag;
		
	}
	
	public void clickanddownloadGraphImage_OnYOYPage() throws InterruptedException 
	{
		List<WebElement> saveImageButtons = driver.findElements(By.xpath("(//button[@data-tip='Save Image'])"));

		int cnt = saveImageButtons.size();

		for(int i=1; i<= cnt; i++) 
		{
			WebElement button = driver.findElement(By.xpath("(//button[@data-tip='Save Image'])["+i+"]"));
			js.executeScript("arguments[0].scrollIntoView();", button);
			button.click();
			Thread.sleep(4000);

		}
	}

}

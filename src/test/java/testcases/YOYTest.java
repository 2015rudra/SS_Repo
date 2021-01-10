package testcases;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.DashBoardPage;
import pages.LoginPage;
import pages.YOYPage;
import testBase.TestBase;
import utility.TestUtil;

public class YOYTest extends TestBase 
{

	LoginPage loginPage;
	DashBoardPage dashboard;
	TestUtil util;
	JavascriptExecutor js;
	YOYPage yoy;

	String TESTDATA_SHEET_PATH = "D:\\Practise\\SalesSuite_Automation\\src\\main\\java\\testdata\\TestData.xlsx";


	boolean status;
	boolean page;

	public YOYTest() throws IOException 
	{
		super();

	}

	@BeforeMethod
	public void setup() throws IOException, InterruptedException 
	{
		initilization();
		loginPage = new LoginPage();
		dashboard = new DashBoardPage();
		util = new TestUtil();
		yoy = new YOYPage();
		js = (JavascriptExecutor) driver;
		dashboard = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		status = dashboard.verifyLoginSuccessfull();
		Assert.assertTrue(status, "Login Unsuccessful");
		yoy = dashboard.navigateOnYOY();
		page = yoy.confirmYOY();
		Assert.assertTrue(page, "YOY Page not displayed properly");

	}




	@Test(priority = 1)
	public void Verify_YOY_Table_Test() 
	{
		boolean flag =yoy.confirmIfYOYTableLoaded();
		Assert.assertTrue(flag, "YOY Data table not displayed");
		log.info("Verifying if YOY data table is displayed");
	}


	@Test(priority = 2)
	public void Verify_Search_Parameters_For_YOY_Sales_Test() 
	{
		ArrayList<String> expected = TestUtil.extractExcelContentByColumnIndex(TESTDATA_SHEET_PATH, "YOY",0);
		log.info("Getting search parameters of YOY page from Excel");
		ArrayList<String> actual = yoy.getYOY_SearchParameters();
		log.info("Getting search parameters of YOY page from UI");
		Assert.assertEquals(actual, expected);
		log.info("Verifying Search Parameters for YOY Page");
	}


	@Test(priority = 3)
	public void Verify_Region_Dropdown_Values_For_YOY_Test() 
	{
		ArrayList<String> expected = TestUtil.extractExcelContentByColumnIndex(TESTDATA_SHEET_PATH, "YOY",1);
		log.info("Getting Regions dropdown values from Excel");
		ArrayList<String> actual = yoy.getRegionDropdownValues();
		log.info("Getting Regions dropdown values from UI");
		Assert.assertEquals(actual, expected);
		log.info("Verifying Region dropdown values");
	}

	@Test(priority = 4)
	public void Verify_Product_Status_Dropdown_Values_For_YOY_Test() 
	{
		ArrayList<String> expected = TestUtil.extractExcelContentByColumnIndex(TESTDATA_SHEET_PATH, "YOY",2);
		log.info("Getting Product Status dropdown values from Excel");
		ArrayList<String> actual = yoy.getProductStatusDropdownValues();
		log.info("Getting Product Status dropdown values from UI");
		Assert.assertEquals(actual, expected);
		log.info("Verifying Product Status dropdown values");
	}

	@Test(priority = 5)
	public void Export_YOY_Test() throws InterruptedException 
	{	
		yoy.ExportYOY();
		log.info("Exporting YOY Table Data");
		ArrayList<String> fileNameActual = dashboard.getFileNames();
		log.info("Getting exported file name from download folder");
		ArrayList<String> fileNameExpected = TestUtil.extractExcelContentByColumnIndex(TESTDATA_SHEET_PATH, "YOY",3);
		log.info("Getting exported file name from Excel");
		Assert.assertEquals(fileNameActual, fileNameExpected);
		log.info("Verifying file names");
		dashboard.cleanUpDownloadFolder();
		log.info("Cleaning up download folder");
	}



	@Test(priority = 6)
	public void Financial_YOY_Graph_Presence_Test() 
	{
		Boolean flag = yoy.getPresenceOfFinancialYOYGrpah();
		Assert.assertTrue(flag, "Financial YOY graph not displayed");
		log.info("Verifying Financial YOY graph presence");
	}


	@Test(priority = 7)
	public void Financial_Monthly_Graph_Presence_Test() 
	{
		Boolean flag = yoy.getPresenceOfFinancialMonthlyGrpah();
		Assert.assertTrue(flag, "Financial YOY graph not displayed");
		log.info("Verifying Financial Monthly graph presence");
	}


	@Test(priority = 8)
	public void verify_Graph_Image_Download_Test() throws InterruptedException 
	{
		dashboard.cleanUpDownloadFolder();
		log.info("Cleaning up the download folder for any existing files");
		yoy.clickanddownloadGraphImage_OnYOYPage();
		log.info("Downloading graph images in download folder");
		ArrayList<String> fileNameActual = dashboard.getFileNames();
		log.info("Getting downloaded graphs file names from folder");
		ArrayList<String> fileNameExpected = TestUtil.extractExcelContentByColumnIndex(TESTDATA_SHEET_PATH, "YOY",4);
		log.info("Getting graphs file names from Excel");
		Assert.assertEquals(fileNameActual, fileNameExpected);
		log.info("Verifying files names.");
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

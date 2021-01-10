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
import pages.ProductSalesPage;
import testBase.TestBase;
import utility.TestUtil;

public class ProductSalesTest extends TestBase
{

	LoginPage loginPage;
	DashBoardPage dashboard;
	TestUtil util;
	JavascriptExecutor js;
	ProductSalesPage productSales;

	String TESTDATA_SHEET_PATH = "D:\\Practise\\SalesSuite_Automation\\src\\main\\java\\testdata\\TestData.xlsx";
	
	
	boolean status;
	boolean page;

	public ProductSalesTest() throws IOException {
		super();
		 //TODO Auto-generated constructor stub
	}

	@BeforeMethod
	public void setup() throws IOException, InterruptedException 
	{
		initilization();
		loginPage = new LoginPage();
		dashboard = new DashBoardPage();
		util = new TestUtil();
		productSales = new ProductSalesPage();
		js = (JavascriptExecutor) driver;
		dashboard = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		status = dashboard.verifyLoginSuccessfull();
		Assert.assertTrue(status, "Login Unsuccessful");
		productSales = dashboard.navigateOnProductSales();
		page = productSales.confirmProductSales();
		Assert.assertTrue(page, "Product Sales Page not displayed properly");

	}




	@Test(priority = 1)
	public void Verify_Product_Sales_Table_Test() 
	{
		boolean flag =productSales.confirmIfProductSalesTableLoaded();
		Assert.assertTrue(flag, "Product Sales Data table not displayed");
		log.info("Verifying in Product Sales Page is loaded");
	}

	@Test(priority = 2)
	public void Verify_Search_Parameters_For_Product_Sales_Test() 
	{
		ArrayList<String> expected = TestUtil.extractExcelContentByColumnIndex(TESTDATA_SHEET_PATH, "Product_Sales",0);
		log.info("Getting Search Parameters from Excel");
		ArrayList<String> actual = productSales.getProductSales_SearchParameters();
		log.info("Getting Search Parameters from Ui");
		Assert.assertEquals(actual, expected);
		log.info("Verifying Search Parameters");
	}
	
	
	@Test(priority = 3)
	public void Verify_Region_Dropdown_Values_For_Product_Sales_Test() 
	{
		ArrayList<String> expected = TestUtil.extractExcelContentByColumnIndex(TESTDATA_SHEET_PATH, "Product_Sales",1);
		log.info("Getting Regions dropdown values from Excel");
		ArrayList<String> actual = productSales.getRegionDropdownValues();
		log.info("Getting Regions dropdown values from UI");
		Assert.assertEquals(actual, expected);
		log.info("Verifying Region dropdown values");
	}
	
	@Test(priority = 4)
	public void Verify_Country_Dropdown_Values_For_Product_Sales_Test() 
	{
		ArrayList<String> expected = TestUtil.extractExcelContentByColumnIndex(TESTDATA_SHEET_PATH, "Product_Sales",2);
		log.info("Getting Country dropdown values from Excel");
		ArrayList<String> actual = productSales.getCountryDropdownValues();
		log.info("Getting Country dropdown values from UI");
		Assert.assertEquals(actual, expected);
		log.info("Verifying Country dropdown values");
	}
	
	
	@Test(priority = 5)
	public void Verify_Profile_Center_Dropdown_Values_For_Product_Sales_Test() 
	{
		ArrayList<String> expected = TestUtil.extractExcelContentByColumnIndex(TESTDATA_SHEET_PATH, "Product_Sales",3);
		log.info("Getting Profile Center dropdown values from Excel");
		ArrayList<String> actual = productSales.getProfileCenterDropdownValues();
		log.info("Getting Profile Center dropdown values from UI");
		Assert.assertEquals(actual, expected);
		log.info("Verifying Profile Center dropdown values");
	}
	
	@Test(priority = 6)
	public void Verify_Product_Status_Dropdown_Values_For_Product_Sales_Test() 
	{
		ArrayList<String> expected = TestUtil.extractExcelContentByColumnIndex(TESTDATA_SHEET_PATH, "Product_Sales",4);
		log.info("Getting Product Status dropdown values from Excel");
		ArrayList<String> actual = productSales.getProductStatusDropdownValues();
		log.info("Getting Product Status dropdown values from UI");
		Assert.assertEquals(actual, expected);
		log.info("Verifying Product Status dropdown values");
	}
	
	@Test(priority = 7)
	public void Verify_Market_Segment_Dropdown_Values_For_Product_Sales_Test() 
	{
		ArrayList<String> expected = TestUtil.extractExcelContentByColumnIndex(TESTDATA_SHEET_PATH, "Product_Sales",5);
		log.info("Getting Market Segment dropdown values from Excel");
		ArrayList<String> actual = productSales.getMarketSegmenetDropdownValues();
		log.info("Getting Market Segment dropdown values from UI");
		Assert.assertEquals(actual, expected);
		log.info("Verifying Market Segment dropdown values");
	}

	@Test(priority = 8)
	public void Verify_Distributor_Dropdown_Values_For_Product_Sales_Test() 
	{
		ArrayList<String> expected = TestUtil.extractExcelContentByColumnIndex(TESTDATA_SHEET_PATH, "Product_Sales",6);
		log.info("Getting Distributor dropdown values from Excel");
		ArrayList<String> actual = productSales.getDistributorDropdownValues();
		log.info("Getting Distributor dropdown values from UI");
		Assert.assertEquals(actual, expected);
		log.info("Verifying Distributor dropdown values");
	}
	
	@Test(priority = 9)
	public void Verify_Product_Dropdown_Values_For_Product_Sales_Test() 
	{
		ArrayList<String> expected = TestUtil.extractExcelContentByColumnIndex(TESTDATA_SHEET_PATH, "Product_Sales",7);
		log.info("Getting Product dropdown values from Excel");
		ArrayList<String> actual = productSales.getProductDropdownValues();
		log.info("Getting Product dropdown values from UI");
		Assert.assertEquals(actual, expected);
		log.info("Verifying Product dropdown values");
	}
	
	@Test(priority = 10)
	public void Export_Product_Sales_Test() throws InterruptedException 
	{	
		productSales.ExportProductSales();
		log.info("Exporting Product Sales Table data");
		ArrayList<String> fileNameActual = dashboard.getFileNames();
		log.info("Getting exported file name in download folder");
		ArrayList<String> fileNameExpected = TestUtil.extractExcelContentByColumnIndex(TESTDATA_SHEET_PATH, "Product_Sales",8);
		log.info("Getting exported file name from Excel");
		Assert.assertEquals(fileNameActual, fileNameExpected);
		log.info("Verifying file names");
		dashboard.cleanUpDownloadFolder();
		log.info("Cleaning up download folder");
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

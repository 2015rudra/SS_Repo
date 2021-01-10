package testcases;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.DashBoardPage;
import pages.LoginPage;
import testBase.TestBase;
import utility.TestUtil;

public class DashBoardTest extends TestBase
{

	LoginPage loginPage;
	DashBoardPage dashboard;
	TestUtil util;
	JavascriptExecutor js;
	//String downloadPath = "D:\\Sales_Suite_Download";
	String TESTDATA_SHEET_PATH = "D:\\Practise\\SalesSuite_Automation\\src\\main\\java\\testdata\\TestData.xlsx";



	boolean status;

	public DashBoardTest() throws IOException {
		super();

	}



	@BeforeMethod
	public void setup() throws IOException, InterruptedException 
	{
		initilization();
		loginPage = new LoginPage();
		log.info("Initiating Login Page Object");
		dashboard = new DashBoardPage();
		log.info("Initiating Dashboard Page Object");
		util = new TestUtil();
		log.info("Initiating TestUtil Page Object");

		js = (JavascriptExecutor) driver;
		dashboard = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		log.info("Logging in to application");
		status = dashboard.verifyLoginSuccessfull();
		log.info("Verifying if login successful");
		Assert.assertTrue(status, "Login Unsuccessful");
	}


		//This test verify if all link(LHS - Links) are loading
		@Test 
		public void BIT_E70_verify_All_Links_Status_Test() throws IOException,InterruptedException 
		{ 
			dashboard.verifyAllLinksWorking();
			log.info("Verifying if all links are working");
		}
	
		@Test 
		public void Get_Search_Parameter_Test() throws IOException,InterruptedException 
		{ 
			dashboard.getSearchParameter();
			log.info("Getting all search parameters");
		}
	
	
		//This test verify search displayed as same as maintained in excel file
		@Test
		public void Verify_Search_Parameter_Test() 
		{
			ArrayList<String> parameterActual = dashboard.verifySearchParameters();
			log.info("Getting search parameters from UI");
			ArrayList<String> parameterexpected = TestUtil.extractExcelContentByColumnIndex(TESTDATA_SHEET_PATH,"Dashboard_TestData",5);
			log.info("Getting search parameters from Excel");
			Assert.assertEquals(parameterActual, parameterexpected);
	
		}
	
	
		//This test verify if Graphs are displayed
		@Test
		public void Verify_Graphs_Displayed_On_Dashboard() 
		{
	
			ArrayList<Boolean> graphPresent = dashboard.graphsOnDashboard();
			log.info("Getting graphs presence status");
			int cnt = graphPresent.size(); 
			for(int i=1;i<cnt;i++) 
			{
				Assert.assertTrue(graphPresent.get(i).booleanValue(), "Graphs not present"); 
			}
	
		}
	
		//This test verify Graph Names displayed on Dash-board
		@Test
		public void Verify_Graph_Names_on_Dashboard_Test() 
		{	
			ArrayList<String> actual = dashboard.getGraphTitle();
			log.info("Getting graph names from UI");
			ArrayList<String> expected = TestUtil.extractExcelContentByColumnIndex(TESTDATA_SHEET_PATH,"Dashboard_TestData",0);	
			log.info("Getting graph names from Excel");
			Assert.assertEquals(actual, expected);	
		}
	
	
		//This test verifies Profile Center drop-down values
		@Test
		public void Verify_Profile_Center_Values_Test() 
		{
			ArrayList<String> actual = dashboard.getProfileCenterValues();
			log.info("Getting profile center dropdown values from UI");
			ArrayList<String> expected = TestUtil.extractExcelContentByColumnIndex(TESTDATA_SHEET_PATH,"Dashboard_TestData",1);
			log.info("Getting profile center dropdown values from Excel");
			Assert.assertEquals(actual, expected);
	
		}
	
		//This test verifies Product Status drop-down values
		@Test
		public void Verify_Product_Status_Values_Test() 
		{
			ArrayList<String> actual = dashboard.getProductStatusValues();
			log.info("Getting product status dropdown values from UI");
			ArrayList<String> expected = TestUtil.extractExcelContentByColumnIndex(TESTDATA_SHEET_PATH,"Dashboard_TestData",2);
			log.info("Getting product status dropdown values from Excel");
			Assert.assertEquals(actual, expected);
	
		}
	
	
		//This test is to verify save image for every graph on Dash-board
		@Test
		public void verify_Graph_Image_Download_Test() throws InterruptedException 
		{
			dashboard.cleanUpDownloadFolder();
			log.info("Cleaning up the download folder for any existing files");
			dashboard.clickanddownloadGraphImage();
			log.info("Downloading graph images in download folder");
			ArrayList<String> fileNameActual = dashboard.getFileNames();
			log.info("Getting downloaded graphs file names from Folder");
			ArrayList<String> fileNameExpected = TestUtil.extractExcelContentByColumnIndex(TESTDATA_SHEET_PATH, "Dashboard_TestData",3);
			log.info("Getting graphs file names from Excel");
			Assert.assertEquals(fileNameActual, fileNameExpected);
			log.info("Verifying files names.");
		}
	
	
		//This test is to verify if Show Table button is displayed on every graph
		@Test
		public void Verify_Show_Table_Button_Test() throws InterruptedException 
		{
			boolean flag = false;
			dashboard.clickOnShowTableButton();
			log.info("Clicking on Show table button");
			List<WebElement> tables = driver.findElements(By.xpath("div[@class='col-11']"));
			js.executeScript("window.scrollTo(0,0)");
			int size = tables.size();
			for(int i=1;i<=size;i++) 
			{
				WebElement showTable = driver.findElement(By.xpath("(div[@class='col-11'])["+i+"]"));
				js.executeScript("arguments[0].scrollIntoView();", showTable);
				flag = showTable.isDisplayed();
				Thread.sleep(4000);
				Assert.assertTrue(flag, "Show Table not displated");
				log.info("Verifying if Show Table button is displayed.");
			}
	
		}
	
		//This test is to verify Export Table functionality on every graph
		@Test
		public void Export_Tables_Test() throws InterruptedException 
		{
			dashboard.cleanUpDownloadFolder();
			log.info("Cleaning up download folder");
			dashboard.clickonShowTableExportButton();
			log.info("Clicking on Export button on show table");
			ArrayList<String> fileNameActual = dashboard.getFileNames();
			log.info("Get filenames in download folder");
			ArrayList<String> fileNameExpected = TestUtil.extractExcelContentByColumnIndex(TESTDATA_SHEET_PATH, "Dashboard_TestData",4);
			log.info("Getting expected filenames from Excel");
			Assert.assertEquals(fileNameActual, fileNameExpected);
			log.info("Verifying files names.");
		}
	
	
		//This test is to verify: Show Table, Export and Verify Previous year and Current year total with Excel and UI for Unit By Product Graph
		@Test
		public void Units_By_Product_Verify_Previous_Current_Value_Test() throws InterruptedException 
		{
	
			dashboard.ExportUnitByProductTableData();
			log.info("Exporting Units By Product table data in download folder");
			ArrayList<String> previousYear_productExcelInString = dashboard.readProductExcelForPreviousYear();
			log.info("Getting previous year values from Excel");
			ArrayList<Integer> py_productExcelInInteger = dashboard.getIntegerArray(previousYear_productExcelInString);
			log.info("Converting String values to Integer");
			int intSumPY = (int) py_productExcelInInteger.stream().mapToLong(Integer::longValue).sum();
			log.info("Getting total value for previous year");
			js.executeScript("window.scrollTo(0,0)");
			int actualPY = dashboard.getPreviousYearValueForUnitsByProduct();
			log.info("Getting previous year value from UI for Unit By Product");
	
			Assert.assertEquals(intSumPY, actualPY);
			log.info("Verifying previous year values from Excel and UI");
	
			ArrayList<String> currentYear_productExcelInString = dashboard.readProducExcelForCurrentYear();
			log.info("Getting current year values from Excel");
			ArrayList<Integer> cy_productExcelInInteger = dashboard.getIntegerArray(currentYear_productExcelInString);
			log.info("Converting String values to Integer");
			int intSumCY = (int) cy_productExcelInInteger.stream().mapToLong(Integer::longValue).sum();
			log.info("Getting total value for current year");
			js.executeScript("window.scrollTo(0,0)");
			int actualCY = dashboard.getCurrentYearValueForUnitsByProduct();
			log.info("Getting current year value from UI for Unit By Product");
	
			Assert.assertEquals(intSumCY, actualCY);
			log.info("Verifying current year values from Excel and UI");
	
			//CleanUp The folder
			dashboard.cleanUpDownloadFolder();
			log.info("Cleaning up download folder");
		}
	
	
		//This test is to verify: Show Table, Export and Verify Previous year and Current year total with Excel and UI for Item By Description Graph
		@Test
		public void Units_By_Item_Description_Verify_Previous_Current_Value_Test() throws InterruptedException 
		{
			dashboard.ExportUnitByItemDescriptionTableData();
			log.info("Exporting Units By Item Description table data in download folder");
			ArrayList<String> previousYear_itemExcelInString = dashboard.readItemsExcelForPreviousYear();
			log.info("Getting previous year values from Excel");
			ArrayList<Integer> py_itemExcelInInteger = dashboard.getIntegerArray(previousYear_itemExcelInString);
			log.info("Converting String values to Integer");
			int intSumPY = (int) py_itemExcelInInteger.stream().mapToLong(Integer::longValue).sum();
			log.info("Getting total value for previous year");
			js.executeScript("window.scrollTo(0,0)");
			int actualPY = dashboard.getPreviousYearValueForItemByDescription();
			log.info("Getting previous year value from UI for Unit By Item Description");
	
			Assert.assertEquals(intSumPY, actualPY);
			log.info("Verifying current year values from Excel and UI");
	
			ArrayList<String> currentYear_productExcelInString = dashboard.readItemExcelForCurrentYear();
			log.info("Getting previous year values from Excel");
			ArrayList<Integer> cy_productExcelInInteger = dashboard.getIntegerArray(currentYear_productExcelInString);
			log.info("Converting String values to Integer");
			int intSumCY = (int) cy_productExcelInInteger.stream().mapToLong(Integer::longValue).sum();
			log.info("Getting total value for current year");
			js.executeScript("window.scrollTo(0,0)");
			int actualCY = dashboard.getCurrentYearValueForItemByDescription();
			log.info("Getting current year value from UI for Unit By Item Description");
	
			Assert.assertEquals(intSumCY, actualCY);
			log.info("Verifying current year values from Excel and UI");
	
			//CleanUp The folder
			dashboard.cleanUpDownloadFolder();
			log.info("Cleaning up download folder");
		}

		//This test is to verify: Show Table, Export and Verify Units total with Excel and UI for Units By Consumables Graph
		@Test
		public void Units_By_Consumables_Verify_Units_Test() throws InterruptedException 
		{
			dashboard.ExportUnitsByConsumablesTableData();
			log.info("Exporting Units By Consumables table data in download folder");
			ArrayList<String> unitsInString = dashboard.readUnitsExcelForConsumables();
			log.info("Getting Units value from Excel");
			ArrayList<Integer> unitsInInteger = dashboard.getIntegerArray(unitsInString);
			log.info("Converting String values to Integer");
			int Units = (int) unitsInInteger.stream().mapToLong(Integer::longValue).sum();
			log.info("Getting total value for Units");
			js.executeScript("window.scrollTo(0,0)");
			int actualPY = dashboard.getUnitsForItemByConsumables();
			log.info("Getting Units value from UI for Unit By Consumables");
	
			Assert.assertEquals(Units, actualPY);
			log.info("Verifying Units values from Excel and UI");
	
			//CleanUp The folder
			dashboard.cleanUpDownloadFolder();
			log.info("Cleaning up download folder");
		}


		//This test is to verify: Show Table, Export and Verify Units total with Excel and UI for Units By Distributor Graph
			@Test
			public void Units_By_Distributor_Verify_Units_Test() throws InterruptedException 
			{
				dashboard.ExportUnitsByDistributorTableData();
				log.info("Exporting Units By Distributor table data in download folder");
				ArrayList<String> unitsInString = dashboard.readUnitsExcelForDistributor();
				log.info("Getting Units value from Excel");
				ArrayList<Integer> unitsInInteger = dashboard.getIntegerArray(unitsInString);
				log.info("Converting String values to Integer");
				int Units = (int) unitsInInteger.stream().mapToLong(Integer::longValue).sum();
				log.info("Getting total value for Units");
				js.executeScript("window.scrollTo(0,0)");
				int actualPY = dashboard.getUnitsForItemByDistributor();
				log.info("Getting Units value from UI for Unit By Distributor");
	
				Assert.assertEquals(Units, actualPY);
				log.info("Verifying Units values from Excel and UI");
	
				//CleanUp The folder
				dashboard.cleanUpDownloadFolder();
				log.info("Cleaning up download folder");
			}

	//This test is to verify: Show Table, Export and Verify Units total with Excel and UI for Units By Distributor Graph
	@Test public void Units_By_Countries_Verify_Units_Test() throws InterruptedException 
	{
		dashboard.ExportUnitsByCountryTableData();
		log.info("Exporting Units By Country table data in download folder");
		ArrayList<String> unitsInString = dashboard.readUnitsExcelForCountry();
		log.info("Getting Units value from Excel");
		ArrayList<Integer> unitsInInteger = dashboard.getIntegerArray(unitsInString);
		log.info("Converting String values to Integer");
		int Units = (int) unitsInInteger.stream().mapToLong(Integer::longValue).sum();
		log.info("Getting total value for Units");
		js.executeScript("window.scrollTo(0,0)");
		int actualPY = dashboard.getUnitsForItemByCountry();
		log.info("Getting Units value from UI for Unit By Country");

		Assert.assertEquals(Units, actualPY);
		log.info("Verifying Units values from Excel and UI");

		//CleanUp The folder
		dashboard.cleanUpDownloadFolder();
		log.info("Cleaning up download folder");
	}

	
	
//	//This test is to verify: Show Table, Export and Verify Units total with Excel and UI for Units By Region Graph
//	@Test public void Units_By_Region_Verify_Units_Test() throws InterruptedException 
//	{
//		dashboard.ExportUnitsByRegionTableData();
//		log.info("Exporting Units By Region table data in download folder");
//		ArrayList<String> unitsInString = dashboard.readUnitsExcelForRegion();
//		log.info("Getting Units value from Excel");
//		ArrayList<Integer> unitsInInteger = dashboard.getIntegerArray(unitsInString);
//		log.info("Converting String values to Integer");
//		int Units = (int) unitsInInteger.stream().mapToLong(Integer::longValue).sum();
//		log.info("Getting total value for Units");
//		js.executeScript("window.scrollTo(0,0)");
//		int actualPY = dashboard.getUnitsForItemByCountry();
//		log.info("Getting Units value from UI for Unit By Region");
//
//		Assert.assertEquals(Units, actualPY);
//		log.info("Verifying Units values from Excel and UI");
//
//		//CleanUp The folder
//		dashboard.cleanUpDownloadFolder();
//		log.info("Cleaning up download folder");
//	}
//	
//	//This test is to verify: Show Table, Export and Verify Units total with Excel and UI for Units By Unit Status Graph
//	@Test public void Units_By_Unit_Status_Verify_Units_Test() throws InterruptedException 
//	{
//		dashboard.ExportUnitsByUnitStatusTableData();
//		log.info("Exporting Units By Unit Status table data in download folder");
//		ArrayList<String> unitsInString = dashboard.readUnitsExcelForUnitStatus();
//		log.info("Getting Units value from Excel");
//		ArrayList<Integer> unitsInInteger = dashboard.getIntegerArray(unitsInString);
//		log.info("Converting String values to Integer");
//		int Units = (int) unitsInInteger.stream().mapToLong(Integer::longValue).sum();
//		log.info("Getting total value for Units");
//		js.executeScript("window.scrollTo(0,0)");
//		int actualPY = dashboard.getUnitsForItemByCountry();
//		log.info("Getting Units value from UI for Unit By Unit Status");
//
//		Assert.assertEquals(Units, actualPY);
//		log.info("Verifying Units values from Excel and UI");
//
//		//CleanUp The folder
//		dashboard.cleanUpDownloadFolder();
//		log.info("Cleaning up download folder");
//	}
	
	//This test is to verify: Show Table, Export and Verify Units total with Excel and UI for Units By Market Segment Graph
	@Test public void Units_By_Market_Segment_Verify_Units_Test() throws InterruptedException 
	{
		dashboard.ExportUnitsByMarketSegmentTableData();
		log.info("Exporting Units By Market Segment table data in download folder");
		ArrayList<String> unitsInString = dashboard.readUnitsExcelForMarketSegment();
		log.info("Getting Units value from Excel");
		ArrayList<Integer> unitsInInteger = dashboard.getIntegerArray(unitsInString);
		log.info("Converting String values to Integer");
		int Units = (int) unitsInInteger.stream().mapToLong(Integer::longValue).sum();
		log.info("Getting total value for Units");
		js.executeScript("window.scrollTo(0,0)");
		int actualPY = dashboard.getUnitsForItemByMarketSegment();
		log.info("Getting Units value from UI for Unit By Market Segment");

		Assert.assertEquals(Units, actualPY);
		log.info("Verifying Units values from Excel and UI");

		//CleanUp The folder
		dashboard.cleanUpDownloadFolder();
		log.info("Cleaning up download folder");
	}
	
	//This test is to verify: Show Table, Export and Verify Units total with Excel and UI for Units By Application Segment Graph
	@Test public void Units_By_Application_Segment_Verify_Units_Test() throws InterruptedException 
	{
		dashboard.ExportUnitsByApplicationSegmentTableData();
		log.info("Exporting Units By Application Segment table data in download folder");
		ArrayList<String> unitsInString = dashboard.readUnitsExcelForApplicationSegment();
		log.info("Getting Units value from Excel");
		ArrayList<Integer> unitsInInteger = dashboard.getIntegerArray(unitsInString);
		log.info("Converting String values to Integer");
		int Units = (int) unitsInInteger.stream().mapToLong(Integer::longValue).sum();
		log.info("Getting total value for Units");
		js.executeScript("window.scrollTo(0,0)");
		int actualPY = dashboard.getUnitsForItemByApplicationSegment();
		log.info("Getting Units value from UI for Unit By Application Segment");

		Assert.assertEquals(Units, actualPY);
		log.info("Verifying Units values from Excel and UI");

		//CleanUp The folder
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

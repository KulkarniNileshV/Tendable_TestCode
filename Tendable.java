package com.demo;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import Utility.ConfigReader;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.testng.annotations.BeforeSuite;

public class Tendable extends ConfigReader {
//	WebDriver driver;
	ConfigReader config;
	Properties prop;
	FileInputStream fis;
	Document doc;
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest extentTest;
	static WebDriver driver;
	
	@BeforeSuite
	public void beforeSuite() throws DocumentException 
	{
		readFromXml("Tendable.xml");
	}
	
	@BeforeClass
	@Parameters("browser")
	public void openApplication(String browser) throws Exception 
	{
		    System.out.println("Execution started ");
			config = new ConfigReader();
			
			if(browser.equalsIgnoreCase("Chrome"))
			{
			  //System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true");
			  System.setProperty("webdriver.chrome.silentOutput","true");
			  System.setProperty("webdriver.chrome.driver", config.getChromePath());
			  driver = new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("FireFox"))
			{
			 // System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"./FFLogs.txt");
			  System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"null");	
			  System.setProperty("webdriver.gecko.driver", config.getFireFoxPath());
			  driver = new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("Edge"))
			{
			  System.setProperty(EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY,"true");
			  System.setProperty("webdriver.edge.driver", config.getMSEdgePath());
			  driver = new EdgeDriver();
			}
			
			driver.get(config.getApplicationUrl());
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	  
	}
	
	/*@BeforeClass
	public void beforeClass() {
		htmlReporter = new ExtentHtmlReporter("./reports/extent.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Reports");
		htmlReporter.config().setReportName("Automation Test Results");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();	
		extent.setSystemInfo("Organization", "Webmatrix");
		extent.setSystemInfo("Browser", "Chrome");
		extent.attachReporter(htmlReporter);
			
	} */
	
	
	@Test
	public void Tendable() throws IOException, InterruptedException 
	{
		List<Map<String,String>> testDataInMap=DriverScript.getMapTestInData();
		System.out.println(testDataInMap.get(0).get("TestCaseDescription"));
		String tcname = testDataInMap.get(0).get("TestCaseDescription");
		String testcaseid = testDataInMap.get(0).get("TestCaseId");
		try
		{
			
			// Accessibility of top menus
			
			List<WebElement> allLinks = driver.findElements(By.tagName("a"));

			for(WebElement specificlink : allLinks ) {

			if(driver.findElement(By.xpath(retrieveXmlValue(TendableElement,HomeElement)).getText().equals("Our Story ")
			{

			System.out.println("Our Story Link is clickable");

			//Code

			}
			
			if(driver.findElement(By.xpath(retrieveXmlValue(TendableElement,OurStoryElement)).getText().equals("Our Story ")
			 {

					System.out.println("Our Story Link is clickable");

					
			//code

			 }
			
			
            //"Request a demo button is present
			
			}
			if(driver.findElement(By.xpath(retrieveXmlValue(TendableElement,RequestADemoElement))).isDisplayed())
			
			{
				driver.findElement(By.xpath(retrieveXmlValue(TendableElement,RequestADemoElement))).click();
			}

			else
			    System.out.println("Button is not more or clickable");
			
			
			
			// Click on contact us and select marketing , complete the form.
			
			driver.findElement(By.className(//a[@class = 'button inverted is-active'])).click();
			driver.findElement(By.linkText(" Marketing")).click();		
			
			// filling input form
			
			driver.findElement(By.id("form-input-fullName")).sendKeys("Nilesh");
			
			// and same for other field except message field
			
			
			//expected error text
			
		      String exp = "Sorry, there was an error submitting the form. Please try again.";
		      
		      //identify actual error message
		      
		      WebElement m = driver.findElement(By.className("ff-form-errors"));
		      
		      String act = m.getText();
		      
		      System.out.println("Error message is: "+ act);
		      
		      //verify error message with Assertion
		      Assert.assertEquals(exp, act);
					
					
			
		//driver.findElement(By.xpath("//*[@id='sign-in-btn']")).click();  
		
		Thread.sleep(2000);
		ExtentTestManager.getTest().log(Status.PASS, "Successfully Clicked On SignIn");
		}
		catch(NoSuchElementException e)
		{
			ExtentTestManager.getTest().log(Status.FAIL, "Not Clicked On SignIn");
		}
	}
	
	
	
/*	@AfterMethod
	public void afterMethod(ITestResult result) {
		String methodname=result.getMethod().getMethodName();
		if(result.getStatus()==ITestResult.FAILURE)
		{
			String exceptionmessage=Arrays.toString(result.getThrowable().getStackTrace());
			extentTest.fail("<details><summary><b><font color=red>Exception Occured,click to see detials:"
					+"</font></b></summary>"+exceptionmessage.replaceAll(",", "<br>")+"</details> \n");
			String path=takeScreenShot(result.getMethod().getMethodName());
			try
			{
				extentTest.fail("<b><font color=red>" + "Screenshot of failure"+"</font></b>",
						MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			}
			catch(Exception e)
			{
				extentTest.fail("Test Failed,can not attach screen shot");
			}
			String logtext = "<b>Test Method" + methodname +"Failed</b>";
			Markup m=MarkupHelper.createLabel(logtext, ExtentColor.RED);
			extentTest.log(Status.FAIL, m);
			
		} else if (result.getStatus()==ITestResult.SUCCESS ) {
			String logtext = "<b>Test Method" + methodname +"Successful</b>";
			Markup m=MarkupHelper.createLabel(logtext, ExtentColor.GREEN);
			extentTest.log(Status.PASS, m);
		}else if (result.getStatus()==ITestResult.SKIP ) {
			String logtext = "<b>Test Method" + methodname +"Skipped</b>";
			Markup m=MarkupHelper.createLabel(logtext, ExtentColor.YELLOW);
			extentTest.log(Status.SKIP, m);
		}
	}
	
	public static String takeScreenShot(String methodname) {
		String filename = getScreenShotName(methodname);
		String directory = System.getProperty("user.dir")+"/screenshots/";
		new File(directory).mkdirs();
		String path = directory + filename;
		try
		{
			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(path));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		 return path;
	}
	
	public static String getScreenShotName(String methodname) {
		 Date d = new Date();
		 String filename = methodname+"_"+d.toString().replace(":", "_").replace(" ","_")+".png";
		 return filename;
	} */
	

	
	  
	@AfterClass
	public void afterClass() {
		System.out.println("Execution Completed ");
		driver.quit();
	//	extent.flush();
	} 
	
//	@AfterTest
//	public void tearDown() {
//		System.out.println("Execution Completed ");
//		driver.quit();
//	}

}

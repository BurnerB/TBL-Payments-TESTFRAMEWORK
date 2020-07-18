package StepDefinitions;


import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.BaseClass;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;



@RunWith(Cucumber.class)
public class stepDefinitions extends BaseClass{
	public Properties Pro;
	public static WebDriver driver;
	
	public static sharedatastep sharedata;
	
	public stepDefinitions (sharedatastep sharedata) {
		
		stepDefinitions.sharedata=sharedata;
		
	}
	
	@Before(order=2)
	public void method1()throws Exception
	{
		Pro = new Properties();
		FileInputStream fls = new FileInputStream("src\\test\\resources\\global.properties");
		Pro.load(fls);
		
	}

    @Given("^User navigates to the login page$")
    public void user_navigates_to_the_login_page() throws Throwable {
    	driver = BaseClass.getDriver();
    	driver.get("http://18.202.88.7:8001/trips-ui/faces/login/tripsLogin.xhtml");
    }
    
    @When("^Enters the username \"([^\"]*)\" and password \"([^\"]*)\" to login$")
    public void enters_the_username_something_and_password_something_to_login(String strArg1, String strArg2) throws Throwable {
    	driver.findElement(By.id("loginForm:username")).sendKeys(strArg1);
    	driver.findElement(By.id("loginForm:password")).sendKeys(strArg2);
    	driver.findElement(By.xpath("//*[@id=\"loginForm:j_idt19\"]/span")).click();
    }
    
    @Then("^User should be logged in$")
    public void user_should_be_logged_in() throws Throwable {
    	String URL = driver.getCurrentUrl();
    	Assert.assertEquals(URL, "http://18.202.88.7:8001/trips-ui/faces/login/Welcome.xhtml" );
    }
    
    @Then("^User logs out successfully$")
    public void user_logs_out_successfully() throws Throwable {
    	driver.findElement(By.id("Logout")).click();
    }
    
////----------------------------------------[SUC:05-15] Open a Cash Office---------------------------------------------------------------------------------------------///
    @Given("^navigate to Revenue Collection>>Cash Office Daily Control$")
    public void navigate_to_revenue_collectioncash_office_daily_control() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("RevenueCollection_RevenueCollection_XPATH"))).click();
        driver.findElement(By.xpath(Pro.getProperty("RevenueCollection_CashOfficeDailyControl_XPATH"))).click();
        Thread.sleep(2000);
    }
    
    @When("^the User clicks Cash Office Name$")
    public void the_user_clicks_cash_office_name() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("cashOffice_NameDropdown_Xpath"))).click();
        Thread.sleep(2000);
        driver.findElement(By.id("CashOfficeDailyControlform:CashOfficeName_2")).click();
        
        Thread.sleep(4000);
        
    }

    @And("^clicks Open Cash Office$")
    public void clicks_open_cash_office() throws Throwable {
    	// checks if office is already open and reconciles to enable it to be opened again
    	Boolean openCashofficeButton = driver.findElement(By.xpath("//*[@id='CashOfficeDailyControlform:btnOpenCashOffice']")).isEnabled();

        if (openCashofficeButton) {
            driver.findElement(By.xpath("//*[@id='CashOfficeDailyControlform:btnOpenCashOffice']")).click();	
        }else
        driver.findElement(By.xpath("//*[@id=\"CashOfficeDailyControlform:btnReconcileCashOffice\"]")).click();
        Thread.sleep(9000);
        driver.findElement(By.xpath("//*[@id=\"CashOfficeDailyControlform:btnOpenCashOffice\"]/span")).click();
    }
    
    @Then("^System opens the Cash Office$")
    public void system_opens_the_cash_office() throws Throwable {
    	Thread.sleep(4000);
    	WebDriverWait wait=new WebDriverWait(driver, 30);
    	WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-messages-info-summary")));
    	Assert.assertEquals(success.getText(), "Cash Office Opened Successfully");	
    }
    	
///--------------------------UAT_M7_02-02-Verify the Process of Allocate Cash Till---------------------------------------------------------------------////
    
    @Given("^navigate to Revenue Collection>>Cash Till Maintenance$")
    public void navigate_to_revenue_collectioncash_till_maintenance() throws Throwable {
    	driver.findElement(By.xpath(Pro.getProperty("revenueCollection_Xpath"))).click();
        driver.findElement(By.xpath(Pro.getProperty("cashtillMaintenance_Xpath"))).click();
        Thread.sleep(4000);
    }
    
    @When("^clicks on Request Cash Till button$")
    public void clicks_on_request_cash_till_button() throws Throwable {
    	driver.findElement(By.id(Pro.getProperty("requestCashTill_id"))).click();
    }
    
    @Then("^success message displayed$")
    public void success_message_displayed() throws Throwable {
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	
    	WebElement Message = driver.findElement(By.xpath("//span[contains(text(),'Processing Completed')]"));
    	if(Message.isDisplayed()) {
    		Assert.assertTrue("Success message displayed",true);
    	}else {
    		Assert.fail("No Success message displayed");
    	}
    	String Text=Message.getText();
    	String ARN = Text.substring(Text.lastIndexOf(" ")+1);
    	sharedatastep.P_CRMARN=ARN;
    	System.out.print(sharedatastep.P_CRMARN);
    	System.out.print("Reference Number is -" + sharedatastep.P_CRMARN);
    }
    
    @Given("^Open CRM URL Module$")
    public void open_CRM_URL_Module() throws Throwable {
    	driver = BaseClass.getDriver();
    	driver.get(Pro.getProperty("MRA_crm_url_Registration"));
    }

    @When("^Close Popup Window$")
    public void close_Popup_Window() throws Throwable {
    	
    	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    	WebElement  specificframe= (driver.findElement(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame__ID"))));
    	  driver.switchTo().frame(specificframe);
    	WebDriverWait CloseWindow=new WebDriverWait(driver,60);
    	CloseWindow.until(ExpectedConditions.elementToBeClickable(By.id(Pro.getProperty("CRM_ExploreCrmWindow_Frame_Close_ID")))).click();
    }
    
    @And("^Click on Case management dropdown$")
    public void click_on_case_management_dropdown() throws Throwable {
    	driver.findElement(By.xpath("//*[@id=\"TabCS\"]/a/span")).click();
    }
    
    @And("^click on Revenue Collection application$")
    public void click_on_revenue_collection_application() throws Throwable {
    	driver.findElement(By.xpath("//*[@id=\"tbg_revenuecollectionapplication\"]")).click();
    }
    
    @Then("^switch to frame$")
    public void switch_to_frame() throws Throwable {
    	driver.switchTo().defaultContent();
    	WebDriverWait wait=new WebDriverWait(driver, 30);
    	WebElement specificframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Pro.getProperty("NextStage_Frame_ID"))));
    	  driver.switchTo().frame(specificframe);
    	  Thread.sleep(3000);
    	 
    }
    
    @When("^enters reference number in search results$")
    public void enters_in_search_results() throws Throwable {
    	WebDriverWait wait=new WebDriverWait(driver, 20);
    	WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("crmGrid_findCriteria")));
    	search.sendKeys(sharedatastep.P_CRMARN);
//    	search.sendKeys("CT00001143");
    	search.sendKeys(Keys.ENTER);
    	
    	Thread.sleep(2000);
    }
    
    @Then("^Click selected Reference Number$")
    public void click_selected_Reference_Number() throws Throwable {
    	WebElement elementLocator = driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_Select_ReffNo_XPATH")));

    	Actions actions = new Actions(driver);
    	actions.doubleClick(elementLocator).perform();
    	
    	driver.switchTo().defaultContent();
    	Thread.sleep(9000);
    }
    
    @And("^clicks Approve from the dropdown$")
    public void clicks_Approve_from_the_dropdown() throws Throwable {
    	driver.switchTo().frame("contentIFrame1");
    	Thread.sleep(9000);
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    	Actions action=new Actions(driver);
    	WebElement Outcome=driver.findElement(By.id(Pro.getProperty("Taxpayer_Accounting_Approval_Outcome_ID")));
    	WebElement hasLoaded= driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));
    			
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	Thread.sleep(7000);
    	if(hasLoaded.isDisplayed()) {
    		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    		Thread.sleep(5000);
    	}else {
    		action.doubleClick(Outcome).build().perform();
        	Outcome.click();
        	action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    	}
    	
    }
    
    @And("^click save on Payments$")
    public void click_save_on_Payments() throws Throwable {
    	driver.switchTo().defaultContent();
    	Thread.sleep(2000);
    	driver.findElement(By.id("tbg_revenuecollectionapplication|NoRelationship|Form|Mscrm.Form.tbg_revenuecollectionapplication.Save")).click();
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }
    
    @Then("^Application Account Adjustment status should be \"([^\"]*)\"$")
    public void application_account_adjustment_status_should_be_something(String Status) throws Throwable {
    	driver.switchTo().frame("contentIFrame1");
    	WebDriverWait wait = new WebDriverWait(driver,30);
    	Thread.sleep(3000);
    	String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Status_label\"]"))).getText();
        if(text.contains(Status))
       	{

       		System.out.println("Text Verified and"+Status);
       	}
         else
            {
                 System.out.println("Text Not Verfied and failed");
             }
         Thread.sleep(2000);
    }
    @When("^selects the ref number$")
    public void selects_the_ref_number() throws Throwable {
        WebElement cashTillReferenceDropdown=driver.findElement(By.xpath("//*[@id=\"CashTillMaintenance:TillReference\"]/div[3]"));
        cashTillReferenceDropdown.click();
        Thread.sleep(2000);
        
        //click on the ref number of the specific cash till on the dropdown
//        WebElement refNumber =driver.findElement(By.xpath("//option[@value='"+ sharedatastep.P_CRMARN +"']"));
        
        WebElement refNumber =driver.findElement(By.xpath("//option[@value='"+ sharedatastep.P_CRMARN +"']"));
        Thread.sleep(2000);
        refNumber.click();
        driver.findElement(By.xpath("//html")).click();
        
    }
    
    @And("^enters float amount (.+)$")
    public void enters_float_amount(String amount) throws Throwable {
    	Thread.sleep(4000);
        WebElement floatAmount = driver.findElement(By.id("CashTillMaintenance:FloatAmount_input"));
        floatAmount.sendKeys(amount);
    }
    
    @And("^clicks Save$")
    public void clicks_save() throws Throwable {
    	WebElement CashTillMaintenance_Save=driver.findElement(By.id("CashTillMaintenance:save"));
    	CashTillMaintenance_Save.click();
    	
    	Thread.sleep(4000);
    }
    
    @Then("^Cash Till is now open$")
    public void cash_till_is_now_open() throws Throwable {
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	
    	WebElement Message = driver.findElement(By.xpath("//span[contains(text(),'Cash Till is now open')]"));
    	if(Message.isDisplayed()) {
    		Assert.assertTrue("Success message displayed",true);
    	}else {
    		Assert.fail("No Success message displayed");
    	}
    }
    
    //Cash Till is then suspended to allow for creation of other Cash Tills
    //Delete this if no other cash till is to be created further down
    @Then("^Suspend CashTill$")
    public void suspend_cashtill() throws Throwable {
    	Thread.sleep(5000);
    	WebElement suspendCashTill=driver.findElement(By.id("CashTillMaintenance:btnSuspendCashtill"));
    	suspendCashTill.click();
        
    	Thread.sleep(4000);
    }
    
    @Then("^Cash Till is now suspended$")
    public void cash_till_is_now_suspended() throws Throwable {
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	
    	WebElement Message = driver.findElement(By.xpath("//span[contains(text(),'Cash Till suspended.')]"));
    	if(Message.isDisplayed()) {
    		Assert.assertTrue("Success message displayed",true);
    	}else {
    		Assert.fail("No Success message displayed");
    	}
    }
    
}




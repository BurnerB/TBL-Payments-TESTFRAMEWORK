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
//    	intergtation link for backoffice
    	driver.get("http://18.202.88.7:8001/trips-ui/faces/login/tripsLogin.xhtml");

        //    	SIT link for backoffice
//    	driver.get("https://backoffice.mra.mw:8443/trips-ui/faces/login/tripsLogin.xhtml");
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
//    	Assert.assertEquals(URL, "https://backoffice.mra.mw:8443/trips-ui/faces/login/Welcome.xhtml" );
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
        Thread.sleep(9000);
    }
    
    @When("^the User clicks Cash Office Name$")
    public void the_user_clicks_cash_office_name() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("cashOffice_NameDropdown_Xpath"))).click();
        Thread.sleep(2000);
        driver.findElement(By.id("CashOfficeDailyControlform:CashOfficeName_8")).click();
        
        Thread.sleep(4000);
        
    }

    @And("^clicks Open Cash Office$")
    public void clicks_open_cash_office() throws Throwable {
    	// checks if office is already open and reconciles to enable it to be opened again
    	Boolean openCashofficeButton = driver.findElement(By.xpath("//*[@id='CashOfficeDailyControlform:btnOpenCashOffice']")).isEnabled();

        if (openCashofficeButton) {
            driver.findElement(By.xpath("//*[@id='CashOfficeDailyControlform:btnOpenCashOffice']")).click();	
        }
        Thread.sleep(4000);
        driver.findElement(By.id("CashOfficeDailyControlform:btnReconcileCashOffice")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("CashOfficeDailyControlform:btnOpenCashOffice")).click();
    }
    
    @Then("^System opens the Cash Office$")
    public void system_opens_the_cash_office() throws Throwable {
    	Thread.sleep(4000);
    	WebDriverWait wait=new WebDriverWait(driver, 30);
    	WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-messages-info-summary")));
    	Assert.assertEquals(success.getText(), "Cash Office Opened Successfully");
    
    }
    
    @Then("^System reconciles the Cash Office$")
    public void system_reconciles_the_cash_office() throws Throwable {
    	Thread.sleep(4000);
    	WebDriverWait wait=new WebDriverWait(driver, 30);
    	WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-messages-info-summary")));
    	Assert.assertEquals(success.getText(), "Cash Office Reconciled Successfully");
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
    	System.out.print("Reference Number is - " + sharedatastep.P_CRMARN);
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
//    	search.sendKeys("CT00001741");
    	search.sendKeys(Keys.ENTER);
    	
    	Thread.sleep(2000);
    }
    
    @Then("^Click selected Reference Number$")
    public void click_selected_Reference_Number() throws Throwable {
    	WebElement elementLocator = driver.findElement(By.xpath(Pro.getProperty("CaseManagement_Queue_Select_ReffNo_XPATH")));

    	Actions actions = new Actions(driver);
    	actions.doubleClick(elementLocator).perform();
    	
    	driver.switchTo().defaultContent();
    	Thread.sleep(4000);
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
    	String text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='Status_label']"))).getText();
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
    
    @When("^enters approved ref number$")
    public void enters_approved_ref_number() throws Throwable {
    	
    	WebElement cashTillReferenceDropdown=driver.findElement(By.xpath("//*[@id=\"CashTillMaintenance:TillReference\"]/div[3]"));
    	cashTillReferenceDropdown.click();
    	Thread.sleep(2000);

    	//click on the ref number of the specific cash till on the dropdown
    	WebElement refNumber =driver.findElement(By.xpath("//li[@data-label='"+sharedatastep.P_CRMARN+"']"));

//        WebElement refNumber =driver.findElement(By.xpath("//li[@data-label='CT00001801']"));
    	Thread.sleep(2000);
    	refNumber.click();
    }
    
    @When("^selects ref number$")
    public void selects_ref_number() throws Throwable {
        WebElement cashTillReferenceDropdown=driver.findElement(By.xpath("//*[@id=\"CashTillMaintenance:TillReference\"]/div[3]"));
        cashTillReferenceDropdown.click();
        Thread.sleep(2000);
        
        //click on the ref number of the specific cash till on the dropdown
        WebElement refNumber =driver.findElement(By.xpath("CashTillMaintenance:TillReference_1"));
        Thread.sleep(2000);
        refNumber.click();
        
    }
    
    @And("^enters float amount (.+)$")
    public void enters_float_amount(String amount) throws Throwable {
    	Thread.sleep(4000);
        WebElement floatAmount = driver.findElement(By.id("CashTillMaintenance:FloatAmount_input"));
        floatAmount.sendKeys(amount);
        Thread.sleep(4000);
    }
    
    @And("^clicks Save on cash till mainenance$")
    public void clicks_Save_on_cash_till_mainenance() throws Throwable {
    	Thread.sleep(4000);
    	driver.findElement(By.id("CashTillMaintenance:save")).click();    	
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
    
    @And("^clicks Decline from the dropdown$")
    public void clicks_Decline_from_the_dropdown() throws Throwable {
    	driver.switchTo().frame("contentIFrame1");
    	Thread.sleep(10000);

    	Actions action=new Actions(driver);
    	WebElement Outcome=driver.findElement(By.id(Pro.getProperty("Taxpayer_Accounting_Approval_Outcome_ID")));
    	WebElement hasLoaded= driver.findElement(By.id("header_process_tbg_approvaloutcome_lock"));
    			
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	Thread.sleep(7000);
    	if(hasLoaded.isDisplayed()) {
    		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	}else {
    		action.doubleClick(Outcome).build().perform();
        	Outcome.click();
        	action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
    	}
    }
    
    @Then("^Enter Outcome Notes (.+)$")
    public void enter_outcome_notes(String Notes) throws Throwable {
    	Thread.sleep(3000);
    	Actions action1 = new Actions(driver);
	     WebElement element1=driver.findElement(By.id((Pro.getProperty("Individual_NextStage_RefNum_Reject_OutComeNotes_ID"))));
	     action1.sendKeys(element1, Notes).build().perform();
	     Thread.sleep(5000);
    }
    
    @Then("^Enter Outcome Reason for Taxpayer accounting$")
    public void enter_Outcome_Reason_for_Taxpayer_accounting() throws Throwable {
    	WebElement specificframe=driver.findElement(By.id("WebResource_RevenueCollectionRejectionDataWebResource"));
    	driver.switchTo().frame(specificframe);
    	WebElement dropDown = driver.findElement(By.xpath("//*[@id=\"viewoption\"]"));

    	dropDown.click();
    	
    	driver.findElement(By.xpath("//option[@value='2']")).click();

    }
    
    @Then("^cashTill status should be (.+)$")
    public void cashtill_status_should_be(String status) throws Throwable {
    	Thread.sleep(4000);
    	WebElement cashTillStatus = driver.findElement(By.id("CashTillMaintenance:TillStatus_label"));

        Assert.assertEquals(status, cashTillStatus.getText());
    }
    
    @Then("^error message displayed$")
    public void error_message_displayed() throws Throwable {
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	
    	WebElement Message = driver.findElement(By.xpath("//span[contains(text(),'Float Amount - A value is required')]"));
    	if(Message.isDisplayed()) {
    		Assert.assertTrue("Error message displayed",true);
    	}else {
    		Assert.fail("No Error message displayed");
    	}
    }
    
    @When("^selects the (.+)$")
    public void selects_the(String refnumber) throws Throwable {
    	WebElement cashTillReferenceDropdown=driver.findElement(By.xpath("//*[@id=\"CashTillMaintenance:TillReference\"]/div[3]"));
        cashTillReferenceDropdown.click();
        Thread.sleep(2000);
        sharedatastep.P_CRMARN=refnumber;
        //click on the ref number of the specific cash till on the dropdown
        WebElement refrenceNumber =driver.findElement(By.xpath("//li[@data-label='"+refnumber+"']"));
        
        Thread.sleep(2000);
        refrenceNumber.click();
//        driver.findElement(By.xpath("//html")).click();
        Thread.sleep(5000);
    }
    
    @And("^clicks on Open Cash Till button$")
    public void clicks_on_open_cash_till_button() throws Throwable {
    	Thread.sleep(4000);
    	driver.findElement(By.id(Pro.getProperty("openCashTill_id"))).click();
    }
    @Then("^successfuly awaits approval$")
    public void successfuly_awaits_approval() throws Throwable {
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	
    	WebElement Message = driver.findElement(By.xpath("//span[contains(text(),'Request has been sent successfully for approval')]"));
    	if(Message.isDisplayed()) {
    		Assert.assertTrue("Success message displayed",true);
    	}else {
    		Assert.fail("No Success message displayed");
    	}
    }
    
    @And("^enters payment collection (.+)$")
    public void enters_payment_collection(String payment) throws Throwable {
        WebElement paymentCollectionInput = driver.findElement(By.id("CashTillMaintenance:PaymentCollected_input"));
//        paymentCollectionInput.clear();
        paymentCollectionInput.sendKeys(payment);
    }
    
    @And("^clicks on close Cash Till button$")
    public void clicks_on_close_cash_till_button() throws Throwable {
    	Thread.sleep(4000);
    	driver.findElement(By.id(Pro.getProperty("closeCashTill_id"))).click();
    	Thread.sleep(3000);
    }
    
    @Then("^prompt to click save appears$")
    public void prompt_to_click_save_appears() throws Throwable {
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	
    	WebElement Message = driver.findElement(By.xpath("//span[contains(text(),'To close the cash till, use')]"));
    	if(Message.isDisplayed()) {
    		Assert.assertTrue("Success message displayed",true);
    	}else {
    		Assert.fail("No Success message displayed");
    	}
    }
    
    @Then("^unreconciled error message displayed$")
    public void unreconciled_error_message_displayed() throws Throwable {
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	
    	WebElement Message = driver.findElement(By.xpath("//span[contains(text(),'Reconciliation failed')]"));
    	if(Message.isDisplayed()) {
    		Assert.assertTrue("Error message displayed",true);
    	}else {
    		Assert.fail("No Error message displayed");
    	}
    }
  
///-----------------------------------------------Cash Office Reconciliation------------------------------------------------------------------------------------------///    
    @And("^leaves cash office blank and click reconcile$")
    public void leaves_cash_office_blank_and_click_reconcile() throws Throwable {
    	driver.findElement(By.xpath(Pro.getProperty("cashOffice_NameDropdown_Xpath"))).click();
        Thread.sleep(2000);
        driver.findElement(By.id("CashOfficeDailyControlform:CashOfficeName_0")).click();
        
        WebElement reconcileButton = driver.findElement(By.xpath("//*[@id=\"CashOfficeDailyControlform:btnReconcileCashOffice\"]"));
        reconcileButton.click();
        Thread.sleep(4000);
    }
    @Then("^message is displayed \"([^\"]*)\"$")
    public void message_is_displayed_something(String strArg1) throws Throwable {
//    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	WebDriverWait wait=new WebDriverWait(driver,10);
    	
    	WebElement Message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'"+strArg1+"')]")));
    	if(Message.isDisplayed()) {
    		Assert.assertTrue("Error message displayed",true);
    	}else {
    		Assert.fail("No Error message displayed");
    	}
    }
    
    @When("^selects Cash Office Name (.+)$")
    public void selects_cash_office_name(String cfn) throws Throwable {
    	driver.findElement(By.xpath(Pro.getProperty("cashOffice_NameDropdown_Xpath"))).click();
        Thread.sleep(2000);
        
        driver.findElement(By.xpath("//li[@data-label='"+cfn+"']")).click();  
        Thread.sleep(4000);
    }
    
    @And("^clicks reconcile Cash Office$")
    public void clicks_reconcile_cash_office() throws Throwable {
    	Thread.sleep(6000);
    	WebElement reconcileButton = driver.findElement(By.id("CashOfficeDailyControlform:btnReconcileCashOffice"));
        reconcileButton.click();
        Thread.sleep(7000);
    }

    @And("^Opens the Cash Office$")
    public void opens_the_cash_office() throws Throwable {
        Thread.sleep(4000);
        WebElement reconcileButton = driver.findElement(By.id("CashOfficeDailyControlform:btnOpenCashOffice"));
        reconcileButton.click();
        Thread.sleep(7000);
    }
    
    @When("^enters cash till reference$")
    public void enters_cash_till_reference() throws Throwable {
       WebElement cashTillRefDropdown = driver.findElement(By.xpath("//*[@id=\"CashOfficeDailyControlform:CashTillReference\"]/div[3]"));
       cashTillRefDropdown.click();
       Thread.sleep(2000);
       driver.findElement(By.id("CashOfficeDailyControlform:CashTillReference_1")).click();
    }
    
    @Then("^The System displays the Unreconciled Cash Till details$")
    public void the_system_displays_the_unreconciled_cash_till_details() throws Throwable {
    	Thread.sleep(5000);
        WebElement cashTillStatus = driver.findElement(By.id("CashOfficeDailyControlform:tillStatus"));
        Assert.assertEquals(cashTillStatus.getAttribute("value"), "UNRECONCILED");
    }
    
    @When("^user enters (.+)$")
    public void user_enters(String adjustmentreason) throws Throwable {
    	WebElement adjustmentReasonDropdown = driver.findElement(By.xpath("//*[@id=\"CashOfficeDailyControlform:AdjustmentReason\"]/div[3]"));
    	adjustmentReasonDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@data-label='"+adjustmentreason+"']")).click();
        
        //Adjustment value is the total amount minus float minus payment collected
        String totalAmount = driver.findElement(By.id("CashOfficeDailyControlform:totalAmount")).getAttribute("value");
        
        String floatAmount = driver.findElement(By.id("CashOfficeDailyControlform:floatAmount")).getAttribute("value");
        String paymentCollected = driver.findElement(By.id("CashOfficeDailyControlform:paymentCollected")).getAttribute("value");
        
        System.out.print("floatAmount--"+floatAmount);
        System.out.print("paymentCollected--"+paymentCollected);
        System.out.print("totalAmount--"+totalAmount);
        
        
        
        //AdjustmentValue=totalAmount-(floatAmount+paymentCollected)
        int totalSubtract = Integer.parseInt(floatAmount)+Integer.parseInt(paymentCollected);
        System.out.print("totalSubtract--"+totalSubtract);
        System.out.print("--------------------------------");
        
        float adjustmentValue = Float.parseFloat(totalAmount);
        adjustmentValue=adjustmentValue-totalSubtract;
        
        WebElement adjustmentValueInput = driver.findElement(By.id("CashOfficeDailyControlform:AdjustmentValue_input"));
        adjustmentValueInput.sendKeys(String.valueOf(adjustmentValue));
    }
    
    @Then("^clicks on Save Button$")
    public void clicks_on_save_button() throws Throwable {
    	Thread.sleep(2000);
        WebElement resolutionSaveButton = driver.findElement(By.id("CashOfficeDailyControlform:save"));
        resolutionSaveButton.click();
    }
    
    @When("^clicks Generate Unreconciled Report button$")
    public void clicks_generate_unreconciled_report_button() throws Throwable {
    	
    	WebElement btnGenerateUnrRep = driver.findElement(By.id("CashOfficeDailyControlform:btnGenerateUnrRep"));
    	btnGenerateUnrRep.click();
	    Thread.sleep(4000);
    }
    
    @Then("^Report download should be generate (.+) and (.+)$")
    public void report_download_should_be_generate_and(String downloadpath, String filename) throws Throwable {
    	boolean isPresent = false;
	    File dir = new File(downloadpath);
	    File[] dir_contents = dir.listFiles();
	  	
	    for (int i = 0; i < dir_contents.length; i++) {
	        if (dir_contents[i].getName().equals(filename))
	        	isPresent=true;
	            }
	    Thread.sleep(4000);
	    System.out.print(isPresent);
	    Assert.assertTrue(isPresent);
	    
    }
    
    @And("^clicks  Cash Office Summary Report button$")
    public void clicks_cash_office_summary_report_button() throws Throwable {
    	WebElement btnSummaryRep = driver.findElement(By.id("CashOfficeDailyControlform:btnCashOffSumRep"));
    	btnSummaryRep.click();
	    Thread.sleep(4000);
    }
    
    @And("^clicks Generate Bank Lodgement Report button$")
    public void clicks_generate_bank_lodgement_report_button() throws Throwable {
    	WebElement btnGenerateBankLodRep = driver.findElement(By.id("CashOfficeDailyControlform:btnGenerateBankLodRep"));
    	btnGenerateBankLodRep.click();
	    Thread.sleep(4000);
    }
    
    //----------------------------------------------------------Payment Receipting--------------------------------------------------------------------//
    
    @Given("^navigate to  Revenue Collection>>Receive Payment$")
    public void navigate_to_revenue_collectionreceive_payment() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("RevenueCollection_RevenueCollection_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("RevenueCollection_recieve_Payment_XPATH"))).click();
        Thread.sleep(4000);
        
    }
    
    @When("^click on Find Button$")
    public void click_on_find_button() throws Throwable {
    	WebDriverWait wait=new WebDriverWait(driver,10);
  	  	wait.until(ExpectedConditions.elementToBeClickable(By.id("PaymentSummary:FindTin"))).click();
    }
    
    @Then("^Find Entity pop up window should be displayed$")
    public void find_entity_pop_up_window_should_be_displayed() throws Throwable {
    	Thread.sleep(7000);
    	//Switch to iframe to allow interaction with modal
        WebElement frame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(frame);
        Thread.sleep(2000);
        
        String modalHeader = driver.findElement(By.id("SearchForm:directorHeader")).getText();
        Assert.assertEquals(modalHeader, "Trips - Find Entity");
    }
    
    @When("^User enters (.+) and (.+)$")
    public void user_enters_and(String taxpayerclassificationtype, String tin) throws Throwable {
        WebElement taxpayerClassificationdropDown=driver.findElement(By.xpath("//*[@id=\"SearchForm:DTYPE\"]/div[3]"));
        taxpayerClassificationdropDown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@data-label='"+taxpayerclassificationtype+"']")).click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        WebElement tinInput = driver.findElement(By.id("SearchForm:accountNumber"));
        tinInput.sendKeys(tin);
    }
    
    ////---------------------------------------------View Payment--------------------------------------------------------------------------------------------//
    @Given("^navigate to  Revenue Collection>>View Payment$")
    public void navigate_to_revenue_collectionview_payment() throws Throwable {
    	driver.findElement(By.xpath(Pro.getProperty("RevenueCollection_RevenueCollection_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("RevenueCollection_view_Payment_XPATH"))).click();
        Thread.sleep(3000);
    }
    
    @When("^From Find Payment window enters (.+) and (.+)$")
    public void from_find_payment_window_enters_and(String tin, String paymentmethod) throws Throwable {
    	WebElement tinInput = driver.findElement(By.id("SearchForm:TIN"));
        tinInput.sendKeys(tin);
        
        WebElement paymentMethoddropDown=driver.findElement(By.xpath("//*[@id=\"SearchForm:PaymentMethod\"]/div[3]"));
        paymentMethoddropDown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@data-label='"+paymentmethod+"']")).click();
        
    }
    
    @And("^clicks search$")
    public void clicks_search() throws Throwable {
    	
        WebElement searchButton = driver.findElement(By.id("SearchForm:j_idt42"));
        searchButton.click();
    }
    
    //////------------------------------------------------Payment Receipting-------------------------------------------------------------///
    @Then("^System displays message Records Not Found$")
    public void system_displays_message_records_not_found() throws Throwable {
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	
    	String emptyDatatable = driver.findElement(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]/tr/td")).getText();
        Assert.assertEquals(emptyDatatable, "No records found.");
    }
    
    @And("^clicks search button$")
    public void clicks_search_button() throws Throwable {
    	WebElement searchButton = driver.findElement(By.id("SearchForm:j_idt21"));
        searchButton.click();
    }
    
    @Then("^Payment Summary window displayed (.+)$")
    public void payment_summary_window_displayed(String tin) throws Throwable {
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	Thread.sleep(8000);
        String paymentSummaryLabel = driver.findElement(By.id("PaymentSummary:TaxpayerHeader")).getText();
        Assert.assertEquals(paymentSummaryLabel, "Taxpayer Account");
        
//        WebElement TIN = driver.findElement(By.id("PaymentSummary:TIN"));
//        Thread.sleep(2000);
//        Assert.assertEquals(tin, TIN.getAttribute("value"));
        
    }
    
    @When("^From Payment Summary window enters (.+)$")
    public void from_payment_summary_window_enters(String nameofpersonpaying) throws Throwable {
        WebElement personPayingInput = driver.findElement(By.id("PaymentSummary:personPaying_id"));
        personPayingInput.sendKeys(nameofpersonpaying);
        Thread.sleep(4000);
    }
    
    @And("^clicks add button$")
    public void clicks_add_button() throws Throwable {
        WebElement paymentSummaryAddBtn = driver.findElement(By.id("PaymentSummary:PaymentListDataTable:AddBtn"));
        
        Actions actions = new Actions(driver);
    	actions.doubleClick(paymentSummaryAddBtn).perform();
        Thread.sleep(9000);
    }
    
    @Then("^Payment Details should be displayed$")
    public void payment_details_should_be_displayed() throws Throwable {
    	Thread.sleep(10000);
        String paymentDetailsHeader = driver.findElement(By.xpath("//*[@id=\"PaymentDetails:paymentAccordion\"]/ul/li")).getText();
        Assert.assertEquals(paymentDetailsHeader, "Payment Details");
        
    }
    
    @When("^user fills Payment Amount as (.+)$")
    public void user_fills_payment_amount_as(String amount) throws Throwable {
    	WebElement paymentAmountInput = driver.findElement(By.id("PaymentDetails:paymentAccordion:paymentAmount_id_input"));
    	paymentAmountInput.sendKeys(amount);
    }
    
    @And("^clicks Next button$")
    public void clicks_next_button() throws Throwable {
    	WebElement nextBtn = driver.findElement(By.id("PaymentDetails:paymentAccordion:Next"));
    	nextBtn.click();
    	Thread.sleep(4000);
    }
    
    @Then("^Payment Allocation Summary tab should be displayed$")
    public void payment_allocation_summary_tab_should_be_displayed() throws Throwable {
        WebElement paymentAllocationSummaryTab = driver.findElement(By.xpath("//*[@id=\"PaymentDetails:paymentAccordion\"]/ul/li[2]"));
        Assert.assertTrue(paymentAllocationSummaryTab.isDisplayed());
    }
    
    @When("^clicks on On Account Button$")
    public void clicks_on_on_account_button() throws Throwable {
        WebElement onAccountBtn = driver.findElement(By.id("PaymentDetails:paymentAccordion:PaymentSpread:OnAccount"));
        onAccountBtn.click();
        Thread.sleep(4000);
    }
     
    @Then("^Account Payment Details pop up window should be displayed$")
    public void account_payment_details_pop_up_window_should_be_displayed() throws Throwable {
    	Thread.sleep(7000);
    	WebElement paymentonAccountHeader = driver.findElement(By.xpath("//*[@id=\"PaymentDetails:paymentAccordion:PaymentSpread:OnAccount_dlg\"]/div[1]"));
        Assert.assertTrue(paymentonAccountHeader.isDisplayed());
    }

    @Then("^Find document Details pop up window should be displayed$")
    public void find_document_details_pop_up_window_should_be_displayed() throws Throwable {
        Thread.sleep(7000);
        WebElement paymentonAccountHeader = driver.findElement(By.xpath("//*[@id=\"PaymentDetails:paymentAccordion:PaymentSpread:DocumentAllocation_dlg\"]/div[1]"));
        Assert.assertTrue(paymentonAccountHeader.isDisplayed());
    }
    
    @When("^On Account Payment Details enters (.+) and (.+)$")
    public void on_account_payment_details_enters_and(String taxtype, String amountallocated) throws Throwable {
    	//Switch to iframe to allow interaction with modal
        WebElement frame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(frame);
        Thread.sleep(2000);
        
        //Enter tax type
        WebElement taxTypedropDown = driver.findElement(By.xpath("//*[@id=\"OnAccountPayment:RegimeType\"]/div[3]"));
        taxTypedropDown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@data-label='"+taxtype+"']")).click();
        
        //Enter returnType
//        WebElement returnTypeDropdown = driver.findElement(By.xpath("//*[@id=\"OnAccountPayment:ReturnType\"]/div[3]"));
//        returnTypeDropdown.click();
//        Thread.sleep(2000);
//        driver.findElement(By.id("OnAccountPayment:ReturnType_1")).click();
        
        //Enter amount
        Thread.sleep(2000);
        driver.findElement(By.id("OnAccountPayment:AmountAllocated_input")).sendKeys(amountallocated);
    }
    
    @And("^clicks ok$")
    public void clicks_ok() throws Throwable {
//    	//Switch to iframe to allow interaction with modal
        WebElement frame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(frame);
        Thread.sleep(2000);
    	
    	
        WebElement okButton = driver.findElement(By.id("OnAccountPayment:Ok"));
        okButton.click();
        Thread.sleep(4000);
    }
    
    @Given("^From Payment Details window click on Save Button$")
    public void from_payment_details_window_click_on_save_button() throws Throwable { 	
    	WebElement paymentAllocationBtn = driver.findElement(By.id("PaymentDetails:Ok"));
    	paymentAllocationBtn.click();
	    Thread.sleep(4000);
    }
    
    @Given("^Payment Summary window click on Save Button$")
    public void payment_summary_window_click_on_save_button() throws Throwable {
    	WebElement paymentSummarySaveBtn = driver.findElement(By.id("PaymentSummary:Save"));
    	paymentSummarySaveBtn.click();
	    Thread.sleep(4000);
    }
    
    @And("^check non registered taxpayer$")
    public void check_non_registered_taxpayer() throws Throwable {
        WebElement nonRegisteredTaxCheckBox = driver.findElement(By.xpath("//*[@id=\"PaymentSummary:taxpayerType\"]/div[2]"));
        nonRegisteredTaxCheckBox.click();
    }
    
    @And("^enters Non Registered Taxpayer Details (.+) and (.+) and (.+) and (.+)$")
    public void enters_non_registered_taxpayer_details_and_and_and(String firstname, String lastname, String nationalidno, String address) throws Throwable {
    	Thread.sleep(4000);
        WebElement firstNameInput = driver.findElement(By.id("PaymentSummary:FirstName"));
        firstNameInput.sendKeys(firstname);
        
        WebElement lastNameInput = driver.findElement(By.id("PaymentSummary:LastName"));
        lastNameInput.sendKeys(lastname);
        
        WebElement nationalidnoInput = driver.findElement(By.id("PaymentSummary:NationalId"));
        nationalidnoInput.sendKeys(nationalidno);
        
        WebElement addressInput = driver.findElement(By.id("PaymentSummary:Address"));
        addressInput.sendKeys(address);
    }
    
    @And("^clicks proceed on the popup$")
    public void clicks_proceed_on_the_popup() throws Throwable {
    	Thread.sleep(4000);
    	//Switch to iframe to allow interaction with modal
//        WebElement frame = driver.findElement(By.tagName("iframe"));
//        driver.switchTo().frame(frame);
//        Thread.sleep(2000);
        
        WebElement confirmationYesButton = driver.findElement(By.id("PaymentDetails:j_idt46"));
        confirmationYesButton.click();
    }

    @And("^Payment list click on Save Button$")
    public void payment_list_click_on_save_button() throws Throwable {
        Thread.sleep(4000);
        WebElement saveBtn=driver.findElement(By.id("PaymentSummary:Save"));
        saveBtn.click();
    }
    
    @Then("^account payment error message is displayed \"([^\"]*)\"$")
    public void account_payment_error_message_is_displayed_something(String strArg1) throws Throwable {
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	WebElement Message = driver.findElement(By.xpath("//span[contains(text(),'"+strArg1+"')]"));
    	if(Message.isDisplayed()) {
    		Assert.assertTrue("Error message displayed",true);
    	}else {
    		Assert.fail("No Error message displayed");
    	}
    }
    
    //-----------------------------------------------Receipt Document Control-----------------------------------------------------------------
    
    @Given("^navigate to  Revenue Collection>>Receipt Document Control$")
    public void navigate_to_revenue_collectionreceipt_document_control() throws Throwable {
    	driver.findElement(By.xpath(Pro.getProperty("RevenueCollection_RevenueCollection_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("RecieptDocumentControl_xpath"))).click();
        Thread.sleep(3000);
    }
    
    @When("^enters Receipt Document Control (.+) and (.+) and (.+) and (.+)$")
    public void enters_receipt_document_control_and_and_and(String cashofficeno, String receiptnumberfrom, String receiptnumberto, String distributionstatus) throws Throwable {
        WebElement cashOfficedropDown = driver.findElement(By.xpath("//*[@id=\"ReceiptDocumentControl:CashOfficeDD\"]/div[3]"));
        cashOfficedropDown.click();
        Thread.sleep(2000);
//        driver.findElement(By.id("ReceiptDocumentControl:CashOfficeDD_1")).click();
        driver.findElement(By.xpath("//*[@id='ReceiptDocumentControl:CashOfficeDD_"+cashofficeno+"']")).click();
//        driver.findElement(By.xpath("//li[text()='Balaka Office1']")).click();
        
        WebElement recieptNumberFromInput = driver.findElement(By.id("ReceiptDocumentControl:ReceiptNumFrom"));
        recieptNumberFromInput.sendKeys(receiptnumberfrom);
        
        WebElement recieptNumberToInput = driver.findElement(By.id("ReceiptDocumentControl:ReceiptNumTo"));
        recieptNumberToInput.sendKeys(receiptnumberto);
        
        WebElement distributionStatusDropdown = driver.findElement(By.xpath("//*[@id=\"ReceiptDocumentControl:distributionStatus\"]/div[3]"));
        distributionStatusDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@data-label='"+distributionstatus+"']")).click();
        
    }

    @And("^enters cashOffice (.+)$")
    public void enters_cashoffice(String cashoffice2) throws Throwable {
        Thread.sleep(2000);
        WebElement cashOfficedropDown = driver.findElement(By.xpath("//*[@id='ReceiptDocumentControl:cashOffice']/div[3]"));
        cashOfficedropDown.click();
        Thread.sleep(2000);

       driver.findElement(By.id("ReceiptDocumentControl:cashOffice_1")).click();
//        driver.findElement(By.xpath("//li[@data-label='"+cashoffice2+"']")).click();
    }
    @And("^enters cashOfficer (.+)$")
    public void enters_cashofficer(String cashofficer) throws Throwable {
        Thread.sleep(2000);
        WebElement cashOfficerdropDown = driver.findElement(By.xpath("//*[@id=\"ReceiptDocumentControl:officer\"]/div[3]"));
        cashOfficerdropDown.click();
        Thread.sleep(2000);

        driver.findElement(By.id("ReceiptDocumentControl:officer_1")).click();
//        driver.findElement(By.xpath("//li[@data-label='"+cashoffice2+"']")).click();
    }

    
    @And("^clicks on Receipt Document Control Save Button$")
    public void clicks_on_receipt_document_control_save_button() throws Throwable {
    	WebElement ReceiptbtnSave=driver.findElement(By.id("ReceiptDocumentControl:btnSave"));
    	ReceiptbtnSave.click();
    }
    
    @Then("^The System saves the updated Receipt Document record$")
    public void the_system_saves_the_updated_receipt_document_record() throws Throwable {
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	
    	WebElement Message = driver.findElement(By.xpath("//span[contains(text(),'Document Details Saved Successfully')]"));
    	if(Message.isDisplayed()) {
    		Assert.assertTrue("Success message displayed",true);
    	}else {
    		Assert.fail("No Success message displayed");
    	}
    }
    
    @When("^enters Receipt Document Control (.+) and (.+) leaving other blank$")
    public void enters_receipt_document_control_and_leaving_other_blank(String cashofficeno, String distributionstatus) throws Throwable {
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	WebElement cashOfficedropDown = driver.findElement(By.xpath("//*[@id=\"ReceiptDocumentControl:CashOfficeDD\"]/div[3]"));
        cashOfficedropDown.click();
        Thread.sleep(4000);

        driver.findElement(By.xpath("//*[@id='ReceiptDocumentControl:CashOfficeDD_"+cashofficeno+"']")).click();
//        driver.findElement(By.id("ReceiptDocumentControl:CashOfficeDD_1")).click();
        
        WebElement distributionStatusDropdown = driver.findElement(By.xpath("//*[@id=\"ReceiptDocumentControl:distributionStatus\"]/div[3]"));
        distributionStatusDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@data-label='"+distributionstatus+"']")).click();
    }
    
    @And("^checks Issued To Cashier$")
    public void checks_issued_to_cashier() throws Throwable {
	    Thread.sleep(4000);
        WebElement cashierOfficeCheckBox = driver.findElement(By.xpath("//*[@id='ReceiptDocumentControl:issuedTo']/tbody/tr/td[1]/div/div[2]"));
        cashierOfficeCheckBox.click();
    }
    
    @And("^checks Issued To Cash Officer$")
    public void checks_issued_to_cash_officer() throws Throwable {
        Thread.sleep(4000);
    	WebElement cashierOfficerCheckBox = driver.findElement(By.xpath("//*[@id='ReceiptDocumentControl:issuedTo']/tbody/tr/td[2]/div/div[2]"));
    	cashierOfficerCheckBox.click();
    }
    
    ////------------------------------------------------------------Revenue Collection Reports-------------------------------------------------------------------------------------///


    @Given("^navigate to  Reporting>>Reports$")
    public void navigate_to_reportingreports() throws Throwable {
        driver.findElement(By.xpath(Pro.getProperty("reportingButton"))).click();
        driver.findElement(By.xpath(Pro.getProperty("reportsButton"))).click();
        Thread.sleep(3000);
    }
    
    @When("^clicks Cashiering Report$")
    public void clicks_cashiering_report() throws Throwable {
        WebElement casheeringReport = driver.findElement(By.id("ReportTree:t1:7_0:j_idt42"));
        casheeringReport.click();
        Thread.sleep(5000);
    }
    
    @Then("^Report screen should be displayed (.+)$")
    public void report_screen_should_be_displayed(String reportname) throws Throwable {
        String reportDetails = driver.findElement(By.id("frmReportDetails:frmReportDetailsLabel")).getText();
        Assert.assertEquals(reportDetails, "Report Details");
        
        String reportName = driver.findElement(By.id("frmReportDetails:ReportName")).getAttribute("value");
        Assert.assertEquals(reportName, reportname);
        
    }
    
    @When("^enters cash till reference as \"([^\"]*)\"$")
    public void enters_cash_till_reference_as_something(String reference) throws Throwable {
        WebElement cashTillRefInput = driver.findElement(By.id("frmReportDetails:CASH_TILL_ID"));
        cashTillRefInput.sendKeys(reference);
    }
    
    @And("^clicks run report button$")
    public void clicks_run_report_button() throws Throwable {
        WebElement runReportbtn = driver.findElement(By.id("frmReportDetails:RunReport"));
        runReportbtn.click();
        Thread.sleep(4000);
    }
    
    @When("^clicks Daily Payment Report$")
    public void clicks_daily_payment_report() throws Throwable {
    	 WebElement casheeringReport = driver.findElement(By.id("ReportTree:t1:7_1:j_idt42"));
         casheeringReport.click();
         Thread.sleep(5000);
    }
    
    @When("^enters Daily Payment report parameters$")
    public void enters_daily_payment_report_parameters() throws Throwable {
        WebElement endDate = driver.findElement(By.id("frmReportDetails:EndDate_input"));
        endDate.sendKeys(Keys.ENTER);
        
        WebElement taxOfficeDropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:TAX_OFFICE\"]/div[3]"));
        taxOfficeDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.id("frmReportDetails:TAX_OFFICE_1")).click();
        
        Thread.sleep(5000);
    }
    
    @When("^clicks Receipt Document Summary$")
    public void clicks_receipt_document_summary() throws Throwable {
    	WebElement reportDocSumm = driver.findElement(By.id("ReportTree:t1:7_2:j_idt42"));
    	reportDocSumm.click();
        Thread.sleep(5000);
    }
    
    @When("^enters Receipt Document Summary report parameters$")
    public void enters_receipt_document_summary_report_parameters() throws Throwable {
    	WebElement toendDate = driver.findElement(By.id("frmReportDetails:EndDate_input"));
    	toendDate.sendKeys(Keys.ENTER);
        
        WebElement recieptStatusDropdown = driver.findElement(By.xpath("//*[@id=\"frmReportDetails:Distribution_Status\"]/div[3]"));
        recieptStatusDropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.id("frmReportDetails:Distribution_Status_1")).click();
        
        Thread.sleep(5000);
    }


    
    @And("^clicks cancel button$")
    public void clicks_cancel_button() throws Throwable {
        WebElement cancelBtn = driver.findElement(By.id("frmReportDetails:btnCancel"));
        cancelBtn.click();
        Thread.sleep(5000);
    }
    
    @Then("^navigated back to Reports screen$")
    public void navigated_back_to_reports_screen() throws Throwable {
        String reportListHeader = driver.findElement(By.id("ReportTree:ReportReprintLabel")).getText();
        Assert.assertEquals(reportListHeader, "Reports List");
    }
    
    /////------------------------------------------------------------Print Refund Documents-------------------------------------------------------------------------------------///

    @And("^clicks yes on confirmation prompt$")
    public void clicks_yes_on_confirmation_prompt() throws Throwable {
    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        
        WebElement yesBtn = driver.findElement(By.id("PaymentDetails:j_idt46"));
        yesBtn.click();
        
    }
    
    @Then("^Reciept generated successfully$")
    public void reciept_generated_successfully() throws Throwable {
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	
    	WebElement Message = driver.findElement(By.xpath("//span[contains(text(),'PaymentReceipt.pdf')]"));
    	if(Message.isDisplayed()) {
    		Assert.assertTrue("Success message displayed",true);
    	}else {
    		Assert.fail("No Success message displayed");
    	}
    }
    
    /////----------------------------------------------------------------Reverse Payment---------------------------------------------------------------------------------------------------------///
    
    @Given("^navigate Revenue Collection>>Reverse Payment$")
    public void navigate_revenue_collectionreverse_payment() throws Throwable {
    	driver.findElement(By.xpath(Pro.getProperty("RevenueCollection_RevenueCollection_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("reversePayment_Xpath"))).click();
        Thread.sleep(3000);
    }
    
    @When("^enters (.+) on Find Payment page$")
    public void enters_on_find_payment_page(String tin) throws Throwable {
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement tinInput = driver.findElement(By.id("SearchForm:TIN"));
        tinInput.sendKeys(tin);
    }
    
    @And("^click search on find payment page$")
    public void click_search_on_find_payment_page() throws Throwable {
    	
    	WebElement searchBtn = driver.findElement(By.id("SearchForm:j_idt42"));
    	searchBtn.click();
    }
    
    @Then("^Searched results should be displayed in a table$")
    public void searched_results_should_be_displayed_in_a_table() throws Throwable {
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement firstRow = driver.findElement(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]/tr[1]"));
        Assert.assertTrue(firstRow.isDisplayed());
    }
    
    @When("^user clicks Taxpayers Payment to be reversed$")
    public void user_clicks_taxpayers_payment_to_be_reversed() throws Throwable {
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//*[@id='SearchForm:resultsDataTable_data']/tr[1]")).click();

    }
    @And("^clicks Continue button$")
    public void clicks_continue_button() throws Throwable {    	
    	WebElement continueBtn = driver.findElement(By.id("SearchForm:j_id22"));
    	continueBtn.click();
    }
    
    @When("^clicks on payment list and view$")
    public void clicks_on_payment_list_and_view() throws Throwable {
        Thread.sleep(3000);
        WebElement paymentList = driver.findElement(By.xpath("//*[@id=\"PaymentSummary:PaymentListDataTable_data\"]/tr/td[2]"));
        paymentList.click();
        
        WebElement viewBtn = driver.findElement(By.id("PaymentSummary:PaymentListDataTable:View"));
        viewBtn.click();
    }
    
    @And("^enters required comment (.+)$")
    public void enters_required_comment(String comment) throws Throwable {
    	Thread.sleep(5000);
        WebElement paymentCommentInput = driver.findElement(By.id("PaymentDetails:paymentAccordion:Comments"));
        paymentCommentInput.sendKeys(comment);
    }
    
    @And("^clicks reverse button$")
    public void clicks_reverse_button() throws Throwable {
    	WebElement reverseButton = driver.findElement(By.id("PaymentDetails:Reverse"));
    	reverseButton.click();
    	Thread.sleep(5000);
    }
    
    @When("^clicks confirm reversal button$")
    public void clicks_confirm_reversal_button() throws Throwable {
    	WebElement confirmReverseButton = driver.findElement(By.id("PaymentSummary:Reverse"));
    	confirmReverseButton.click();
    	Thread.sleep(5000);
    }
    @Then("^no records found$")
    public void no_records_found() throws Throwable {
    	String emptyDatatable = driver.findElement(By.xpath("//*[@id=\"SearchForm:resultsDataTable_data\"]/tr/td")).getText();
        Assert.assertEquals(emptyDatatable, "No records found.");
    }
    
    
    ////---------------------------------------------------------------Bulk Upload - Payments--------------------------------------------------------------------------///
    
    @Given("^navigate Revenue Collection>>Bulk Payment$")
    public void navigate_revenue_collectionbulk_payment() throws Throwable {
    	driver.findElement(By.xpath(Pro.getProperty("RevenueCollection_RevenueCollection_XPATH"))).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(Pro.getProperty("BulkPayment_Xpath"))).click();
        Thread.sleep(3000);
    }
    
    @When("^user clicks on browse button$")
    public void user_clicks_on_browse_button() throws Throwable {
    	WebElement browseBtn=driver.findElement(By.xpath("//*[@id='BulkPayment:UploadFile']/span[1]"));
    	Assert.assertTrue(browseBtn.isDisplayed());
    }
    
    @And("^Selects the file to be uploaded (.+)$")
    public void selects_the_file_to_be_uploaded(String path) throws Throwable {
    	Thread.sleep(3000);
    	driver.findElement(By.id("BulkPayment:UploadFile_input")).sendKeys(path);
    }
    
    @And("^Then click Save Button on upload$")
    public void then_click_save_button_on_upload() throws Throwable {
    	WebElement saveUploadButton = driver.findElement(By.id("BulkPayment:save"));
    	saveUploadButton.click();
    	Thread.sleep(5000);
    }
    
    ///---------------------------------------------------Taxpayer Payment - Portal------------------------------------------------//
    @Given("^User navigates to the Portal login page$")
    public void user_navigates_to_the_portal_login_page() throws Throwable {
    	driver = BaseClass.getDriver();
    	driver.get(Pro.getProperty("PortalURL"));
    }
    
    @And("^Enters the Portal username \"([^\"]*)\" and password \"([^\"]*)\" to login$")
    public void enters_the_portal_username_something_and_password_something_to_login(String strArg1, String strArg2) throws Throwable {
    	Thread.sleep(5000);
        WebElement usernameInput = driver.findElement(By.xpath("//*[@id=\"id_userName\"]"));
        usernameInput.sendKeys(strArg1);
        
        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"id_password\"]"));
        passwordInput.sendKeys(strArg2);
              
        WebElement loginBtn = driver.findElement(By.id("btnSubmit"));
        loginBtn.click();
    }
    
    @When("^User clicks login as Taxpayer$")
    public void user_clicks_login_as_taxpayer() throws Throwable {
    	Thread.sleep(5000);
        WebElement taxPayer = driver.findElement(By.xpath("/html/body/trips-app/div/app-portal-home/div/div/div[1]/div[3]/div[1]/p/a"));
        taxPayer.click();
    }
    
    //----------------------------------------------------Taxpayer Payment - Portal-------------------------------------------------------------------------------------///
    @Given("^navigate to My Tax>>make payment$")
    public void navigate_to_my_taxmake_payment() throws Throwable {
       Thread.sleep(4000);
       driver.findElement(By.id("id_btnMyTaxToggle")).click();
 
       Thread.sleep(2000);
       WebElement makePayment=driver.findElement(By.xpath("//*[@id=\"id_makeAPayment\"]"));
       makePayment.click();
    }
    
    @Then("^Outstanding Payments List Screen should be displayed$")
    public void outstanding_payments_list_screen_should_be_displayed() throws Throwable {
    	Thread.sleep(4000);
    	String header=driver.findElement(By.id("id_outstandingPaymentForm")).getText();
    	Assert.assertEquals(header, "Outstanding Payments");
    }
    
    @Given("^Selects the Tax Type liability to be paid$")
    public void selects_the_tax_type_liability_to_be_paid() throws Throwable {
    	Thread.sleep(4000);
        WebElement taxTaypeChosen=driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/app-revenue-collection/app-outstanding-payment-list/div[2]/p-table/div/div[1]/table/tbody/tr[1]/td[8]/span[2]/div/p-tablecheckbox/div/div[2]"));
        taxTaypeChosen.click();
    }
    
    @And("^click Pay Selected Button$")
    public void click_pay_selected_button() throws Throwable {
    	WebElement payBtn=driver.findElement(By.id("paySelected"));
        payBtn.click();
    }
    
    @Then("^Payment Reference Number screen should be displayed$")
    public void payment_reference_number_screen_should_be_displayed() throws Throwable {
    	Thread.sleep(4000);
    	String header=driver.findElement(By.id("id_paymentSelectionForm")).getText();
    	Assert.assertEquals(header, "Payment Selection Details");
    }
    
    @Given("^user submits valid payment amount (.+)$")
    public void user_submits_valid_payment_amount(String amount) throws Throwable {
        WebElement paymentInput=driver.findElement(By.xpath("/html/body/trips-app/div/ng-component/div/app-revenue-collection/ng-component/div[2]/p-table/div/div[1]/table/tbody/tr/td[8]/span[2]/tb-png-input-number/div/div[2]/span/input"));
        paymentInput.clear();
        Thread.sleep(2000);
        paymentInput.sendKeys(amount);
    }
    
    @And("^Clicks on Pay At The Bank button$")
    public void clicks_on_pay_at_the_bank_button() throws Throwable {
        WebElement payBankBtn=driver.findElement(By.id("payBank"));
        payBankBtn.click();
    }
    
    @Then("^Portal error is displayed \"([^\"]*)\"$")
    public void portal_error_is_displayed_something(String strArg1) throws Throwable {
    	WebElement Message = driver.findElement(By.xpath("/html/body/trips-app/div/p-messages/div"));
    	if(Message.isDisplayed()) {
    		Assert.assertTrue("Success message displayed",true);
    	}else {
    		Assert.fail("No Success message displayed");
    	}
    }
    
    @And("^enters designation (.+)$")
    public void enters_designation(String designation) throws Throwable {
    	WebElement designationInput = driver.findElement(By.id("PaymentSummary:designation_id"));
    	designationInput.sendKeys(designation);
    }
    @When("^clicks on Document Allocation Button$")
    public void clicks_on_document_allocation_button() throws Throwable {
        WebElement docAllocationBtn = driver.findElement(By.id("PaymentDetails:paymentAccordion:PaymentSpread:DocumentAllocation"));
        docAllocationBtn.click();
        Thread.sleep(4000);
    }

    
    
}




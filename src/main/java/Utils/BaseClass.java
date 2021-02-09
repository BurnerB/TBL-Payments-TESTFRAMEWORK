package Utils;

import java.io.FileInputStream;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//Base class to allow access to browser from hooks
public class BaseClass {
	//local variable that gets assigned below after properties class is instantiated
	public static WebDriver driver;
	public static Properties prop;


	
	public static WebDriver getDriver() throws IOException
	{
		prop = new Properties();
		FileInputStream fls = new FileInputStream("src\\test\\resources\\global.properties");
		prop.load(fls);
		
		System.setProperty("webdriver.chrome.driver", "Browsers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        return driver;

	}
	public static String randomDate() {
	        
	        LocalDate from = LocalDate.of(2000, 1, 1);
	        LocalDate to = LocalDate.of(2017, 1, 1);
	        long days = from.until(to, ChronoUnit.DAYS);
	        long randomDays = ThreadLocalRandom.current().nextLong(days + 1);
	        LocalDate randomDate = from.plusDays(randomDays);
	        return randomDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	    }
	
	public static String todaysDate() {
		LocalDate today = LocalDate.now();
    	String formattedDate = today.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
    	return formattedDate;
	}
	public static String tomorrowsDate() {
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
    	String formattedDate = tomorrow.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
    	return formattedDate;
	}
}

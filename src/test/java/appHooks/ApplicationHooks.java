package appHooks;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.factory.DriverFactory;
import com.util.ConfigReader;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class ApplicationHooks {
	Properties prop;
	DriverFactory driverFactory;
	WebDriver driver;
	
	@Before(order=0)
	public void getProperty() {
		ConfigReader cr = new ConfigReader();
		prop = cr.init_prop();
	}
	
	@Before(order=1)
	public void launchSite() {
		String browserName = prop.getProperty("browser");
		driverFactory = new DriverFactory();
		driver = driverFactory.init_driver(browserName);
		
	}
	
	@After(order=0)
	public void quitBrowser( ) {
		driver.quit();
	}
	
	@After(order=1) 
	public void tearDown(Scenario scenario) {
		
		if(scenario.isFailed()) {
			//String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(sourcePath, "image/png");
		}
	}
}

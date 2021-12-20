package com.pages;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TimePage {
	WebDriver driver;
	private By fullTime = By.xpath("//p[@class='ClassicTimer-time']/span");
	
	public TimePage(WebDriver driver) {
		this.driver = driver;
	}
	
//	public int getMinutes() {
//		List<WebElement> time = driver.findElements(fullTime);
//		if(time.size()>1) 
//			return Integer.parseInt((time.get(0).getText().split(" "))[0]);
//		else
//			return 0;
//	}
	
	public int getMinutes() {
		List<WebElement> time = driver.findElements(fullTime);
		if(time.size()>1 || (time.size() == 1 && time.get(0).getText().contains("minute"))) 
			return Integer.parseInt((time.get(0).getText().split(" "))[0]);
		else
			return 0;
	}
	
	public int getSeconds() {
		List<WebElement> time = driver.findElements(fullTime);
		if(time.size()>1) {
			return Integer.parseInt((time.get(1).getText().split(" "))[0]);
		}
		else if(time.size()>1 || (time.size() == 1 && time.get(0).getText().contains("minute")))
			return 0;
		else
			return Integer.parseInt((time.get(0).getText().split(" "))[0]);
	}
	
	public void getFullTime() {
		List<WebElement> time = driver.findElements(fullTime);
		for(WebElement timings: time) {
			System.out.println(timings.getText());
		}
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public boolean alertExceptionNotOccurs() {
		try {
			driver.findElement(fullTime).getText();
			return true;
		}
		catch(UnhandledAlertException f) {
			System.out.println(f.getAlertText());
			return false;
		}
	}
}

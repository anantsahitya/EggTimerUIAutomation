package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	WebDriver driver;
	
	private By timerInput = By.cssSelector("input.EggTimer-start-time-input-text");
	private By startButton = By.xpath("(//button)[1]");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enterTime(String time) {
		driver.findElement(timerInput).sendKeys(time);
	}
	
	public TimePage clickStartButton() {
		driver.findElement(startButton).click();
		return new TimePage(driver);
	}
	
}

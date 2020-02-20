package test.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTestValerio {
	public static String browseFile() {
		System.setProperty("webdriver.chrome.driver","Drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/WorkineThor/");
		driver.findElement(By.xpath("//*[@id=\"user\"]")).sendKeys("mario");
		driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("mario");
		driver.findElement(By.xpath("/html/body/div/div/form/input[3]")).click();
		
		driver.findElement(By.xpath("/html/body/nav/div/ul[1]/li[3]/a")).click();
		driver.findElement(By.xpath("/html/body/div/ul/li[1]/a")).click();
		WebElement txtBoxContente = driver.findElement(By.xpath("/html/body/div[1]/h2[1]"));
		String projectName = (txtBoxContente.getText());
		
		driver.close();
		return (projectName);
	}
}

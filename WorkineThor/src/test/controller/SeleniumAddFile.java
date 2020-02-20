package test.controller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumAddFile {
	
	public static String addFileTestSelenium() {
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/WorkineThor/index.jsp");
		
		driver.findElement(By.xpath("//*[@id=\"user\"]")).sendKeys("mario");
		driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("mario");
		
		driver.findElement(By.xpath("/html/body/div/div/form/input[3]")).click();
		
		driver.findElement(By.xpath("/html/body/div/ul/li[1]/a")).click();
		
		driver.findElement(By.xpath("//*[@id=\"add-file-button\"]")).click();
		
		driver.findElement(By.xpath("/html/body/div/input[2]")).click();
		
		WebElement txtBoxElement = driver.findElement(By.xpath("/html/body/div/ul/li[1]/a"));
		String result = txtBoxElement.getText();
		
		driver.close();
		return result;
	}
}

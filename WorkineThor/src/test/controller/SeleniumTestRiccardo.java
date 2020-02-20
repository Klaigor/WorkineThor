package test.controller;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTestRiccardo {
	
	public static String dutiesTestSelenium() {
		System.setProperty("webdriver.chrome.driver","Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/WorkineThor/index.jsp");
		
		//driver.findElement(By.xpath("//*[@id=\"x\"]")).sendKeys("carlo");

		driver.findElement(By.xpath("//*[@id=\"user\"]")).sendKeys("carlo");
		driver.findElement(By.xpath("//*[@id=\"pass\"]")).sendKeys("conti");
		driver.findElement(By.xpath("/html/body/div/div/form/input[3]")).click();
		
		driver.findElement(By.xpath("/html/body/div/ul/li[1]/a")).click();
		
		driver.findElement(By.xpath("//*[@id=\"duties-button\"]")).click();
		WebElement txtBoxContente = driver.findElement(By.xpath("/html/body/div/h1"));
		String mario = (txtBoxContente.getText());
		driver.close();
		return(mario);
	}

}

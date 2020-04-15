package project.nykaa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class nykaa {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.nykaa.com/");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		//Mouse over on brand
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[text()='brands']"))).build().perform();
		Thread.sleep(3000);
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Popular']"))).build().perform();
		Thread.sleep(3000);
		action.moveToElement(driver.findElement(By.xpath("//li[@class='brand-logo menu-links'][5]//img"))).click().build().perform();
		
		String parentWindow = driver.getWindowHandle();
		
		//Switch to the page "L'Oreal Paris"
		Set<String> windows = driver.getWindowHandles();
		List<String> noOfWindows = new ArrayList<String>();
		noOfWindows.addAll(windows);
		driver.switchTo().window(noOfWindows.get(1));
		
		//To verify the title of the page
		if (driver.getTitle().contains("L'Oreal Paris")) {
			System.out.println("Title is matching with the page");
		}else {
			System.out.println("Title doesnt match with the page");
		}
		
		//Scroll page to view correct option is selected
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		
		//Sort by CUSTOMER TOP RATED and select SHAMPOO category
		driver.findElement(By.xpath("//span[text()='Sort By : ']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		Thread.sleep(3000);
		
		js.executeScript("window.scrollBy(0,500)");
		driver.findElement(By.xpath("//div[text()='Category']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//label[@for='chk_Shampoo_undefined']/span")).click();
		
		//To verify the "SHAMPOO" is filtered
		String selectedText = driver.findElement(By.xpath("//li[text()='Shampoo']")).getText();
		Thread.sleep(3000);
		if (selectedText.contains("Shampoo")) {
			System.out.println("Filter is applied for Shampoo");
		}else {
			System.out.println("Filter is not applied");
		}
		
		//Switching to the window2(L'Oreal Paris Colour Protect Shampoo) page
		driver.findElement(By.xpath("//span[text()=\"L'Oreal Paris Colour Protect Shampoo\"]")).click();
		Set<String> windows2 = driver.getWindowHandles();
		List<String> noOfWindows2 = new ArrayList<String>(windows2);
		noOfWindows.addAll(windows2);
		driver.switchTo().window(noOfWindows2.get(2));
		
		
		//click and print the price of the shampoo
		driver.findElement(By.xpath("//span[text()='175ml']")).click();
		Thread.sleep(3000);
		String priceOfShampoo = driver.findElement(By.xpath("//div[@class='clearfix product-des__details']//span[2]/span")).getText();
		System.out.println("SHAMPOO PRICE IS:"+priceOfShampoo);
		Thread.sleep(3000);
		
		//Select the quantity(ML) of the shampoo and add it to the BAG
		driver.findElement(By.xpath("//div[@class='pull-left']/div/button")).click();
		Thread.sleep(3000);
		driver.findElement(By.className("AddBagIcon")).click();
		Thread.sleep(3000);
		
		//Print the grand total amount
		String grandTotal = driver.findElement(By.xpath("//div[@class='payment-tbl-data']/div[4]/div[2]")).getText();
		System.out.println("GRAND TOTAL OF THE PRODUCT IS : "+grandTotal);
		
		
		driver.findElement(By.className("second-col")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		
		//Print the error message
		String imrtMessage = driver.findElement(By.className("message")).getText();
		System.out.println("ERROR MESSAGE SHOWED AS : "+imrtMessage);
		Thread.sleep(3000);
		
		//To close the tab one by one
		driver.close();
		driver.switchTo().window(noOfWindows.get(1));
		Thread.sleep(3000);
		driver.close();
		driver.switchTo().window(parentWindow);
		Thread.sleep(3000);
		driver.close();		
		
	}
	

}

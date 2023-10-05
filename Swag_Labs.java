package orangeHRM_Project;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Swag_Labs {

	public static void main(String[] args) throws InterruptedException, AWTException, IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		// URL Verification
		driver.get("https://www.saucedemo.com/");
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		System.out.println();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// Logging into SauceDemo
		driver.findElement(By.id("user-name")).clear();
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		{
			// Enter the data into form by taking all the data from excel sheet
			String filePath = "C:\\Users\\HP\\Desktop\\Swag_Labs.xlsx";
			FileInputStream fis = new FileInputStream(filePath);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet("Data");

			System.out.println();
			int rows = sheet.getLastRowNum();
			System.out.println("no. of rows: " + rows);

			for (int r = 1; r <= rows; r++) {
				XSSFRow row = sheet.getRow(r);
				XSSFCell F_Name = row.getCell(0);
				XSSFCell L_Name = row.getCell(1);
				XSSFCell PC_zip = row.getCell(2);

				// Selecting First item
				driver.findElement(By.id("item_4_title_link")).click();
				// Adding selected item to cart
				driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
				// Entering the shopping cart to check for the selected item
				driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

				// Clicking on Continue Shopping
				driver.findElement(By.id("continue-shopping")).click();

				// Selecting Second item from the shopping page
				driver.findElement(By.xpath("//div[text()='Sauce Labs Fleece Jacket']")).click();
				// Adding selected item to cart
				driver.findElement(By.name("add-to-cart-sauce-labs-fleece-jacket")).click();
				// Entering the shopping cart to check for the selected item
				driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

				// Clicking on Continue Shopping
				driver.findElement(By.id("continue-shopping")).click();

				// Selecting Third item from the shopping page
				driver.findElement(By.xpath("//div[text()='Test.allTheThings() T-Shirt (Red)']")).click();
				// Adding selected item to cart
				driver.findElement(By.name("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
				// Entering the shopping cart to check for the selected item
				driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

				// Removing an item from the cart
				driver.findElement(By.id("remove-sauce-labs-fleece-jacket")).click();

				// Clicking on Checkout
				driver.findElement(By.xpath("//button[text()='Checkout']")).click();

				System.out.println("First Name: " + F_Name + ", Last Name: " + L_Name + ", Zip/Postal Code: " + PC_zip);
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//span[text()='Checkout: Your Information']")));

				driver.navigate().refresh();
				// Enter Customer Information
				// Entering First Name
				driver.findElement(By.name("firstName")).sendKeys(F_Name.toString());
				// Entering Last Name
				driver.findElement(By.name("lastName")).sendKeys(L_Name.toString());
				// Entering Zip/Postal Code
				driver.findElement(By.name("postalCode")).sendKeys(PC_zip.toString());
				fis.close();
				Thread.sleep(2000);

				// Clicking on Continue
				driver.findElement(By.xpath("//input[@type='submit']")).click();
				System.out.println();

				// Printing Cart List
				String myText1 = driver.findElement(By.xpath("//div[@class='cart_list']")).getText();
				System.out.println(myText1);
				System.out.println();

				// Printing Payment Information
				String myText2 = driver.findElement(By.xpath("//div[@class='summary_info']")).getText();
				System.out.println(myText2);
				System.out.println();

				// Clicking on Finish
				driver.findElement(By.name("finish")).click();

				// Printing Thank you
				String myText3 = driver.findElement(By.xpath("//h2[text()='Thank you for your order!']")).getText();
				System.out.println(myText3);
				System.out.println();

				// Printing Dispatching of Order
				String myText4 = driver.findElement(By.xpath("//div[@class='complete-text']")).getText();
				System.out.println(myText4);
				System.out.println();

				// Clicking on Back Home
				driver.findElement(By.id("back-to-products")).click();

			}
			Thread.sleep(1000);

			// Clicking on Twitter Button
			driver.findElement(By.xpath("//li[@class='social_twitter']/child::a")).click();

			// Clicking on Facebook Button
			driver.findElement(By.xpath("//li[@class='social_facebook']/child::a")).click();

			// Clicking on LinkedIn Button
			driver.findElement(By.xpath("//li[@class='social_linkedin']/child::a")).click();

			// Clicking on Menu Button
			driver.findElement(By.id("react-burger-menu-btn")).click();
			driver.findElement(By.xpath("//a[text()='About']")).click();
			driver.navigate().back();
			Thread.sleep(500);
			driver.findElement(By.id("logout_sidebar_link")).click();
		}

		// Closing the Browser
		driver.quit();

	}

}

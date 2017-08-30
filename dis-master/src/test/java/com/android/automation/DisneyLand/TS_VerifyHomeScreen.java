package com.android.automation.DisneyLand;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.android.automation.application.ApplicationLibrary;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class TS_VerifyHomeScreen {
	
	private static final Logger LOGGER = Logger.getLogger(TS_VerifyHomeScreen.class.getName());
	private AndroidDriver<MobileElement> driver;
	private AppiumDriverLocalService appiumService;
	private ApplicationLibrary appLib;

	public TS_VerifyHomeScreen() {
		appLib = new ApplicationLibrary(driver);
	}

	@BeforeTest
	public void setUpAppium() throws MalformedURLException {
		final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
		URL url = new URL(URL_STRING);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("VERSION", "7.0");
		capabilities.setCapability("deviceName", "a721dacc0703");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.disney.wdpro.dlr");
		capabilities.setCapability("appActivity", "com.disney.wdpro.park.activities.SplashActivity");
		driver = new AndroidDriver<MobileElement>(url, capabilities);
	}

	@Test
	public void verifyHomeScreen() {

		appLib.delay(30000);
		
		/*boolean max = driver.findElements(By.xpath("//android.widget.Button[@text='Continue']")).size() > 0;
		if(max){
			LOGGER.info("maxmaxmax@@@@@@@@@@");
			driver.findElement(By.xpath("//android.widget.Button[@text='Continue']")).click();
			appLib.delay(7000);
		}*/
		boolean max = driver.findElements(By.xpath("//android.view.View[@content-desc='Continue']")).size() > 0;
		if(max){
			LOGGER.info("maxmaxmax@@@@@@@@@@");
			driver.findElement(By.xpath("//android.view.View[@content-desc='Continue']")).click();
			appLib.delay(7000);
		}
				
		boolean noInternetDialog = driver.findElements(By.id("com.disney.wdpro.dlr:id/dismiss_banner_button")).size() > 0;
		if(noInternetDialog){
			driver.findElement(By.id("com.disney.wdpro.dlr:id/dismiss_banner_button")).click();
		}
		
		boolean maxPass = driver.findElements(By.xpath("//android.widget.TextView[@text='Choose Your Tickets']")).size() > 0;
		if(maxPass){
			driver.findElement(By.id("//android.widget.TextView[@text='Choose Your Tickets']")).click();
			appLib.delay(15000);
		}
		
		boolean cachingPolicyAlert = driver.findElements(By.xpath("//android.widget.Button[@text='Close']")).size() > 0;
		if(cachingPolicyAlert){
			driver.findElement(By.id("fr.disneylandparis.android:id/btn_thanks")).click();
			appLib.delay(15000);
			LOGGER.info("Clicked successfully on Close");
		}

		boolean mickyIcon = driver.findElements(By.id("com.disney.wdpro.dlr:id/img_avatar")).size() > 0;
		if(mickyIcon){
			LOGGER.info("mickyIcon-mickyIcon-mickyIcon-mickyIcon@@@@@@@@@@@@@@@@@@@@");
			driver.findElement(By.id("com.disney.wdpro.dlr:id/img_avatar")).click();
		}
		appLib.delay(10000);
		
		boolean max2 = driver.findElements(By.xpath("//android.view.View[@content-desc='Continue']")).size() > 0;
		if(max2){
			LOGGER.info("maxmaxmax@@@@@@@@@@");
			driver.findElement(By.xpath("//android.view.View[@content-desc='Continue']")).click();
			appLib.delay(7000);
		}
		
		appLib.delay(30000);
		String homeScreenTitle = driver.findElement(By.id("com.disney.wdpro.dlr:id/section_text_title")).getText();
		// Verify 'Park Info & Entry' screen is displayed
		Assert.assertEquals("Park Info & Entry", homeScreenTitle);

		List<MobileElement> parkInfoSection = driver.findElements(By.id("text_view_cta_button"));
		WebElement todaysShowtimes = parkInfoSection.get(0);
		WebElement parkHours = parkInfoSection.get(1);
		WebElement myTickets = parkInfoSection.get(2);
		WebElement BuyTickets = parkInfoSection.get(3);

		BuyTickets.click();
		appLib.delay(10000);

		Assert.assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@text='Choose Your Tickets']")).isDisplayed());

		// Purchasing a ticket 
		appLib.waitForElementVisibility(By.id("number_of_days_text_view"));
		List<MobileElement> numberofdays = driver.findElements(By.id("number_of_days_text_view"));
		WebElement two = numberofdays.get(1);
		two.click();
		appLib.delay(5000);

		WebElement add = driver.findElement(By.id("com.disney.wdpro.dlr:id/plus_button"));
		add.click();
		appLib.delay(3000);

		Dimension size2 = driver.manage().window().getSize();

		int x1 = (int) (size2.width / 2);
		int startY1 = (int) (size2.height * 0.90);
		int endY1 = (int) (size2.height * 0.20);
		driver.swipe(x1, startY1, x1, endY1, 1000);

		WebElement oneparkperday = driver.findElement(By.id("com.disney.wdpro.dlr:id/price_information_section_front"));
		oneparkperday.click();
		//		appLib.delay(1000);
		appLib.waitForElementVisibility(By.id("txt_username"));
		WebElement email = driver.findElement(By.id("txt_username"));
		email.click();
		appLib.delay(2000);
		email.clear();
		appLib.delay(2000);
		email.sendKeys("pharsha@test.com");

		WebElement password = driver.findElement(By.id("txt_password"));
		password.click();
		password.sendKeys("test123!");

		WebElement signin = driver.findElement(By.id("com.disney.wdpro.dlr:id/btn_login"));
		signin.click();

		appLib.delay(10000);
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}
}

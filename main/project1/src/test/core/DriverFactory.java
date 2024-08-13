package test.core;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
	public static  WebDriver	webChromeDriver;
	
	public static WebDriver getDriver() {
		if (webChromeDriver == null) {
			createDriver();
		}
		return webChromeDriver;	
	}
	

	public static void createDriver(){
		System.setProperty("webdriver.chrome.driver", "/Users/guilhermetenorio/Downloads/drivers/chromedriver114");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		webChromeDriver = new ChromeDriver(options);
//		webDriver.get("https://www.ib12.tu.teste.internet/ibpfnovologin/login.htm");
		webChromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}
	public static void killDRiver() {
		if (webChromeDriver != null ) {
			webChromeDriver.quit();
			webChromeDriver = null;		
		}
}
}




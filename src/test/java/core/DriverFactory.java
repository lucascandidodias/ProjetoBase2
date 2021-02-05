package core;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {

	private static WebDriver driver;

	private DriverFactory() {
	}

	public static WebDriver getDriver(String browser) {
		String firefox;
		String chrome;

		String cwd = System.getProperty("user.dir");
		String sistema = System.getProperty("os.name");
		if (sistema.equalsIgnoreCase("linux")) {
			System.out.println("Driver Linux");
			chrome = "/drivers/chromedriver";
			firefox = "/drivers/geckodriver";
		} else {
			System.out.println("Driver windows");
			chrome = "/drivers/chromedriver.exe";
			firefox = "/drivers/geckodriver.exe";
		}

		if (driver == null) {

			if (browser.equals("firefox")) {
				FirefoxOptions options = new FirefoxOptions();
				String pathselenium = cwd + firefox;
				System.setProperty("webdriver.gecko.driver", pathselenium);
				options.setAcceptInsecureCerts(true);
				driver = new FirefoxDriver(options);
			} else if (browser.equals("chrome")) {
				String pathselenium = cwd + chrome;
				ChromeOptions options = new ChromeOptions();
				System.setProperty("webdriver.chrome.driver", pathselenium);
				System.out.println("Chrome driver: " + pathselenium);
				driver = new ChromeDriver(options);
			}
		}
		return driver;
	}

	public static void killDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

}

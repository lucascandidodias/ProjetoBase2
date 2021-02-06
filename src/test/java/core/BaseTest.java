package core;

import org.openqa.selenium.WebDriver;

public class BaseTest {

	String browser;

	public BaseTest(String browser) {
		this.browser = browser;
	}

	public WebDriver inicializa() {
		WebDriver driver = DriverFactory.getDriver(browser);
		driver.manage().window().maximize();
		return driver;

	}

	public void finaliza() {
		DriverFactory.killDriver();
	}

}

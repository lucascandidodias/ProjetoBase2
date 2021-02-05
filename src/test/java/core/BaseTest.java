package core;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;



/**
 * @author eduardo.lana
 */
public class BaseTest {
	
	String browser;
	public BaseTest(String browser) {
		this.browser = browser;
	}
	
     public WebDriver inicializa()
       { 
    		WebDriver driver = DriverFactory.getDriver( browser);
    		driver.manage().window().maximize();
    	    return driver;
    	
       }
    

     public void finaliza()
        {	
            DriverFactory.killDriver();
        }
    
}

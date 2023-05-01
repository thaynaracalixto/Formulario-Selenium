package br.ce.thaynara.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static br.ce.thaynara.core.DriverFactory.getDriver;
import br.ce.thaynara.core.DriverFactory;

public class TesteGoogle {
	
	
	@Before
	public void Inicializar () {
		DriverFactory.getDriver().get("http://www.google.com");
		
	}
	@After
	public void FecharBrowser() {
		 DriverFactory.killDriver();
	}
	
	@Test
	public void teste() {
//    	System.setProperty("webgetDriver().gecko.driver", "/Users/thayn/Downloads/Drivers/geckodriver");
//		System.setProperty("webgetDriver().chrome.driver", "/Users/thayn/Downloads/Drivers/chromedriver");
//      WebDriver Driver = new ChromeDriver();
//		getDriver().manage().window().maximize();
		Assert.assertEquals("Google", getDriver().getTitle());
	}
	

}
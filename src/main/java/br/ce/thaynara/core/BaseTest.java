package br.ce.thaynara.core;
import static br.ce.thaynara.core.DriverFactory.getDriver;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {
	
	@Rule
	public TestName testName = new TestName();
	
	@After
	public void FecharBrowser() throws IOException {
		TakesScreenshot scrensh = (TakesScreenshot) getDriver();
		File arquivo = scrensh.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(arquivo, new File(testName.getMethodName() + ".jpg"));
		
		
		if(Propriedades.FECHAR_BROWSER) {
			DriverFactory.killDriver();
		}	
	}

}

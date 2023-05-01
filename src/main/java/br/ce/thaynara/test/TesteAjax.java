package br.ce.thaynara.test;
import java.time.Duration;
import static br.ce.thaynara.core.DriverFactory.getDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import br.ce.thaynara.core.DSL;
import br.ce.thaynara.core.DriverFactory;

public class TesteAjax {
	
	private DSL dsl;
		
	@Before
	public void Inicializa(){
		DriverFactory.getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl = new DSL();
	}	
	@After
	public void FecharBrowser(){
		DriverFactory.killDriver();
	}
	
	@Test
	public void TestAjax() {
		dsl.escreve("j_idt343:name", "Teste");
		dsl.clicarBotao("j_idt343:j_idt347");
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
		wait.until(ExpectedConditions.textToBe(By.id("j_idt343:display"), "Teste"));
		Assert.assertEquals("Teste", dsl.obterTexto("j_idt343:display"));
	}

}

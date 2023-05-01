package br.ce.thaynara.test;
import static br.ce.thaynara.core.DriverFactory.getDriver;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import br.ce.thaynara.core.DSL;
import br.ce.thaynara.core.DriverFactory;

public class TesteSincronismo {

	private DSL dsl;
		
	@Before
	public void Inicializa(){
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}	
	@After
	public void FecharBrowser(){
		DriverFactory.killDriver();		
	}
	
	@Test
	//O elemento não foi encontrado, pois o selenium deu um comando de escrita para o novo campo, porém a página ainda não havia carregado.
	public void deveUtilizarEsperaFixa() throws InterruptedException {
		dsl.clicarBotao("buttonDelay");
		Thread.sleep(5000); //Espera Fixa (Definida por um tempo pré determinado)
		dsl.escreve("novoCampo", "Thaynara");
	}
	
	@Test
	public void deveUtilizarEsperaImplicita() throws InterruptedException {
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		dsl.clicarBotao("buttonDelay");
		dsl.escreve("novoCampo", "Thaynara");
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	
	@Test
	public void deveUtilizarEsperaExplicita() throws InterruptedException {
		dsl.clicarBotao("buttonDelay");
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escreve("novoCampo", "Thaynara");
	}
}

package br.ce.thaynara.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import br.ce.thaynara.core.DSL;
import br.ce.thaynara.core.DriverFactory;

public class TesteAlert {
	
	private DSL dsl;
	
	@Before
	public void Inicializar () {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		dsl = new DSL();
	}
	
	@After 
	public void FecharBrowser(){
		DriverFactory.killDriver();
	}
	
	@Test
	public void deveInteragirComAlertSimples(){		
		dsl.clicarBotao("alert");
		String texto = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Alert Simples", texto);
		
		dsl.escreve("elementosForm:nome", texto);
	}	
	
	@Test
	@Ignore
	public void deveInteragirComAlertConfirm(){		
		dsl.clicarBotao("confirm"); 
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceita());
		Assert.assertEquals("Confirmado", dsl.alertaObterTextoEAceita());
		
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENega());
		Assert.assertEquals("Negado", dsl.alertaObterTextoENega());
	}
	
	@Test
	@Ignore
	public void deveInteragirComAlertPrompt(){		
		dsl.clicarBotao("prompt");
		Assert.assertEquals("Digite um numero", dsl.alertaObterTexto() );
		dsl.alertaEscrever("12");
		Assert.assertEquals("Era 12?", dsl.alertaObterTextoEAceita());
		Assert.assertEquals(":D", dsl.alertaObterTextoEAceita());
		}		
}

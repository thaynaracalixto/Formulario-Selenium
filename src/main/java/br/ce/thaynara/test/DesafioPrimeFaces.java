package br.ce.thaynara.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.thaynara.core.DSL;
import br.ce.thaynara.core.DriverFactory;

public class DesafioPrimeFaces {
		
	private DSL dsl;
		
	@Before
	public void Inicializa(){
		DriverFactory.getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		dsl = new DSL();
	}	
	@After
	public void FecharBrowser(){
		DriverFactory.killDriver();
	}
	
	@Test
	public void deveInteragirComCombo() {
		dsl.clicarRadio(By.xpath("//*[@id=\"j_idt343:option\"]/../..//span"));
		dsl.clicarRadio(By.xpath("//*[@id=\"j_idt343:option_items\"]//li[.=\"Option2\"]"));
		Assert.assertEquals(dsl.obterTexto("j_idt343:option_label"), "Option2");
		//dsl.selecionarCombo("j_idt343:option_input", "Option2");
	}
}

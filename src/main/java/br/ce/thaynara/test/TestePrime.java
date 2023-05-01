package br.ce.thaynara.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.thaynara.core.DSL;
import br.ce.thaynara.core.DriverFactory;

public class TestePrime {
	
	
	private DSL dsl;
		
	@Before
	public void Inicializa(){
		DriverFactory.getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		dsl = new DSL();
	}	
	@After
	public void FecharBrowser(){
		DriverFactory.killDriver();	
	}
	@Test
	public void deveInteragirComRadioPrime () {
		dsl.clicarRadio(By.xpath("//input[@id=\"j_idt344:console:1\"]/../..//span"));	
		Assert.assertTrue(dsl.isRadioMarcado("j_idt344:console:1"));
		//input[@id="j_idt344:console:1"]/../..//span
	}
	@Test
	public void deveInteragirComOutrasOpcoes() {
		//dsl.clicarRadio(By.xpath("//label[.= \'Opção 3\']/..//span"));
		dsl.clicarRadio(By.xpath("//input[@id=\"j_idt344:console:2\"]/../..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt344:console:2"));
		//label[.= "Opção 3"]/..//span
	}	
}

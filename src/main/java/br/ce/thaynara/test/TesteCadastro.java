package br.ce.thaynara.test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.thaynara.core.BaseTest;
import br.ce.thaynara.core.DriverFactory;
import br.ce.thaynara.page.CampodeTreinamentoPage;


public class TesteCadastro extends BaseTest {
	
	//Instâncias
	private CampodeTreinamentoPage page;
	
	@Before
	public void inicializa() {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");		
		page = new CampodeTreinamentoPage();
	}

	@Test
	public void DesafioCadastro() {
		page.setNome("Thaynara");
		page.setSobrenome("Calixto");
		page.setSexoFeminino();
		page.setComidaCarne();
		page.setEscolaridade("Superior");
		page.setEsporte("Corrida");
		page.cadastrar();
		
		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastrado());
		Assert.assertEquals("Thaynara", page.obterNomeCadastro());
		Assert.assertEquals("Calixto", page.obterSobrenomeCadastro());
		Assert.assertEquals("Feminino", page.obterSexoCadastro());
		Assert.assertEquals("superior", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Corrida", page.obterEsportesCadastro());
		Assert.assertEquals("", page.obterResultadoSugestoes());
	}
}



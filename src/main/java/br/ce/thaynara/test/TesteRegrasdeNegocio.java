package br.ce.thaynara.test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.thaynara.core.BaseTest;
import br.ce.thaynara.core.DSL;
import br.ce.thaynara.core.DriverFactory;
import br.ce.thaynara.page.CampodeTreinamentoPage;

public class TesteRegrasdeNegocio extends BaseTest {
	
	private DSL dsl;
	private CampodeTreinamentoPage page;
	
	@Before
	public void inicializa() {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");	
		dsl = new DSL();
		page = new CampodeTreinamentoPage();
	}
	
	@Test
	public void TesteCamposObrigatorios (){	
		page.cadastrar();
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
				
		page.setNome("Thaynara");
		page.cadastrar();
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
		
		page.setSobrenome("Calixto");
		page.cadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
		
		page.setSexoFeminino();
		dsl.isRadioMarcado("elementosForm:sexo:1");
		page.cadastrar();
		
		Assert.assertTrue(page.obterResultadoCadastrado().startsWith("Cadastrado!"));
		Assert.assertTrue(page.obterNomeCadastro().endsWith("Thaynara"));
		Assert.assertTrue(page.obterSobrenomeCadastro().endsWith("Calixto"));
		Assert.assertTrue(page.obterSexoCadastro().endsWith("Feminino"));
	}
	
	@Test
	public void TestarRegraComidaFavorita () {	
		page.setNome("Thaynara");
		page.setSobrenome("Calixto");
		page.setSexoFeminino();
		page.setComidaCarne();
		page.setComidaVegetariano();
		page.cadastrar();
				
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
				
		//Validar que após desmarcar Vegetariano passe:
		page.setComidaCarne();
		page.cadastrar();
		Assert.assertTrue(page.obterResultadoCadastrado().startsWith("Cadastrado!"));		
	}
	
	@Test
    public void TestarRegraEsportes() {
		page.setNome("Thaynara");
		page.setSobrenome("Calixto");
		page.setSexoFeminino();
		page.setComidaCarne();
		page.setEsporte("Corrida");
		page.setEsporte("O que eh esporte?");
		page.cadastrar();
		
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
				
		//Validar que depois de deselecionar "o que eh esporte?" passe
		dsl.deselecionarCombo("elementosForm:esportes", "O que eh esporte?");
		page.cadastrar();
		Assert.assertTrue(page.obterEsportesCadastro().endsWith("Corrida"));
	}
	
	@Test
    public void ValidarCadastrar() {
		page.setNome("Thaynara");
		page.setSobrenome("Calixto");
		page.setSexoFeminino();
		page.setComidaCarne();
		page.setEscolaridade("Superior");
		page.setEsporte("Corrida");
		page.cadastrar();
		
		Assert.assertTrue(page.obterResultadoCadastrado().startsWith("Cadastrado!"));
		Assert.assertTrue(page.obterNomeCadastro().endsWith("Thaynara"));
		Assert.assertTrue(page.obterSobrenomeCadastro().endsWith("Calixto"));
		Assert.assertTrue(page.obterSexoCadastro().endsWith("Feminino"));
		Assert.assertTrue(page.obterComidaCadastro().endsWith("Carne"));
		Assert.assertTrue(page.obterEscolaridadeCadastro().endsWith("superior"));
		Assert.assertTrue(page.obterEsportesCadastro().endsWith("Corrida"));
		Assert.assertTrue(page.obterResultadoSugestoes().endsWith(""));
	}
}

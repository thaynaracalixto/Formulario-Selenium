package br.ce.thaynara.test;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.thaynara.core.BaseTest;
import br.ce.thaynara.core.DSL;
import br.ce.thaynara.core.DriverFactory;
import br.ce.thaynara.page.CampodeTreinamentoPage;

public class TesteCampoTreinamento extends BaseTest{
	
private CampodeTreinamentoPage page;

	private DSL dsl;
	
	@Before
	public void inicializa() {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");		
		page = new CampodeTreinamentoPage();
		dsl = new DSL();
	}
	
	@Test
	public void testeTextField(){		
		page.setNome("Thaynara");
		Assert.assertEquals("Thaynara", dsl.obterValorCampos("elementosForm:nome"));
	}
	
	@Test
	public void testeTextFieldSobrenome(){		
		page.setNome("Thaynara");;
		Assert.assertEquals("Thaynara", dsl.obterValorCampos("elementosForm:nome"));
		page.setSobrenome("Calixto");
		Assert.assertEquals("Calixto", dsl.obterValorCampos("elementosForm:sobrenome"));
	}
	
	@Test
	public void testeTextFieldDuplo(){	
		//para limpar o campo, pois na dsl tem o clear.
		page.setNome("Thaynara");
		Assert.assertEquals("Thaynara", dsl.obterValorCampos("elementosForm:nome"));
		page.setNome("Arthur");
		Assert.assertEquals("Arthur", dsl.obterValorCampos("elementosForm:nome"));
	}
	
	@Test
    public void deveInteragirComTextArea(){		
		dsl.escreve("elementosForm:sugestoes", "Teste");
		Assert.assertEquals("Teste", dsl.obterValorCampos("elementosForm:Sugestoes"));
	}
	
	@Test
    public void deveInteragirComRadioButton(){		
		page.setSexoFeminino();
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:1"));
	}
	
	@Test
    public void deveInteragirComCheckBox(){		
		page.setComidaCarne();
		Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:0"));
	}
	
	@Test
    public void deveInteragirComCombo(){		
		page.setEscolaridade("Superior");
		Assert.assertEquals("Superior", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
	
	@Test
    public void deveVerificarValoresCombo(){		
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
	}
	
	@Test
    public void deveVerificarValoresComboMultiplo(){		
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		dsl.deselecionarCombo("elementosForm:esportes", "O que eh esporte?");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "Corrida")));		
	}	
	
	@Test
    public void deveinteragirComBotoes(){
		
		dsl.clicarBotao("buttonSimple");	
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
	}
	
	@Test
    public void deveinteragirComLinks(){		
		dsl.clicarLink("Voltar");
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}	
	
	@Test
    public void deveBuscarTextosNaPagina(){
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}
	@Test
	public void deveClicarBotaoTabela() {
		dsl.clicarBotaoTabela("Escolaridade", "Mestrado", "Radio", "elementosForm:tableUsuarios");
	}
	
}
		//DevebuscarTextosNaPagina
		//Assert.assertTrue(getDriver().findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));	
		//Não é eficiente, pois dependendo do tamanho da página o log fica muito lento.
		//além disso ele não informa em qual campo esta
		//Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", getDriver().findElement(By.tagName("span")).getText());
		//forma generica: Ele retornou a primeira ocorrencia, pois havia um "span" antes do que estavamos procurando
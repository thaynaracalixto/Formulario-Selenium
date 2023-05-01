package br.ce.thaynara.page;
import org.openqa.selenium.By;
import br.ce.thaynara.core.BasePage;

public class CampodeTreinamentoPage extends BasePage {


	public void setNome(String nome) {
		dsl.escreve("elementosForm:nome", nome);		
	}
	
	public void setSobrenome(String sobrenome) {
		dsl.escreve("elementosForm:sobrenome", sobrenome);
	}
	
	public void setSexoFeminino() {
		dsl.clicarRadio("elementosForm:sexo:1");
	}
	
	public void setSexoMasculino() {
		dsl.clicarRadio("elementosForm:sexo:0");
	}
	
	public void setComidaCarne() {
		dsl.clicarCheck("elementosForm:comidaFavorita:0");
	}
	
	public void setComidaFrango() {
		dsl.clicarCheck("elementosForm:comidaFavorita:1");
	}
	
	public void setComidaPizza() {
		dsl.clicarCheck("elementosForm:comidaFavorita:2");
	}
	
	public void setComidaVegetariano() {
		dsl.clicarCheck("elementosForm:comidaFavorita:3");
	}
	
	
	public void setEscolaridade(String valor) {
		dsl.selecionarCombo("elementosForm:Escolaridade", valor);
	}
	
	public void setEsporte(String valor) {
		dsl.selecionarCombo("elementosForm:esportes", valor);	
	}
	
	public void cadastrar() {
		dsl.clicarBotao("elementosForm:cadastrar");
	}
	
	public String obterResultadoCadastrado() {
		return dsl.obterTexto(By.xpath("//*[@id='resultado']/span"));
	}
	
	public String obterNomeCadastro() {
		return dsl.obterTexto(By.xpath("//*[@id='descNome']/span"));
	}
	
	public String obterSobrenomeCadastro() {
		return dsl.obterTexto(By.xpath(("//*[@id='descSobrenome']/span")));
	}
	
	public String obterSexoCadastro() {
		return dsl.obterTexto(By.xpath(("//*[@id='descSexo']/span")));
	}
	
	public String obterComidaCadastro() {
		return dsl.obterTexto(By.xpath(("//*[@id='descComida']/span")));
	}
	
	public String obterEscolaridadeCadastro() {
		return dsl.obterTexto(By.xpath(("//*[@id='descEscolaridade']/span")));
	}
	
	public String obterEsportesCadastro() {
		return dsl.obterTexto(By.xpath(("//*[@id='descEsportes']/span")));
	}
	
	public String obterResultadoSugestoes() {
		return dsl.obterTexto(By.xpath(("//*[@id='descSugestoes']/span")));
	}

}

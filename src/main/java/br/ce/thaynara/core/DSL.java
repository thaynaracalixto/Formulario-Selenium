package br.ce.thaynara.core;
import static br.ce.thaynara.core.DriverFactory.getDriver;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {		
	
	//**********TextField e TexArea
	
	public void escreve(By by, String texto){
		getDriver().findElement(by).clear();
		getDriver().findElement(by).sendKeys(texto);		
	}
	
	public void escreve(String id_campo, String texto){
		escreve(By.id(id_campo), texto);
	}
	
	public String obterValorCampos(String id_campo){		
		return getDriver().findElement(By.id(id_campo)).getAttribute("value");
	}
	
	//**********Radio e Check
	public void clicarRadio(By by){		
		getDriver().findElement(by).click();
	}
	
	public void clicarRadio(String id){		
		clicarRadio(By.id(id));
	}
	
	public boolean isRadioMarcado(String id){		
		return getDriver().findElement(By.id(id)).isSelected();
	}
	
	public void clicarCheck(String id){
		getDriver().findElement(By.id(id)).click();
	}
	
	public boolean isCheckMarcado(String id){
		return getDriver().findElement(By.id(id)).isSelected();
	}
	
	//************Combo
		
	public void selecionarCombo(String id, String valor) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}
	
	public void deselecionarCombo(String id, String valor){
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.deselectByVisibleText(valor);
	}
	
	//1. ObterValoresCombo: ele busca o combo, coleta todos os valores que estão selecionados nele 
	//e cria uma lista contendo o texto de todos esses valores.
	public String obterValorCombo(String id){
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	public List<String> obterValoresCombo(String id){
		WebElement element = getDriver().findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
	    List<String> valores = new ArrayList<String>();
		for(WebElement opcao: allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;		
	}
	
	//2. obterQuantidadeOpcoesCombo: Ele busca o combo, coleta uma lista com todas as opções disponíveis 
	//e retorna o tamanho dessa lista
	public int  obterQuantidadeOpcoesCombo(String id){
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
	
	//3. verificarOpcaoCombo: Esse método obtém todas as opções do combo 
	//e verifica uma a uma se contém a opção passada por parametro.
	public boolean verificarOpcaoCombo(String id, String opcao) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for(WebElement option: options) {
			if(option.getText().equals(opcao)){
				return true;
			}
		}
		return false;		
	}	
	
	//*********Botão
	
	public void clicarBotao(String id){
		getDriver().findElement(By.id(id)).click();
	}
	
	public String obterValueElemento(String id){
		return getDriver().findElement(By.id(id)).getAttribute("value");
	}
	
	//**********Link
	public void clicarLink(String link){
		getDriver().findElement(By.linkText(link)).click();
	}
	
	//**********Textos
	
	public String obterTexto(By by){
		return getDriver().findElement(by).getText();
	}
	
	public String obterTexto(String id) {
		return obterTexto(By.id(id));
	}
	
	//*********Alerts
	
	public String alertaObterTexto() {
		Alert alert = getDriver().switchTo().alert();
		return alert.getText();
	}
	
	public String alertaObterTextoEAceita(){
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;
	}
	
	public String alertaObterTextoENega(){
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor; 		
	}
	
	public void alertaEscrever(String valor) {
		Alert alert = getDriver().switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}
	
	//**********Janelas e Frames
	
	public void entrarFrame(String id){
		getDriver().switchTo().frame(id);		
	}
	
	public void sairFrame(){
		getDriver().switchTo().defaultContent();		
	}
	
	public void trocarJanela(String id) {
		getDriver().switchTo().window(id);		
	}
	/***************** JS *****************/
	
	public Object executarJS (String comando, Object... param ) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
				return js.executeScript(comando, param);
	}
	
	/****************** Tabela *************/
	//no Xpath o indice começa em 1
	public void clicarBotaoTabela(String tituloColuna, String valor, String colunaBotao, String idTabela) {
		//procurar coluna do registro
		WebElement tabela = getDriver().findElement(By.xpath("//*[@id=\"elementosForm:tableUsuarios\"]"));
		int idColuna = obterIndiceColuna(tituloColuna, tabela);
		
		//encontrar linha do registro
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);
		
		//procurar coluna do botao
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		
		//clicar no botao 
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		celula.findElement(By.xpath(".//input")).click();		
				
	}

	private int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		int idLinha = -1;
		for (int i = 0; i < linhas.size(); i++) {
			if(linhas.get(i).getText().equals(valor)) {
				idLinha = i+1;
				break;
			}
		}
		return idLinha;
	}		
		
	private int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for (int i = 0; i < colunas.size(); i++) {
			if(colunas.get(i).getText().equals(coluna)) {
				idColuna = i+1;
				break;
			}
		}
		return idColuna;
	}
	
	
}

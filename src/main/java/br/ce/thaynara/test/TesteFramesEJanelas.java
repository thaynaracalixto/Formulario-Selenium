package br.ce.thaynara.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static br.ce.thaynara.core.DriverFactory.getDriver;
import br.ce.thaynara.core.DSL;
import br.ce.thaynara.core.DriverFactory;

public class TesteFramesEJanelas {

	private DSL dsl;

	@Before
	public void Inicializa() {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}

	@After
	public void FecharBrowser() {
		DriverFactory.killDriver();
	}

	@Test
	public void deveInteragirComFrames() {
		dsl.entrarFrame("Frame1");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);
		dsl.sairFrame();
		dsl.escreve("elementosForm:nome", msg);
	}

	// Usando JS para resolver o problema de encontrar o botão do Frame na tela
	@Test
	public void deveInteragirComFrameEscondido() {
		WebElement frame = getDriver().findElement(By.id("Frame2"));
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("Frame2");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);
		// dsl.sairFrame();
		// dsl.escreve("elementosForm:nome", msg );
	}

	@Test
	public void deveInteragirComJanelas() {
		dsl.clicarBotao("buttonPopUpEasy");
		dsl.trocarJanela("Popup");
		dsl.escreve(By.tagName("textarea"), "Deu Certo?");
		getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[0]);
		dsl.escreve(By.tagName("textarea"), "E Agora?");
	}

	@Test
	public void deveInteragirComJanelasSemTitulo() {
		dsl.clicarBotao("buttonPopUpHard");
		System.out.println(getDriver().getWindowHandle());
		System.out.println(getDriver().getWindowHandles());
		dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[1]);
		dsl.escreve(By.tagName("textarea"), "Deu Certo??");
		dsl.trocarJanela((String) getDriver().getWindowHandles().toArray()[0]);
		dsl.escreve(By.tagName("textarea"), "E agora?");
	}

}

package br.ce.thaynara.suites;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.thaynara.core.DriverFactory;
import br.ce.thaynara.test.TesteCadastro;
import br.ce.thaynara.test.TesteCampoTreinamento;
import br.ce.thaynara.test.TesteRegrasdeNegocio;

@RunWith(Suite.class)
@SuiteClasses ({
	TesteCadastro.class,
	TesteRegrasdeNegocio.class,
	TesteCampoTreinamento.class
	})


public class SuiteTestes {
	
	@AfterClass
	public static void finalizaTudo() {
		DriverFactory.killDriver();
	}
	

}

package test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import core.BaseTest;
import core.DriverFactory;
import pages.LoginPage;

public class LoginTest {

	static WebDriver driver;
	static BaseTest baseTest;
	LoginPage loginPage;

	@BeforeAll
	public static void inicializa() throws FileNotFoundException {
		baseTest = new BaseTest("chrome");
	}

	@AfterAll
	public static void finaliza() {
		baseTest.finaliza();
	}

	@BeforeEach
	public void start(TestInfo testInfo) {
		//screen = new ScreenShot();
		driver = baseTest.inicializa();
		DriverFactory.getDriver("chrome").get("https://mantis-prova.base2.com.br/login_page.php");
		loginPage = new LoginPage(driver);
		// screen.excluirEvidencia(acessoAmbiente, codCenario);
	}

	@AfterEach
	public void atualiza() {
		// screen.print("_Finalizacao_Cenario", "", driver, "");
		//ScreenShot.indiceScreenShot = 1;
		baseTest.finaliza();
	}

	@Test
	@DisplayName("CT-01")
	public void loginInvalido() {
		loginPage.setUsuario(driver, "usuarioinvalido");
		loginPage.setSenha(driver, "949494");
		loginPage.clicarBotaoLogin(driver);
		assertEquals("Your account may be disabled or blocked or the username/password you entered is incorrect.", loginPage.obterMensagemErroLogin(driver));

	}

}

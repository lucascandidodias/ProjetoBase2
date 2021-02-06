package test;

import java.io.FileNotFoundException;
import java.util.Properties;

import static org.junit.Assert.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import core.BaseTest;
import core.DriverFactory;
import core.ScreenShot;
import pages.LoginPage;
import utils.ConfiguracaoPropeties;

public class LoginTest {

	static WebDriver driver;
	static BaseTest baseTest;
	static ScreenShot screen;
	String codCenario;;
	LoginPage loginPage;
	static Properties conf;
	static ConfiguracaoPropeties properties = new ConfiguracaoPropeties();

	@BeforeAll
	public static void inicializa() throws FileNotFoundException {
		baseTest = new BaseTest("chrome");
		conf = properties.lerArquivoConfiguracao();
	}

	@AfterAll
	public static void finaliza() {
		baseTest.finaliza();
	}

	@BeforeEach
	public void start(TestInfo testInfo) {
		screen = new ScreenShot();
		driver = baseTest.inicializa();
		DriverFactory.getDriver("chrome").get("https://mantis-prova.base2.com.br/login_page.php");
		loginPage = new LoginPage(driver);
		codCenario = testInfo.getDisplayName();
		screen.excluirEvidencia(codCenario);
	}

	@AfterEach
	public void atualiza() {
		screen.print("_Finalizacao_Cenario", codCenario, driver);
		ScreenShot.indiceScreenShot = 1;
		baseTest.finaliza();
	}
	
	@Test
	@DisplayName("CT-01")
	public void loginInvalido() {
		loginPage.setUsuario("usuarioinvalido");
		loginPage.setSenha("949494");
		screen.print("Usuario e Senha", codCenario, driver);
		loginPage.clicarBotaoLogin();
		assertEquals("Your account may be disabled or blocked or the username/password you entered is incorrect.",
				loginPage.obterMensagemErroLogin());
	}

	@Test
	@DisplayName("CT-02")
	public void loginValido() {
		loginPage.setUsuario(conf.getProperty("login.usuario"));
		loginPage.setSenha(conf.getProperty("login.senha"));
		screen.print("Usuario e Senha", codCenario, driver);
		loginPage.clicarBotaoLogin();
		assertEquals(conf.getProperty("login.usuario").trim(), loginPage.obterNomeUsuarioTelaInicial());
	}

	@Test
	@DisplayName("CT-03")
	public void realizarLogout() {
		loginPage.setUsuario(conf.getProperty("login.usuario"));
		loginPage.setSenha(conf.getProperty("login.senha"));
		screen.print("Usuario e Senha", codCenario, driver);
		loginPage.clicarBotaoLogin();
		assertEquals(conf.getProperty("login.usuario").trim(), loginPage.obterNomeUsuarioTelaInicial());
		loginPage.clicarBotaoSair();
		assertTrue(loginPage.validarExistenciaBotaoLogin());

	}

}

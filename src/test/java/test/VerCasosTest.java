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
import pages.RelatarCasosPage;
import pages.VerCasosPage;
import utils.ConfiguracaoPropeties;

public class VerCasosTest {

		static WebDriver driver;
		static BaseTest baseTest;
		static ScreenShot screen;
		String codCenario;
		LoginPage loginPage;
		RelatarCasosPage relatarCasosPage;
		VerCasosPage verCasosPage;
		static Properties conf;
		static ConfiguracaoPropeties properties = new ConfiguracaoPropeties();
		String arquivo = System.getProperty("user.dir") + "/arquivoTeste/AqruivoTeste.docx";

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
			relatarCasosPage = new RelatarCasosPage(driver);
			verCasosPage = new VerCasosPage(driver);
			codCenario = testInfo.getDisplayName();
			screen.excluirEvidencia(codCenario);
			loginValido();
		}

		@AfterEach
		public void atualiza() {
			screen.print("_Finalizacao_Cenario", codCenario, driver);
			ScreenShot.indiceScreenShot = 1;
			baseTest.finaliza();
		}
		
		public void loginValido() {
			loginPage.setUsuario(conf.getProperty("login.usuario"));
			loginPage.setSenha(conf.getProperty("login.senha"));
			screen.print("Usuario e Senha", codCenario, driver);
			loginPage.clicarBotaoLogin();
			assertEquals(conf.getProperty("login.usuario").trim(), loginPage.obterNomeUsuarioTelaInicial());
		}
		
		@Test
		@DisplayName("CT-06")
		public void pesquisarCaso() {
			verCasosPage.acessarMenuVerCaso();
			verCasosPage.PesquisarCaso(conf.getProperty("ct06.numeroCaso"));
			screen.print("Pesquisando caso", codCenario, driver);
			verCasosPage.clicarBotaoIrPara();
			assertEquals(conf.getProperty("ct06.numeroCaso"), verCasosPage.obterNumeroCaso());
			assertEquals(conf.getProperty("ct04.projeto"), verCasosPage.obterNomeProjeto());
			assertEquals(conf.getProperty("login.usuario"), verCasosPage.obterNomeRelator());
			assertEquals(conf.getProperty("ct04.prioridade"), verCasosPage.obterPrioridade());
			assertEquals(conf.getProperty("ct04.gravidade"), verCasosPage.obterGravidade());
			assertEquals(conf.getProperty("ct04.frequencia"), verCasosPage.obterFrequencia());
			assertEquals(conf.getProperty("ct04.plataforma"), verCasosPage.obterPlataforma());
			assertEquals(conf.getProperty("ct04.sistemaOperacional"), verCasosPage.obterSo());
			assertEquals(conf.getProperty("ct04.VersaoSo"), verCasosPage.obterVersaoSo());
		}
		
	}


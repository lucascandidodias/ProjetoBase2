package test;

import java.io.FileNotFoundException;
import static org.junit.Assert.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import core.BaseTest;
import core.DriverFactory;
import core.ScreenShot;
import pages.LoginPage;
import pages.RelatarCasosPage;

public class RelatarCasosTest {

	static WebDriver driver;
	static BaseTest baseTest;
	static ScreenShot screen;
	String codCenario;;
	LoginPage loginPage;
	RelatarCasosPage relatarCasosPage;

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
		screen = new ScreenShot();
		driver = baseTest.inicializa();
		DriverFactory.getDriver("chrome").get("https://mantis-prova.base2.com.br/login_page.php");
		loginPage = new LoginPage(driver);
		relatarCasosPage = new RelatarCasosPage(driver);
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
		loginPage.setUsuario("lucas.dias");
		loginPage.setSenha("Mantis@123");
		screen.print("Usuario e Senha", codCenario, driver);
		loginPage.clicarBotaoLogin();
		assertEquals("lucas.dias", loginPage.obterNomeUsuarioTelaInicial());
	}
	
	@Test
	@DisplayName("CT-04")
	public void relatarCaso() {
		relatarCasosPage.acessarMenuRelatarCaso();
		assertEquals("Selecionar Projeto", relatarCasosPage.validarCarregamentoPaginaSelecionarProjeto());
		relatarCasosPage.selecionarProjeto("Lucas Dias's Project");
		relatarCasosPage.clicarBotaoSelecionarProjeto();
		assertTrue(relatarCasosPage.validarCarregamentoFormularioDeCaso());
		relatarCasosPage.selecionarCategoria("[Todos os Projetos] General");
		relatarCasosPage.selecionarFrequencia("aleatório");
		relatarCasosPage.selecionarGravidade("grande");
		relatarCasosPage.selecionarPrioridade("alta");
		relatarCasosPage.selecionarPerfil("PC Windows 7");
		relatarCasosPage.escreverPlataforma("PC");
		relatarCasosPage.escreverSistemaOperacional("Windows");
		relatarCasosPage.escreverVersaoSo("Windows 7");
		relatarCasosPage.escreverResumo("a");
		relatarCasosPage.escreverDescricao("a");
		relatarCasosPage.escreverPassos("a");
		relatarCasosPage.escreverInformacoesAdicionais("a");
	}

}

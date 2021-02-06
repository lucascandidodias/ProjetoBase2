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
import utils.ConfiguracaoPropeties;

public class RelatarCasosTest {

	static WebDriver driver;
	static BaseTest baseTest;
	static ScreenShot screen;
	String codCenario;
	LoginPage loginPage;
	RelatarCasosPage relatarCasosPage;
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
		relatarCasosPage.selecionarProjeto(conf.getProperty("ct04.projeto"));
		screen.print("_Projeto selecionado", codCenario, driver);
		relatarCasosPage.clicarBotaoSelecionarProjeto();
		assertTrue(relatarCasosPage.validarCarregamentoFormularioDeCaso());
		relatarCasosPage.selecionarCategoria(conf.getProperty("ct04.categoria"));
		relatarCasosPage.selecionarFrequencia(conf.getProperty("ct04.frequencia"));
		relatarCasosPage.selecionarGravidade(conf.getProperty("ct04.gravidade"));
		relatarCasosPage.selecionarPrioridade(conf.getProperty("ct04.prioridade"));
		relatarCasosPage.selecionarPerfil(conf.getProperty("ct04.perfil"));
		relatarCasosPage.escreverPlataforma(conf.getProperty("ct04.platorma"));
		relatarCasosPage.escreverSistemaOperacional(conf.getProperty("ct04.sistemaOperacional"));
		relatarCasosPage.escreverVersaoSo(conf.getProperty("ct04.VersaoSo"));
		screen.print("_Relatando caso", codCenario, driver);
		relatarCasosPage.escreverResumo(conf.getProperty("ct04.resumo"));
		relatarCasosPage.escreverDescricao(conf.getProperty("ct04.descricao"));
		relatarCasosPage.escreverPassos(conf.getProperty("ct04.passos"));
		relatarCasosPage.escreverInformacoesAdicionais(conf.getProperty("ct04.informacoesAdicionais"));
		screen.print("_Relatando caso", codCenario, driver);
		relatarCasosPage.informarArquivoImportacao(conf.getProperty("ct04.caminhoArquivo"));
		screen.print("_Arquivo Selecionado", codCenario, driver);
		relatarCasosPage.ClicarBotaoEnviarRelatorio();
		assertTrue(relatarCasosPage.obterMensagemOperacaoRealizadaComSucesso("Operação realizada com sucesso."));
		
	}

}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.BasePage;

public class RelatarCasosPage extends BasePage {

	WebDriver driver;
	By menuRelatarCaso = By.xpath("//td[@class='menu']/a[text()='Relatar Caso']");
	By tituloSelecionarProjeto = By.xpath("//td[@class='form-title' and normalize-space(text()='Selecionar Projeto')]");
	By comboBoxProjeto = By.xpath("//td[2]/select[@name='project_id']");
	By btnSelecionarProjeto = By.xpath("//input[@class='button']");
	By tituloFormularioDetalherelatorio = By.xpath("//td[@class='form-title' and normalize-space(text()='Digite os Detalhes do Relatório')]");
	By comboBoxCategoria = By.xpath("//td[2]/select[@name='category_id']");
	By comboBoxFrequencia = By.xpath("//td[2]/select[@name='reproducibility']");
	By comboBoxGravidade = By.xpath("//td[2]/select[@name='severity']");
	By comboBoxPrioridade = By.xpath("//td[2]/select[@name='priority']");
	By comboBoxPerfil = By.xpath("//td[2]/select[@name='profile_id']");
	By inputPlataforma = By.id("platform");
	By inputSo = By.id("os");
	By inputVersaoSo = By.id("os_build");
	By inputResumo = By.xpath("//input[@name='summary']");
	By inputDescricao = By.xpath("//textarea[@name='description']");
	By inputPassos = By.xpath("//textarea[@name='steps_to_reproduce']");
	By inputInformacaoAdicionais = By.xpath("//textarea[@name='additional_info']");
	By btnEscolherArquivo = By.id("ufile[]");
	By btnEnviarRelatorio = By.xpath("//input[@value='Enviar Relatório']");
	By mensagemSucesso = By.xpath("//div[@align='center' and normalize-space(contains(text(),' Operação realizada com sucesso.'))]");
	By mensagemFalhaRelatarCaso = By.xpath("//p[@style='color:red']");

	public RelatarCasosPage(WebDriver driver) {
		this.driver = driver;
	}

	public void acessarMenuRelatarCaso() {
		clicar(menuRelatarCaso, driver, 1);
	}

	public String validarCarregamentoPaginaSelecionarProjeto() {
		return getText(tituloSelecionarProjeto, driver, "titulo pagina", 2);
	}

	public void selecionarProjeto(String projeto) {
		selectComboBoxPorTexto(comboBoxProjeto, projeto, driver);
	}

	public void clicarBotaoSelecionarProjeto() {
		clicar(btnSelecionarProjeto, driver, 2);
	}

	public boolean validarCarregamentoFormularioDeCaso() {
		return existe(tituloFormularioDetalherelatorio, driver, 2);
	}

	public void selecionarCategoria(String categoria) {
		selectComboBoxPorTexto(comboBoxCategoria, categoria, driver);
	}

	public void selecionarFrequencia(String frequencia) {
		selectComboBoxPorTexto(comboBoxFrequencia, frequencia, driver);
	}

	public void selecionarGravidade(String gravidade) {
		selectComboBoxPorTexto(comboBoxGravidade, gravidade, driver);
	}

	public void selecionarPrioridade(String prioridade) {
		selectComboBoxPorTexto(comboBoxPrioridade, prioridade, driver);
	}

	public void selecionarPerfil(String perfil) {
		selectComboBoxPorTexto(comboBoxPerfil, perfil, driver);
	}

	public void escreverPlataforma(String plataforma) {
		escrever(inputPlataforma, driver, plataforma);
	}

	public void escreverSistemaOperacional(String so) {
		escrever(inputSo, driver, so);
	}

	public void escreverVersaoSo(String versao) {
		escrever(inputVersaoSo, driver, versao);
	}

	public void escreverResumo(String resumo) {
		escrever(inputResumo, driver, resumo);
	}

	public void escreverDescricao(String descricao) {
		escrever(inputDescricao, driver, descricao);
	}

	public void escreverPassos(String passos) {
		escrever(inputPassos, driver, passos);
	}

	public void escreverInformacoesAdicionais(String informacoesAdicionais) {
		escrever(inputInformacaoAdicionais, driver, informacoesAdicionais);
	}

	public void ClicarBotaoEnviarRelatorio() {
		clicar(btnEnviarRelatorio, driver, 1);
	}

	public void informarArquivoImportacao(String caminho) {
		scrollToElement(driver, btnEscolherArquivo);
		escrever(btnEscolherArquivo, driver, caminho);
	}
	
	public boolean obterMensagemOperacaoRealizadaComSucesso (String texto) {
		return contains(mensagemSucesso, texto, driver);
	}
	
	public String obterMensagemFalhaOperacao () {
		return getText(mensagemFalhaRelatarCaso, driver, "Mensagem de falha", 5);
	}
}

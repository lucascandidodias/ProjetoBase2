
package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

	WebDriver driver;
	By aguardarLogar = By.xpath("/html/body/div[3]/div/div/div[2]/div/img");
	By inputUSer = By.name("username");
	By inputPass = By.name("password");
	By btnContinuar = By.className("button");
	By msgErro = By.xpath("//font[@color='red']");
	
	
	
	
	
	By motivoBloq = By.xpath("//div[@id=\"avisoBloqueio_avisosContentPanel\"]//div[text()=\"Consultora Cessada.\"]");
	By popuKit = By.id("kitInicio_kitInicioContentPanel");
	By impers = By.id("impersonacaoConsultora_templateWrapper");
	By btnRealizarPedidoVoce = By.id("impersonacaoConsultora_realizarPedidoVoceButton");
	By btnIniciarPedido = By.id("impersonacaoConsultora_iniciarPedidoButton");
	By radioRealizarPedidoMesmoGrupo = By.id("impersonacaoConsultora_realizarPedidoCNGrupoLabel");
	By siteConsultora = By.xpath("//*[@id=\"itemSiteConsultoriaHeader\"]/div/div");
	By revistaNatura = By.xpath("//*[@id=\"itemRevistaNaturaHeader\"]/div/div");
	By ajudaOnline = By.xpath("//*[@id=\"itemAjudaOnlineHeader\"]/div/div");
	By formaPg = By.xpath("//*[@id=\"itemFormasPagamentoHeader\"]/div/div");
	By escolherForma = By.xpath("//*[@id=\"itemEscolherFormaPagamentoHeader\"]/div/div");
	By treinamento = By.xpath("//*[@id=\"itemTreinamentoEducacaoFinanceiraHeader\"]/div/div");
	By cartaConvite = By.xpath("//*[@id=\"itemCartasConviteHeader\"]/div/div");
	By notificacoes = By.id("main_iconNotificacoes");
	By avisoBloqueio = By.id("avisoBloqueio_bloqueioPedidoNaturaMainPanel");
	By notificacaoComunicadoNatura = By.xpath("//li[@id=\"itemNotificacoesHeader\"]//strong/../../p[2]");
	By popUpConcerrenciaAcesso = By.className("faces-HTML");
	By carregando = By.xpath("//div[@class='faces-popup-loading-wrapper']//img");
	By labelProdutoDoPedido = By.id("produto_produtoPedidoLabel");
	By inputCodigoConsultoraImpsersionada = By.id("impersonacaoConsultora_codigoConsultoraText");
	By continuarPedido = By.id("impersonacaoConsultora_iniciarPedidoButton");
	By radioButtonImpersonar = By.id("impersonacaoConsultora_realizarPedidoCNGrupoButton");
	By msgBloqueioDebito = By.xpath("//div[contains(text(), 'Captação não permitida por indisponibilidade de crédito.')]");
	By btnSairLogout = By.id("main_buttonSair");
	By buttonSimPopUpConfirmacao = By.xpath("//*[@class='faces-Confirm-content']//button[text()='Sim']");
	By painelRealizarPedidoCn = By.id("impersonacaoConsultora_realizarPedidoParaCNLabel");
	By radioButtonVoceComoCn = By.xpath("//div[@id='impersonacaoConsultora_realizarPedidoVoceLabel']/../span[2]");
	
	
	public void Logout(WebDriver driver) {
		clicar(btnSairLogout, driver, 2);
		clicar(buttonSimPopUpConfirmacao, driver, 10);
	}

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean carregouPagina(WebDriver driver) {
		return existe(inputUSer, driver, 15);
	}

	public void setUsuario(WebDriver driver, String usuario) {
		escrever(inputUSer, driver, usuario);
	}

	public void setSenha(WebDriver driver, String senha) {
		escrever(inputPass, driver, senha);
	}
	
	public String obterMensagemErroLogin(WebDriver driver) {
		return getText(msgErro, driver, "erro ao logar", 30);
	}

	public void clicarBotaoLogin(WebDriver driver) {
		clicar(btnContinuar, driver, 3);
	}

	public void aguardarCarregamento(WebDriver driver) {
		aguarda(carregando, driver);
	}

	public boolean existeMangemErroLogin(WebDriver driver) {
		return !existe(msgErro, driver, 10);
	}
		
	public String obterMensagemErroBloqueiPorDebito (WebDriver driver) {
		return getText(msgBloqueioDebito, driver, "Mensagem de bloqueio por debito", 5);
	}
	
	public void clicarIniciarPedido(WebDriver driver) {
		clicar(btnIniciarPedido, driver, 2);
	}
	
	public boolean existePainelImpersionarPedidoCN () {
		return existe(painelRealizarPedidoCn, driver, 5);
	}
	
	public void escreverConsultoraImpsersionada (String cn, WebDriver driver) {
		escrever(inputCodigoConsultoraImpsersionada, driver, cn);
	}
	
	public void clicarRadioButtonVoceComoCn (WebDriver driver) {
		clicar(radioButtonVoceComoCn, driver, 3);
	}
	

}

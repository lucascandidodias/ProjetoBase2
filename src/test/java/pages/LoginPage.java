
package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

	WebDriver driver;
	By aguardarLogar = By.xpath("/html/body/div[3]/div/div/div[2]/div/img");
	By inputUSer = By.name("username");
	By inputPass = By.name("password");
	By btnLogin = By.className("button");
	By msgErro = By.xpath("//font[@color='red']");
	By usuarioTelaInicial = By.xpath("//td[@class='login-info-left']/span[1]");
	By btnSairLogout = By.xpath("//a[text() ='Sair']");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clicarBotaoSair() {
		clicar(btnSairLogout, driver, 2);
	}

	public void setUsuario(String usuario) {
		escrever(inputUSer, driver, usuario);
	}

	public void setSenha(String senha) {
		escrever(inputPass, driver, senha);
	}
	
	public String obterMensagemErroLogin() {
		return getText(msgErro, driver, "erro ao logar", 10);
	}

	public void clicarBotaoLogin() {
		clicar(btnLogin, driver, 3);
	}
		
	public String obterNomeUsuarioTelaInicial() {
		return getText(usuarioTelaInicial, driver, "Nome usuario tela inicial", 10);
	}
	
	public boolean validarExistenciaBotaoLogin () {
		return existe(btnLogin, driver, 10);
	}

}

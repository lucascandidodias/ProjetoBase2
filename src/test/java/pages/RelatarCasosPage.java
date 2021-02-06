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
}

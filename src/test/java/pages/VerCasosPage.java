package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.BasePage;

public class VerCasosPage extends BasePage {

	WebDriver driver;
	By menuVerCaso = By.xpath("//td[@class='menu']/a[text()='Ver Casos']");
	By codigoCaso = By.xpath("//*[@id='buglist']/tbody/tr[4]/td[4]/a");
	By nomeProjeto = By.xpath("//td[@class='category' and text()='Projeto']/../../tr[3]/td[2]");
	By nomeRelator = By.xpath("//td[@class='category' and text()='Relator']/../td[2]");
	By detalhePrioridade = By.xpath("//td[@class='category' and text()='Prioridade']/../td[2]");
	By detalheGravidade = By.xpath("//td[@class='category' and text()='Prioridade']/../td[4]");
	By detalheFrequencia = By.xpath("//td[@class='category' and text()='Prioridade']/../td[6]");
	By detalhePlataforma= By.xpath("//td[@class='category' and text()='Plataforma']/../td[2]");
	By detalheSo = By.xpath("//td[@class='category' and text()='Plataforma']/../td[4]");
	By detalheVersaoSo = By.xpath("//td[@class='category' and text()='Plataforma']/../td[6]");
	
	public VerCasosPage(WebDriver driver) {
		this.driver = driver;
	}

	public void acessarMenuVerCaso() {
		clicar(menuVerCaso, driver, 1);
	}

	public void clicarPrimeiroCasoDaLista() {
		clicar(codigoCaso, driver, 5);
	}
	
	public String obterNomeProjeto() {
		return getText(nomeProjeto, driver, "Nome Projeto", 5);
	}
	
	public String obterNomeRelator() {
		return getText(nomeRelator, driver, "Nome Relator", 1);
	}
	
	public String obterPrioridade() {
		return getText(detalhePrioridade, driver, "Prioridade", 1);
	}
	
	public String obterGravidade() {
		return getText(detalheGravidade, driver, "Gravidade", 1);
	}
	
	public String obterFrequencia() {
		return getText(detalheFrequencia, driver, "Frequencia", 1);
	}
	
	public String obterPlataforma() {
		return getText(detalhePlataforma, driver, "Plataforma", 1);
	}
	
	public String obterSo() {
		return getText(detalheSo, driver, "Sistema operacional", 1);
	}
	
	public String obterVersaoSo() {
		return getText(detalheVersaoSo, driver, "Versao Sistema operacional", 1);
	}
	
	
	
	

}

package core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;


import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author eduardo.lana
 */
public class BasePage {

//	ScreenShot screen = new ScreenShot();
	By carregando = By.xpath("/html/body/div[4]/div/div/div[2]/div/img");
	WebDriver driver;
	By msgErro = By.className("erro-corpo");

	public boolean clicar(By Element, WebDriver driver, int tempo) {
		try {
			existe(Element, driver, tempo);
			driver.findElement(Element).click();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public void escrever(By Element, WebDriver driver, String Text) {
		WebElement inpt = driver.findElement(Element);
		inpt.sendKeys(Text);
	}

	public boolean limparEescrever(By Element, WebDriver driver, String Text) {
		try {
			driver.findElement(Element).clear();
			Thread.sleep(300);
			driver.findElement(Element).sendKeys(Text);
			Thread.sleep(300);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public boolean enter(By Element, WebDriver driver) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.visibilityOfElementLocated((Element)));
			driver.findElement(Element).sendKeys(Keys.ENTER);
			if (existe(msgErro, driver, 10)) {
				System.out.println("Ocorreu erro na aplicação");
				throw new RuntimeException();
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean existe(By Element, WebDriver driver, int tempo) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, tempo);

			if (wait.until(ExpectedConditions.visibilityOfElementLocated((Element))) != null) {
				return true;

			} else {

				return false;
			}
		} catch (Exception e) {

			return false;
		}

	}

	public boolean aguarda(By carregando, WebDriver driver) {
		boolean visible = true;
		int i = 0;
		do {
			visible = existe(carregando, driver, 3);
			i++;
		} while (visible && i < 5000);

		return visible;
	}

	public void upload(By Element, WebDriver driver, String obj) {

		WebElement upload = driver.findElement(Element);
		upload.click();

		upload.sendKeys(obj);

	}

	public String getText(By Element, WebDriver driver, String nome, int timeout) {

		try {
			if (existe(Element, driver, timeout)) {
				String texto = driver.findElement(Element).getText();
				return texto;
			} else {
				System.out.println("O " + nome + " não foi localizado");
				return "Erro";
			}
		} catch (Exception e) {
			System.out.println("O " + nome + " não foi localizado");
			return "Erro";
		}
	}

	public boolean check(By Element, WebDriver driver, String pos) {
		boolean result;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		result = existe(Element, driver, 10);
		WebElement check = driver.findElement(Element);
		try {
			js.executeScript("arguments[" + pos + "].click()", check);
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public void scroll(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0, 1000);");
	}

	public void scrollParametro(WebDriver driver, int parametro) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0, " + parametro + ");");
	}

	public void scrollToElement(WebDriver driver, By by) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement element = driver.findElement((by));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public boolean selectText(By Element, String text, WebDriver driver, String campo) {
		boolean result = true;
		List<WebElement> opcoes;
		String opcao;

		try {
			WebElement select = driver.findElement(Element);
			opcoes = select.findElements(By.xpath(".//*"));
			for (int i = 0; i < opcoes.size(); i++) {
				opcao = opcoes.get(i).getText();
				if (opcao.equals(text)) {
					opcoes.get(i).click();
					aguarda(carregando, driver);
					return true;
				}
			}

		} catch (Exception e) {
			System.out.println(
					"A opcao " + text + " inserida no arquivo de configuracao nao foi econtrada em: " + campo + "");
			result = false;
		}
		return result;
	}

	public boolean selectTextByValue(By Element, String text, WebDriver driver, String campo) {
		boolean result = true;
		List<WebElement> opcoes;
		String opcao;
		try {
			WebElement select = driver.findElement(Element);
			opcoes = select.findElements(By.tagName("option"));
			for (int i = 0; i < opcoes.size(); i++) {
				opcao = opcoes.get(i).getAttribute("label");
				if (opcao.equals(text)) {
					opcoes.get(i).click();
					System.out.println("Campo: " + campo);
					Thread.sleep(900);
					return true;
				}
			}

		} catch (Exception e) {
			System.out.println(
					"A opcao " + text + " inserida no arquivo de configuracao nao foi econtrada em: " + campo + "");
			result = false;
		}
		return result;
	}

	public boolean select(By Element, String text, WebDriver driver, String campo) {
		boolean result = true;
		List<WebElement> opcoes;

		try {
			WebElement element = driver.findElement(Element);
			Select opcao = new Select(element);
			opcao.selectByValue(text);

		} catch (Exception e) {

			result = false;
		}
		return result;
	}

	public void atualizaPagina(WebDriver driver) {
		Actions actionObject = new Actions(driver);
		actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).perform();
	}

	public void selectComboBoxPorIndice(By elemento, int index, WebDriver driver) {
		Select combobox = new Select(driver.findElement(elemento));
		combobox.selectByIndex(index);
	}

	public void selectComboBoxPorTexto(By elemento, String texto, WebDriver driver) {
		Select combobox = new Select(driver.findElement(elemento));
		combobox.selectByVisibleText(texto);
	}

	public String getTextByValue(By Element, WebDriver driver, String nome, int timeout) {

		try {
			if (existe(Element, driver, timeout)) {
				String texto = driver.findElement(Element).getAttribute("value");
				return texto;
			} else {
				System.out.println("O " + nome + " não foi localizado");
				return "Erro";
			}
		} catch (Exception e) {
			System.out.println("O " + nome + " não foi localizado");
			return "Erro";
		}
	}

	public String getTextSelectBox(By element, WebDriver driver, String nome, int timeout) {
		try {
			if (existe(element, driver, timeout)) {
				Select combobox = new Select(driver.findElement(element));
				return combobox.getFirstSelectedOption().getText();
			} else {
				System.out.println("O " + nome + " não foi localizado");
				return "Erro";
			}
		} catch (Exception e) {
			System.out.println("O " + nome + " não foi localizado");
			return "Erro";
		}
	}

	public void pausar(int tempo) {
		try {
			Thread.sleep(tempo);
		} catch (InterruptedException e) {
		}
	}

}

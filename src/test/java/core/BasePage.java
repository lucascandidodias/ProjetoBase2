package core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

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

	public boolean contains(By Element, String texto, WebDriver driver) {
		if (getText(Element, driver, texto, 10).contains(texto)) {
			return true;
		} else {
			return false;
		}
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

	public void scrollToElement(WebDriver driver, By by) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement element = driver.findElement((by));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void selectComboBoxPorTexto(By elemento, String texto, WebDriver driver) {
		Select combobox = new Select(driver.findElement(elemento));
		combobox.selectByVisibleText(texto);
	}

}

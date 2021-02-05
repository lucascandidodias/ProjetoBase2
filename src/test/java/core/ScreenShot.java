package core;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;
import org.apache.commons.io.FileUtils;
import org.docx4j.model.datastorage.XPathEnhancerParser.additiveExpr_return;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
//import utils.InsertImage;

import utils.InsertImage;


public class ScreenShot {
	public static int indiceScreenShot = 1;

	public void print(String nomeTela, String codigoCenario, WebDriver driver) {
		takeScreenshot(codigoCenario + "_" + indiceScreenShot + nomeTela, codigoCenario, driver);
		indiceScreenShot++;
	}

	public static void takeScreenshot(String fileName, String cenario, WebDriver driver) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String cwd = System.getProperty("user.dir");
		String pathCenario = cwd + "/" + "Evidencias" + "/" + cenario;
		criaDir(pathCenario);

		try {
			FileUtils.copyFile(scrFile, new File(pathCenario, fileName + ".png"));
			InsertImage.inserirImagem(cenario, pathCenario, fileName + ".png", fileName);
		} catch (IOException e) {

		}
	}

	public static boolean criaDir(String diretorio) {
		boolean result = false;
		File atmpDir = new File(diretorio);
		if (!atmpDir.exists()) {
			try {
				atmpDir.mkdirs();
				result = true;
			} catch (Exception e) {
				result = false;
			}
		}
		return result;
	}


	public void excluirEvidencia(String cenario) {
		String cwd = System.getProperty("user.dir");
		String pathCenario = cwd + "/" + "Evidencias" + "/" + cenario;
		
		File docEvidencia = new File(pathCenario);
		
		if (docEvidencia.exists()) {			
			try {
				FileUtils.deleteDirectory(docEvidencia);
			} catch (IOException e) {
				System.out.println("Não foi possível deletar a pasta de evidencias");
			}
		}
		
	}

}

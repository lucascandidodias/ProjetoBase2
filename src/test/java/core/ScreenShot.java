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
			//InsertImage.inserirImagem(cenario, pathCenario, fileName + ".png", fileName);
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

	public void copiaEvidencia(File origem, File destino) throws IOException {

		String eviDestino = destino.toString();
		criaDir(eviDestino);
		copiaDir(origem, destino);
	}

	public static void copiaDir(File dirFont, File dirDest) throws IOException {

		String[] arqs;
		File arq;
		int i;

		criaDir(dirDest.toString());
		// Copia arquivos do diretório:
		arqs = dirFont.list();
		for (i = 0; i < arqs.length; i++) {
			arq = new File(dirFont.getPath(), arqs[i]);
			if (arq.isDirectory()) {
				copiaDir(arq, new File(dirDest.getPath(), arqs[i]));
			} else {
				copiaArq(arq, new File(dirDest.getPath(), arqs[i]));
			}
		}
	}

	public static void copiaArq(File arqFont, File arqDest) throws IOException {

		FileChannel canalFont = null;
		FileChannel canalDest = null;

		// Apaga destino, se existir:
		if (arqDest.exists()) {
			arqDest.delete();
		}

		// Copia arquivo:
		try {
			canalFont = new FileInputStream(arqFont).getChannel();
			canalDest = new FileOutputStream(arqDest).getChannel();
			canalFont.transferTo(0, canalFont.size(), canalDest);
		} finally {
			if (canalFont != null && canalFont.isOpen()) {
				canalFont.close();
			}
			if (canalDest != null && canalDest.isOpen()) {
				canalDest.close();
			}
		}
	}

	public void excluirEvidencia(String cenario) {
		String cwd = System.getProperty("user.dir") + "/..";
		String pathCenario = cwd + "/" + "Evidencias" + "/" + "/" +
				 "/" + cenario;
		
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

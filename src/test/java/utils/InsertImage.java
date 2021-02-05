package utils;

import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;


public class InsertImage {

	public static int i = 1;
	public static WordprocessingMLPackage wordPackage = null;
	public static MainDocumentPart mainDocumentPart = null;
	
	public static void inserirImagem(String nomeCenario, String caminhoCenario, String nomeImagem, String nomeImagemEvidencia) {
		try {			
			File exportFile = new File(caminhoCenario + "/" + nomeCenario + ".docx");
			
			//Cria o arquivo	
			if(!exportFile.exists()) {
				System.out.println("Adicionando screenshot no arquivo Word ...");
				wordPackage = WordprocessingMLPackage.createPackage();
				mainDocumentPart = wordPackage.getMainDocumentPart();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
				mainDocumentPart.addStyledParagraphOfText("Title", "Evidência - "+nomeCenario);
				mainDocumentPart.addStyledParagraphOfText("Title", sdf.format(new Date()));
			} else if(exportFile.exists()) {
				System.out.println("Adicionando screenshot no arquivo Word ...");
				wordPackage = WordprocessingMLPackage.load(new File(caminhoCenario + "/" + nomeCenario + ".docx"));
				mainDocumentPart = wordPackage.getMainDocumentPart();
			}
						
			mainDocumentPart.addParagraphOfText(nomeImagemEvidencia);			
			
			//imagem
			File image = new File(caminhoCenario+"/"+nomeImagem);
			byte[] fileContent;
			fileContent = Files.readAllBytes(image.toPath());
			
			BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordPackage, fileContent);
			Inline inline = imagePart.createImageInline("Baeldung Image (filename hint)", "Alt Text", 1,2, false);
			P Imageparagraph = addImageToParagraph(inline);				
			mainDocumentPart.getContent().add(Imageparagraph);	
			
			//salva o arquivo
			wordPackage.save(exportFile);
			i++;
		} catch (Exception e) {
			System.out.println("Erro ao inserir screenshot no arquivo Word. Favor fechar o arquivo caso o mesmo esteja aberto");
		}

	}

	private static P addImageToParagraph(Inline inline) {
		ObjectFactory factory = new ObjectFactory();
		P p = factory.createP();
		R r = factory.createR();
		p.getContent().add(r);
		Drawing drawing = factory.createDrawing();
		r.getContent().add(drawing);
		drawing.getAnchorOrInline().add(inline);
		return p;
	}
	
	public void addPageBreak(WordprocessingMLPackage wordMLPackage,
			ObjectFactory factory, STBrType sTBrType) {
		MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
		Br breakObj = new Br();
		breakObj.setType(sTBrType);
		P paragraph = factory.createP();
		paragraph.getContent().add(breakObj);
		documentPart.addObject(paragraph);
	}
	 
}

package adapterpdf;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import org.apache.pdfbox.pdmodel.PDDocument;

import interfaces.IDocument;

public class AdapterPdf implements IDocument{

	PDDocument pdfdocument;
	
	public void open(File file) throws IOException {

		this.pdfdocument = PDDocument.load(file);
		
		
	}

	@Override
	public JFrame geteditor() throws IOException {
		
		return new JFPdf(this.pdfdocument);
	}

	
	
}

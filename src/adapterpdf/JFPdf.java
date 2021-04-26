package adapterpdf;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class JFPdf extends JFrame implements ActionListener {

	private int controlepagina;
	private JButton btnAnterior;
	private JButton btnProximo;
	private JPanel painelprincipal;
	private BufferedImage[] bim;
	private JLabel conteudo;
	private int qtdpagina;
	
	public JFPdf(PDDocument document) throws IOException {
		
		this.qtdpagina = document.getNumberOfPages();
		this.controlepagina = 0;
		this.btnAnterior = new JButton("Anterior");
		this.btnProximo = new JButton("Próximo");
		this.painelprincipal = new JPanel(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.btnAnterior.addActionListener(this);
        this.btnProximo.addActionListener(this);
        
        this.painelprincipal.add(this.btnAnterior,BorderLayout.LINE_START);
        this.painelprincipal.add(this.btnProximo,BorderLayout.LINE_END);
        
        PDFRenderer render = new PDFRenderer(document);
        this.bim = new BufferedImage [document.getNumberOfPages()];
        
        for(int i = 0; i < document.getNumberOfPages(); i++) 
        	this.bim[i] = render.renderImage(i);
        
        this.conteudo = new JLabel(new ImageIcon(bim[0]));
        this.painelprincipal.add(this.conteudo, BorderLayout.CENTER);
        this.add(painelprincipal);
        this.pack();
        this.setLocationRelativeTo(null);
	}

@Override
public void actionPerformed(ActionEvent e) {

	if(e.getSource() == btnProximo) {
	 
		if (controlepagina != qtdpagina - 1) {
			painelprincipal.remove(conteudo);
			controlepagina++;
			conteudo.setIcon(new ImageIcon(bim[controlepagina]));
			painelprincipal.add(conteudo, BorderLayout.CENTER);
			painelprincipal.revalidate();
			painelprincipal.repaint();
		}
	}
	
	if(e.getSource() == btnAnterior) {
		 
		if (controlepagina != 0) {
			painelprincipal.remove(conteudo);
			controlepagina--;
			conteudo.setIcon(new ImageIcon(bim[controlepagina]));
			painelprincipal.add(conteudo, BorderLayout.CENTER);
			painelprincipal.revalidate();
			painelprincipal.repaint();
		}
	}
}
}
package uiFile;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import thread.ThreadA;

	public class FileExplorer extends JFrame implements ActionListener{
	    
		private JButton openButton;
	    private String dir;
	    private ThreadA thread;
	    
	    public FileExplorer (ThreadA thread) {
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setLayout(new FlowLayout());
	        this.openButton = new JButton("Open");
	        this.openButton.addActionListener(this);
	        this.setLocationRelativeTo(null);
	        this.add(openButton);
	        this.pack();
	        this.setVisible(true);
	        this.thread=thread;
	    }
	    
	    public void actionPerformed(ActionEvent e ){
	        if(e.getSource()==this.openButton) {
	            JFileChooser fileChooser = new JFileChooser();
	            int response = fileChooser.showOpenDialog(null);
	            if(response == JFileChooser.APPROVE_OPTION) {
	                this.dir = fileChooser.getSelectedFile().getAbsolutePath();
	                dispose();
	                this.thread.release();
	            }
	        }
	    }
	    public String getAbsoluteDir(){
	            return this.dir;
	    }
	}

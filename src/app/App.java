package app;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;

import interfaces.IDocument;
import thread.ThreadA;
import uiFile.FileExplorer;

	public class App {

		public static void main(String []arg) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, InterruptedException {
			
			menu();
			
		}
		
		public static void menu() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, InterruptedException {
			
			ThreadA pause = new ThreadA();
			FileExplorer filefinder = new FileExplorer(pause);
			synchronized (pause) {
				try{
					System.out.println("Aguardando definir opcao...");
					pause.wait();
			    	}catch(InterruptedException e){
			    		e.printStackTrace();
			    	}
				}
			String directory = filefinder.getAbsoluteDir();
			prepareData(directory);
		}
		
		public static void prepareData(String directory) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, InterruptedException {
			
			boolean sucess = false;
			String extension = directory.split("\\.")[1];
			File currentDir = new File ("./src/plugins");
            		String[] pluginsF = currentDir.list();
            		URL[] jars = new URL [pluginsF.length];
            		for (int i = 0 ; i < pluginsF.length; i++) {
                		jars[i] = (new File("./src/plugins/" + pluginsF[i])).toURL();
            		}

            		URLClassLoader urlc = new URLClassLoader(jars);
            		for(int i = 0; i < pluginsF.length; i++) {
                		if (pluginsF[i].toLowerCase().contains(extension.toLowerCase())) {
                    			String documentName = pluginsF[i].split("\\.")[0];
                    			IDocument document = (IDocument) Class.forName(documentName.toLowerCase() + "." + documentName, true, urlc).newInstance();
                    			opendocument(document, directory);
                    			sucess = true;
                		}
            		}
            		if (!sucess)
            			System.out.println("Não existe plugin que suporte este arquivo");
		}

		private static void opendocument(IDocument document, String directory) throws IOException {
			
			document.open(new File (directory));
			document.geteditor().setVisible(true);
		}
			// TODO Auto-generated method stub
			
		}

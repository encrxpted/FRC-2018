package Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.Constants;

public class Logger implements Constants {
	private File file;
	private FileWriter fw;
	private FileReader fr;
	private BufferedWriter bw;
	private BufferedReader br;
	
	public Logger() {
	}
	
	public void createNewFile(String name) {
		File newFile = new File(outputPath +"/" + name);
		if(!newFile.exists())
			try {
				newFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		else
			System.out.println("This file already exists so a new" +
					           " one will not be created.");		
	}
	
	public void writeLine(String line) {
		try {
			bw.write(line);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
	
	public String readLine() {
		String line = "";
		try {
			line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}
	
	public void changePath(String nameOrPath, boolean useFileLookup) {
		//If useFileLookup is true then it will search for the specified
		//fileName and get its path.
		//---------------------useFileLookup currently unused
		if(useFileLookup) {
			for(File file: new File(outputPath).listFiles())
				if(file.getName().equals(nameOrPath)) changePath(file.getPath(), false);
		}
		
		try {
    		file = new File(nameOrPath);
    		if(!file.exists())
    			file.createNewFile();
    		if(file != null) {
    			fw = new FileWriter(file);
    			fr = new FileReader(file);
    		}
    		if(fw != null) bw = new BufferedWriter(fw);
    		if(fr != null) br = new BufferedReader(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public File[] getFiles(String path) {
		List<File> textFiles = new ArrayList<File>();
		File dir = new File(path);
		for (File file : dir.listFiles()) {
			if (file.getName().toLowerCase().endsWith((".txt"))) {
				textFiles.add(file);
			}
		}
		
		File[] allFiles = new File[textFiles.size()];
		for(int i = 0; i < textFiles.size(); i++)
			allFiles[i] = textFiles.get(i);
		
		return allFiles;
	}
	
	public String getWorkingFile() {
		return file.getName();
		
	}
}

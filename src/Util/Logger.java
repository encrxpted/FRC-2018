package Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import main.Constants;

public class Logger implements Constants {
	private File file;
	private FileWriter fw;
	private FileReader fr;
	private BufferedWriter bw;
	private BufferedReader br;
	
	public Logger() {
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
	
	public void changePath(String path) {
		try {
    		file = new File(path);
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
}

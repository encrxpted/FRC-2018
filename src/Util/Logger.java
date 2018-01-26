package Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import main.Constants;

public class Logger implements Constants {
	public File file;
	private FileWriter fw;
	private FileReader fr;
	private BufferedWriter bw;
	private BufferedReader br;
	
	public Logger(String path) {
    	try {
    		file = new File(path);
    		if(!file.exists()){
    			file.createNewFile();
    		}
			fw = new FileWriter(file);
			fr = new FileReader(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	bw = new BufferedWriter(fw);
    	br = new BufferedReader(br);
	}
	
	public void writeLine(String line) {
		try {
			bw.write(line);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String readLine() {
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return e.toString();
		}
	}
	
	public void close() {
		try {
			fw.close();
			fr.close();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}

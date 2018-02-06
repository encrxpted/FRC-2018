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
	
	public Logger(String path, boolean singleFileMode) {
    	try {
    		file = new File(path);
    		if(file.exists() && singleFileMode){
    			file.delete();
    			file.createNewFile();
    		}
    		if(!file.exists())
    			file.createNewFile();
			fw = new FileWriter(file);
			fr = new FileReader(file);
			bw = new BufferedWriter(fw);
	    	br = new BufferedReader(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeLine(String line) {
		try {
			if(bw != null) {
				bw.write(line);
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String readLine() {
		try {
			if(br != null) return br.readLine();
			else return null;
		} catch (IOException e) {
			e.printStackTrace();
			return e.toString();
		}
	}
	
	public void close() {
		try {
			if(fw != null) fw.close();
			if(fr != null) fr.close();
			if(bw != null) bw.close();
			if(br != null) br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}

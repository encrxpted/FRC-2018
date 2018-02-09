package Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import main.Constants;

public class Logger implements Constants {
	public static File file;
	private FileWriter fw;
	private FileReader fr;
	private BufferedWriter bw;
	private BufferedReader br;
	
	public Logger(String path, boolean singleFileMode) {
    	try {
    		file = new File(path);
    		/*if(file.exists() && singleFileMode){  
    			file.delete();
    			file.createNewFile();
    		}*/
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
	
	public void writeLine(String line) {
		try {
			bw.write(line);
			bw.newLine();
			bw.flush();
			//fw.close();
			//bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		//try {
			//if(bw != null) {
				//bw.write(line);
				//bw.newLine();
			//}
		//} catch (IOException e) {
			//e.printStackTrace();
		//}
	}
	
	public String readLine() {
		String line = "";
		try {
			line = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return line;
		/*try {
			if(br != null) {
				return br.readLine();
			}
			else {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return e.toString();
		}*/
	}
	public static void changePath(String path) {
		file = new File(path);
	}
	/*
	public void open() {
		if(file != null) {
			try {
				fw = new FileWriter(file);
				fr = new FileReader(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(fw != null) bw = new BufferedWriter(fw);
		if(fr != null) br = new BufferedReader(fr);
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
	
	public void reset() {
		close();
		open();
	}*/
}

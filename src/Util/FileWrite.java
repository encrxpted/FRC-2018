package Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import main.Constants;

public class FileWrite implements Constants {
	public File file;
	private BufferedWriter bw;
	private FileWriter fw;
	
	public void FileWrite() {
    	try {
    		file = new File(outputPath);
    		if(!file.exists()){
    			file.createNewFile();
    		}
			fw = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	bw = new BufferedWriter(fw);
	}
}

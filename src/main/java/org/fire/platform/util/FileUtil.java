package org.fire.platform.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.jar.Pack200;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {
	
	public static void createFile(String filePath,String fileName,String data){
	  try{
	      File file =new File(filePath+File.separator+fileName);
	      if(!file.exists()){
	        file.createNewFile();
	      }
	      FileWriter fw = new FileWriter(file.getAbsoluteFile());
	      BufferedWriter bw = new BufferedWriter(fw);
	      bw.write(data);
	      bw.close();
	      
	     }catch(IOException e){
	        e.printStackTrace();
	     }
	}
	
	public static void zip(String sourceDIR, String targetZipFile) {
		try {
		    FileOutputStream target = new FileOutputStream(targetZipFile);
		    ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(target));
		    int BUFFER_SIZE = 1024;
		    byte buff[] = new byte[BUFFER_SIZE];
		    File dir = new File(sourceDIR);
		    if (!dir.isDirectory()) {
		      throw new IllegalArgumentException(sourceDIR+" is not a directory!");
		    }
		    File files[] = dir.listFiles();
		    for (int i = 0; i < files.length; i++) {
		      FileInputStream fi = new FileInputStream(files[i]);
		      BufferedInputStream origin = new BufferedInputStream(fi);
		      ZipEntry entry = new ZipEntry(files[i].getName());
		      out.putNextEntry(entry);
		      int count;
		      while ((count = origin.read(buff)) != -1) {
		        out.write(buff, 0, count);
		      }
		      fi.close();
		      origin.close();
		    }
		    out.close();
			target.close();
		  } catch (IOException e) {
		      
		  }
    }

    public static String getTomcatPath(){
		String path = System.getProperty("user.dir").replace("bin", "webapps");
		if(path.endsWith("webapps")) {
			return path;
		}else{
			return path + "/webapps";
		}
	}
	

	

}

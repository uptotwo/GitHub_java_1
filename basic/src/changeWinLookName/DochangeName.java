package changeWinLookName;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DochangeName {
	
	public static void main(String[] args) {
		List<File> fileList = getFiles("C:/Users/admin/AppData/Local/Packages/Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy/LocalState/Assets");
		for (File file : fileList) {
			System.out.println(file.getName());
		}
		renameFiles(fileList);
		System.out.println(fileList.size());
	}
	
	public static List<File> getFiles(String path){
		File root = new File(path);
		List<File> files = new ArrayList<File>();
		if (!root.isDirectory()) {
			files.add(root);
		} else {
			File[] subFiles = root.listFiles();
			for (File file : subFiles) {
				//files.addAll(getFiles(file.getAbsolutePath())); 
				files.add(file);
			}
		}
		return files;
	}
	
	// 
	public static void renameFiles(List<File> list){
		
		InputStream ips = null;
		OutputStream ops = null;
		byte[] buffer = new byte[1024];
		int bytesRead;
		int i = 1000;
		for (File file : list) {
			
			String fileName = file.getAbsolutePath();
			File oldFile = new File(fileName);
			File renamedFilePath = new File("G:"+File.separator+"alreadyChanged");
			if(!renamedFilePath.exists()){
				try {
					renamedFilePath.mkdirs();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			File renamedFile = new File(renamedFilePath,i+".jpg" );
			try {
				renamedFile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				ips = new FileInputStream(oldFile);
				ops = new FileOutputStream(renamedFile);
				while ( ( bytesRead = ips.read(buffer) ) >0) {
					  ops.write(buffer, 0, bytesRead);
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.toString());
			}
			i++;
			//file.renameTo(new File("G:"+File.separator+"alreadyChanged"+File.separator+file.getName()+".jpg"));
		}
	}
	
	
	
	
	
	
	
}

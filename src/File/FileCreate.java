package File;

import java.io.File;
import java.io.IOException;

public class FileCreate {
	public static void main(String[] args) {
		try {
			File temp = File.createTempFile("temp_", ".tmp", new File("/Users/hyoin/Desktop/효인/study/java/Test"));
			
			System.out.println("temp file : " + temp.getAbsolutePath());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

///Users/hyoin/Desktop/효인/study/java/Test/file.txt
package File;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReadWrite2 {
	public static void main(String[] args) throws IOException {
		File file = new File("./src/File/fileTest.txt");
		
//		if(!file.exists()) file.createNewFile();
//		
//		BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
//		
//		writer.write("테스트");
//		writer.newLine();
//		writer.write("12345");
//		
//		// 버퍼 및 스트림 뒷정리
//		writer.flush(); // 버퍼의 남은 데이터를 모두 쓰기
//		writer.close(); // 스트림 종료
		
		if(file.exists()) {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String line = null;
			while((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			
			reader.close();
		}
	}
}

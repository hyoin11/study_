package File;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReadWrite {
	public static void main(String[] args) {
		try(
				// 이 안에서 객체를 생성하면 try 종료 후 자동으로 close 처리됨
				// true -> 기존 파일에 이어서 작성(default -> false)
				FileWriter fw = new FileWriter("fileWriteTest.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
			)
		{
			bw.write("가나다"); // 버퍼에 데이터 입력
			bw.newLine();
			bw.write("라마바");
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File file = new File("fileWriteTest.txt");
		if(file.isFile()) {
			System.out.println("파일 있음");
		}
		
		try(
				FileReader rw = new FileReader("fileWriteTest.txt");
				BufferedReader br = new BufferedReader(rw);
			)
		{
			// 읽을 라인이 없을 경우 br은 null을 리턴
			String readLine = null;
			while((readLine = br.readLine()) != null) {
				System.out.println(readLine);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

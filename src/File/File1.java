package File;

import java.io.File;
import java.io.IOException;

public class File1 {
	public static void main(String[] args) {
		// file.txt 객체 생성
		File file = new File("file.txt");
		
		try {
			if(!file.exists()) {
				file.createNewFile();				
			}
			
			System.out.println("파일 이름 : " + file.getName());
			System.out.println("파일 경로 : " + file.getPath());
			System.out.println("파일 절대 경로 : " + file.getAbsolutePath());
			System.out.println("파일 정규 경로 : " + file.getCanonicalPath());
			System.out.println("파일 상위 디렉토리 : " + file.getParent());
			
			// 파일의 쓰기/읽기 권한 체크
			if(file.canWrite()) {
				System.out.println("쓸 수 있음");
			}else {
				System.out.println("쓸 수 없음");
			}
			if(file.canRead()) {
				System.out.println("읽을 수 있음");
			}else {
				System.out.println("읽을 수 없음");
			}
			
			// 객체의 파일, 폴더 여부 체크
			if(file.isFile()) {
				System.out.println("파일임");
			}else {
				System.out.println("파일아님");
			}
			if(file.isDirectory()) {
				System.out.println("폴더 유");
			}else {
				System.out.println("폴더 무");
			}
			
			// 파일 내용 길이 표시
			System.out.println("길이 : " + file.length());
		}catch(IOException e) {
			System.out.println("!!!error!!!");
			e.printStackTrace();
		}
	}
}
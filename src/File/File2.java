package File;

import java.io.File;

public class File2 {
	public static void main(String[] args) {
		// 프로젝트 현재 디렉토리를 객체로 생성
		File file = new File(".");
		
		// 폴더의 파일/폴더 목록을 문자열 배열로 반환
		String[] files = file.list();
		
		for(String fileName : files) {
			System.out.println(fileName);
		}
	}
}

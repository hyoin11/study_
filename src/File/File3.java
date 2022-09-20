package File;

import java.io.File;

public class File3 {
	public static void main(String[] args) {
		// 하드디스크의 루트 경로들을 배열로 반환
		File[] roots = File.listRoots();
		
		for(File root : roots) {
			// 루트 경로의 절대 경로
			System.out.println("root : " + root.getAbsolutePath());
			
			// 하드디스크 전체 용량
			System.out.println("하드 용량 : " + root.getTotalSpace());
			
			// 사용가능한 디스크 용량
			System.out.println("사용 가능한 디스크 용량 : " + root.getUsableSpace());
			
			// 여유 디스크 용량
			System.out.println("여유 디스크 용량 : " + root.getFreeSpace());
			
			// 사용한 디스크 용량
			System.out.println("사용한 디스크 용량 : " + (root.getTotalSpace() - root.getUsableSpace()));
		}
	}
}

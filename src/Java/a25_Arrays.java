package Java;

import java.util.Arrays;

public class a25_Arrays {
	public static void main(String[] args) {
		/*
		 * 배열의 복사
		 * 자바에서 배열은 한 번 생성하면 그 길이를 변경할 수 없음
		 * 따라서 더 많은 데이터를 저장하기 위해서는 더욱 큰 배열을 만들고, 이전 배열의 데이터를 새로 만든 배열로 복사해야 함
		 * 배열의 복사를 위한 방법
		 * 	1. System 클래스의 arraycopy()
		 * 	2. Arrays 클래스의 copyOf()
		 * 	3. Object 클래스의 clone() 메소드
		 * 	4. for문과 인덱스를 이용한 복사
		 */
		
		int[] arr1 = new int[] {1, 2, 3, 4, 5};
		int newLen = 10;
		
		// 1. System 클래스의 arraycopy()메소드
		int[] arr2 = new int[newLen];
		System.arraycopy(arr1, 0, arr2, 0, arr1.length);
		
		// 2. Arrays 클래스의 copyOf() 메소드
		int[] arr3 = Arrays.copyOf(arr1, 10);
		
		// 3. Object 클래스의 clone() 메소드
		int[] arr4 = (int[])arr1.clone();
		
		// 4. for문과 인덱스를 이용한 복사
		int[] arr5 = new int[newLen];
		for(int i=0; i<arr1.length; i++) {
			arr5[i] = arr1[i];
		}
		
		/*
		 * Enhanced for문
		 * 	문법
		 * 	for(타입 변수이름 : 배열이나컬렉션이름){
		 * 		배열의 길이만큼 반복적으로 실행하고자 하는 명령문;
		 * 	}
		 */
	}
}

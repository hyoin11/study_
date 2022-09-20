package Programmers;

import java.util.Arrays;

public class Sort_HIndex {
	public static void main(String[] args) {
//		System.out.println();
//		int[] citations = {3,0,6,1,5};
		int[] citations = {1, 1, 5, 7, 6};
		
		System.out.println(solution(citations));
	}
	
	public static int solution(int[] citations) {
		int h_index = -1;
		
//		for(int i=1; i<citations.length; i++) {
//			int target = citations[i];
//			int j = i-1;
//			
//			while(0 <= j && citations[j] < target) {
//				citations[j+1] = citations[j];
//				j--;
//			}
//			citations[j+1] = target;
//		}
//		System.out.println(Arrays.toString(citations));
//		for(int i=0; i<citations.length; i++) {
//			if((i+1) > citations[i]) {
//				h_index = i;
//				break;
//			}
//		}
//		if(h_index == -1) {
//			h_index = citations.length;
//		}
//		
//		return h_index;
		
		int answer = 0;
		Arrays.sort(citations);
		for(int i=0; i<citations.length; i++) {
			System.out.println("i " + i + " " + citations[i]);
			int smaller = Math.min(citations[i], citations.length-i);
			answer = Math.max(answer, smaller);
		}
		return answer;
	}
}
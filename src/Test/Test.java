package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Test {
	public static void main(String[] args) {
//		// [1, 1, 9, 1, 1, 1]	0	5
////		int[] priorities = {1,1,9,1,1,1};
////		int location = 5;
////		
////		System.out.println(solution(priorities, location));
//		
//		int[] citations = {0, 1, 1};
////		System.out.println(solution(citations));
//		
//		int a = 9;
//		int b = 11;
//		System.out.println(Integer.toBinaryString(a));
//		System.out.println(Integer.toBinaryString(b));
//		System.out.println(a & b);
//		System.out.println(Integer.toBinaryString(a & b));
		
		
//		int[] arr = {1, 5, 2, 7, 0};
//		System.out.println(Arrays.toString(arr));
//		Arrays.sort(arr);
//		System.out.println(Arrays.toString(arr));
//		ArrayList<Integer> list = Arrays.asList(arr);
//		
//		System.out.println(Collections.reverse(Arrays.asList(arr)));
	}
	
	public static int solution(int[]citations) {
		int answer = 0;
        
        for(int i=0; i<citations.length-1; i++) {
			for(int j=i+1; j<citations.length; j++) {
				if(citations[i] < citations[j]) {
					int temp = citations[i];
					citations[i] = citations[j];
					citations[j] = temp;
				}
			}
		}
        System.out.print(Arrays.toString(citations));
		
		for(int i=1; i<=citations.length; i++) {
			int temp = 0;
			for(int j=0; j<citations.length; j++) {
				if(citations[j] < i) {
					break;
				}
				temp++;
			}
			
			if(i == temp) {
				answer = i;
				break;
			}
		}
        
        if(answer == 0 && citations[0] > 0) {
			answer = citations.length;
		}
        
        return answer;
	}
//	
//	public static int solution(int[] priorities, int location) {
//		int answer = 0;
//		
//		Queue que_index = new LinkedList<Integer>();
//		Queue que_pri = new LinkedList<Integer>();
//		for(int i=0; i<priorities.length; i++) {
//			que_index.add(i);
//			que_pri.add(priorities[i]);
//		}
//		
//		System.out.println(que_index);
//		System.out.println(que_pri);
//		
//		for(int i=0; i<priorities.length; i++) {
//			boolean is_end = false;
//			for(int j=0; j<priorities.length-1; j++) {
//				for(int k=i+1; k<priorities.length; k++) {
//					if(priorities[i] < priorities[j]) {
//						System.out.println("i " + i + " j " + j);
//						
//						Object index = que_index.poll();
//						que_index.add(index);
//						Object pri = que_pri.poll();
//						que_pri.add(pri);
//						System.out.println(que_index);
//						System.out.println(que_pri);
//						is_end = true;
//						break;
//					}
//				}
//				if(is_end) break;
//			}
//		}
//		System.out.println(que_index);
//		System.out.println(que_pri);
//			
//	
//		return answer;
//    }
}
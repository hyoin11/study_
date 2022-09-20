package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Sort_Biggest {
	public static void main(String[] args) {
//		System.out.println();
		int[] numbers = {3, 30, 34, 5, 9};
		// 6210
//		int[] numbers = {20,200,20};
		
		
		System.out.println(solution(numbers));
		
		System.out.println("5".compareTo("123"));
	}
	
	public static String solution(int[] numbers) {
		String answer = "";
		
		String[] num_arr = new String[numbers.length];
		for(int i=0; i<numbers.length; i++) {
			num_arr[i] = Integer.toString(numbers[i]);
		}
		
		Arrays.sort(num_arr, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				System.out.println("o1 " + o1);
				System.out.println("o2 " + o2);
				System.out.println((o2+o1).compareTo(o1+o2));
				return ((o2+o1).compareTo(o1+o2));
			}
		});
		System.out.println(Arrays.toString(num_arr));
		
		if(num_arr[0].equals("0")) {
			answer = "0";
		}else {
			for(String str : num_arr) {
				answer += str;
			}
		}
		
//		for(int i=0; i<numbers.length-1; i++) {
//			for(int j=i+1; j<numbers.length; j++) {
//				String str_s = Integer.toString(numbers[i]).length() > Integer.toString(numbers[j]).length() ?
//						Integer.toString(numbers[j]) : Integer.toString(numbers[i]);
//				String str_l = Integer.toString(numbers[i]).length() > Integer.toString(numbers[j]).length() ?
//						Integer.toString(numbers[i]) : Integer.toString(numbers[j]);
//				
//				if(str_s.length() == str_l.length()) {
//					// 자리수가 같을 때 
//					if(numbers[i] < numbers[j]) {
//						int temp = numbers[i];
//						numbers[i] = numbers[j];
//						numbers[j] = temp;
//					}
//				}else {
//					// 자리수가 다를 때 
//					for(int k=0; k<str_s.length(); k++) {
//						if(str_s.charAt(k) > str_l.charAt(k)) {
//							// 짧은 숫자가 더 큰 경우
//							numbers[i] = Integer.parseInt(str_s);
//							numbers[j] = Integer.parseInt(str_l);
//							break;
//						}else if(str_s.charAt(k) < str_l.charAt(k)) {
//							// 긴 숫자가 더 큰 경우
//							numbers[i] = Integer.parseInt(str_l);
//							numbers[j] = Integer.parseInt(str_s);
//							break;
//						}else {
//							if(str_s.charAt(0) < str_l.charAt(str_l.length()-1)) {
//								// 긴 숫자 맨 뒷자리가 짧은 숫자 맨 앞자리보다 큰 경우
//								numbers[i] = Integer.parseInt(str_l);
//								numbers[j] = Integer.parseInt(str_s);
//							}else {
//								// 긴 숫자 맨 뒷자리가 짧은 숫자 맨 앞자리보다 작거나 같은 경우 
//								numbers[i] = Integer.parseInt(str_s);
//								numbers[j] = Integer.parseInt(str_l);
//							}	
//						}
//					}
//				}
//			}
//			answer += answer.equals("0") && numbers[i] == 0 ? "" : Integer.toString(numbers[i]);
//		}
//		answer += answer.equals("0") && numbers[numbers.length-1] == 0 ? "" : Integer.toString(numbers[numbers.length-1]);
		
		return answer;
	}
}

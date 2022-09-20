package Sort;

import java.util.Arrays;

public class CountingSort {
	/*
	 * 계수 정렬(카운팅 정렬)
	 * 데이터의 값이 몇 번 나왔는지 세줌
	 * 
	 * 1.배열을 돌면서 해당 값을 인덱스로하는 새로운 배열(counting)의 값을 1 증가시킴
	 * 2. counting 배열의 각 값을 누적의 합으로 변환
	 */
	
	public static void main(String[] args) {
		int[] arr = new int[100];		// 수열의 원소 : 100개
		int[] counting = new int[31];	// 수의 범위 : 0 ~ 30
		int[] result = new int[100];	// 정렬된 배열
		
		for(int i=0; i<arr.length; i++) {
			arr[i] = (int)(Math.random() * 31); // 0 ~ 30
		}
		
		// Counting Sort
		// 과정1
		for(int i=0; i<arr.length; i++) {
			// arr의 value를 index로 하는 counting 배열 값 1 증가
			counting[arr[i]]++;
		}
		
		// 과정2
		for(int i=1; i<counting.length; i++) {
			// 누적 합 해주기
			counting[i] += counting[i-1];
		}
		
		// 과정3
		for(int i=arr.length-1; i>=0; i--) {
			// i번째 원소를 인덱스로 하는 counting 배열을 1 감소시킨 뒤 counting 배열의 값을 인덱스로 하여 result에 value를 저장
			int value = arr[i];
			counting[value]--;
			result[counting[value]] = value;
		}
		
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(result));
	}
}

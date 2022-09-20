package Programmers;

import java.util.Arrays;
import java.util.Stack;

public class Stack_Stock {
	public static void main(String[] args) {
		int[] prices = {1, 2, 3, 2, 3, 1};
		
		System.out.println(Arrays.toString(solution2(prices)));
	}
	
	public static int[] solution(int[] prices) {
		int[] answer = new int[prices.length];

		for(int i=0; i<prices.length-1; i++) {
			for(int j=i+1; j<prices.length; j++) {
				/*
				 * 내가 푼거
				 * i번째랑 j번째랑 비교해서 크거나 같으면 초 증가
				 * 작으면 초 증가하고 멈추기
				 * 
				 * 다른사람 풀이
				 * 어짜피 초는 증가
				 * 작으면 브레이크
				 */
//				if(prices[i] <= prices[j]) answer[i]++;
//				else {
//					answer[i]++;
//					break;
//				}
				answer[i]++;
				if(prices[i] > prices[j]) break;
			}
		}
		
		return answer;
	}
	
	// stack 사용해서 풀기
	public static int[] solution2(int[] prices) {
		Stack<Integer> beginIdxs = new Stack<>();
		int i=0;
		int[] terms = new int[prices.length];
		
		beginIdxs.add(i);
		for(i=1; i<prices.length; i++) {
			while(!beginIdxs.empty() && prices[i] < prices[beginIdxs.peek()]) {
				int beginIdx = beginIdxs.pop();
				terms[beginIdx] = i - beginIdx;
			}
			beginIdxs.push(i);
			System.out.println(beginIdxs);
		}
		while(!beginIdxs.empty()) {
			int beginIdx = beginIdxs.pop();
			terms[beginIdx] = i - beginIdx - 1;
		}
		
		return terms;
	}
}

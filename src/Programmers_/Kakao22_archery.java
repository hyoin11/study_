package Programmers_;

import java.util.Arrays;

public class Kakao22_archery {
	static int[] answer = {-1};
	static int[] lion = new int[11];
	static int max = -1000;
	
	public static void dfs(int[] info, int cnt, int n) {
		if(cnt == n+1) {
			int apeach_point = 0;
			int lion_point = 0;
			
			for(int i=0; i<=10; i++) {
				if(info[i] != 0 || lion[i] != 0) {
					if(info[i] < lion[i]) lion_point += 10 - i;
					else apeach_point += 10 - i;
				}
			}
			if(lion_point > apeach_point) {
				if(lion_point - apeach_point >= max) {
					answer = lion.clone();
					max = lion_point - apeach_point;
				}
			}
			return;
		}
		
		for(int i=0; i<=10 && lion[i] <= info[i]; i++) {
			lion[i]++;
			dfs(info, cnt+1, n);
			lion[i]--;
		}
	}
	
	public static void main(String[] args) {
		int n = 10;
		int[] info = {0,0,0,0,0,0,0,0,3,4,3};
		
		System.out.println(Arrays.toString(solution(n, info)));
	}
	
	public static int[] solution(int n, int[] info) {
		dfs(info, 1, n);
		
		return answer;
	}
}

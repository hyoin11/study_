package Programmers_;

import java.util.Arrays;

public class Kakao22_undestroyedBuilding {
	public static void main(String[] args) {
		int[][] board = {{5,5,5,5,5}, {5,5,5,5,5}, {5,5,5,5,5}, {5,5,5,5,5}};
		int[][] skill = {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}};
		
		System.out.println(solution(board, skill));
	}
	
	public static int solution(int[][] board, int[][] skill) {
		int answer = 0;
		
		for(int i=0; i<skill.length; i++) {
			for(int j=skill[i][1]; j<=skill[i][3]; j++) {
				for(int k=skill[i][2]; k<=skill[i][4]; k++) {
					if(skill[i][0] == 1) {
						board[j][k] -= skill[i][5];
					}else {
						board[j][k] += skill[i][5];
					}
				}
			}
		}
		
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				if(board[i][j] > 0) answer++;
			}
		}
		return answer;
	}
}

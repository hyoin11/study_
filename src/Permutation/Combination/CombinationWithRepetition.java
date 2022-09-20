package Permutation.Combination;

import java.util.ArrayList;

public class CombinationWithRepetition {
	/*
	 * 중복 조합
	 * 중복으로 선택가능하며 순서 상관 없는 경우의 수
	 * H로 쓰임
	 */
	
	private int n;
	private int r;
	private int[] output;
	private boolean[] visited;
	private ArrayList<ArrayList<Integer>> result;
	
	public ArrayList<ArrayList<Integer>> getCombinationWithRepetition(){
		return result;
	}
	
	public CombinationWithRepetition(int n, int r) {
		this.n = n;
		this.r = r;
		this.output = new int[r];
		this.visited = new boolean[n];
		this.result = new ArrayList<>();
	}
	
	public void combinationWithRepetition(int[] arr, int depth) {
		if(depth == r) {
			ArrayList<Integer> temp = new ArrayList<>();
			for(int num : output) {
				temp.add(num);
			}
			result.add(temp);
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			output[depth] = arr[i];
			combinationWithRepetition(arr, depth+1);
		}
	}
}

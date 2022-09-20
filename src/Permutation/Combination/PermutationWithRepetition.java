package Permutation.Combination;

import java.util.ArrayList;

public class PermutationWithRepetition {
	/*
	 * 중복순열
	 * 중복으로 선택가능하며 순서 상관 있는 경우의 수
	 */
	
	private int n;
	private int r;
	private int[] output;
	private boolean[] visited;
	private ArrayList<ArrayList<Integer>> result;
	
	public ArrayList<ArrayList<Integer>> getPermutationWithRepetition(){
		return result;
	}
	
	public PermutationWithRepetition(int n, int r) {
		this.n = n;
		this.r = r;
		this.output = new int[n];
		this.visited = new boolean[n];
		this.result = new ArrayList<>();
	}
	
	public void permutationWithRepetition(int[] arr, int depth) {
		if(depth == r) {
			ArrayList<Integer> temp = new ArrayList<>();
			for(int i=0; i<r; i++) {
				temp.add(output[i]);
			}
			result.add(temp);
			return;
		}
		for(int i=0; i<n; i++) {
			visited[i] = true;
			output[depth] = arr[i];
			permutationWithRepetition(arr, depth+1);
			visited[i] = false;
		}
	}
}

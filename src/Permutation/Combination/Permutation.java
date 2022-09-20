package Permutation.Combination;

import java.util.ArrayList;

public class Permutation {
	/*
	 * 순열
	 * 순서 상관 있는 경우의 수
	 * [1,2]와 [2,1] 다름
	 */
	
	private int n;
	private int r;
	private int[] output;
	private boolean[] visited;
	private ArrayList<ArrayList<Integer>> result;
	
	public ArrayList<ArrayList<Integer>> getPermutation(){
		return result;
	}
	
	public Permutation(int n, int r) {
		this.n = n;
		this.r = r;
		this.output = new int[r];
		this.visited = new boolean[n];
		this.result = new ArrayList<ArrayList<Integer>>();
	}
	
	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public void permutation(int[] arr, int depth) {
		if(depth == r) {
			ArrayList<Integer> temp = new ArrayList<>();
			for(int i=0; i<output.length; i++) {
				temp.add(output[i]);
			}
			result.add(temp);
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				output[depth] = arr[i];
				permutation(arr, depth+1);
				visited[i] = false;
			}
		}
	}
}

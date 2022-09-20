package Permutation.Combination;

import java.util.ArrayList;

public class Combination {
	/*
	 * 조합
	 * 순서 상관 없는 경우의 수
	 * [1,2]와 [2,1] 같음
	 */
	
	private int n;
	private int r;
	private boolean[] visited;
	private ArrayList<ArrayList<Integer>> result;
	
	public ArrayList<ArrayList<Integer>> getCombination(){
		return result;
	}
	
	public Combination(int n, int r) {
		this.n = n;
		this.r = r;
		this.visited = new boolean[n];
		this.result = new ArrayList<ArrayList<Integer>>();
	}
	
	public void combination(int[] arr, int depth) {
		if(r == 0) {
			ArrayList<Integer> temp = new ArrayList<>();
			for(int i=0; i<visited.length; i++) {
				if(visited[i]) {
					temp.add(arr[i]);
				}
			}
			result.add(temp);
			return;
		}
		if(depth == arr.length) { return; }
		
		visited[depth] = true;
		n--;
		r--;
		combination(arr, depth+1);
		
		visited[depth] = false;
		r++;
		combination(arr, depth+1);
	}
}

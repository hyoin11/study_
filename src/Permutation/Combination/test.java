package Permutation.Combination;

import java.util.ArrayList;

public class test {
	public static void main(String[] args) {
		int[] arr = {1, 2, 3};
//		Combination comb = new Combination(3, 2);
//		comb.combination(arr, 0);
//		ArrayList<ArrayList<Integer>> result = comb.getCombination();
//		
//		for(int i=0; i<result.size(); i++) {
//			for(int j=0; j<result.get(i).size(); j++) {
//				System.out.print(result.get(i).get(j) + " ");
//			}
//			System.out.println();
//		}
		
		CombinationWithRepetition comb2 = new CombinationWithRepetition(3, 2);
		comb2.combinationWithRepetition(arr, 0);
		ArrayList<ArrayList<Integer>> result = comb2.getCombinationWithRepetition();
		
		for(int i=0; i<result.size(); i++) {
			for(int j=0; j<result.get(i).size(); j++) {
				System.out.print(result.get(i).get(j) + " ");
			}
			System.out.println();
		}
		
//		Permutation per = new Permutation(3, 1);
//		per.permutation(arr, 0);
//		ArrayList<ArrayList<Integer>> result = per.getPermutation();
//		
//		for(int i=0; i<result.size(); i++) {
//			for(int j=0; j<result.get(i).size(); j++) {
//				System.out.print(result.get(i).get(j) + " ");
//			}
//			System.out.println();
//		}
		
//		PermutationWithRepetition per2 = new PermutationWithRepetition(3, 2);
//		per2.permutationWithRepetition(arr, 0);
//		ArrayList<ArrayList<Integer>> result = per2.getPermutationWithRepetition();
//		
//		for(int i=0; i<result.size(); i++) {
//			for(int j=0; j<result.get(i).size(); j++) {
//				System.out.print(result.get(i).get(j) + " ");
//			}
//			System.out.println();
//		}
	}
	
}

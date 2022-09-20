package Programmers_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Kakao22_sheepAndWolf {
//	static int sheep_count = 0;
//	static int wolf_count = 0;
//	static HashMap<Integer, ArrayList<Integer>> nodes;
//	
//	public static void main(String[] args) {
//		int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
//		int[][] edges = {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}};
//		
//		System.out.println(solution(info, edges));
//	}
//	
//	public static int solution(int[] info, int[][] edges) {
//		nodes = new HashMap<>();
//		for(int[] edge : edges) {
//			if(!nodes.containsKey(edge[0])) nodes.put(edge[0], new ArrayList<>());
//			
//			nodes.get(edge[0]).add(edge[1]);
//		}
//		System.out.println(nodes);
//		
//		ArrayList<Integer> list = new ArrayList<>();
//		list.add(0);
//		
//		dfs(info, 0, list);
//		
//		return sheep_count;
//	}
//	
//	public static void dfs(int[] info, int idx, ArrayList<Integer> list) {
//		System.out.println("idx " + idx);
//		if(info[idx] == 0) sheep_count++;
//		else wolf_count++;
//		
//		if(sheep_count <= wolf_count) return;
//		
//		ArrayList<Integer> next = new ArrayList<>();
//		next.addAll(list);
//		if(nodes.containsKey(idx)) next.addAll(nodes.get(idx));
//		next.remove(Integer.valueOf(idx));
//		System.out.println(next);
//		
//		for(int n : next) {
//			dfs(info, n, next);
//		}
//		return;
//	}
	
	
	static int maxSheepCount;
	static ArrayList<Integer>[] childs;
	
	public static void main(String[] args) {
		int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
		int[][] edges = {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}};
		
		System.out.println(solution(info, edges));
	}
	
	public static int solution(int[] info, int[][] edges) {
		maxSheepCount = 0;
		
		// childs: 각 노드의 자식 노드 번호들을 저장
		// childs[x] 에는 x 노드의 자식 노드들이 arrayList에 저장
		childs = new ArrayList[info.length];
		
		for(int[] link : edges) {
			int parent = link[0];
			int child = link[1];
			
			if(childs[parent] == null) childs[parent] = new ArrayList<>();
			
			childs[parent].add(child);
		}
		System.out.println(Arrays.toString(childs));
		
		List<Integer> nextNodes = new ArrayList<>();
		nextNodes.add(0);
		getAnimal(0, 0, 0, nextNodes, info);
		
		return maxSheepCount;
	}
	
	// sheepCount : 양의 수, wolfCount : 늑대의 수, node : 현재 노드, nextNodes : 다음에 갈 수 있는 노드들
	public static void getAnimal(int sheepCount, int wolfCount, int node, List<Integer> nextNodes, int[] info) {
		System.out.println("idx " + node);
		
		sheepCount += info[node] ^ 1;
		wolfCount += info[node];
		maxSheepCount = Math.max(maxSheepCount, sheepCount);
		
		if(sheepCount <= wolfCount) return;
		
		List<Integer> copied = new ArrayList<>();
		copied.addAll(nextNodes);
		if(childs[node] != null) copied.addAll(childs[node]);
		copied.remove(Integer.valueOf(node));
		System.out.println(copied);
		
		for(int nextNode : copied) {
			getAnimal(sheepCount, wolfCount, nextNode, copied, info);
		}
	}
}

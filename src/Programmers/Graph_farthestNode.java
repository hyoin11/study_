package Programmers;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Graph_farthestNode {
	public static void main(String[] args) {
		int n = 6;
		int[][] vertex = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
		
		System.out.println(solution(n, vertex));
	}
	
	public static int solution(int n, int[][] edge) {
		int answer = 0;
		
		LinkedList<Integer>[] nodeList = new LinkedList[n+1];
		for(int i=0; i<=n; i++) {
			nodeList[i] = new LinkedList<Integer>();
		}
		
		for(int i=0; i<edge.length; i++) {
			int node1 = edge[i][0];
			int node2 = edge[i][1];
			
			nodeList[node1].add(node2);
			nodeList[node2].add(node1);
			
			Collections.sort(nodeList[node1]);
			Collections.sort(nodeList[node2]);
		}
		
		int[] count = new int[n+1];
		boolean[] visited = new boolean[n+1];
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);	// 시작점
		visited[0] = visited[1] = true;
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			
			for(int nextNode : nodeList[node]) { // node와 연결된 노드들
				if(!visited[nextNode]) {
					count[nextNode] = count[node]+1;
					visited[nextNode] = true;
					queue.add(nextNode);
				}
				
			}
		}
		
		System.out.println(Arrays.toString(count));
		Arrays.sort(count);
		int max = 0;
		for(int i=count.length-1; i>=0; i--) {
			if(max < count[i]) max = count[i];
			if(max > count[i]) break;
			answer++;
		}
		
		return answer;
	}
}

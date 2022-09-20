package DFS.BFS;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
	/*
	 * 너비 우선 탐색 (Bread-First Search)
	 * 루트 혹은 다른 임의의 노드에서 시작한 인접 노드를 먼저 탐색하는 방법
	 * 
	 * 특징
	 * BFS는 재귀적으로 동작하지 않음
	 * 그래프 탐색의 경우 어떤 노드를 방문했었는지 여부를 반드시 검사해야함
	 * 방문한 노드들을 차례로 저장한 후 꺼낼 수 있는 자료 구조인 큐를 사용
	 * 선입선출 원칙으로 탐색
	 * 시작 정점으로부터 가까운 정점을 먼저 방문하고 멀리 떨어져 있는 정점을 나중에 방문하는 순회방법
	 * 김게(deep) 탐색하기 전에 넓게(wide) 탐색하는 것
	 * 두 노드 사이의 최단 경로 혹은 임의의 경로를 찾고 싶을 때 이 방법을 사용
	 * 
	 */
	
	public static void main(String[] args) {
		int[] numbers = {0, 1, 2, 3, 4};
		boolean[] visit = new boolean[numbers.length];
		
		bfs(numbers, 0, visit);
	}
	
	public static void bfs(int[] numbers, int start, boolean[] visit) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		visit[start] = true;
		
		while(!queue.isEmpty()) {
			int next = queue.poll();
			System.out.println(next);
			
			for(int i : numbers) {
				if(!visit[i]) {
					queue.add(i);
					visit[i] = true;
				}
			}
		}
	}
}

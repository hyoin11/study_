package DFS.BFS;

import java.util.Arrays;

public class DFS {
	/*
	 * 깊이 우선 탐색 (Depth-First Search)
	 * 루트 혹은 다른 임의의 노드에서 다음 분기로 넘어가기 전에, 해당 분기를 모두 탐색하는 방법.
	 * 탐색 후에는 다시 원점으로 돌아가 다른 분기를 탐색함
	 * 
	 * 특징
	 * 자기 자신을 호출하는 순환 알고리즘의 형태를 지님(재귀 또는 스택)
	 * 이 알고리즘을 구현할 때 가장 큰 차이점은 그래프 탐색의 경우 어떤 노드를 방문했었는지 여부를 반드시 검사해야함(이를 검사하지 않을 경우 무한루프에 빠질 수 있음)
	 * 미로를 탐색할 때, 해당 분기에서 갈 수 있을 때까지 계속 가다가 더 이상 갈 수 없게 되면 다시 가장 가까운 갈림길로(새로운 분기)로 돌아와서 다른 방향으로 탐색을 진행하는 방법과 유사
	 * 모든 노드를 방문하고자 할 때, 이 방법을 선택
	 * 너비우선탐색(BFS)보다 더 간단
	 * 검색 속도 자체는 너비우선탐색(BFS)에 비해 느림
	 * 
	 */
	
	public static void main(String[] args) {
		int[] numbers = {0, 1, 2, 3, 4};
		boolean[] visit = new boolean[numbers.length];
		
		dfs(numbers, 0, visit);
	}
	
	public static void dfs(int[] numbers, int idx, boolean[] visit) {
		visit[idx] = true;
		System.out.println(idx);
		
		for(int i : numbers) {
			if(!visit[i]) {
				dfs(numbers, i, visit);
			}
		}
	}
}

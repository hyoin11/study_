package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Stack_Printer {
	public static void main(String[] args) {
		int[] priorities = {2,1,3,2};
		int location = 2;
		
		System.out.println(solution(priorities, location));
		System.out.println(solution2(priorities, location));
	}
	
	public static int solution(int[] priorities, int location) {
		Queue<ArrayList<Integer>> pri_que = getQueue(priorities);
		Queue<ArrayList<Integer>> answer_que = checkPriority(pri_que);
		
		return findLocation(answer_que, location);
	}
	
	private static Queue<ArrayList<Integer>> getQueue(int[] priorities){
		Queue<ArrayList<Integer>> que = new LinkedList<>();
		
		// 큐에 현재 순서와 출력 우선순위 넣기
		for(int i=0; i<priorities.length; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			temp.add(i);
			temp.add(priorities[i]);
			que.add(temp);
		}
		
		return que;
	}
	
	private static Queue<ArrayList<Integer>> checkPriority(Queue<ArrayList<Integer>> pri_que){
		Queue<ArrayList<Integer>> que = new LinkedList<>();
		
		// 큐에서 하나씩 뽑아서 우선순위가 더 높은게 있는지 확인. 있으면 다시뽑고 없으면 answer_que에 넣기
		ArrayList<Integer> current = null;
		while(!pri_que.isEmpty()) {
			current = pri_que.poll();
			
			boolean is_cur_high = true;
			for(ArrayList<Integer> prioritie : pri_que) {
				if(current.get(1) < prioritie.get(1)) {
					pri_que.add(current);
					current = null;
					is_cur_high = false;
					break;
				}
			}
			if(is_cur_high) {
				que.add(current);
				current = null;
			}
		}
		
		return que;
	}
	
	private static int findLocation(Queue<ArrayList<Integer>> answer_que, int location) {
		// location이 몇번째 위치하는지 확인
		int i=0;
		int answer = 0;
		for(ArrayList<Integer> prioritie : answer_que) {
			if(prioritie.get(0) == location) {
				answer = i + 1;
			}
			i++;
		}
		
		return answer;
	}
	
	// 다른사람풀이
	public static int solution2(int[] priorities, int location) {
		int answer = 0;
		int l = location;
		
		Queue<Integer> que = new LinkedList<>();
		for(int i : priorities) {
			que.add(i);
		}
		
		Arrays.sort(priorities);
		int size = priorities.length - 1;
		
		while(!que.isEmpty()) {
			System.out.println("que" + que);
			Integer i = que.poll();
			if(i == priorities[size - answer]) {
				answer++;
				l--;
				if(l < 0) break;
			}else {
				que.add(i);
				l--;
				if(l < 0) l = que.size()-1;
			}
		}
		
		return answer;
	}
}

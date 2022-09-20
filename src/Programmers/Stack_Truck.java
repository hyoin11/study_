package Programmers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Stack_Truck {
	private static int answer;
	
	public static void main(String[] args) {
		int bridge_length = 100;
		int weight = 100;
		int[] truck_weights = {10,10,10,10,10,10,10,10,10,10};
		
		System.out.println(solution(bridge_length, weight, truck_weights));
		System.out.println(solution2(bridge_length, weight, truck_weights));
		System.out.println(solution3(bridge_length, weight, truck_weights));
	}
	
	public static int solution(int bridge_length, int weight, int[] truck_weights) {
		answer = 0;
		
		// que에는 다리위에 올라가는 트럭을 넣음
		Queue<Integer> que = new LinkedList<>();
		for(int i=0; i<truck_weights.length; i++) {
			// 다리에 트럭이 가득 차있다면 빼주기
			if(que.size() == bridge_length) {
				que.poll();
			}
			
			// 다리위에 있는 트럭의 무게를 계산
			int total_truck_weight = 0;
			for(int que_truck : que) {
				total_truck_weight += que_truck;
			}
			
			// 다리위에 있는 트럭과 새로 올라갈 트럭의 무게가 제한무게를 넘으면 트럭을 올릴 수 없음
			if(total_truck_weight + truck_weights[i] > weight) {
				que.add(0);
				answer++;
				i--;
			}else {
				que.add(truck_weights[i]);
				answer++;
			}
		}
		
		// 다리에 트럭이 다 올라가지 않았다면 그만큼 이동시킬 공간 추가
		if(que.size() < bridge_length) {
			int que_size = que.size();
			
			while(que_size < bridge_length) {
				que.add(0);
				que_size++;
			}
		}
		
		// 다리 위에서 트럭 이동시키기
		while(!que.isEmpty()) {
			que.poll();
			answer++;
		}
		
		return answer;
	}
	
	// 다른사람 풀이1
	static class Truck{
		int weight;
		int move;
		
		public Truck(int weight) {
			this.weight = weight;
			this.move = 1;
		}
		
		public void moving() {
			move++;
		}
	}
	public static int solution2(int bridge_length, int weight, int[] truck_weights) {
		Queue<Truck> waitQ = new LinkedList<>();
		Queue<Truck> moveQ = new LinkedList<>();
		
		for(int truck : truck_weights) {
			waitQ.offer(new Truck(truck));
		}
		
		int answer = 0;
		int curWeight = 0;
		
		while(!waitQ.isEmpty() || !moveQ.isEmpty()) {
			answer++;
			
			if(moveQ.isEmpty()) {
				Truck truck = waitQ.poll();
				curWeight += truck.weight;
				moveQ.offer(truck);
				continue;
			}
			
			for(Truck truck : moveQ) {
				truck.moving();
			}
			
			if(moveQ.peek().move > bridge_length) {
				Truck truck = moveQ.poll();
				curWeight -= truck.weight;
			}
			
			if(!waitQ.isEmpty() && curWeight + waitQ.peek().weight <= weight) {
				Truck truck = waitQ.poll();
				curWeight += truck.weight;
				moveQ.offer(truck);
			}
		}
		
		return answer;
	}
	
	// 다른사람 풀이2
	public static int solution3(int bridge_length, int weight, int[] truck_weights) {
		Stack<Integer> truckStack = new Stack<>();
		Map<Integer, Integer> bridgeMap = new HashMap<>();
		
		for(int i=truck_weights.length-1; i>=0; i--) {
			truckStack.push(truck_weights[i]);
		}
		
		int answer = 0;
		int sum = 0;
		while(true) {
			answer++;
			
			if(bridgeMap.containsKey(answer)) bridgeMap.remove(answer);
			
			sum = bridgeMap.values().stream().mapToInt(Number::intValue).sum();
			
			if(!truckStack.isEmpty()) {
				if(sum + truckStack.peek() <= weight) {
					bridgeMap.put(answer + bridge_length, truckStack.pop());
				}
			}
			
			if(bridgeMap.isEmpty() && truckStack.isEmpty()) break;
		}
		
		return answer;
	}
}

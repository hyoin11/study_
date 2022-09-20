package Programmers;

import java.util.Arrays;

public class Greedy_Boat {
	public static void main(String[] args) {
		int[] people = {70, 50, 80, 50};
		int limit = 100;
		
		System.out.println(solution(people, limit));
	}
	
	public static int solution(int[] people, int limit) {
		int answer = 0;
		
		Arrays.sort(people);
		
		boolean[] on_boat = new boolean[people.length];
		int limit_people = 0;
		int sum = 0;
		
		for(int i=people.length-1; i>0; i--) {
			if(!on_boat[i]) {
				on_boat[i] = true;
				limit_people++;
				sum = people[i];
				
				answer++;
				
				if(sum + 40 <= limit) {
					for(int j=0; j<people.length-i; j++) {
						if(on_boat[j]) continue;
						if((sum+people[j] > limit)) break;
						
						sum += people[j];
						on_boat[j] = true;
						
						break;
					}
				}
				limit_people = 0;
				sum = 0;
			}
		}
		
		if(!on_boat[0]) answer++;
		
		return answer;
	}
}
package Programmers_;

import java.util.ArrayList;
import java.util.Arrays;

public class Kakao22_parking {
	public static void main(String[] args) {
		int[] fees = {180, 5000, 10, 600};
		String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
		
		System.out.println(Arrays.toString(solution(fees, records)));
		// System.out.println();
	}
	
	public static int[] solution(int[] fees, String[] records) {
		int[] answer;
		
		ArrayList<Integer> list = new ArrayList<>();
		
		int base_time = fees[0];
		int base_price = fees[1];
		int unit_time = fees[2];
		int unit_price = fees[3];
		
		int[] in_time = new int[10000];
		Arrays.fill(in_time, -1);
		int[] total_time = new int[10000];
		Arrays.fill(in_time, -1);
		
		for(String record : records) {
			String[] arr = record.split(" ");
			int minute = (Integer.parseInt(arr[0].split(":")[0])*60) + 
					Integer.parseInt(arr[0].split(":")[1]);
			int car_num = Integer.parseInt(arr[1]);
			if(arr[2].equals("IN")) {
				in_time[car_num] = minute;
			}else {
				total_time[car_num] += minute-in_time[car_num];
				in_time[car_num] = -1;	// 출차했으니까 기록 
			}
		}
		int num = 0;
		for(int i=0; i<in_time.length; i++) {
			if(in_time[i] != -1) {
				total_time[i] += 1439 - in_time[i];
			}
			if(total_time[i] > 0) num++;
		}
		answer = new int[num];
		for(int i=0; i<total_time.length; i++) {
			if(total_time[i] == 0) continue;
			
			int time = total_time[i]-base_time;
			if(time < 0) {
				time = 0;
			}else {
				time = time % unit_time == 0 ? time / unit_time : (int)time / unit_time + 1;
			}
			int total_price = base_price + time * unit_price;
			list.add(total_price);
		}
		for(int i=0; i<list.size(); i++) {
			answer[i] = list.get(i);
		}
		
		return answer;
	}
}

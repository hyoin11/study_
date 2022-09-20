package Programmers_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Kakao22_report {
	public static void main(String[] args) {
//		String[] id_list = {"muzi", "frodo", "apeach", "neo"};
//		String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
		String[] id_list = {"con", "ryan"};
		String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
		int k = 2;
		
		System.out.println(Arrays.toString(solution(id_list, report, k)));
		
		//id_list	report	k	result
//		["muzi", "frodo", "apeach", "neo"]	["muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"]	2	[2,1,1,0]
		
		//["con", "ryan"]	["ryan con", "ryan con", "ryan con", "ryan con"]	
	}
	
	public static int[] solution(String[] id_list, String[] report, int k) {
		int[] answer = new int[id_list.length];
		
		HashMap<String, ArrayList<String>> report_list = new HashMap<>();
		for(int i=0; i<id_list.length; i++) {
			ArrayList<String> arr = new ArrayList<>();
			report_list.put(id_list[i], arr);
		}
		
		for(int i=0; i<report.length; i++) {
			String[] arr = report[i].split(" ");
			if(!report_list.get(arr[1]).contains(arr[0])) {
				report_list.get(arr[1]).add(arr[0]);				
			}
		}
		
		for(String id : report_list.keySet()) {
			if(report_list.get(id).size() >= k) {
				for(int i=0; i<report_list.get(id).size(); i++) {
					for(int j=0; j<id_list.length; j++) {
						if(id_list[j].equals(report_list.get(id).get(i))) {
							answer[j]++;
							break;
						}
					}
				}
			}
		}
		
		return answer;
	}
}

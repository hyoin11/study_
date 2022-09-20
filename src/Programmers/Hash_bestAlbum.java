package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Hash_bestAlbum {
	private static HashMap<String, Integer> totalPlays;
	private static HashMap<String, ArrayList<int[]>> genreTotalPlays;
	
	public static void main(String[] args) {
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		
		System.out.println(Arrays.toString(solution(genres, plays)));
		System.out.println(Arrays.toString(solution2(genres, plays)));
		System.out.println(Arrays.toString(solution3(genres, plays)));
	}
	
	public static int[] solution(String[] genres, int[] plays) {
		totalPlays = new HashMap<>();
		genreTotalPlays = new HashMap<>();
		
		List<Entry<String, Integer>> list_entries;
		ArrayList<Integer> list;
		
		
		setHashMap(genres, plays);

		list_entries = sortTotalPlays();
		sortGenreTotalPlays();
		
		list = getAnswer(list_entries);

		return listToArray(list);
	}
	
	private static void setHashMap(String[] genres, int[] plays) {
		for(int i=0; i<genres.length; i++) {
			if(totalPlays.containsKey(genres[i])){
				totalPlays.put(genres[i], totalPlays.get(genres[i]) + plays[i]);
			}else {
				totalPlays.put(genres[i], plays[i]);
			}
			
			int[] play = new int[2];
			play[0] = i;
			play[1] = plays[i];
			ArrayList<int[]> temp;
			
			if(genreTotalPlays.containsKey(genres[i])) {
				temp = genreTotalPlays.get(genres[i]);
			}else {
				temp = new ArrayList<>();
			}
			temp.add(play);
			
			genreTotalPlays.put(genres[i], temp);
		}
	}
	
	private static List<Entry<String, Integer>> sortTotalPlays(){
		List<Entry<String, Integer>> list_entries = new ArrayList<Entry<String, Integer>>(totalPlays.entrySet());
		Collections.sort(list_entries, new Comparator<Entry<String, Integer>>(){
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
//				return o1.getValue().compareTo(o2.getValue());	// 오름차순
				return o2.getValue().compareTo(o1.getValue());	// 내림차순
			}
		});
		
		return list_entries;
	};
	
	private static void sortGenreTotalPlays() {
		for(String key : genreTotalPlays.keySet()) {
			ArrayList<int[]> temp = genreTotalPlays.get(key);
			Collections.sort(temp, new Comparator<int[]>() {
				public int compare(int[] o1, int[] o2) {
					int answer = o2[1] > o1[1] ? 1 : -1;
					if(o2[1] == o1[1]) answer = o2[1] > o1[1] ? -1 : 1;
					return answer;
				}
			});
		}
	}
	
	private static ArrayList<Integer> getAnswer(List<Entry<String, Integer>> list_entries){
		ArrayList<Integer> answer = new ArrayList<>();
		
		for(Entry<String, Integer> list : list_entries) {
			for(int i=0; i<genreTotalPlays.get(list.getKey()).size(); i++) {
				answer.add(genreTotalPlays.get(list.getKey()).get(i)[0]);
				
				if(i == 1) break;
			}
		}
		
		return answer;
	}
	
	private static int[] listToArray(ArrayList<Integer> list) {
		int[] answer = new int[list.size()];
		for(int i=0; i<list.size(); i++) {
			answer[i] = list.get(i);
		}
		
		return answer;
	}
	
	// 다른사람 풀이
	public static int[] solution2(String[] genres, int[] plays) {
		HashMap<String, Object> genresMap = new HashMap<>();	// <장르, 곡정보>
		HashMap<String, Integer> playMap = new HashMap<>();	// <장르, 총 재생수>
		ArrayList<Integer> resultAl = new ArrayList<>();
		
		for(int i=0; i<genres.length; i++) {
			String key = genres[i];
			HashMap<Integer, Integer> infoMap;	// 곡정보 <곡 고유번호, 재생횟수>
			
			if(genresMap.containsKey(key)) infoMap = (HashMap<Integer, Integer>)genresMap.get(key);
			else infoMap = new HashMap<Integer, Integer>();
			
			infoMap.put(i, plays[i]);
			genresMap.put(key, infoMap);
			
			// 재생수
			if(playMap.containsKey(key)) playMap.put(key, playMap.get(key) + plays[i]);
			else playMap.put(key, plays[i]);
		}
		
		int mCnt = 0;
		Iterator it = sortByValue(playMap).iterator();
		
		while(it.hasNext()) {
			String key = (String)it.next();
			Iterator indexIt = sortByValue((HashMap<Integer, Integer>)genresMap.get(key)).iterator();
			int playsCnt = 0;
			
			while(indexIt.hasNext()) {
				resultAl.add((int)indexIt.next());
				mCnt++;
				playsCnt++;
				if(playsCnt > 1) break;
			}
		}
		
		int[] answer = new int[resultAl.size()];
		
		for(int i=0; i<resultAl.size(); i++) {
			answer[i] = resultAl.get(i).intValue();
		}
		
		return answer;
	}
	
	private static ArrayList<String> sortByValue(final Map map) {
		ArrayList<String> keyList = new ArrayList<>();
		keyList.addAll(map.keySet());
		
		Collections.sort(keyList, new Comparator() {
			public int compare(Object o1, Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);
				
				return ((Comparable) v2).compareTo(v1);
			}
		});
		
		return keyList;
	}
	
	
}

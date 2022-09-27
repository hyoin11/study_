package Programmers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Hash_pocketmon {
    // 1845

    public static void main(String[] args) {
        try {
            Hash_pocketmon obj = new Hash_pocketmon();
            obj.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run(String[] args) throws Exception {
        int[] nums = {3,1,2,3};
        System.out.println(solution(nums));
    }

    public int solution(int[] nums) {
        int hNum = nums.length / 2;

        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            if(map.containsKey(num)){
                map.put(num, map.get(num)+1);
            }else{
                map.put(num, 1);
            }
        }

        return hNum <= map.size() ? hNum : map.size();
    }

    // 다른사람 풀이
    // hashSet -> 중복을 허용하지 않음, 순서대로 입력되지 않고 일정하게 유지됨
    public int solution2(int[] nums){
        HashSet<Integer> hs = new HashSet<>();

        for(int i=0; i<nums.length; i++){
            hs.add(i);
        }

        return hs.size() > nums.length / 2 ? nums.length / 2 : hs.size();
    }
}

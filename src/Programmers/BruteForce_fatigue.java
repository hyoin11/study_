package Programmers;

import java.util.HashMap;
import java.util.Map;

public class BruteForce_fatigue {
    // 87946
    // https://school.programmers.co.kr/learn/courses/30/lessons/87946

    public static void main(String[] args) {
        try {
            BruteForce_fatigue obj = new BruteForce_fatigue();
            obj.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run(String[] args) throws Exception {
        int k = 80;
        int[][] dungeons = {{80,20}, {50,40}, {30,10}}; // 최소 피로도, 소모 피로도

        System.out.println(solution(k, dungeons));
    }

    public int solution(int k, int[][] dungeons) {
        perm(dungeons, 0, k);

        return dungeonCount;
    }

    int dungeonCount = 0;
    public void perm(int[][] dungeons, int depth, int k){
        if(depth == dungeons.length){
            int count = 0;
            for(int[] dungeon : dungeons){
                if(dungeon[0] <= k && k - dungeon[1] >= 0){
                    k -= dungeon[1];
                    count++;
                }else{
                    break;
                }
            }
            dungeonCount = dungeonCount >= count ? dungeonCount : count;
            return;
        }
        for(int i=depth; i<dungeons.length; i++){
            swap(dungeons, depth, i);
            perm(dungeons, depth+1, k);
            swap(dungeons, depth, i);
        }
    }

    public void swap(int[][] arr, int depth, int i){
        int[] temp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = temp;
    }
}
package Programmers;

import java.util.HashMap;
import java.util.Map;

public class BruteForce_minBox {
    // 86491

    public static void main(String[] args) {
        try {
            BruteForce_minBox obj = new BruteForce_minBox();
            obj.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run(String[] args) throws Exception {
        int[][] sizes = {{60,50}, {30,70}, {60,30}, {80,40}};
        System.out.println(solution(sizes));
    }

    public int solution(int[][] sizes) {
        // 가로 > 세로
        int w = 0;
        int h = 0;
        for(int[] size : sizes){
            int width;
            int height;
            if(size[0] > size[1]){
                width = size[0];
                height = size[1];
            }else{
                width = size[1];
                height = size[0];
            }

            if(w < width){
                w = width;
            }
            if(h < height){
                h = height;
            }
        }

        return w * h;
    }
}

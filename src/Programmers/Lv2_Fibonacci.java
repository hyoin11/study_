package Programmers;

import java.util.Arrays;

public class Lv2_Fibonacci {
    // 12945

    public static void main(String[] args) {
        try {
            Lv2_Fibonacci obj = new Lv2_Fibonacci();
            obj.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run(String[] args) throws Exception {
//        System.out.println(solution(3));
        System.out.println(solution(1500));
    }

    public int solution(int n) {
        int[] arr = new int[n+1];
        arr[1] = 1;

        for(int i=2; i<=n; i++){
            arr[i] = (arr[i-1] % 1234567) + (arr[i-2] % 1234567);   // (A + B) % 1234567 = ((A % 1234567) + (B % 1234567)) % 1234567 동일
        }

        return arr[n] % 1234567;
    }
}

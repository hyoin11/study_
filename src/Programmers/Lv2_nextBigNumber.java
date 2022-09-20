package Programmers;

import Bank.Bank;

public class Lv2_nextBigNumber {
    public static void main(String[] args) {
        try {
            Lv2_nextBigNumber obj = new Lv2_nextBigNumber();
            obj.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run(String[] args) throws Exception {
        System.out.println(solution(78));
        System.out.println(solution(15));
    }

    public int solution(int n) {
        int answer = 0;
        int nCount = Integer.bitCount(n);   // Integer.bitCount(). 이진수로 변환하여 1의 갯수를 반환

        for(int i=n+1; n<=1000000; i++){
            if(Integer.bitCount(i) == nCount){
                answer = i;
                break;
            }
        }
        return answer;
    }
}

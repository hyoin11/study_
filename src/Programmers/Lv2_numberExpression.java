package Programmers;

public class Lv2_numberExpression {
    // 12924

    public static void main(String[] args) {
        try {
            Lv2_numberExpression obj = new Lv2_numberExpression();
            obj.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run(String[] args) throws Exception {
        System.out.println(solution(15));
    }

    public int solution(int n) {
        int answer = 0;
//        int i=1;
//        while(true){
//            if(i > n) break;
//            int sum = 0;
//            for(int j=i; j<=n; j++){
//                sum += j;
//                if(sum == n){
//                    answer++;
//                    break;
//                }else if(sum > n){
//                    break;
//                }
//            }
//            i++;
//        }

        for(int i=1; i<=n; i++){
            int sum = 0;
            for(int j=i; j<=n; j++){
                sum += j;
                if(sum == n){
                    answer++;
                    break;
                }else if(sum > n){
                    break;
                }
            }
        }

        return answer;
    }
}

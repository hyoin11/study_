package Programmers;

public class BruteForce_vowelDictionary {
    // https://school.programmers.co.kr/learn/courses/30/lessons/84512

    public static void main(String[] args) {
        try {
            BruteForce_vowelDictionary obj = new BruteForce_vowelDictionary();
            obj.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run(String[] args) throws Exception {
        String word = "AAAAE";

        System.out.println(solution(word));
    }

    public int solution(String word) {
        int answer = -1;

        // A E I O U
        // 1 2 3 4 5
        String[] wordArr = word.split("");
        word = "0.";

        for(String w : wordArr){
            switch(w){
                case "A":
                    word += "1";
                    break;
                case "E":
                    word += "2";
                    break;
                case "I":
                    word += "3";
                    break;
                case "O":
                    word += "4";
                    break;
                case "U":
                    word += "5";
                    break;
                default:
                    break;
            }

        }

        findIndex(word);

        return answer;
    }

    int index = 0;
    double now = 0d;
    public void findIndex(String word){

        if(Double.parseDouble(word) == now){
            return;
        }
//        for()
    }
}

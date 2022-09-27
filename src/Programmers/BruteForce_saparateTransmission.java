package Programmers;

public class BruteForce_saparateTransmission {
    // https://school.programmers.co.kr/learn/courses/30/lessons/86971

    public static void main(String[] args) {
        try {
            BruteForce_saparateTransmission obj = new BruteForce_saparateTransmission();
            obj.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run(String[] args) throws Exception {
        int n = 9;
        int[][] wires = {{1,3}, {2,3}, {3,4}, {4,5}, {4,6}, {4,7}, {7,8}, {7,9}};

        System.out.println(solution(n, wires));
    }

    public int solution(int n, int[][] wires) {
        int answer = -1;

        return answer;
    }
}

package Programmers;

import DataStructure.Stack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lv2_minSum {
    // 12941

    public static void main(String[] args) {
        try {
            Lv2_minSum obj = new Lv2_minSum();
            obj.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run(String[] args) throws Exception {
        int[] a = {1, 4, 2};
        int[] b = {5, 4, 4};
        System.out.println(solution(a, b));
        System.out.println(solution2(a, b));
    }

    public int solution(int []A, int []B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        for(int i=0; i<A.length; i++){
            answer += A[i] * B[A.length-1-i];
        }

        return answer;
    }

    // 다른사람풀이 quicksort 구현
    public int solution2(int []A, int []B){
        int answer = 0;

        int length = A.length;

        quickSort(A, 0, length-1);
        quickSort(B, 0, length-1);

        for(int i=0; i<length-1; i++){
            answer += A[i] * B[length-1-i];
        }

        return answer;
    }

    public void quickSort(int[] arr, int left, int right){
        int i, j, pivot, tmp;

        if(left < right){
            i = left;
            j = right;
            pivot = arr[left];

            // 분할과정
            while(i < j){
                while(arr[j] > pivot) j--;
                while(i < j && arr[i] <= pivot) i++;

                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
            arr[left] = arr[i];
            arr[i] = pivot;

            // 정렬과정
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }
    }
}

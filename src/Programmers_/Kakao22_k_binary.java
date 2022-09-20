package Programmers_;

public class Kakao22_k_binary {
	public static void main(String[] args) {
		int n = 437674;
		int k = 3;
		
		System.out.println(solution(n, k));
	}
	
	public static int solution(int n, int k) {
		int answer = 0;
		
		String num = Integer.toString(n, k);
		String[] arr_num = num.split("");
		
		String str = "";
		for(String number : arr_num) {
			if(number.equals("0") && str != "") {
				Long prime = Long.parseLong(str);	
				// int가 아닌 long형으로 사용하는 이유는 1 ≤ n ≤ 1,000,000 이기 때문(1,000,000을 2진수로 바꾸면 int로 허용할 수 있는 범위 초과)
				if(isPrime(prime)) {
					answer++;
				}
				str = "";
			}else {
				str += number;
			}
		}
		// 마지막 str 판별
		Long prime = Long.parseLong(str);
		if(isPrime(prime)) answer++;
		
		return answer;
	}
	
	public static boolean isPrime(Long num) {
		boolean is_prime = true;
		if(num < 2) return false;
		for(int i=2; i*i<=num; i++) {
			if(num % i == 0) {
				is_prime = false;
				break;
			}
		}
		return is_prime;
	}
}

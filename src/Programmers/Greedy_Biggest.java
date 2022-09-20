package Programmers;

public class Greedy_Biggest {
	public static void main(String[] args) {
		String number = "1231234";
		int k = 3;
		
		System.out.println(solution(number, k));
	}
	
	public static String solution(String number, int k) {
		String answer = "";
        if(number.charAt(0) == '0') return "0";
        
        StringBuffer str_num = new StringBuffer(number);
        int index = 0;
		for(int i=0; i<k; i++) {
			for(int j=index; j<str_num.length()-1; j++) {
				if(str_num.charAt(j) == '9') index = j;	// 9가 제일 큰 수이기 때문에 비교할 필요가 없음
				if(str_num.charAt(j) < str_num.charAt(j+1)) {
					str_num = str_num.deleteCharAt(j);
					break;
				}
			}
		}
        answer = str_num.toString();
		if(answer.length() > number.length()-k) {
			answer = answer.substring(0, number.length()-k);
		}
        return answer;
	}
}
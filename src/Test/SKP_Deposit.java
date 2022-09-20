package Test;

import java.util.Arrays;
import java.util.Stack;

public class SKP_Deposit {
	public static void main(String[] args) {
		int[] deposit = {500, 1000, -300, 200, -400, 100, -100};
		
		System.out.println(Arrays.toString(solution(deposit)));
	}
	
	public static int[] solution(int[] deposit) {
		Stack<Integer> stack = getDepositStack(deposit);

        return getArray(stack);
	}
	
	public static Stack<Integer> getDepositStack(int[] deposit){
		Stack<Integer> stack = new Stack<>();
		int money = 0;
		boolean is_end;
		for(int i=0; i<deposit.length; i++) {
			money = 0;
			if(i > 0 && deposit[i] < 0) money = stack.pop();
			money += deposit[i];
			
			is_end = false;
			while(!is_end) {
				if(money >= 0) is_end = true;
				else money += stack.pop();
			}
			
			if(money > 0) stack.push(money);
		}
		
		return stack;
	}
	
	public static int[] getArray(Stack<Integer> stack) {
		int[] answer = new int[stack.size()];
		int i = 1;
		while(!stack.isEmpty()) {
			answer[answer.length-i] = stack.pop();
			i++;
		}
		return answer;
	}
}

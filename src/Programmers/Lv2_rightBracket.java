package Programmers;

import DataStructure.Stack;

import java.util.LinkedList;
import java.util.Queue;

public class Lv2_rightBracket {
    // 12909

    public static void main(String[] args) {
        try {
            Lv2_rightBracket obj = new Lv2_rightBracket();
            obj.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run(String[] args) throws Exception {
        System.out.println(solution("())()(()"));
        System.out.println(solution("(())()"));
        System.out.println(solution(")()("));
        System.out.println(solution("(()("));
    }

    public boolean solution(String s) {
        if(s.length() % 2 == 1 || s.charAt(0) == ')'){
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);

            if(c == '('){
                stack.push(c);
            }else if(c == ')' && !stack.empty()){
                stack.pop();
            }else{
                return false;
            }
        }

        return stack.empty();
    }
}

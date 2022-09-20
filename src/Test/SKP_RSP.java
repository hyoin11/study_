package Test;

import java.util.Arrays;

public class SKP_RSP {
	private static int r_count;
	private static int s_count;
	private static int p_count;
	private static int[] answer;
	
	public static void main(String[] args) {
		String[] rsp3 = {"SPR","PPR","PSS","RSS","RRR"};
		
		System.out.println(Arrays.toString(solution(rsp3)));
//		result
//		[3,1,2]
	}
	
	public static int[] solution(String[] rsp3) {
		answer = new int[3];

        for(String rsp : rsp3){
        	r_count = 0;
    		s_count = 0;
    		p_count = 0;
        	
            String[] player = rsp.split("");
            
            getRSPCount(player);
            
            findWinner(player);
        }
        
        return answer;
    }

	private static void getRSPCount(String[] player) {
		for(int i=0; i<player.length; i++){
          if(player[i].equals("R")) r_count++;
          else if(player[i].equals("S")) s_count++;
          else p_count++;
      }
	}
	
	private static void findWinner(String[] player) {
		// 승자가 없을 경우 - 다 같은것을 낼 경우, 다 다른것을 낼 경우
        if(r_count == 3 || s_count == 3 || p_count == 3) return;
        else if(r_count == 1 && s_count == 1 && p_count == 1) return;
        // // 승자가 1명인 경우 - 가위 1 보 2, 바위 1 가위 2, 보1 바위 2
        else if((s_count == 1 && p_count == 2) || (r_count == 1 && s_count == 2) || (p_count == 1 && r_count == 2)){
            // 가위 1 보2
            if(s_count == 1){
                if(player[0].equals("S")) answer[0] += 2;
                else if(player[1].equals("S")) answer[1] += 2;
                else answer[2] += 2;
            }
            // 바위 1 가위 2
            else if(r_count == 1){
                if(player[0].equals("R")) answer[0] += 2;
                else if(player[1].equals("R")) answer[1] += 2;
                else answer[2] += 2;
            }
            // 보 1 바위 2
            else{
                if(player[0].equals("P")) answer[0] += 2;
                else if(player[1].equals("P")) answer[1] += 2;
                else answer[2] += 2;
            }
        }
        // 승자가 2명인 경우 - 가위 2 보1, 바위2 가위 1, 보2 바위 1
        else if(
            (s_count == 2 && p_count == 1) || 
            (r_count == 2 && s_count == 1) ||
            (p_count == 2 && r_count == 1)
        ){
            // 가위 2 보 1
            if(p_count == 1){
                if(player[0].equals("P")){
                    if(answer[1] == answer[2]){
                        answer[1]++;
                        answer[2]++;
                    }else if(answer[1] > answer[2]){
                        answer[2] += 2;
                    }else{
                        answer[1] += 2;
                    }
                }else if(player[1].equals("P")){
                    if(answer[0] == answer[2]){
                        answer[0]++;
                        answer[2]++;
                    }else if(answer[0] > answer[2]){
                        answer[2] += 2;
                    }else{
                        answer[0] += 2;
                    }
                }else{
                    if(answer[0] == answer[1]){
                        answer[0]++;
                        answer[1]++;
                    }else if(answer[0] > answer[1]){
                        answer[1] += 2;
                    }else{
                        answer[0] += 2;
                    }
                }
            }
            // 바위 2 가위 1
            else if(s_count == 1){
                if(player[0].equals("S")){
                    if(answer[1] == answer[2]){
                        answer[1]++;
                        answer[2]++;
                    }else if(answer[1] > answer[2]){
                        answer[2] += 2;
                    }else{
                        answer[1] += 2;
                    }
                }else if(player[1].equals("S")){
                    if(answer[0] == answer[2]){
                        answer[0]++;
                        answer[2]++;
                    }else if(answer[0] > answer[2]){
                        answer[2] += 2;
                    }else{
                        answer[0] += 2;
                    }
                }else{
                    if(answer[0] == answer[1]){
                        answer[0]++;
                        answer[1]++;
                    }else if(answer[0] > answer[1]){
                        answer[1] += 2;
                    }else{
                        answer[0] += 2;
                    }
                }
            }
            // 보 2 바위 1
            else{
                if(player[0].equals("R")){
                    if(answer[1] == answer[2]){
                        answer[1]++;
                        answer[2]++;
                    }else if(answer[1] > answer[2]){
                        answer[2] += 2;
                    }else{
                        answer[1] += 2;
                    }
                }else if(player[1].equals("R")){
                    if(answer[0] == answer[2]){
                        answer[0]++;
                        answer[2]++;
                    }else if(answer[0] > answer[2]){
                        answer[2] += 2;
                    }else{
                        answer[0] += 2;
                    }
                }else{
                    if(answer[0] == answer[1]){
                        answer[0]++;
                        answer[1]++;
                    }else if(answer[0] > answer[1]){
                        answer[1] += 2;
                    }else{
                        answer[0] += 2;
                    }
                }
            }
        }
    
	}
}

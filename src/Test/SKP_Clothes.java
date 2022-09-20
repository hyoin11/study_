package Test;

public class SKP_Clothes {
	private static String[] top_color;
	private static String[] bottom_color;
	private static boolean[] is_select;
	
	public static void main(String[] args) {
		String[] color = {"RG", "WR", "BW", "GG"};
		int[] prices = {5000, 6000};
		
		System.out.println(solution(color, prices));
	}
	
	public static int solution(String[] color, int[] prices) {
        top_color = new String[color.length];
        bottom_color = new String[color.length];
        is_select = new boolean[color.length];
        
        separateColor(color);
        
        findSet();

        return Math.min((prices[0] * 5), getTotalPrice(prices));
    }
	
	private static void separateColor(String[] color) {
		for(int i=0; i<color.length; i++) {
			String[] temp = color[i].split("");
			
			top_color[i] = temp[0];
			bottom_color[i] = temp[1];
		}
	}
	
	private static void findSet() {
		for(int i=0; i<top_color.length; i++){
            for(int j=0; j<bottom_color.length; j++){
                if(top_color[i].equals(bottom_color[j]) && !is_select[j]){
                    is_select[j] = true;
                    break;
                }
            }
        }
	}
	
	private static int getTotalPrice(int[] prices) {
		int totalPrice = 0;
		
		for(int i=0; i<is_select.length; i++){
            if(is_select[i]) totalPrice += prices[0];
            else totalPrice += prices[1];
        }
		
		return totalPrice;
	}

}

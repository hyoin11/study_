package ETC;

import java.util.Arrays;
import java.util.Collections;

public class ReverseSort {
	public static void main(String[] args) {
		int[] arr = {10, 2, 4, 1, 5, 3, 8, 9, 7, 6};
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		Integer[] arr2 = Arrays.stream(arr).boxed().toArray(Integer[]::new);
		Arrays.sort(arr2, Collections.reverseOrder());
		System.out.println(Arrays.toString(arr2));
		// Arrays.sort(arr, Collections.reverseOrder()); -> 에러나는 이유는 타입이 안맞기때문이였나...
		// java에서 int Integer 다름 
	}
}

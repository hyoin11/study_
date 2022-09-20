package 연산자;

public class BitwiseOperator {
	/*
	 * 비트 연산자
	 * &	대응되는 비트가 모두 1이면 1을 반환 (AND 연산)
	 * |	대응되는 비트 중에서 하나라도 1이면 1을 반환(OR 연산)
	 * ^	대응되는 비트가 서로 다르면 1을 반환함 (XOR 연산)
	 * ~	비트를 1이면 0으로, 0이면 1로 반전 (NOT 연산, 1의 보수)
	 * <<	명시된 수만큼 비트들을 전부 왼쪽으로 이동시킴 (left shift 연산)
	 * >>	부호를 유지하면서 지정한 수만큼 비트를 오른쪽으로 이동시킴 (right shift 연산)
	 * >>>	지정한 수만큼 비트를 전부 오른쪽으로 이동시키며, 새로운 비트는 전부 0이 됨
	 */
	public static void main(String[] args) {
		int a = 9;
		int b = 11;
		
		// AND 연산자
		System.out.println("AND(&) 연산자");
		System.out.println(Integer.toBinaryString(a));	// Integer.toBinaryString -> 10진을 2진으로
		System.out.println(Integer.toBinaryString(b));
		System.out.println(Integer.toBinaryString(a&b));
		System.out.println();
		
		// OR 연산자
		System.out.println("OR(|) 연산자");
		System.out.println(Integer.toBinaryString(a));
		System.out.println(Integer.toBinaryString(b));
		System.out.println(Integer.toBinaryString(a|b));
		System.out.println();
		
		// XOR 연산자
		System.out.println("XOR(^) 연산자");
		System.out.println(Integer.toBinaryString(a));
		System.out.println(Integer.toBinaryString(b));
		System.out.println(Integer.toBinaryString(a^b));
		System.out.println();
		
		// NOT 연산자
		System.out.println("NOT(~) 연산자");
		System.out.println(Integer.toBinaryString(a));
		System.out.println(Integer.toBinaryString(b));
		System.out.println(Integer.toBinaryString(~a));
		System.out.println(Integer.toBinaryString(~b));
		System.out.println();
		
		// << 연산자
		System.out.println("<< 연산자");
		System.out.println(Integer.toBinaryString(a));
		System.out.println(Integer.toBinaryString(b));
		System.out.println(Integer.toBinaryString(a<<2));
		System.out.println(Integer.parseInt(Integer.toBinaryString(a<<2), 2));
		System.out.println(Integer.toBinaryString(36));
		System.out.println(Integer.toBinaryString(b<<2));
		System.out.println(Integer.parseInt(Integer.toBinaryString(b<<2), 2));
		System.out.println(Integer.toBinaryString(44));
		System.out.println();
		
		// >> 연산자
		System.out.println(">> 연산자");
		System.out.println(Integer.toBinaryString(a));
		System.out.println(Integer.toBinaryString(b));
		System.out.println(Integer.toBinaryString(a>>2));
		System.out.println(Integer.toBinaryString(b>>2));
		System.out.println(Integer.parseInt(Integer.toBinaryString(a>>2), 2));
		System.out.println(Integer.parseInt(Integer.toBinaryString(b>>2), 2));
		System.out.println(Integer.toBinaryString(2));
		System.out.println();
		
		// >>> 연산자
		System.out.println(">>> 연산자");
		System.out.println(Integer.toBinaryString(a));
		System.out.println(Integer.toBinaryString(b));
		System.out.println(Integer.toBinaryString(a>>>2));
		System.out.println(Integer.toBinaryString(b>>>2));
		System.out.println(Integer.parseInt(Integer.toBinaryString(a>>2), 2));
		System.out.println(Integer.parseInt(Integer.toBinaryString(b>>2), 2));
		System.out.println(Integer.toBinaryString(2));
		System.out.println();
	}
}

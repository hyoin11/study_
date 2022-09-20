package String;

import java.util.Date;
import java.util.Locale;

public class Format {
	/*
	 * %d	10진수 형식
	 * %s	문자열 형식
	 * %f	실수형 형식
	 * locale 설정
	 * %t	날짜시간 형식
	 * %c	유니코드 문자 형식
	 * %o, %x	8진수, 16진수 형식
	 */
	
	public static void main(String[] args) {
		/*
		 * Integer Formatting
		 * 
		 * %와 d사이에 정수를 설정하면, 글자 길이를 설정할 수 있음
		 * 기본적으로 오른쪽 정렬, -를 붙일 경우 왼쪽 정렬
		 * 표현할 숫자인 i의 길이가 5보다 작을 경우 0을 붙임
		 * 
		 * % 바로 뒤에 ,를 붙이면 3자리 단위로 쉼표를 찍어줌
		 */
		int i = 11;
		
		System.out.println(String.format("%d_", i));
		System.out.println(String.format("%5d_", i));
		System.out.println(String.format("%-5d_", i));
		System.out.println(String.format("%05d_", i));
		System.out.println(String.format("%d_", i));
		System.out.println();
		
		int i2 = 123456789;
		System.out.println(String.format("%,d_", i2));
		System.out.println(String.format("%,15d_", i2));
		System.out.println(String.format("%,-15d_", i2));
		System.out.println(String.format("%,015d_", i2));
		System.out.println();
		
		
		/*
		 * String Formatting
		 * 
		 * %s는 문자열을 그대로 출력
		 * %s 앞에 숫자를 설정할 경우, str.length()가 n보다 작을 경우 공백 추가
		 * -를 붙일 경우, 왼쪽 정렬(default는 오른쪽 정렬)
		 * 숫자를 설정할 경우, 최대 n길이 만큼만 출력
		 */
		String str = "test";
		System.out.println(String.format("%s_", str));
		System.out.println(String.format("%12s_", str));
		System.out.println(String.format("%-12s_", str));
		System.out.println(String.format("%.2s_", str));
		System.out.println(String.format("%-12.2s_", str));
		System.out.println(String.format("%12.2s_", str));
		System.out.println();
		
		
		/*
		 * Floating point Formatting
		 * 
		 * 실수형 숫자 형식을 설정할 때
		 * %f는 %.6f와 같음
		 * %15.2f는 글자길이 15, 소수점 아래 2자로 나타내라는 의미로 .도 글자길이에 포함됨
		 * 소수점 아래는 반올림하여 출력
		 * %d와 마찬가지로, 숫자 %뒤 정수부 앞에 0을 붙이면, 왼쪽에 공백으로 표시될 부분을 0으로 채워줌
		 */
		double n = 123.45678;
		
		System.out.println(3.4);
		System.out.println(n);
		System.out.println();
		
		System.out.println(String.format("%f_", 3.4));
		System.out.println(String.format("%f_", n));
		System.out.println(String.format("%.6f_", n));
		System.out.println(String.format("%15f_", n));
		System.out.println(String.format("%-15f_", n));
		System.out.println(String.format("%.3f_", n));
		System.out.println(String.format("%.2f_", n));
		System.out.println(String.format("%15.2f_", n));
		System.out.println(String.format("%-15.2f_", n));
		System.out.println(String.format("%015f_", n));
		System.out.println(String.format("%015.2f_", n));
		System.out.println();
		
		
		/*
		 * Locale 설정
		 * 
		 * String.format(Locale, format, value);
		 * 메서드를 이용하면 국가별 포맷 설정이 가능
		 * 
		 * 오전 오후를 표시하는 포맷인 %tp의 경우에도 기본값으로는 '오후'를 출력
		 */
		int money = 35000;
		Date today = new Date();
		
		System.out.println(String.format("￦ %,d", money));
		System.out.println(String.format(Locale.GERMANY, "%,d €", money));
		System.out.println(String.format("%tp", today));
		System.out.println(String.format(Locale.ENGLISH, "%tp", today));
		System.out.println();
		
		
		/*
		 * DateTime Formatting
		 * 
		 * y	연, year
		 * m	월, month
		 * d	일, day of month
		 * H	시, 24hour
		 * h	시, 12hour
		 * M	분, minute
		 * s	초, second
		 * 
		 * 기본적으로 시간 및 날짜 형식에는 leading-0s를 붙임
		 * 가장 많이 이용될 포맷 형식은 %tF와 %tT로, 날짜와 시각을 연-월-일, 시:분:초로 나타냄
		 * 연월일은 yyMMdd 형태로 출력(leading-0s 포함)하고 싶을 때에는 %ty%tm%d
		 * 시분초를 HH:mm:ss 형태로 출력(leading-0s 포함)하고 싶을 때에는 %tH%tM%tS
		 * * 포맷에 %문자를 쓰고 싶다면 %%와 같이 % 문자를 번 쓰면 됨
		 * 
		 * 참고로 %tB와 %tb는 한글로는 똑같이 출력되는데 영어에서는 February, Feb 이렇게 표현됨
		 */
		
		Date day = new Date();
		System.out.println(day + "\n");
		System.out.println(String.format("%%tF(yyyy-MM-dd): %tF", day));
		System.out.println(String.format("%%tT(02H:02m:02s): %tT, %%tR(02H:02m): %tR", day, day));
		System.out.println(String.format("%%ty(2y): %ty, %%tY(4y): %tY", day, day));
		System.out.println(String.format("%%tm(02M): %tm", day));
		System.out.println(String.format("%%td(02d): %td, %%te(d): %te", day, day));
		System.out.println(String.format("%%tH(02H): %tH", day));
		System.out.println(String.format("%%tM(02m): %tM", day));
		System.out.println(String.format("%%tS(02s): %tS", day));
		System.out.println(String.format("%%tZ(time zone): %tZ, %%tz(time zone offset): %tz", day, day));
		System.out.println();
		
		System.out.println(String.format("%%tA(day of week, Full name): %tA, %%ta: %ta", day, day));
		System.out.println(String.format("%%tB(month, Full name): %tB, %%tb: %tb", day, day));
		System.out.println(String.format(Locale.ENGLISH, "%%tB(month, Full name): %tB, %%tb: %tb", day, day));
		System.out.println(String.format("%%tc(= %%ta %%tb %%td %%tT %%tZ %%tY): %tc", day));
		System.out.println(String.format("%%tD(MM/dd/yy): %tD", day));
		System.out.println(String.format("%%td(02d): %td, %%te(d): %te", day, day));
		System.out.println(String.format("%%tF(yyyy-02M-02d): %tF", day));
		System.out.println(String.format("%%tH(02H, 00-23): %tH, %%tK(H, 0-23): %tk", day, day));
		System.out.println(String.format("%%tI(02h, 01-12): %tI, %%tl(h, 1-12): %tl", day, day));
		System.out.println(String.format("%%tj(day of year, 001-366): %tj", day));
		System.out.println(String.format("%%tp(오전 또는 오후): %tp", day));
		System.out.println();
		
		
		/*
		 * Unicode char Formatting
		 * 
		 * 숫자를 유니코드로 변환해줌
		 */
		System.out.println(String.format("Unicode 코드 -> 문자"));
		System.out.println(String.format("48 -> %c, 57 -> %c", 48, 57));
		System.out.println(String.format("65 -> %c, 90 -> %c", 65, 90));
		System.out.println(String.format("97 -> %c, 122 -> %c", 97, 122));
		System.out.println(String.format("44032 -> %c, 55203 -> %c", 44032, 55203));
		System.out.println();
		
		
		/*
		 * Octal/Hex Formatting
		 * 
		 * 8진수 16진수
		 */
		int num = 100;
		System.out.println(String.format("10진수(%d) -> 2진수(%s), 8진수(%o), 16진수(%x)", num, Integer.toBinaryString(num), num, num));
	}
}

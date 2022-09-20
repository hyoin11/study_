package Java;

public class a10_RealNumber {
	/*
	 * 실수의 표현 방식
	 * 컴퓨터에서 실수를 표현하는 방법은 정수에 비해 훨씬 복잡함
	 * 컴퓨터는 2진수로만 표현해야 하기 때문
	 * 	1. 고정 소수점(fixed point) 방식
	 * 		실수는 보통 정수부와 소수부로 나눌 수 있음
	 * 		따라서 실수를 표현하는 가장 간단한 방식은 소수부의 자릿수를 미리 정하여 고정된 자릿수의 소수를 표현하는 것
	 * 		32 비트 실수를 고정 소수점 방식으로 표현
	 * 			1비트		15비트	16비트
	 * 			----	-----	-----
	 * 			부호		정수부	소수부
	 * 			---------------------
	 * 					32비트
	 * 		이 방식은 정수부와 소수부의 자릿수가 크지 않으므로 표현할 수 있는 범위가 매우 적음
	 * 	2. 부동 소수점(floating point) 방식
	 * 		실수는 보통 정수부와 소수부로 나누지만, 가수부와 지수부로 나누어 표현할 수도 있음
	 * 		부동 소수점 방식은 다음 수식을 이용하여 매우 큰 실수까지도 표현할 수 있음
	 * 			수식
	 * 			+-(1.가수부)*2^지수부-127
	 * 		현재 대부분의 시스템에서는 부동 소수점 방식으로 실수를 표현하고 있음
	 * 
	 * IEEE 부동 소수점 방식
	 * 현재 사용되고 있는 부동 소수점 방식은 대부분 IEEE 754 표준을 따르고 있음
	 * 32비트의 float형 실수를 IEEE 부동 소수점 방식으로 표현하면 다음과 같음
	 * 	1비트		8비트		23비트
	 * 	----	----	-----
	 * 	부호		지수부	가수부
	 * 	---------------------
	 * 			32비트
	 * 62비트의 double형 실수를 IEEE 부동 소수점 방식으로 표현하면 다음과 같음
	 * 	1비트		11비트	52비트
	 * 	----	-----	-----
	 * 	부호		지수부	가수부
	 * 	---------------------
	 * 			64비트
	 * 
	 * 부동 소수점 방식의 오차
	 * 고정 소수점 방식보다 훨씬 더 많은 범위까지 표현할 수 있음
	 * 하지만 부동 소수점 방식에 의한 실수의 표현은 항상 오차가 존재한다는 단점을 가지고 있음
	 * 부동 소수점 방식에서의 오차는 앞서 살펴본 공식에 의해 발생함
	 * 이 공식을 사용하면 표현할 수 있는 범위는 늘어나지만, 10진수를 정확하게 표현할 수는 없게 됨
	 * 따라서 컴퓨터에서 실수를 표현하는 방법은 정확한 표현이 아닌 언제나 근사치를 표현할 뿐임을 항상 명심해야함
	 */
}
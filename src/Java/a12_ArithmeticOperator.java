package Java;

public class a12_ArithmeticOperator {
	/*
	 * 산술 연산자
	 * 사칙연산을 다루는 연산자로, 가장 기본적이면서도 가장 많이 사용되는 연산자 중 하나
	 * 모두 두개의 피연산자를 가지는 이항 연산자이며, 피연산자들의 결합 방향은 왼쪽에서 오른쪽임
	 * (항이란 해당 연산의 실행이 가능하기 위해 필요한 값이나 변수를 의미함. 따라서 이항 연산자란 해당 연산의 실행을 위해서 두 개의 값이나 변수가 필요한 연산자를 의미)
	 * 	산술 연산자	설명
	 * 	+			왼쪽의 피연산자에 오른쪽의 피연산자를 더함
	 * 	-			왼쪽의 피연산자에서 오른쪽의 피연산자를 뺌
	 * 	*			왼쪽의 피연산자에 오른쪽의 피연산자를 곱함
	 * 	/			왼쪽의 피연산자를 오른쪽의 피연산자로 나눔
	 * 	%			왼쪽의 피연산자를 오른쪽의 피연산자로 나눈 후, 그 나머지를 반환함
	 * 
	 * 연산자의 우선순위(operator precedence)와 결합 방향(associativity)
	 * 괄호 (()) 연산자를 사용하여 연산자의 처리 순서를 변경
	 * 	우선순위	연산자		설명								결합 방향
	 * 	1		[]			첨자 연산자						왼쪽에서 오른쪽으로
	 * 			.			멤버 연산자						왼쪽에서 오른쪽으로
	 * 	2		++			후위 증가 연산자					왼쪽에서 오른쪽으로
	 * 			--			후위 감소 연산자					왼쪽에서 오른쪽으로
	 * 	3		!			논리 NOT 연산자					오른쪽에서 왼쪽으로
	 * 			~			비트 NOT 연산자					오른쪽에서 왼쪽으로
	 * 			+			양의 부호(단항 연산자)				오른쪽에서 왼쪽으로
	 * 			-			음의 부호(단항 연산자)				오른쪽에서 왼쪽으로
	 * 			++			전위 증가 연산자					오른쪽에서 왼쪽으로
	 * 			--			전위 감소 연산자					오른쪽에서 왼쪽으로
	 * 			(타입)		타입 캐스트 연산자					오른쪽에서 왼쪽으로
	 * 	4		*			곱셈 연산자						왼쪽에서 오른쪽으로
	 * 			/			나눗셈 연산자						왼쪽에서 오른쪽으로
	 * 			%			나머지 연산자						왼쪽에서 오른쪽으로
	 * 	5		+			덧셈 연산자(이항 연산자)				왼쪽에서 오른쪽으로
	 * 			-			뺄셈 연산자(이항 연산자)				왼쪽에서 오른쪽으로
	 * 	6		<<			비트 왼쪽 시프트 연산자				왼쪽에서 오른쪽으로
	 * 			>>			부호 비트를 확장하면서 비트 오른쪽 시프트	왼쪽에서 오른쪽으로
	 * 			>>>			부호 비트까지 모두 비트 오른쪽 시프트		왼쪽에서 오른쪽으로
	 * 	7		<			관계 연산자(보다 작은)				왼쪽에서 오른쪽으로
	 * 			<=			관계 연산자(보다 작거나 같은)			왼쪽에서 오른쪽으로
	 * 			>			관계 연산자(보다 큰)					왼쪽에서 오른쪽으로
	 * 			>=			관계 연산자(보다 크거나 같은)			왼쪽에서 오른쪽으로
	 * 			instanceOf	인스턴스의 실제 타입 반환				왼쪽에서 오른쪽으로
	 * 	8		==			관계 연산자(와 같은)					왼쪽에서 오른쪽으로
	 * 			!=			관계 연산자(와 같지 않은)				왼쪽에서 오른쪽으로
	 * 	9		&			비트 AND 연산자					왼쪽에서 오른쪽으로
	 * 	10		^			비트 XOR 연산자					왼쪽에서 오른쪽으로
	 * 	11		|			비트 OR 연산자						왼쪽에서 오른쪽으로
	 * 	12		&&			논리 AND 연산자					왼쪽에서 오른쪽으로
	 * 	13		|| 			논리 OR 연산자						왼쪽에서 오른쪽으로
	 * 	14		?:			삼항 조건 연산자					오른쪽에서 왼쪽으로
	 * 	15	 	=			대입 연산자 및 복합 대입 연산자			오른쪽에서 왼쪽으로
	 */
}
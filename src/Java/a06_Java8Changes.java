package Java;

public class a06_Java8Changes {
	/*
	 * JAVA8 변경사항
	 * 1. 람다 표현식(lambda expression) : 함수형 프로그래밍
	 * 2. 스트림 API(stream API) : 데이터의 추상화
	 * 3. java.time 패키지 : Joda-Time을 이용한 새로운 날짜와 시간 API
	 * 4. 나즈혼(Nashorn) : 자바스크립트의 새로운 엔진
	 * 
	 * 람다 표현식
	 * 간단히 말해 메소드를 하나의 식으로 표현한 것
	 * 즉, 식별자 없이 실행할 수 있는 함수 표현식을 의미하며, 익명함수(anonymous function)이라고도 불림
	 * 클래스를 만들고 객체를 생성하지 않아도 메소드를 만들 수 있음
	 * 메소드의 매개변수로 전달될 수도 있고, 메소드의 결과값으로 반환될 수도 있음
	 * 불필요한 코드를 줄여주고, 가독성을 높이는데 목적이 있음
	 * 예제
	 * 	new Thread(new Runnable(){
	 * 		public void run(){
	 * 			System.out.println("전통적인 방식의 일회용 스레드 생성");
	 * 		}
	 *	}).start();
	 *	
	 *	new Thread(() -> {
	 *		System.out.println("람다 표현식을 사용한 일회용 스레드 생성");
	 *	}).start();
	 * 
	 * 스트림 API
	 * 자바에서는 많은 양의 데이터를 저장하기 위해 배열이나 컬렉션을 사용함
	 * 또한, 이렇게 저장된 데이터에 접근하기 위해서는 반복문이나 반복자(iterator)를 사용하여 매번 코드를 작성해야했음
	 * 하지만 이렇게 작성된 코드는 길고 가독성도 떨어지며, 재사용이 거의 불가능함
	 * 또한, 데이터베이스의 쿼리와 같은 정형화된 처리 패턴을 가지지 못했기에 데이터마다 다른 방법으로 접근해야만 했음
	 * 이러한 문제점을 극복하기 위해 도입된 방법이 바로 스트림api
	 * 스트림 api는 데이터를 추상화하여 다루므로, 다양한 방식으로 저장된 데이터를 읽고 쓰기 위한 공통된 방법을 제공
	 * 따라서 스트림 api를 이용하면 배열이나 컬렉션뿐만 아니라 파일에 저장된 데이터도 모두 같은 방법으로 다룰 수 있음
	 * 예제
	 * 	String[] arr = new String[]{"넷", "둘", "셋", "하나"};
	 * 
	 *	// 배열에서 스트림 생성
	 *	Stream<String> stream1 = Arrays.stream(arr);
	 * 	stream1.forEach(e -> System.out.println(e + " "));
	 * 	System.out.println();
	 * 	
	 * 	// 배열의 특정 부분만을 이용한 스트림 생성
	 * 	Stream<String> stream2 = Arrays.stream(arr, 1, 3);
	 * 	stream2.forEach(e -> System.out.println(e + " "));
	 * 
	 * java.time 패키지
	 * Date클래스를 사용하여 날짜에 관한 처리를 수행했지만 현재 대부분의 메소드가 사용을 권장하지 않음(deprecated)
	 * 새롭게 제공됨 Calendar 클래스로 날짜와 시간에 대한 정보를 얻을 수 있지만 문제점이 있음
	 * 	1. 캘린더 인스턴스는 불변 객체(immutable object)가 아니라서 값이 수정될 수 있음
	 * 	2. 윤호(leap second)와 같은 특별한 상황을 고려하지 않음
	 * 	3. 캘린더 클래스에서는 월(month)을 나타낼때 1부터 12월을 0부터 11까지로 표현해야하는 불편함이 있음
	 * Joda-Time 라이브러리를 발전시킨 새로운 날짜와 시간인 API인 java.time 패키지를 제공함
	 * 위와 같은 문제점을 모두 해결했으며, 다향한 기능을 지원하는 다수의 하위 패키지를 포함함
	 * 예제
	 * 	LocalDate today = LocalDate.now();
	 * 	System.out.println("올해는" + today.getYear() + "년 입니다.");
	 * 	
	 * 	LocalDate otherDay = today.withYear(1982);
	 * 	System.out.println("올해는" + otherDay.getYear() + "년 입니다.);
	 * 	
	 * 나즈혼
	 * 지금까지 자바스크립트의 기본 엔진으로는 모질라의 리노(Rhino)가 사용되어왔음
	 * 리노는 자바의 최신 개선 사항등을 제대로 활용하지 못하는 등 노후화된 모습을 보여줌
	 * 따라서 새로운 엔진인 오라클의 나즈혼을 도입
	 * 성능과 메모리 관리면에서 크게 개선된 스크립트 엔진
	 */
}

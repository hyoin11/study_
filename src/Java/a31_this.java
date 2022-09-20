package Java;

public class a31_this {
	/*
	 * this 참조 변수
	 * 인스턴스가 바로 자기 자신을 참조하는 데 사용하는 변수
	 * 이러한 this 참조 변수는 해당 인스턴스의 주소를 가리키고 있음
	 * 매개변수 이름과 인스턴스 변수의 이름이 같을 경우에는 인스턴스 변수 앞에 this 키워드를 붙여 구분해야함
	 * 	class Car{
	 * 		private String modelName;
	 * 		private int modelYear;
	 * 
	 * 		Car(String modelName, int modelYear){
	 * 			this.modelName = modelName;
	 * 			this.modelYear = modelYear;
	 * 		}
	 * 	}
	 * this 참조 변수를 사용하여 인스턴스 변수에 접근할 수 있음
	 * this 참조 변수를 사용할 수 있는 영역은 인스턴스 메소드뿐이며, 클래스 메소드에서는 사용할 수 없음
	 * 모든 인스턴스 메소드에는 this 참조 변수가 숨겨진 지역 변수로 존재하고 있음
	 * 
	 * this() 메소드
	 * 생성자 내부에서만 사용할 수 있으며, 같은 클래스의 다른 생성자를 호출할 때 사용
	 * this()메소드에 인수를 전달하면, 생성자 중에서 메소드 시그니처가 일치하는 다른 생성자를 찾아 호출해줌
	 * 	class Car{
	 * 		private String modelName;
	 * 		private int modelYear;
	 * 
	 * 		Car(String modelName, int modelYear){
	 * 			this.modelName = modelName;
	 * 			this.modelYear = modelYear;
	 * 		}
	 * 
	 * 		Car(){
	 * 			this("소나타", 2012);	// 다른 생성자를 호출함
	 * 		}
	 * 	}
	 * 첫번째 생성자는 this 참조변수를 사용하여 인스턴스 변수에 접근
	 * 두번째 생성자는 내부에서 this() 메소드를 이용하여 첫번째 생성자를 호출
	 * 이렇게 내부적으로 다른 생성자를 호출하여 인스턴스 변수를 초기화할 수 있음
	 * (단, 한 생성자에서 다른 생성자를 호출할 때에는 반드시 해당 생성자의 첫 줄에서만 호출할 수 있음)
	 */
}

package edu.kh.oarr.model.vo;

public class Member {
	// 필드( == 멤버변수( 인스턴스변수, 클래스변수 )
	private String memberId;
	private String memberPw;
	private String memberName;
	private int memberAge;
	private String region; // 지역
	
	// 생성자
		// 생성자 규칙
		// 1. 생성자 이름은 클래스명과 같아야한다
		// 2. 반환형이 없다.
	
		public Member() {} // 기본생성자
		
	// 매개변수 생성자
	public Member(String memberId, String memberPw, String memberName, int memberAge, String region) {
		
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberAge = memberAge;
		this.region = region;
	}
	
	// 메소드
	// getter / setter
	// alt + shift + s + r => select all => generate
	
	
	/*다 접근가능하도록 public
	 * String 자료형 돌려보내겠다
	 * 매개변수가 없어서 중복된 이름의 memberId가 없기 때문에 memberId라고만 써도 ok
	 * */
	public String getMemberId() { // 외부클래스에서 접근 가능하게 public, string자료형을 리턴하겠다, 메소드명쓰고
		return memberId;          // 필드의 멤버 아이디라는 변수를 get 호출한곳으로 돌려보내겠다 그래서 값이 없음()안에
	}
	
	/*setter는 말 그대로 셋팅하는 애 무엇을 셋팅할지 전달받아올 애가 필요!
	  --> 매개변수
	  setter는 보통! 반환 값은 없음.
	 매개변수로 무언가를 받아온다 == 어디간에서 쓰여서 전달하는 값이 있기 때문에 public
	 매개변수로 받아온걸 딱히 쓰지는 않고 필드에 대입해 저장,셋팅해줄거라 반환값없어서 void
	 getter가 this없어도 되는 이유 딱히 겹치는애가 없고 비교할애가 없어서 */
	
	
	public void setMemberId(String memberId) { 
		this.memberId = memberId;				
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
		
		/* 모든 메서드는 종료 시 호출한 곳으로 돌아가는 
		return구문이 작성되어야 하지만 단, void일 경우 생략 가능하다
		 -> 생략 시 컴파일러가 자동 추가 // => return; */
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getMemberAge() {
		return memberAge;
	}

	public void setMemberAge(int memberAge) {
		this.memberAge = memberAge;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
 }
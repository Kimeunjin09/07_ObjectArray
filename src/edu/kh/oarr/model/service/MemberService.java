package edu.kh.oarr.model.service;

import java.util.Scanner;

import edu.kh.oarr.model.vo.Member;

public class MemberService {
	private Scanner sc = new Scanner(System.in);
	
	// Member 5칸짜리 객체 배열 선언 및 할당
	private Member[] memberArr = new Member[5];
	
	// 현재 로그인한 회원의 정보를 저장할 변수 선언
	private Member loginMember = null;
	
	public MemberService() { // 기본생성자
		//memberArr 배열 0,1,2 인덱스 초기화
		memberArr[0] = new Member("user01", "pass01", "홍길동", 30, "서울");
		memberArr[1] = new Member("user02", "pass02", "홍길순", 25, "경기");
		memberArr[2] = new Member("user03", "pass03", "고길순", 37, "강원");
	}
	
	// 메뉴 출력용 메서드
	
	public void displayMenu() {
		int menuNum = 0; // 메뉴를 입력받기 위한 지역변수 생성
			
		do { // 한 번은 무조건 반복
			System.out.println("\n====== 회원 정보 관리 프로그램 v2 ======");
			System.out.println("1. 회원 가입");
			System.out.println("2. 로그인");
			System.out.println("3. 회원 정보 조회");
			System.out.println("4. 회원 정보 수정");
			System.out.println("5. 회원 검색(지역)");
			System.out.println("0. 프로그램 종료");
			
			System.out.println("메뉴 입력 >> ");
			menuNum = sc.nextInt();
			sc.nextLine();
			
			switch(menuNum) {
			case 1: System.out.println( signup() ); break;
			case 2: System.out.println( login() );break;
			case 3: System.out.println( selectMember()); break;
			case 4: 
				
				int result = updateMember(); // 0, -1, 1
				
				if(result == -1) {
					System.out.println("로그인 후 이용해주세요.");
				} else if(result == 0) {
					System.out.println("회원 정보 수정 실패(비밀번호 불일치)");
				} else { // result == 1
					System.out.println("회원 정보가 수정되었습니다!");
				}
				break;
			case 5: searchRegion();
			break;
			case 0: System.out.println("\n프로그램을 종료합니다." ); break;
			default : System.out.println("\n잘못 입력 하셨습니다.");
			
			}
			
		}while(menuNum != 0); // 0번이 아닐때까지 반복
	}
	
	// 회원가입 메서드
	public String signup() {
		System.out.println("\n====== 회원 가입 ======");
		
		// 객체 배열(memberArr)에 가입한 회원 정보를 저장할 예정
		// -> 새로운 회원 정보를 저장할 공간이 있는지 확인하고
		//    빈 공간의 인덱스 번호를 얻어오기(빈공간이 있는지 확인하고 인덱스 번흐를 반환해줄 메소드 작성) --> 새로운 메서드 작성
		
		int index = emptyIndex(); // memberArr배열에서 비어있는 인덱스를 반환받음.
		
		System.out.println("현재 회원 수 : " + index);
		//아까 기본생성자에서 3명을 이미 만들어서 0,1,2는 이미 끝남 3,4만 비어있음
		// empty인덱스에서 3,4를 리턴할거임 그게 저기 memberArr[index]들어가고 비어있는데다가()안이 생성되겠다
		
		if(index == -1) { // 비어있는 인덱스가 없을 경우 -> 회원 가입 불가
			return "회원가입이 불가능 합니다.(인원 수 초과)";
		}
		
		System.out.print("아이디 : ");
		String memberId = sc.next();
		
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		
		System.out.print("비밀번호 확인 : ");
		String memberPw2 = sc.next();
		
		System.out.print("이름 : ");
		String memberName = sc.next();
		
		System.out.print("나이 : ");
		int memberAge = sc.nextInt();
		
		System.out.print("지역 : ");
		String region = sc.next();
		
		// 비밀번호, 비밀번호 확인 일치 시 회원가입
		if(memberPw.equals(memberPw2)) {
			// Member 객체를 생성해서 할당된 주소를 memberArr 비어있는 인덱스에 대입
			memberArr[index] = new Member( memberId, memberPw, memberName, memberAge, region );
			
			return "회원 가입 성공!!";
		} else {
			
			return "회원 가입 실패(비밀번호 불일치)";
		}
	}
	
	// memberArr의 비어있는 인덱스 번호를 반환하는 메서드
	// 단, 비어있는 인덱스가 없으면 -1 반환
	
	public int emptyIndex() {
		// memberArr의 배열을 0번 인덱스부터 배열의 끝까지 접근해서
		// 참조하는 값이 null인 경우에 인덱스를 반환
		for(int i = 0; i < memberArr.length; i++) {
			if(memberArr[i] == null) {
				return i;
			}
		}
		// for문을 수행했지만 return이 되지 않은 경우 해당 위치 코드가 수행!
		// for문에서 return되지 않았다 == 배열에 빈칸이 없다
		// ==> -1 반환

		return -1;
	}
	
	// 로그인 메서드
	public String login() {
		System.out.println("\n********로그인********");
		
		System.out.print("아이디 입력 : ");
		String memberId = sc.next();
		
		System.out.print("패스워드 입려 : ");
		String memberPw = sc.next();
		
		// 1) memberArr 배열 내 요소를 순서대로 접근하여 null이 아닌지 확인
		for(int i = 0; i < memberArr.length; i++) { // 회원정보가 있을 경우
			if(memberArr[i] != null) {
				// 2) (1번안의조건) 회원 정보(memberArr[i])의 아이디, 비밀번호와
				// 입력받은 아이디, 비밀번호가 같은지 확인하기
				// null이 아닌 index중에 사용자한테 받은 아이디 패스워드가 일치하는애가 있는지 찾아야함
				if(memberArr[i].getMemberId().equals(memberId) &&
				   memberArr[i].getMemberPw().equals(memberPw) ) { // String형이니까 equals비교
					
                    //	3) (2번안의 조건) 로그인 회원 정보 객체 (Member)를 참조하는 변수 loginMember에
					// 	현재 접근중인 memberArr[i] 요소에 저장된 주소를 얕은복사(대입)
					
					loginMember = memberArr[i];
					
					break; // 더 이상 같은 아이디, 비밀번호 없으므로 for문 종료
				}
			}
		} // for문 끝
		
		// 4) 로그인 성공/실패 여부에 따라 결과값을 반환
		
		if(loginMember == null ) { // 로그인 실패
			return "아이디 또는 비밀번호가 일치하지 않습니다.";
		} else { // 로그인 성공
			return loginMember.getMemberName() + "님 환영합니다.";
		}
	}
	
	// 회원 정보 메서드
	public String selectMember() {
		System.out.println("\n*********회원정보 조회********");
		
		// 1) 로그인 여부 확인 -> 필드 안에 loginMember가 참조하는 객체가 있는지 확인
		if(loginMember == null) {
			return "로그인 후 이용해주세요.";
		}
		// 2) 로그인이 되어있는 경우
		//    회원정보를 출력할 문자열을 만들어서 변환(return)
		//    (단, 비밀번호 제외) 
		String str = "이름 : " + loginMember.getMemberName();
		str += "\n아이디 : " + loginMember.getMemberId();
		str += "\n나이 : " + loginMember.getMemberAge() + "세";
		str += "\n지역 : " + loginMember.getRegion();
		
		// 이름 : 홍길동
		// 아이디 : user01
		// 나이 : 20세
		// 지역 : 서울
		
		return str; // 셀렉트멤버를 호출한곳(switch문의 케이스)으로 str을 보내줘야함
	}
	
	// 회원 정보 수정 메서드
	public int updateMember() { // -1,0,1로 보내서 위에서 프린트 구문으로 출력할려고 int반환형 사용
		System.out.println("\n********회원 정보 수정********");
		
		// 1) 로그인 여부 판별
		//    로그인이 되어있지 않으면 -1 반환
		if(loginMember == null) {
			return -1;
		}
		
		// 2) 수정할 회원 정보 입력 받기(이름,나이,지역)
		
		System.out.print("수정할 이름 입력: ");
		String inputName = sc.next();
		
		System.out.print("수정할 나이 입력: ");
		int inputAge = sc.nextInt();
		sc.nextLine(); // int 다음에 string이 오는경우에는 line넣어주는게좋음
					   // 입력 버퍼 정리용
		
		System.out.print("수정할 지역 입력: ");
		String inputRegion = sc.next();
		
		// 3) 비밀번호를 입력 받아서
		//    로그인한 회원의 비밀번호와 일치하는지 확인
		System.out.print("비밀번호 입력: ");
		String inputPw = sc.next();
		
		if( inputPw.equals(loginMember.getMemberPw())) {
			// 4) 비밀번호가 일치하는 경우
			//    로그인한 회원의 이름, 나이, 지역 정보를 입력받은 값으로 변경 후 1 반환
			
			loginMember.setMemberName(inputName);
			loginMember.setMemberAge(inputAge);
			loginMember.setRegion(inputRegion); // 캡슐화 원칙때문에 필드에 직접접근 불가해서 set이용
			// 이게 옆에 셋메서드 만든데로 이동해서 this로 들어가고 그다음 필드로 이동]
			
			return 1;
		} else { // 5) 비밀번호가 다를 경우 0 반환
			
			return 0;
		}
	}
	
	// 회원 검색(지역) 메서드
	public void searchRegion() {
		System.out.println("\n******** 회원 검색(지역) ********");
		
		System.out.print("검색할 지역을 입력하세요 : ");
		String inputRegion = sc.next();
		
		boolean flag = false; // 검색결과 신호용 변수
		
		// 1) memberArr 배열의 모든 요소 순차 접근
		for(int i = 0; i < memberArr.length; i++) {
			//2) memberArr[i] 요소가 null인경우 반복 종료
			if(memberArr[i] == null) break;
				 // break; 이렇게 간단한 실행구문? 은 {} 굳이 안쓰고 이렇게 써도 됨.
			
			//3) memberArr[i] 요소에 저장된 지역(getRegion())이
			//   입력받은 지역과(inputRegion) 같을 경우, 회원 아이디, 이름 출력
			if(memberArr[i].getRegion().equals(inputRegion)) {
				System.out.printf("아이디 : %s, 이름 : &s\n",
						memberArr[i].getMemberId(),
						memberArr[i].getMemberName()  );
				
				flag = true; // flag가 두번째 if문을 걸치면서 true로 바뀌면 밑에 for문밖에 가져와서 쓸 수 있음
			}
		}
		// 4) 검색 결과가 없을 경우 "일치하는 검색 결과 없음" 출력
		if(!flag) { // true상태로 왔는데 검색결과가 없는 경우니까 !로 꺾어서
					// if 조건문에 맞게 실행
			
			System.out.println("");
		}
	}
}

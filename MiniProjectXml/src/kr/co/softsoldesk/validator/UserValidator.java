package kr.co.softsoldesk.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.softsoldesk.beans.UserBean;

//커스텀 유효성 설정 
public class UserValidator implements Validator{
	
	//유효성 검사를 할 수 있는 빈인지 체크 
	@Override
	public boolean supports(Class<?> clazz) {
		return UserBean.class.isAssignableFrom(clazz);
	} //이후 아래의 타겟으로 넘겨줌 

	@Override
	public void validate(Object target, Errors errors) { //errors: BindingResult랑 같음 
		//형변환 
		UserBean userBean= (UserBean)target; // 유효성 체크가 가능한 빈 
		
		//들어오는 객체 확인해보기 
		String beanName=errors.getObjectName(); //에러나는 객체의 이름 불러오기 
		//System.out.println(beanName);
		
		
		// 회원가입할때 객체랑 로그인할때 객체랑 둘다(모든) 객체가 들어와서 읽어버려서 로그인객체에서 걸려서 500 에러남 (왜냐면 모든 유저빈 객체를 읽어버려서)
		// 해결방법 : 1) 유저빈을 2개 만듬 (회원가입용, 로그인용. 유지보수 어려움..비추) 2) 조건설정 (조인유저빈만 아래 메소드를 실행하도록 함. )
		//조인유저빈만 아래 메소드를 실행하도록 함.  
		if(beanName.equals("joinUserBean")) {
			//비밀번호가 일치하지 않을때 띄울 메세지의 어노테이션 등록 -> 이후 프로퍼티에서 사용 
			if(userBean.getUser_pw().equals(userBean.getUser_pw2())== false) {
				errors.rejectValue("user_pw", "NotEquals"); //프로퍼티에 등록하기 위해서 커스텀 어노테이션 NotEquals를 만듬 
				errors.rejectValue("user_pw2", "NotEquals"); //프로퍼티에 등록하기 위해서 커스텀 어노테이션 NotEquals를 만듬 
			}
			if(userBean.isUserIdExist()==false) {
				errors.rejectValue("user_id", "DonCheckUserIdExist");
			}
		}
	}

}

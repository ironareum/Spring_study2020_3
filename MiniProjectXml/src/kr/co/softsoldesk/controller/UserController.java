package kr.co.softsoldesk.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.service.UserService;
import kr.co.softsoldesk.validator.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Resource(name="loginUserBean")
	private UserBean loginUserBean;
	
	@GetMapping("/login")
	public String login(@ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean, 
						@RequestParam(value="fail", defaultValue="false" ) boolean fail, Model model) {
						// RequestParam: 주소로 넘어가는 값 (기본값은 false로 설정) 
		
		//fail에 true가 들어오면 실패인정 (=실패, 즉 ) 
		//fail에 false가 들어오면 실패부정 (=성공, 즉 로그인창 ) 
		model.addAttribute("fail", fail);
		System.out.println("model: "+ model.getAttribute("fail"));
		return "user/login";
	}
	
	@PostMapping("/login_pro")
	public String login_pro(@Valid @ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean, BindingResult result) {
		//위에보면 validate 검사하는 객체의 이름을 모델어트리뷰트로 지정해주고 그 이름에 맞게 각각 프로퍼티 지정해서 검사함. 
		
		//유효성 검사해서 결과값 담겨있는 곳 
		if(result.hasErrors()) {
			//유효성 검사시 에러가 있으면 가는곳 
			return "user/login";
		} //(Validator에 조건으로 null 검사 안걸리게 셋팅해주면 에러안남) 
		
		//유효성 검사시 에러가 없을때 아래 진행...! ===============
		
		//오라클에서 아이디 입력정보랑 일치하는 정보 확인. 
		//(이후 세션에 있는 UserBean 객체에 정보담고, 로그인상태 true로 변경완료. 만약 일치하는 값 없을시 계속 false 상태 유지) 
		 userService.getLoginUserInfo(tempLoginUserBean);
		 if(loginUserBean.isUserLogin()==true) {
			 //isUserLogin의 값 true일때 
			 return "user/login_success";
		 } 
		//isUserLogin의 값 false 일때 
		return "user/login_fail"; //에러체크? 
		
	}
	
	@GetMapping("not_login")
	public String not_login() {
		return "user/not_login";
	}
	
	
	@GetMapping("/join")
	public String join(@ModelAttribute("joinUserBean") UserBean joinUserBean) {
		return "user/join";
	}

	@PostMapping("/join_pro")
	public String join_pro(@Valid @ModelAttribute("joinUserBean") UserBean joinUserBean, BindingResult result) {
		
		if(result.hasErrors()) { //유효성에 위배되었을때 
			return "user/join";
		}
		userService.addUserInfo(joinUserBean); //회원정보 저장하는 메소드 호출 (가입) 
		return "user/join_success";
	}

	@GetMapping("/modify")
	public String modify(@ModelAttribute("modifyUserBean") UserBean modifyUserBean) { //form에 주입할 객체 매개변수로 넣어줌  
		//form 태그에 있는 모델어트리뷰트 modifyUserBean에게 자동으로 가져온 정보가 셋 되어있음..
		userService.getModifyUserInfo(modifyUserBean); //아이디, 네임값 가지고 있는상태 
		
		return "user/modify";
	}
	
	@PostMapping("/modify_pro")
	public String modify_pro(@Valid @ModelAttribute("modifyUserBean") UserBean modifyUserBean, BindingResult result) {
		
		if(result.hasErrors()) {
			//유효성 검사시 에러가 있으면 가는곳 
			return "user/modify";
		}
		userService.modifyUserInfo(modifyUserBean); //비밀번호 업데이트 완료 
		return "user/modify_success"; //업데이트 성공 메세지 뿌리기 
	}
	
	@GetMapping("/logout")
	public String logout() {
		//로그인 상태 false로 변경 후 페이지 리턴 
		loginUserBean.setUserLogin(false);
		return "user/logout";
	}
	
	
	
	//커스텀 @Valid 어노테이션 (Validator 등록) 
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator validator1 = new UserValidator();
		binder.addValidators(validator1);
	}
	
	
	
}

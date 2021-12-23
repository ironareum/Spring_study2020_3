package kr.co.softsoldesk.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.softsoldesk.beans.UserBean;

@Controller
public class HomeController {
	
	@Resource(name="loginUserBean")
	@Lazy
	private UserBean loginUserBean;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		//xml 은 (스코프가 세션일때? ) 서버가 올라오면서 생성되고, 생성자가 자동으로 생성되서 에러남  (->방지하려면 @Lazy 주면됨)  
		//System.out.println(loginUserBean);
		
		return "redirect:/main";
		//redirect : 또다른 컨트롤러(여기서는 main)로 넘겨줌 
	}
}


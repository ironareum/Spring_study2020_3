package kr.co.softsoldesk.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.softsoldesk.beans.UserBean;

public class CheckLoginInterceptor implements HandlerInterceptor{
	
	
	//로그인여부를 판단해야 하므로 loginUserBean 객체주입 
	@Resource(name="loginUserBean")
	@Lazy
	private UserBean loginUserBean;
	
	//인터페이스에서는 autorwired가 안되므로 생성자를 활용한다. 
	public CheckLoginInterceptor(UserBean loginUserBean) {
		//로그인 상태 주입 
		this.loginUserBean = loginUserBean;
	}
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//로그인이 되어있지 않으면 
		if(loginUserBean.isUserLogin()==false) {
			//로그인이 되지않은 상태이므로 로그인전(=현재)의 경로를 잡아옴
			String contextPath =request.getContextPath(); //getContextPath(): 경로를 잡아옴 
			//로그인처리가 안되어 있으므로 not_login으로 페이지 전환 (로그인 했을때의 내용들 볼수없게) 
			response.sendRedirect(contextPath+"/user/not_login"); //페이지전환. 다음단계로 이동하지 못하게 잠금
			return false; //다음단계로 이동하지 않음 (로그인 전 상태 =흐름막기) 
		}

		return true; //로그인 후로 흘려보냄 
	}
	
	
}

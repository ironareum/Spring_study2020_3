package kr.co.softsoldesk.interceptor;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.softsoldesk.beans.BoardInfoBean;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.service.TopMenuService;

public class TopMenuInterceptor implements HandlerInterceptor{
	@Autowired
	private TopMenuService topMenuService; //java에서는 안되서 생성자를 만들어줘야 하는데, xml에서는 오토와이어드 할 수 있음. 
	
	@Resource(name="loginUserBean")
	@Lazy
	private UserBean loginUserBean; //로그인 상태 체크용 
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		List<BoardInfoBean> topMenuList = topMenuService.getTopMenueList();
		//요청을 받으면 아래에 있는 얘를 넘기겠다. 
		request.setAttribute("topMenuList", topMenuList);
		request.setAttribute("loginUserBean", loginUserBean);
		
		return true;
	}
}

package kr.co.softsoldesk.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.dao.UserDao;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	//루트에 올려논 세션 스코프타입의 UserBean 호출 (로그인 정보 담을 객체) 
	@Resource(name="loginUserBean")
	private UserBean loginUserBean;
	
	public boolean checkUserIdExist(String user_id) {
		
		String user_name =userDao.checkUserIdExist(user_id);
		
		if(user_name ==null) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public void addUserInfo(UserBean joinUserBean) {
		userDao.addUserInfo(joinUserBean);
	}
	
	public void getLoginUserInfo(UserBean tempLoginUserBean) {
			
			UserBean tempLoginUserBean2 = userDao.getLoginUserInfo(tempLoginUserBean);
			//가져온 데이터가 있다면
			if(tempLoginUserBean2 != null) {
				loginUserBean.setUser_idx(tempLoginUserBean2.getUser_idx());
				loginUserBean.setUser_name(tempLoginUserBean2.getUser_name());
				loginUserBean.setUserLogin(true);
			}
		}
	
	
	public void getModifyUserInfo(UserBean modifyUserBean) {
		//idx값으로 가져온 sql정보를 tempModifyUserBean 객체에 담음 
		UserBean tempModifyUserBean=userDao.getModifyUserInfo(loginUserBean.getUser_idx()); //세션에 떠있는 객체(=loginUserBean)의 인덱스 번호를 가져옴 
//		System.out.println(loginUserBean.getUser_idx());
		//tempModifyUserBean에 있는 정보중 정보수정 페이지에서 보여줄 필요한 정보만 modifyUserBean에게 셋해줌 
		modifyUserBean.setUser_id(tempModifyUserBean.getUser_id());
		modifyUserBean.setUser_name(tempModifyUserBean.getUser_name());
		modifyUserBean.setUser_idx(tempModifyUserBean.getUser_idx());
	}
	
	//비밀번호 업데이트 
	public void modifyUserInfo(UserBean modifyUserBean) {
		//세션에 있는 현재의 인덱스 값 매개변수 객체에 주입 
		modifyUserBean.setUser_idx(loginUserBean.getUser_idx()); 
		userDao.modifyUserInfo(modifyUserBean);
	}
	
}

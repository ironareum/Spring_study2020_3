package kr.co.softsoldesk.beans;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserBean {
	
	private int user_idx;
	
	//유효성 검사가 가능한 빈이 됨 
	@Size(min=2, max=10)
	@Pattern(regexp = "[가-힣]*") //한글만 오는 패턴 
	private String user_name;
	
	@Size(min=4, max=20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String user_id;
	
	@Size(min=4, max=20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String user_pw;
	
	@Size(min=4, max=20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String user_pw2;
	
	//사용가능한 아이디 확인여부 
	private boolean userIdExist; 
	
	//로그인 확인여부 
	private boolean userLogin;
	
	
	public UserBean() {
		this.userIdExist =false; //유효값 검사전에는 false로 시작. 
		this.userLogin = false; //로그인 전 false로 시작. 
	}
	
	
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_pw2() {
		return user_pw2;
	}
	public void setUser_pw2(String user_pw2) {
		this.user_pw2 = user_pw2;
	}
	public boolean isUserIdExist() {
		return userIdExist;
	}
	public void setUserIdExist(boolean userIdExist) {
		this.userIdExist = userIdExist;
	}


	public boolean isUserLogin() {
		return userLogin;
	}


	public void setUserLogin(boolean userLogin) {
		this.userLogin = userLogin;
	}
	
	
	
	
}

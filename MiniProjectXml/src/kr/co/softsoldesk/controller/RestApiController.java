package kr.co.softsoldesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.co.softsoldesk.service.UserService;

@RestController
public class RestApiController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/checkUserIdExist/{user_id}") //데이터 넘기는법 {} 사용 
	public String checkUserIdExist(@PathVariable String user_id) {
		boolean chk =userService.checkUserIdExist(user_id);
		return chk+""; //데이터가 넘어감 (값 그대로 넘어감. 주소x) 
	}
	
}

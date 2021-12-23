package kr.co.softsoldesk.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.softsoldesk.beans.BoardInfoBean;

//sql 메소드 컨트롤러 
//오라클이랑 연동
//매퍼에서 받음 -> dao에서 일반 메소드처럼 사용 -> 뷰단으로 넘어감 (서비스로 내려옴) 
@Repository
public class TopMenuDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<BoardInfoBean> getTopMenuList(){
		List<BoardInfoBean> topMenuList =sqlSessionTemplate.selectList("topmenu.get_topmenu_list");
		return topMenuList; 
	}
	
}

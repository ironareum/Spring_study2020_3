package kr.co.softsoldesk.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.softsoldesk.beans.ContentBean;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.dao.BoardDao;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	//리소스 Lazy처리 안하면 500에러뜸.. 자동생성 막음.. 
	@Resource(name="loginUserBean")
	@Lazy
	private UserBean loginUserBean;
	
	//프로퍼티에 설정해놓은 경로 잡아오기 (=파일 저장되는 업로드 경로) 
	@Value("${path.upload}")
	private String path_upload;
	
	private String saveUploadFile(MultipartFile upload_file) {
		//파일명 중복제거 (중복되는 파일명이 있을수 있으므로, 현재시간과 오리지널 파일네임)
		String file_name= System.currentTimeMillis() +"_"+upload_file.getName()+"."+upload_file.getContentType(); //getOriginalFilename()으로 하면 오리지날 경로가 찍히나봄? 
		
		//경로 오류솔루션 (혹시 경로 못잡아올시..) 
//		String file_name = System.currentTimeMillis() + "_" + FilenameUtils.getBaseName(upload_file.getOriginalFilename()) + "." + 
//						FilenameUtils.getExtension(upload_file.getOriginalFilename());
		
		try {
			//파일 경로이동 
			upload_file.transferTo(new File(path_upload + "/"+file_name)); //sample/test.png
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return file_name;
	}
	
	public void addContenInfo(ContentBean writeContentBean) {
		//데이터 들어오는지 확인 해봄 
//		System.out.println(writeContentBean.getContent_subject());
//		System.out.println(writeContentBean.getContent_text());
//		System.out.println(writeContentBean.getUpload_file().getSize());
		
		//동일한 명으로 설정해야함 
		//업로드 데이터 가져옴 
		MultipartFile upload_file=writeContentBean.getUpload_file();
		
		if(upload_file.getSize() >0) { //파일이 들어왔다면, 
			String file_name=saveUploadFile(upload_file);
			System.out.println(file_name);
			
			//첨부파일 호출
			writeContentBean.setContent_file(file_name); //파일이름 저장 
		}
		//로그인정보 일치여부 확인하는 용. 로그인 했을시 유저확인 후 수정, 삭제까지 가능 
		writeContentBean.setContent_write_idx(loginUserBean.getUser_idx());
		//
		boardDao.addContentInfo(writeContentBean);

	}
	
	
	public void addContentInfo(ContentBean writeContentBean) {
		boardDao.addContentInfo(writeContentBean);
	}
	
	public String getBoardInfoName(int board_info_idx) {
		return boardDao.getBoardInfoName(board_info_idx);
	}
	
	public List<ContentBean> getContentList(int board_info_idx){
		return boardDao.getContentList(board_info_idx);
	}
	
	
}

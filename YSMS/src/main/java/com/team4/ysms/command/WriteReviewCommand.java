package com.team4.ysms.command;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.team4.ysms.dao.Dao_myinfo_Review;

public class WriteReviewCommand implements SCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		// TODO Auto-generated method stub
		Dao_myinfo_Review dao = sqlSession.getMapper(Dao_myinfo_Review.class);

		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest mtfRequest = (MultipartHttpServletRequest) map.get("mtfRequest");
	
		String root_path = httpSession.getServletContext().getRealPath("/"); // 웹서비스 root 경로 
		String attach_path = "resources/reviewPhoto/";
		
		String uploadPath = root_path + attach_path;
		System.out.println(uploadPath);
		
		// 폴더가 없을시 폴더 생성
        if ( ! new File(uploadPath).exists()) {
            new File(uploadPath).mkdirs();
        }
        
        String dbInsertImgName = null;
        
        // upload File
        MultipartFile mf = mtfRequest.getFile("reviewImg");
    	String originFileName = mf.getOriginalFilename(); // 원본 파일 명
        long fileSize = mf.getSize(); // 파일 사이즈
        
        // file upload check
        if(fileSize != 0) {
	        String saveFilename = System.currentTimeMillis() + originFileName; // 저장될 파일명
	        String saveFile = uploadPath + saveFilename;
	        
	        dbInsertImgName = "reviewPhoto/" + saveFilename;
	        
	        try {
	        	mf.transferTo(new File(saveFile));
	        }catch (IllegalStateException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	           e.printStackTrace();
	        }
        }
        
        dao.myInfoReviewWriteDao(mtfRequest.getParameter("reviewContent"), 
        		mtfRequest.getParameter("reviewScore"),
        		dbInsertImgName, 
        		mtfRequest.getParameter("rentalNo"));
		
	}
	
}

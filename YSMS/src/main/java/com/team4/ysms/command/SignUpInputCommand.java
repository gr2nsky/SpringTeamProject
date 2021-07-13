package com.team4.ysms.command;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.team4.ysms.dao.Dao_Login;
import com.team4.ysms.dto.Dto_Login;

public class SignUpInputCommand implements SCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		
		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest mtfRequest = (MultipartHttpServletRequest) map.get("mtfRequest");
		
		String id = null;
		String name = null;
		String pw = null;
		String email = null;
		String phone = null;
		int status = 0;
		String birthday = null;
		String filePath = getFilePath(httpSession);
		
		
		try {
			
			id = mtfRequest.getParameter("id");
			name = mtfRequest.getParameter("name");
			pw = mtfRequest.getParameter("pw1");
			email = mtfRequest.getParameter("email");
			phone = mtfRequest.getParameter("phone1") + "-" + mtfRequest.getParameter("phone2") + "-" + mtfRequest.getParameter("phone3");
			status = 1;
			birthday = mtfRequest.getParameter("year") + "-" + mtfRequest.getParameter("month") + "-" + mtfRequest.getParameter("day");
			
			MultipartFile uploadPhoto = mtfRequest.getFile("uploadPhoto");
	    	String originFileName = uploadPhoto.getOriginalFilename(); // 원본 파일 명
	        long fileSize = uploadPhoto.getSize(); // 파일 사이즈
	        
	        String saveFilename = null;
	        // file upload check
	        if(fileSize != 0) {
		        saveFilename = System.currentTimeMillis() + originFileName; // 저장될 파일명
		        String saveFile = filePath + saveFilename;
		        
		        try {
		        	uploadPhoto.transferTo(new File(saveFile));
		        }catch (IllegalStateException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        } catch (IOException e) {
		            // TODO Auto-generated catch block
		           e.printStackTrace();
		        }
	        }
	        
			filePath = filePath + "/" + uploadPhoto;

			Dao_Login dao = sqlSession.getMapper(Dao_Login.class);
			int queryResult = dao.signUp(id, name, pw, email, phone, status, birthday, saveFilename);
			
			String result = "false";
			if (queryResult == 1) {
				result = "true";
			}
			model.addAttribute("sginUpResult", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getFilePath(HttpSession session) {
		String root_path = session.getServletContext().getRealPath("/"); // 웹서비스 root 경로 
		String attach_path = "resources/user/";
		
		String uploadPath = root_path + attach_path;
		System.out.println(uploadPath);
		
		// 폴더가 없을시 폴더 생성
        if ( ! new File(uploadPath).exists()) {
            new File(uploadPath).mkdirs();
        }
        
        return uploadPath;
	}

}

package com.team4.ysms.command;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.team4.ysms.common.LoginedUserInfo;
import com.team4.ysms.dao.Dao_MyInfo_UpdateProfile;

public class MyInfoUpdateCommand implements SCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {

		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest mtfRequest = (MultipartHttpServletRequest) map.get("mtfRequest");
		int result = 0;
		
		String pw = null;
		String phone = null;
		String filePath = getFilePath(httpSession);
		
		try {

			pw = mtfRequest.getParameter("pw1");
			phone = mtfRequest.getParameter("phone1") + "-" + mtfRequest.getParameter("phone2") + "-" + mtfRequest.getParameter("phone3");
			MultipartFile uploadFile = mtfRequest.getFile("uploadPhoto");
			String uploadPhoto = uploadFile.getOriginalFilename(); // 원본 파일 명
	        long fileSize = uploadFile.getSize(); // 파일 사이즈
	        String saveFilename = null;
	        // file upload check
	        if(fileSize != 0) {
		        saveFilename = System.currentTimeMillis() + uploadPhoto; // 저장될 파일명
		        String saveFile = filePath + saveFilename;
		        try {
		        	uploadFile.transferTo(new File(saveFile));
		        }catch (IllegalStateException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        } catch (IOException e) {
		            // TODO Auto-generated catch block
		           e.printStackTrace();
		        }
	        }
	        
			if(uploadPhoto == null || uploadPhoto.equals("")) {
				filePath = null;
			} else {
				filePath = filePath + "/" + saveFilename;
			}
			
			if(pw == null || pw.equals("")) {
				Dao_MyInfo_UpdateProfile dao = sqlSession.getMapper(Dao_MyInfo_UpdateProfile.class);
				result = dao.updateUserInfo1(LoginedUserInfo.id, phone, filePath);
			}else {
				Dao_MyInfo_UpdateProfile dao = sqlSession.getMapper(Dao_MyInfo_UpdateProfile.class);
				result = dao.updateUserInfo2(LoginedUserInfo.id, pw, phone, filePath);
			}
			
			
			model.addAttribute("updateTty", 1);
			if (result == 1) {
				model.addAttribute("updateResult", "true");
			} else {
				model.addAttribute("updateResult", "false");
			}
			
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

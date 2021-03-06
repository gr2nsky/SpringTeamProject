package com.team4.ysms.command;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.team4.ysms.dao.Dao_Share;
import com.team4.ysms.dto.Dto_Share;

public class WriteCommand implements SCommand {
	
	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		System.out.println("* * WriteCommand start * *");
		
		Dao_Share dao = sqlSession.getMapper(Dao_Share.class);
		
		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest mtfRequest = (MultipartHttpServletRequest) map.get("mtfrequest");
		
		System.out.println(mtfRequest);
		
		String root_path = httpSession.getServletContext().getRealPath("/"); // 웹서비스 root 경로 
		System.out.println("  - root_path : " + root_path);
        
		String attach_path = "resources/share/";
		
		String uploadPath = root_path + attach_path;
		
		// 폴더가 없을시 폴더 생성
        if ( ! new File(uploadPath).exists()) {
            new File(uploadPath).mkdirs();
        }
        
        String saveFilename = null;
        
        
        // upload File
        MultipartFile mf = mtfRequest.getFile("uploadFile");
    	String originFileName = mf.getOriginalFilename(); // 원본 파일 명
        long fileSize = mf.getSize(); // 파일 사이즈
        
        // file upload check
        if(fileSize != 0) {
	        saveFilename = System.currentTimeMillis() + originFileName; // 저장될 파일명
	        String saveFile = uploadPath + saveFilename;
	        
	        try {
	        	mf.transferTo(new File(saveFile));
	        }catch (IllegalStateException e) {
	        	e.printStackTrace();
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
        }
        
        String dayLimit = dateLimitForm(mtfRequest.getParameter("dayLimit"));
		int place_no = dao.find_PlaceNoDao(mtfRequest.getParameter("postCode"), 
											  Integer.parseInt(mtfRequest.getParameter("capacity")), 
											  Integer.parseInt(mtfRequest.getParameter("category")));

        System.out.println("place_no : "+ place_no);
		
              dao.writeDetailDao(mtfRequest.getParameter("title"), 
        		           "user03", 
        		           mtfRequest.getParameter("introduce"), 
        		           mtfRequest.getParameter("price"), 
        		           saveFilename,
        		           place_no, 
        		           mtfRequest.getParameter("startTime"), 
        		           mtfRequest.getParameter("endTime"), 
        		           dayLimit);
        
	}
	
	public String dateLimitForm(String dayLimit) {
		int strLen = dayLimit.length();
		String lessStr = "";
		
		if (strLen < 7) {
			int less = 7 - strLen;
			for(int i = 0; i < less; i++) {
				lessStr += "0";
			}
		}
		
		return lessStr + dayLimit;
	}

}

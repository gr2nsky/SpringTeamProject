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

import com.team4.ysms.common.FilePath;
import com.team4.ysms.dao.Dao_Share;

public class ModifyShareCommand implements SCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		
		Dao_Share dao = sqlSession.getMapper(Dao_Share.class);
		
		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest mtfRequest = (MultipartHttpServletRequest) map.get("mtfrequest");
		
		String root_path = httpSession.getServletContext().getRealPath("/"); 
		String attach_path = "resources/share/";
		String uploadPath = root_path + attach_path;
		
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
        }else {
        	saveFilename = mtfRequest.getParameter("oldFilePath");
        }
        
        dao.modifyShareDao(Integer.parseInt(mtfRequest.getParameter("no")), 
        				   mtfRequest.getParameter("title"), 
        				   mtfRequest.getParameter("introduce"), 
        				   saveFilename, 
        				   mtfRequest.getParameter("price"), 
        				   mtfRequest.getParameter("startTime"), 
        				   mtfRequest.getParameter("endTime"), 
        				   mtfRequest.getParameter("dayLimit"));
        
	}

}

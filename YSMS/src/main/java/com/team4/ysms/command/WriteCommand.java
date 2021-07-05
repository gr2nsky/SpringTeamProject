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
//        System.out.println("Multi : " + mtfRequest.getFile("uploadFile"));
        
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

	
	//mtfRequest.getParameter("user_id")
	
	
	
	
//	@Override
//	public void execute(HttpServletRequest request, HttpServletResponse response) {
//
//		MultipartRequest multi = null;
//		String filePath = null;
//		String user_id = null;
//		String title = null;
//		String introduce = null;
//		String price = null;
//		String startTime = null;
//		String endTime = null;
//		String dayLimit = null;
//		
//		
//		// 3mb로 파일 크기를 제한
//		int fileSize = 1024 * 1024 * 3;
//		//현재 서비스되는 서버가 사용하는 저장공간의 경로를 불러옵니다.
//		ServletContext context = request.getServletContext();
//		String uploadPath = context.getRealPath("/save");
//		
//		//해당 디렉토리가 없을경우 디렉토리를 생성합니다.
//		// File.io
//		File folder = new File(uploadPath);
//		if (!folder.exists()) {
//			try {
//				folder.mkdir(); // 폴더 생성합니다.
//				System.out.println("폴더가 생성되었습니다.");
//			} catch (Exception e) {
//				e.getStackTrace();
//			}
//		} else {
//			System.out.println("이미 폴더가 생성되어 있습니다.");
//		}
//			
//			System.out.println(user_id);
//		try {
//			//일반 request와는 구분됩니다. request.getParameter로는 값을 가져올 수 없습니다.
//			multi = new MultipartRequest(request, uploadPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
//			user_id = multi.getParameter("user_id");
//			title = multi.getParameter("title");
//			introduce = multi.getParameter("introduce");
//			price = multi.getParameter("price");
//			startTime = multi.getParameter("startTime");
//			endTime = multi.getParameter("endTime");
//			dayLimit = multi.getParameter("dayLimit");
//			dayLimit = dateLimitForm(dayLimit);
//			
//			String postCode = multi.getParameter("postCode");
//			int capacity = Integer.parseInt(multi.getParameter("capacity"));
//			int category = Integer.parseInt(multi.getParameter("category"));
//			
//			Dao_Share dao = new Dao_Share();
//			int place_no = dao.Find_placeNo(postCode, capacity, category);
//
//			
//			String uploadFile = multi.getFilesystemName("uploadFile");
//			Dao_Share dao02 = new Dao_Share();
//			filePath = "save/" + uploadFile;
//			dao02.write(title, user_id, introduce, price, filePath, place_no, startTime, endTime, dayLimit);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
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

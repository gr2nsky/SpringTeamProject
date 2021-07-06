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


	
//
//	FilePath file = new FilePath();
//	@Override
//	public void execute(HttpServletRequest request, HttpServletResponse response) {
//		MultipartRequest multi = null;
//		int no = 0;
//		String title = null;
//		String introduce = null;
//		String filePath = null;
//		String price = null;
//		String startTime = null;
//		String endTime = null;
//		String dayLimit = null;
//
//		// 3mb로 파일 크기를 제한
//		int fileSize = 1024 * 1024 * 3;
//		ServletContext context = request.getServletContext();
//		String uploadPath = context.getRealPath("/save");
//		System.out.println(uploadPath);
//
//		File folder = new File(uploadPath);
//		// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
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
//		try {
//			//일반 request와는 구분됩니다. request.getParameter로는 값을 가져올 수 없습니다.
//			multi = new MultipartRequest(request, uploadPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
//			
//			System.out.println(no);
//			
//			no = Integer.parseInt(multi.getParameter("no"));
//
//			System.out.println(no);
//			Dao_Share dao = new Dao_Share();
//			
//			String uploadFile = multi.getFilesystemName("uploadFile");
//			
//			//업로드한 파일이 없을시 save/null로 db에 기록되므로, 이를 방지하기 위해 분기
//			if(uploadFile == null) {
//				filePath = multi.getParameter("oldFilePath");
//				title = multi.getParameter("title");
//				introduce = multi.getParameter("introduce");
//				price = multi.getParameter("price");
//				startTime = multi.getParameter("startTime");
//				endTime = multi.getParameter("endTime");
//				dayLimit = multi.getParameter("dayLimit");
//				dao.update(no, title, introduce, filePath, price, startTime, endTime, dayLimit);
//				System.out.println("update success : uploadFile was null");
//			} else {
//				filePath = "save/" + uploadFile;
//				
//				title = multi.getParameter("title");
//				introduce = multi.getParameter("introduce");
//				price = multi.getParameter("price");
//				startTime = multi.getParameter("startTime");
//				endTime = multi.getParameter("endTime");
//				dayLimit = multi.getParameter("dayLimit");
//				
//				dao.update(no, title, introduce, filePath, price, startTime, endTime, dayLimit);
//				System.out.println("update success : uploadFile was not null");
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("update fail");
//		}
//	}
}

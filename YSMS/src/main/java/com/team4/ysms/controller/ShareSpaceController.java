package com.team4.ysms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.team4.ysms.command.SCommand;
import com.team4.ysms.command.WriteCommand;
import com.team4.ysms.dao.Dao_Share;

@Controller
public class ShareSpaceController {
	
	@Autowired
	private SqlSession sqlSession;
	
	SCommand command;
	
	
	
	
	////////////////////////////////////
	//  210705 12:11   Park Jaewon    //
	//  공간 작성페이지로 이동              //
	////////////////////////////////////
	@RequestMapping("/write_space.four")
	public String writeForm() {
		System.out.println("* * * Controller : writeSpace * * *");
		return "writeSpace";
	}
	
	////////////////////////////////////
	//  210705 12:53   Park Jaewon    //
	//  공간 작성페이지로 이동              //
	////////////////////////////////////
	@RequestMapping("/write_detail.four")
	public String writeSpace(HttpServletRequest request, Model model) {
		System.out.println("* * * Controller : writeSpace * * *");
		
		Dao_Share dao = sqlSession.getMapper(Dao_Share.class);
		
		dao.writeSpaceDao(request.getParameter("postCode"), 
						  request.getParameter("address1"), 
						  request.getParameter("address2"), 
						  request.getParameter("capacity"), 
						  request.getParameter("category"));
		
		// DATA to write.jsp
		model.addAttribute("POSTCODE", request.getParameter("postCode"));
		model.addAttribute("ADDRESS1", request.getParameter("address1"));
		model.addAttribute("ADDRESS2", request.getParameter("address2"));
		model.addAttribute("CAPACITY", request.getParameter("capacity"));
		model.addAttribute("CATEGORY", request.getParameter("category"));
		
		return "write";
	}
	
	
	@RequestMapping("/write.four")
	public String writeDetail(MultipartHttpServletRequest mtfrequest, Model model) {
		System.out.println("* * * Controller : writeDetail * * *");
		
		HttpSession httpSession = mtfrequest.getSession();
		model.addAttribute("mtfrequest", mtfrequest);
		
		command = new WriteCommand();
		command.execute(sqlSession, model, httpSession);
		
		return "redirect:write_space.four";
	}
	
	
	
	
}// End of ShareSpaceController

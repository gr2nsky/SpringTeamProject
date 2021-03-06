package com.team4.ysms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.team4.ysms.command.ShareListCommand;
import com.team4.ysms.command.ContentCommand;
import com.team4.ysms.command.ModifyShareCommand;
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
	@RequestMapping("/write_space")
	public String writeForm() {
		System.out.println("* * * Controller : writeSpace * * *");
		return "writeSpace";
	}
	
	////////////////////////////////////
	//  210705 12:53   Park Jaewon    //
	//  공간 작성페이지로 이동              //
	////////////////////////////////////
	@RequestMapping("/write_detail")
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
	
	////////////////////////////////////
	//  210705 18:25   Park Jaewon    //
	//  MultupartRequest : 공간등록     //
	////////////////////////////////////
	@RequestMapping("/write")
	public String writeDetail(MultipartHttpServletRequest mtfrequest, Model model) {
		System.out.println("* * * Controller : writeDetail * * *");
		
		HttpSession httpSession = mtfrequest.getSession();
		model.addAttribute("mtfrequest", mtfrequest);
		
		command = new WriteCommand();
		command.execute(sqlSession, model, httpSession);
		
		return "redirect:list";
	}
	
	////////////////////////////////////
	//  210705 22:00   Park Jaewon    //
	//  내가 등록한 공간List 불러오기       //
	////////////////////////////////////
	@RequestMapping("/list")
	public String shareList(HttpServletRequest request, Model model) {
		System.out.println("* * * Controller : shareList * * *");
		
		HttpSession httpSession = request.getSession();
		model.addAttribute("request", request);
		
		command = new ShareListCommand();
		command.execute(sqlSession, model, httpSession);
		
		return "sharelist";
	}
	
	//////////////////////////////////
	//  210706 14:27  Park Jaewon   //
    //  수정삭제를 위한 content이동      //
	//////////////////////////////////
	@RequestMapping("/content")
	public String shareContent(HttpServletRequest request, Model model) {
		HttpSession httpSession = request.getSession();
		Dao_Share dao = sqlSession.getMapper(Dao_Share.class);
		
		command = new ContentCommand();
		command.execute(sqlSession, model, httpSession);
		
		model.addAttribute("CONTENTS", dao.shareDetailDao(Integer.parseInt(request.getParameter("no"))));
		
		return "content";
	}
	
	//////////////////////////////////
	//  210706 14:31  Park Jaewon   //
	//  content.jsp 에서 삭제하기      //
	//////////////////////////////////
	@RequestMapping("/sharedelete")
	public String shareDelete(HttpServletRequest request, Model model) {
		System.out.println("* * * Controller : shareDelete * * *");
		
		Dao_Share dao = sqlSession.getMapper(Dao_Share.class);
		
		dao.deleteShareDao(Integer.parseInt(request.getParameter("no")));
		dao.deletePlaceDao(Integer.parseInt(request.getParameter("place_no")));
			
		return "redirect:list";
	}
	
	//////////////////////////////////
	//  210706 17:32  Park Jaewon   //
	//  detail.jsp로 가기!            //
	//////////////////////////////////
	@RequestMapping("/share_detail")
	public String shareDetail(HttpServletRequest request, Model model) {

		Dao_Share dao = sqlSession.getMapper(Dao_Share.class);
		model.addAttribute("DETAIL", dao.shareDetailDao(Integer.parseInt(request.getParameter("no"))));
		
		return "share_detail";
	}
	
	//////////////////////////////////
	//  210706 20:27  Park Jaewon   //
	//  content.jsp 에서 수정         //
	//////////////////////////////////
	@RequestMapping("/share_modify")
	public String shareModify(MultipartHttpServletRequest mtfrequest, Model model) {
		System.out.println("* * * Controller : shareModify * * *");
		
		HttpSession httpSession = mtfrequest.getSession();
		model.addAttribute("mtfrequest", mtfrequest);
		
		command = new ModifyShareCommand();
		command.execute(sqlSession, model, httpSession);

		return "redirect:list";
	}
	
}// End of ShareSpaceController

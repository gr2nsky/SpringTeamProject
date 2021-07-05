package com.team4.ysms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team4.ysms.command.MyinfoQnACommand;
import com.team4.ysms.command.SCommand;
import com.team4.ysms.dao.Dao_myinfo_QnA;

@Controller
public class MyinfoController {
	
	String user_id = "user01";

	@Autowired
	private SqlSession sqlSession;
	
	SCommand command = null;
	
	
	/*
	 * 21.07.06 효경 -myInfoQnA
	 */
	
	// myInfoQnA List - 내가 작성한 qnaList
	@RequestMapping("/myinfo_qna.four")
	public String myInfo_qna(HttpServletRequest request, Model model) {
		System.out.println("myInfo_qna()");
		
		HttpSession httpsession = request.getSession();
		model.addAttribute("request", request);
		
		command = new MyinfoQnACommand();
		command.execute(sqlSession, model, httpsession);
		
		return "view_myinfo_QnA";
	}
	
	// myInfoQnA detailView
	@RequestMapping("/modify_qna.four")
	public String modify_qna(HttpServletRequest request, Model model) {
		System.out.println("modify_qna()");
		
		HttpSession httpsession = request.getSession();
		
		Dao_myinfo_QnA dao = sqlSession.getMapper(Dao_myinfo_QnA.class);
		model.addAttribute("qnaDetail", dao.myInfoQnADetailDao(request.getParameter("qna_no")));
		
		return "modify_QnA";
	}
	
	// myInfoQnA modify
	@RequestMapping("/qna_modify.four")
	public String qna_modify(HttpServletRequest request, Model model) {
		System.out.println("qna_modify()");
		
		HttpSession httpsession = request.getSession();
		
		Dao_myinfo_QnA dao = sqlSession.getMapper(Dao_myinfo_QnA.class);
		dao.myInfoQnaModifyDao(request.getParameter("qna_no"), request.getParameter("qnaContent"));
		
		return "modify_QnA_Completed";
	}
	
	// myInfoQnA Delete check
	@RequestMapping("/deleteCheck_qna.four")
	public String deleteCheck_qna(HttpServletRequest request, Model model) {
		System.out.println("deleteCheck_qna()");
		
		HttpSession httpsession = request.getSession();
		model.addAttribute("qna_no", request.getParameter("qna_no"));
		
		return "delete_QnA_check";
	}
	
	// myInfoQnA delete
	@RequestMapping("/qna_delete.four")
	public String qna_delete(HttpServletRequest request, Model model) {
		System.out.println("qna_delete()");
		
		HttpSession httpsession = request.getSession();
		
		Dao_myinfo_QnA dao = sqlSession.getMapper(Dao_myinfo_QnA.class);
		dao.myInfoQnaDeleteDao(request.getParameter("qna_no"));

		return "delete_QnA_Completed";
	}
}

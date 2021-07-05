package com.team4.ysms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team4.ysms.command.QnACommand;
import com.team4.ysms.command.SCommand;
import com.team4.ysms.command.WriteQnACommand;

@Controller
public class QnaController {
	
	@Autowired
	private SqlSession sqlSession;
	
	SCommand command = null;
	
	@RequestMapping("/qna.four")
	public String quaList(HttpServletRequest request, Model model) {
		System.out.println("qnaList()");
		
		HttpSession httpsession = request.getSession();
		model.addAttribute("request", request);
		
		command = new QnACommand();
		command.execute(sqlSession, model, httpsession);
		
		return "view_QnA";
	}
	
	@RequestMapping("/write_qna.four")
	public String write_qna(HttpServletRequest request, Model model) {
		System.out.println("write_qna()");
		model.addAttribute("place_no", request.getParameter("place_no"));
		
		return "write_QnA";
	}
	
	@RequestMapping("/qna_write.four")
	public String qna_write(HttpServletRequest request, Model model) {
		System.out.println("qna_write()");
		
		HttpSession httpsession = request.getSession();
		model.addAttribute("request", request);
		
		command = new WriteQnACommand();
		command.execute(sqlSession, model, httpsession);
		
		return "write_QnA_Completed";
	}
}
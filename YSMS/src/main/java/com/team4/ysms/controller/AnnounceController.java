package com.team4.ysms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team4.ysms.command.AnnounceCommand;
import com.team4.ysms.command.SCommand;

@Controller
public class AnnounceController {
	
	//@Autowired
	//private SqlSession sqlSession;
	SCommand command;
	
	@RequestMapping("/announce_user.four")
	public String announce_user(HttpServletRequest request, Model model){
		System.out.println("announceList");
		
//		HttpSession httpSession = request.getSession();
	//	model.addAttribute("request", request);
		
	//	command = new AnnounceCommand();
	//	command.execute(sqlSession, model, httpSession);
		
		return "/announce_user";
		
	}
	
	
	
	
	

} // AnnounceController

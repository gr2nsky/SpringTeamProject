package com.team4.ysms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team4.ysms.command.AdminUserDeleteCommand;
import com.team4.ysms.command.AdminUserListCommand;
import com.team4.ysms.command.SCommand;
import com.team4.ysms.command.UserDetailViewCommand;

public class AdminController {
	@Autowired
	private SqlSession sqlSession;

	SCommand command = null;
	
	@RequestMapping("/admin_managingUser")
	public String writeForm(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		HttpSession httpSession = request.getSession();	

		command = new AdminUserListCommand();
		command.execute(sqlSession, model, httpSession);
		
		return "admin_userList";
	}
	
	@RequestMapping("/userDelete")
	public String userDelete(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		HttpSession httpSession = request.getSession();	

		command = new AdminUserDeleteCommand();
		command.execute(sqlSession, model, httpSession);
		
		return "admin_managingUser";
	}
	
	@RequestMapping("/userDetailView")
	public String userDetailView(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		HttpSession httpSession = request.getSession();	

		command = new UserDetailViewCommand();
		command.execute(sqlSession, model, httpSession);
		
		return "userDetailView";
	}
	
	
}

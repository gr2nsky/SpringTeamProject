package com.team4.ysms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team4.ysms.command.AnnounceCommand;
import com.team4.ysms.command.AnnounceContentCommand;
import com.team4.ysms.command.AnnounceDeleteCommand;
import com.team4.ysms.command.AnnounceModifyCommand;
import com.team4.ysms.command.AnnounceWriteCommand;
import com.team4.ysms.command.SCommand;

@Controller
public class AnnounceController {
	
	@Autowired
	private SqlSession sqlSession;
	
	SCommand command;
	
	// 리스트 보여주기(admin)
		@RequestMapping("/announce_admin")
		public String announce_admin(HttpServletRequest request, Model model, HttpSession httpSession){
			System.out.println("announceList_admin");
			
			model.addAttribute("request", request);
			
			command = new AnnounceCommand();
			command.execute(sqlSession, model, httpSession);
			
			return "announce_admin";
			
		}
		
		
		
		// 리스트 보여주기(user)
		@RequestMapping("/announce_user")
		public String announce_user(HttpServletRequest request, Model model, HttpSession httpSession){
			System.out.println("announceList_user");
			
			model.addAttribute("request", request);
			
			command = new AnnounceCommand();
			command.execute(sqlSession, model, httpSession);			
			
			return "announce_user";
			
		}

		
		
		// 공지사항 입력하기(입력하는 곳 형식)
		@RequestMapping("/announceWrite_view_admin")
		public String announceWrite_view_admin() {
			return "announceWrite_view_admin";
		}
		
		// 입력
		@RequestMapping("announceWrite_admin")
		public String announceWrite(HttpServletRequest request, Model model, HttpSession httpSession){
			System.out.println("announceWrite");
			
			model.addAttribute("request", request);
			command = new AnnounceWriteCommand();
			command.execute(sqlSession, model, httpSession);
			
			return "redirect:announce_admin";
		}
		
		
		// content 보여주기(admin)
		@RequestMapping("announceContent_view_admin")
		public String announceContent_view_admin(HttpServletRequest request, Model model, HttpSession httpSession){
			System.out.println("announceView_admin");
			
			model.addAttribute("request", request);
			command = new AnnounceContentCommand();
			command.execute(sqlSession, model, httpSession);
			
			return "announceContent_view_admin";
		}
		
		// content 보여주기(user)
		@RequestMapping("announceContent_view_user")
		public String announceContent_view_user(HttpServletRequest request, Model model, HttpSession httpSession){
			System.out.println("announceView_user");
			
			model.addAttribute("request", request);
			command = new AnnounceContentCommand();
			command.execute(sqlSession, model, httpSession);
			
			return "announceContent_view_user";
		}
		
		// 삭제
		@RequestMapping("announceDelete_admin")
		public String announceDelete(HttpServletRequest request, Model model, HttpSession httpSession){
			System.out.println("announceDelete");
			
			model.addAttribute("request", request);
			command = new AnnounceDeleteCommand();
			command.execute(sqlSession, model, httpSession);
			
			return "redirect:announce_admin";
		}
		
		
		// 수정
		@RequestMapping("announceModify_admin")
		public String announceModify(HttpServletRequest request, Model model, HttpSession httpSession){
			System.out.println("announceModify");
			
			model.addAttribute("request", request);
			command = new AnnounceModifyCommand();
			command.execute(sqlSession, model, httpSession);
			
			return "redirect:announce_admin";
		}
	
	

} // AnnounceController

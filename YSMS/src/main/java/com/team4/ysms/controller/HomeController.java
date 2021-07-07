package com.team4.ysms.controller;


import java.util.Locale;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "mainPage";
	}
	
	// 로고 클릭시 메인 페이지로 이동
	@RequestMapping("/mainPage")
	public String mainPage() {
		
		return "mainPage";
		
	}
	
	// 너공나공?(introduction) 페이지로 이동
	@RequestMapping("/introduction")
	public String introduction() {
		
		return "introduction";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} // HomeController

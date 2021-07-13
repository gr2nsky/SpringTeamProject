package com.team4.ysms.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.team4.ysms.command.SCommand;

@Controller
public class reservationController {
	@Autowired
	private SqlSession sqlSession;
	

	SCommand command = null;

}

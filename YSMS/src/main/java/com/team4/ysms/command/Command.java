package com.team4.ysms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface Command {
	
		void execute(SqlSession sqlSession, Model model);
	
	
}

package com.team4.ysms.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team4.ysms.dao.Dao_Login;

public class LoginCommand implements SCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String userID = request.getParameter("userID");
		String userPW = request.getParameter("userPW");
		
		Dao_Login dao = new Dao_Login();
		String loginedUserID = dao.tryToLogin(userID, userPW);
		
		httpSession.setAttribute("loginedUserID", loginedUserID);
		//로그인을 실행했다는 일종의 토큰
		model.addAttribute("tryLogin", "1");
		
	}



}

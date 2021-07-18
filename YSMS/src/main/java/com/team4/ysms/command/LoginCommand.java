package com.team4.ysms.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team4.ysms.common.LoginedUserInfo;
import com.team4.ysms.dao.Dao_Login;
import com.team4.ysms.dao.Dao_QnA;
import com.team4.ysms.dto.Dto_Login;

public class LoginCommand implements SCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String userID = request.getParameter("userID");
		String userPW = request.getParameter("userPW");
		
		Dao_Login dao = sqlSession.getMapper(Dao_Login.class);
		Dto_Login dto = dao.tryToLogin(userID, userPW);
		
		String loginedUserID = dto.getId();
		LoginedUserInfo.id = dto.getId();
		LoginedUserInfo.name = dto.getName();
		LoginedUserInfo.email = dto.getEmail();
		LoginedUserInfo.phone = dto.getPhone();
		
		httpSession.setAttribute("loginedUserID", loginedUserID);
		//로그인을 실행했다는 일종의 토큰
		model.addAttribute("tryLogin", "1");
	}



}

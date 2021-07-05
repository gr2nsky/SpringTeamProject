package com.team4.ysms.command;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team4.ysms.common.LoginedUserInfo;
import com.team4.ysms.dao.Dao_QnA;

public class WriteQnACommand implements SCommand {

//	String user_id = LoginedUserInfo.id;
	String user_id = "user01";

	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		// TODO Auto-generated method stub
		

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		

		Dao_QnA dao = sqlSession.getMapper(Dao_QnA.class);
		dao.qnaWriteDao(request.getParameter("qnaContent"), user_id, request.getParameter("place_no"));
		
		
	}
	
	

}

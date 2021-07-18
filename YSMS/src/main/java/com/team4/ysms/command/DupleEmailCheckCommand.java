package com.team4.ysms.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team4.ysms.dao.Dao_Login;
import com.team4.ysms.dto.Dto_Login;

public class DupleEmailCheckCommand implements SCommand {


	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String result = "unuseable";
		
		//중복체크
		String email = request.getParameter("email");
		
		Dao_Login dao = sqlSession.getMapper(Dao_Login.class);
		Dto_Login dto = dao.emailDupleCheck(email);
		if (dto == null) {
			result = "useable";
		}
		
		model.addAttribute("emailDupleCheckResult", result);
		model.addAttribute("email", email);
	}

}

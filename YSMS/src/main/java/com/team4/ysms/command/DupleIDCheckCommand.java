package com.team4.ysms.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import com.team4.ysms.dao.Dao_Login;

public class DupleIDCheckCommand implements SCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		
		String id = request.getParameter("id");

		Dao_Login dao = new Dao_Login();
		String result = dao.IDdupleCheck(id);
		if (result == "useable"){
			model.addAttribute("duplicate_checked_id", id);
		}
		
	}

}

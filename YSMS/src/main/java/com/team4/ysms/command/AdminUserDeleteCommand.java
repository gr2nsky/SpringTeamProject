package com.team4.ysms.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team4.ysms.dao.Dao_Manage;

public class AdminUserDeleteCommand implements SCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		System.out.println("AdminUserDeleteCommand");
		String selectedUsers = request.getParameter("selectedUsers");
		
		System.out.println("selectedUsers : " + selectedUsers);
		Dao_Manage dao = sqlSession.getMapper(Dao_Manage.class);
		
		int result = dao.deleteUsers(selectedUsers);
		
		if (result == 1) {
			model.addAttribute("deleteResult", "ture");
		} else {
			model.addAttribute("deleteResult", "false");
		}
		
		model.addAttribute("tryDelete", 1);
	}
}

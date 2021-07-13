package com.team4.ysms.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team4.ysms.dao.Dao_Manage;
import com.team4.ysms.dto.Dto_SignUp;

public class UserDetailViewCommand implements SCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int no = Integer.parseInt(request.getParameter("no"));

		Dao_Manage dao = sqlSession.getMapper(Dao_Manage.class);
		Dto_SignUp dto = dao.getUserInfo(no);
		
		String[] phone = dto.getPhone().split("-");
		
		model.addAttribute("DTO", dto);
		model.addAttribute("phone1", phone[0]);
		model.addAttribute("phone2", phone[1]);
		model.addAttribute("phone3", phone[2]);
	}

}

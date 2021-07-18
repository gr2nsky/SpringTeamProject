package com.team4.ysms.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team4.ysms.common.LoginedUserInfo;
import com.team4.ysms.dao.Dao_Login;
import com.team4.ysms.dao.Dao_MyInfo_UpdateProfile;
import com.team4.ysms.dto.Dto_Login;
import com.team4.ysms.dto.Dto_SignUp;

public class MyInfoFormCommand implements SCommand{

	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		String loginedId = LoginedUserInfo.id;
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		
		if (loginedId.equals("") || loginedId == null) {
			model.addAttribute("error", 1);
		}
		
		Dao_MyInfo_UpdateProfile dao = sqlSession.getMapper(Dao_MyInfo_UpdateProfile.class);
		Dto_Login dto = dao.getUserInfo(loginedId);
		
		if(dto.getFilePath() == null || dto.getFilePath().equals("")) {
			System.out.println("none photo");
		}
		System.out.println(dto.getFilePath());
		
		String[] phone = dto.getPhone().split("-");
		
		model.addAttribute("DTO", dto);
		model.addAttribute("phone1", phone[0]);
		model.addAttribute("phone2", phone[1]);
		model.addAttribute("phone3", phone[2]);
	}

}

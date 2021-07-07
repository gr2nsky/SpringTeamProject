package com.team4.ysms.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team4.ysms.dao.Dao_Announce;
import com.team4.ysms.dto.Dto_Announce;

public class AnnounceContentCommand implements SCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request"); 
		
		String no = request.getParameter("no");
		
		Dao_Announce dao = sqlSession.getMapper(Dao_Announce.class);
		Dto_Announce dto = dao.announceContent_view(no); // 얘는 select 할 때 쓴다!
		
		model.addAttribute("announceContent_view", dto);
	}

} // AnnounceContentCommand


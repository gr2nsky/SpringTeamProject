package com.team4.ysms.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team4.ysms.dao.Dao_Announce;

public class AnnounceModifyCommand implements SCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request"); 
		
		String no = request.getParameter("no");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		System.out.println("AdminModifyCommand : no = " + no + ", title = " + title);
		
		Dao_Announce dao = sqlSession.getMapper(Dao_Announce.class);
		dao.announceModify(title, content, no);

	}

} // AnnounceModifyCommand

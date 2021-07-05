package com.team4.ysms.command;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team4.ysms.dao.Dao_SearchPlace;
import com.team4.ysms.dto.Dto_SearchPlace;

public class SSearchPlaceCommand implements SCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		// TODO Auto-generated method stub
		Dao_SearchPlace dao = new Dao_SearchPlace();
		// 목록 불러오기
		ArrayList<Dto_SearchPlace> dtos1 = dao.shareList(1, 5);
		
		// 검색 결과		
		
		ArrayList<Dto_SearchPlace> dtos = dao.SearchLocation(null, null, null, 0, 0);
		
		model.addAttribute("searchPlaceResult", dtos);

	}

}

package com.team4.ysms.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team4.ysms.dao.Dao_Review;
import com.team4.ysms.dto.Dto_Paging;

public class ReviewCommand implements SCommand {

	/*
	 * 21.07.06 효경
	 * Review List Command
	 */
	
	int reviewNumOfTuplesPerPage = 1;
	
	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		// TODO Auto-generated method stub
		int requestPage = 1;

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int place_no = Integer.parseInt(request.getParameter("place_no"));
		
		
		if(request.getParameter("reviewPage") != null) {
			requestPage = Integer.parseInt(request.getParameter("reviewPage"));
		}
		
		Dao_Review dao = sqlSession.getMapper(Dao_Review.class);
		
		Dto_Paging dto = dao.reviewListCountDao(place_no);
		
		int countedTuple = dto.getTotalPage();
		ArrayList<Integer> reviewPageList = calcNumOfPage(countedTuple);
		model.addAttribute("reviewPageList", reviewPageList);
		
		
		int offset = requestPage-1;
		if(offset != 0) {
			offset *= reviewNumOfTuplesPerPage;
		}
		
		model.addAttribute("reviewList", dao.reviewListDao(place_no, offset, reviewNumOfTuplesPerPage));
		
		
	}
	
	public ArrayList<Integer> calcNumOfPage(int countedTuple){
		ArrayList<Integer> reviewArr = new ArrayList<Integer>();
		
		int calcPage = 0;
		
		// 튜플의 총 갯수가 딱 맞아떨어지는 경우를 대비해 조건분기
		if (countedTuple % reviewNumOfTuplesPerPage == 0) {
			calcPage = countedTuple / reviewNumOfTuplesPerPage;
		} else {
			calcPage = countedTuple / reviewNumOfTuplesPerPage + 1;
		}
		
		for(int i=1; i<=calcPage; i++) {
			reviewArr.add(i);
		}
		
		return reviewArr;
	}

}

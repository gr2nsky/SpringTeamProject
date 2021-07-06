package com.team4.ysms.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team4.ysms.dao.Dao_myinfo_Review;
import com.team4.ysms.dto.Dto_Paging;

public class MyinfoReviewCommand implements SCommand {


//	String user_id = LoginedUserInfo.id;
	String user_id = "user01";
	int numOfTuplesPerPage = 5;
	
	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		// TODO Auto-generated method stub
		
		int requestPage = 1;

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		if(request.getParameter("myinfoReviewPage") != null) {
			requestPage = Integer.parseInt(request.getParameter("myinfoReviewPage"));
		}
		
		Dao_myinfo_Review dao = sqlSession.getMapper(Dao_myinfo_Review.class);
		
		Dto_Paging dto = dao.myInfoReviewListCountDao(user_id);
		
		int countedTuple = dto.getTotalPage();
		ArrayList<Integer> myinfoReviewPageList = calcNumOfPage(countedTuple);
		model.addAttribute("myinfoReviewPageList", myinfoReviewPageList);
		
		
		int offset = requestPage-1;
		if(offset != 0) {
			offset *= numOfTuplesPerPage;
		}
		
		model.addAttribute("myinfoReviewList", dao.myInfoReviewListDao(user_id, offset, numOfTuplesPerPage));
		
	}

	public ArrayList<Integer> calcNumOfPage(int countedTuple){
		ArrayList<Integer> arr = new ArrayList<Integer>();
		
		int calcPage = 0;
		
		// 튜플의 총 갯수가 딱 맞아떨어지는 경우를 대비해 조건분기
		if (countedTuple % numOfTuplesPerPage == 0) {
			calcPage = countedTuple / numOfTuplesPerPage;
		} else {
			calcPage = countedTuple / numOfTuplesPerPage + 1;
		}
		
		for(int i=1; i<=calcPage; i++) {
			arr.add(i);
		}
		
		return arr;
	}

}// endLine
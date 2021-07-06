package com.team4.ysms.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team4.ysms.common.LoginedUserInfo;
import com.team4.ysms.dao.Dao_myinfo_QnA;
import com.team4.ysms.dto.Dto_Paging;

public class MyinfoQnACommand implements SCommand {

//	String user_id = LoginedUserInfo.id;
	String user_id = "user01";
	int numOfTuplesPerPage = 5;
	

	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		// TODO Auto-generated method stub

		int requestPage = 1;

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		if(request.getParameter("myinfoQnaPage") != null) {
			requestPage = Integer.parseInt(request.getParameter("myinfoQnaPage"));
		}
		
		Dao_myinfo_QnA dao = sqlSession.getMapper(Dao_myinfo_QnA.class);
		
		Dto_Paging dto = dao.myInfoQnaListCountDao(user_id);
		
		int countedTuple = dto.getTotalPage();
		ArrayList<Integer> myinfoQnaPageList = calcNumOfPage(countedTuple);
		model.addAttribute("myinfoQnaPageList", myinfoQnaPageList);
		
		
		int offset = requestPage-1;
		if(offset != 0) {
			offset *= numOfTuplesPerPage;
		}
		
		model.addAttribute("myInfoQnaList", dao.myInfoQnAListDao(user_id, offset, numOfTuplesPerPage));
		
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

}

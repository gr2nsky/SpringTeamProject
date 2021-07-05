package com.team4.ysms.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team4.ysms.dao.Dao_QnA;
import com.team4.ysms.dto.Dto_Paging;

public class QnACommand implements SCommand {

	/* 
	 	-----------------------------
	 	21.07.05 hyokyeong JO
	 	DB table qna_review
	 	
	 	****현재 place_no = 3으로 변수 선언해서 진행중
	 	-> 추후 세션으로 받아와서 바꿀 것.
	 	-----------------------------
	 */
	int qnaNumOfTuplesPerPage = 1;

	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		// TODO Auto-generated method stub

		int requestPage = 1;

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int place_no = Integer.parseInt(request.getParameter("place_no"));
		
		
		if(request.getParameter("qnaPage") != null) {
			requestPage = Integer.parseInt(request.getParameter("qnaPage"));
		}
		
		Dao_QnA dao = sqlSession.getMapper(Dao_QnA.class);
		
		Dto_Paging dto = dao.qnaListCountDao(place_no);
		
		int countedTuple = dto.getTotalPage();
		ArrayList<Integer> qnaPageList = calcNumOfPage(countedTuple);
		model.addAttribute("qnaPageList", qnaPageList);
		
		
		int offset = requestPage-1;
		if(offset != 0) {
			offset *= qnaNumOfTuplesPerPage;
		}
		System.out.println(place_no);
		
		model.addAttribute("qnaList", dao.qnaListDao(place_no, offset, qnaNumOfTuplesPerPage));
		
		
	}
	
	public ArrayList<Integer> calcNumOfPage(int countedTuple){
		ArrayList<Integer> qnaArr = new ArrayList<Integer>();
		
		int calcPage = 0;
		
		// 튜플의 총 갯수가 딱 맞아떨어지는 경우를 대비해 조건분기
		if (countedTuple % qnaNumOfTuplesPerPage == 0) {
			calcPage = countedTuple / qnaNumOfTuplesPerPage;
		} else {
			calcPage = countedTuple / qnaNumOfTuplesPerPage + 1;
		}
		
		for(int i=1; i<=calcPage; i++) {
			qnaArr.add(i);
		}
		
		return qnaArr;
	}

}

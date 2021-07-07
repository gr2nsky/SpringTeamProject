package com.team4.ysms.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team4.ysms.dao.Dao_Host_QnA;
import com.team4.ysms.dto.Dto_Paging;
import com.team4.ysms.dto.Dto_QnA;

public class HostQnACommand implements SCommand {

	int qnaNumOfTuplesPerPage = 5;
	
	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		// TODO Auto-generated method stub
		int requestPage = 1;

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int place_no = Integer.parseInt(request.getParameter("place_no"));
		model.addAttribute("place_no", place_no);
		
		
		if(request.getParameter("hostQnaPage") != null) {
			requestPage = Integer.parseInt(request.getParameter("hostQnaPage"));
		}
		
		Dao_Host_QnA dao = sqlSession.getMapper(Dao_Host_QnA.class);
		
		Dto_QnA dtoQna = dao.hostQnaShareTitleDao(place_no);
		model.addAttribute("hostQnaShareTitle", dtoQna.getQnaPlaceName());
		
		Dto_Paging dto = dao.hostQnaListCountDao(place_no);
		
		int countedTuple = dto.getTotalPage();
		ArrayList<Integer> hostQnaPageList = calcNumOfPage(countedTuple);
		model.addAttribute("hostQnaPageList", hostQnaPageList);
		
		
		int offset = requestPage-1;
		if(offset != 0) {
			offset *= qnaNumOfTuplesPerPage;
		}

		model.addAttribute("hostQnaList", dao.hostQnaListDao(place_no, offset, qnaNumOfTuplesPerPage));
		
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

package com.team4.ysms.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team4.ysms.common.LoginedUserInfo;
import com.team4.ysms.common.ShareVar_login;
import com.team4.ysms.dao.Dao_Share;
import com.team4.ysms.dto.Dto_Share;


public class ShareListCommand implements SCommand {

	int numOfTuplesPerPage = 5;

	String user_id = LoginedUserInfo.id;
	
	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		
		Dao_Share dao = sqlSession.getMapper(Dao_Share.class);
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int requestPage = 1;
		
		if (request.getParameter("page") != null) {
			requestPage = Integer.parseInt(request.getParameter("page"));
			// content에서 목록보기 요청시 최근 페이지 목록으로 돌아가기 위해 세션에 저장
			model.addAttribute("currentPage", requestPage);
		}
	
		int offset = requestPage-1;
		if(offset != 0) {
			offset *= numOfTuplesPerPage;
		}

		// 반환되는 총 튜플의 수
		int countedTuple = dao.countTuple(user_id);
		// 페이지 목록 (1...n)
		ArrayList<Integer> pageList = calcNumOfPage(countedTuple);
		// 페이지 목록을 세션에 담는다. *list에 진입하면 무조건 세션이 갱신되므로 새 글이 생겨도 최신화가 된다.
		model.addAttribute("pageList", pageList);
	
		model.addAttribute("SHARELIST", dao.shareListDao(user_id, offset, numOfTuplesPerPage));
		
	}

	public ArrayList<Integer> calcNumOfPage(int countedTuple) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int calcPage = 0;
		// 튜플의 총 갯수가 딱 맞아떨어지는 경우를 대비해 조건분기
		if (countedTuple % numOfTuplesPerPage == 0) {
			calcPage = countedTuple / numOfTuplesPerPage;
		} else {
			calcPage = countedTuple / numOfTuplesPerPage + 1;
		}

		for (int i = 1; i <= calcPage; i++) {
			System.out.println("page : " + i);
			arr.add(i);
		}
		return arr;
	
	}
	
	}



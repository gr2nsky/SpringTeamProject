package com.team4.ysms.command;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team4.ysms.dao.Dao_SearchPlace;
import com.team4.ysms.dao.IDao_SearchPlace;
import com.team4.ysms.dto.Dto_Paging;
import com.team4.ysms.dto.Dto_SearchPlace;

public class SSearchPlaceCommand implements SCommand {
	
	//페이지당 표시할 게시글의 수
	int numOfTuplesPerPage = 5;

	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		// TODO Auto-generated method stub	
		//사용자가 요청한 페이지 번호 초기값은 가장 최신글을 보여주는 1
		int requestPage = 1;
				
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		IDao_SearchPlace dao = sqlSession.getMapper(IDao_SearchPlace.class);
	
		
		// 입력된 값 불러옴
		String inputCategory = request.getParameter("categorySpace"); // 입력된 값 불러옴
		String inputLocation = request.getParameter("location") ; //입력된 검색어 값 불러옴
		String inputDate = request.getParameter("date") ; //입력된 검색어 값 불러옴

		String search4 = "";
		
//		if(inputCategory.equals("0")) {
//			inputCategory = "1,2,3,4";
//		}
			
		
			//최초 목록 진입시 page값을 넘겨주지 않음 -> 초기값인 1페이지 목록을 보여줌
			//목록에서 page요청 -> 해당 페이지 번호로 requestPage 설정
			if (request.getParameter("ResultPlacePage") != null) {
				requestPage = Integer.parseInt(request.getParameter("ResultPlacePage"));
				System.out.println();
						
				System.out.println(requestPage + "페이지 요청");
			}
					
			//반환되는 총 튜플의 수
			Dto_Paging dto = dao.searchPlaceResultPaging(inputCategory, inputLocation);
			int countedTuple = dto.getTotalPage();
	//		int countedTuple = dao.countShareTuple();
					
			//페이지 목록 (1...n)
			ArrayList<Integer> pageList = calcNumOfPage(countedTuple);
			//페이지 목록을 세션에 담는다. *list에 진입하면 무조건 세션이 갱신되므로 새 글이 생겨도 최신화가 된다.
			model.addAttribute("ResultPlacePageList", pageList);
			
			int offset = requestPage-1;
			if(offset != 0) {
				offset *= numOfTuplesPerPage;
			}
						
	
	//		model.addAttribute("shareList", dao.searchPlacelistDao(offset, numOfTuplesPerPage));
	//		model.addAttribute("pageList", dao.searchPlacelistDao(offset, numOfTuplesPerPage));
			model.addAttribute("SearchLocation", dao.searchPlaceResult(inputCategory, inputLocation, inputDate, offset, numOfTuplesPerPage));
	
		}
	
	//총 튜플수를 받아 페이지당 표시할 게시글의 수로 나누어서 페이지수를 계산하고 jsp에서 for-each문을 돌리기 위해 배열에 담는다
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
				System.out.println(i);
				arr.add(i);
			}
			return arr;
		}
		
	}



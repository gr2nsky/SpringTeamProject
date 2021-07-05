package com.team4.ysms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team4.ysms.command.SCommand;
import com.team4.ysms.dao.IDao_SearchPlace;

@Controller
public class PlaceSearchController {
	
	// servlet-context.xml에서 정의한 SqlSession 이용
	@Autowired
	private org.apache.ibatis.session.SqlSession sqlSession;
	
	SCommand command = null;
	
	
	@RequestMapping("/SearchPlacePage.four")
	public String SearchPlacePage(Model model) {
		IDao_SearchPlace dao = sqlSession.getMapper(IDao_SearchPlace.class);
		model.addAttribute("searchPlacelistDao", dao.searchPlacelistDao()); // jsp로 값을 전달
		return "PlaceSearchPage"; // jsp로 이동
	}
	
	
	@RequestMapping("SearchPlaceCommand.four")
	public String PlaceResultPage(HttpServletRequest request, Model model) {
		
		return "PlaceResultPage"; // jsp로 이동
	}
	
	
//	// PlaceSearchPage 보여주기
//case ("/SearchPlacePage.four"):
//	System.out.println("PlaceSearchPage.jsp 출력시도");
//	command = new PlaceListAllCommand(); 
//	command.execute(request, response); 
//	viewPage = "PlaceSearchPage.jsp"; 
//	break;
//
//// PlaceResultPage 보여주기
//case ("/SearchPlaceCommand.four"): 
//	System.out.println("PlaceSearch/PlaceResultPage.jsp 출력시도");
//	command = new SearchPlaceCommand(); // Command 대신 SearchLocationCommand 사용가능
//	command.execute(request, response); // Dao 실행
//	viewPage = "PlaceResultPage.jsp"; // 화면 출력
//	break;	
	
}

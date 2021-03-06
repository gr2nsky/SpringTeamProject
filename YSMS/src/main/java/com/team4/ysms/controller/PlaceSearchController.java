package com.team4.ysms.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team4.ysms.command.PlaceListAllCommand;
import com.team4.ysms.command.QnACommand;
import com.team4.ysms.command.SCommand;
import com.team4.ysms.command.SPlaceListAllCommand;
import com.team4.ysms.command.SSearchPlaceCommand;
import com.team4.ysms.dao.Dao_SearchPlace;
import com.team4.ysms.dao.IDao_SearchPlace;
import com.team4.ysms.dto.Dto_SearchPlace;
import com.team4.ysms.util.Constant;

@Controller
public class PlaceSearchController {
	
	// servlet-context.xml에서 정의한 SqlSession 이용
	@Autowired
	private SqlSession sqlSession;
	
	private JdbcTemplate template;
	
	SCommand command = null;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}
	
	// 검색 메인 페이지
	@RequestMapping("/SearchPlacePage")
	public String SearchPlacePage(HttpServletRequest request, Model model) {
		
		HttpSession httpsession = request.getSession();
		model.addAttribute("request", request);
		
		command = new SPlaceListAllCommand();
		command.execute(sqlSession, model, httpsession);
		

		return "PlaceSearchPage"; // jsp로 이동
	}
	
	
	// 날짜 선택
	@RequestMapping("/placeSearchCalendar")
	public String placeSearchCalendar(HttpServletRequest request, Model model) {
		
//		HttpSession httpsession = request.getSession();
//		model.addAttribute("request", request);
		
		return "placeSearchCalendar"; // jsp로 이동
	}
	
	
	// 검색 결과 페이지로 이동
	@RequestMapping("/PlaceResultPage")
	public String PlaceResultPage(HttpServletRequest request, Model model) {
		HttpSession httpsession = request.getSession();
		model.addAttribute("request", request);
		command = new SSearchPlaceCommand();
		command.execute(sqlSession, model, httpsession);
		
//		IDao_SearchPlace dao = sqlSession.getMapper(IDao_SearchPlace.class);
//		model.addAttribute("inputCategory", dao.searchPlaceResult(inputCategory, inputLocation, inputDate, requestPage, numOfTuplesPerPage) ); // model에 전달
//		command = new SDao_SearchPlace();
	
		
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

package com.team4.ysms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.team4.ysms.command.ModifyReviewCommand;
import com.team4.ysms.command.MyinfoQnACommand;
import com.team4.ysms.command.MyinfoRentalScheduledCommand;
import com.team4.ysms.command.MyinfoReviewCommand;
import com.team4.ysms.command.SCommand;
import com.team4.ysms.dao.Dao_myinfo_QnA;
import com.team4.ysms.dao.Dao_myinfo_Review;

@Controller
public class MyinfoController {
	
	String user_id = "user01";

	@Autowired
	private SqlSession sqlSession;
	
	SCommand command = null;
	
	
	/*
	 * 21.07.06 효경 -myInfoQnA
	 */
	
	// myInfoQnA List - 내가 작성한 qnaList
	@RequestMapping("/myinfo_qna.four")
	public String myInfo_qna(HttpServletRequest request, Model model) {
		System.out.println("myInfo_qna()");
		
		HttpSession httpsession = request.getSession();
		model.addAttribute("request", request);
		
		command = new MyinfoQnACommand();
		command.execute(sqlSession, model, httpsession);
		
		return "view_myinfo_QnA";
	}
	
	// myInfoQnA detailView
	@RequestMapping("/modify_qna.four")
	public String modify_qna(HttpServletRequest request, Model model) {
		System.out.println("modify_qna()");
		
		HttpSession httpsession = request.getSession();
		
		Dao_myinfo_QnA dao = sqlSession.getMapper(Dao_myinfo_QnA.class);
		model.addAttribute("qnaDetail", dao.myInfoQnADetailDao(request.getParameter("qna_no")));
		
		return "modify_QnA";
	}
	
	// myInfoQnA modify
	@RequestMapping("/qna_modify.four")
	public String qna_modify(HttpServletRequest request, Model model) {
		System.out.println("qna_modify()");
		
		HttpSession httpsession = request.getSession();
		
		Dao_myinfo_QnA dao = sqlSession.getMapper(Dao_myinfo_QnA.class);
		dao.myInfoQnaModifyDao(request.getParameter("qna_no"), request.getParameter("qnaContent"));
		
		return "modify_QnA_Completed";
	}
	
	// myInfoQnA Delete check
	@RequestMapping("/deleteCheck_qna.four")
	public String deleteCheck_qna(HttpServletRequest request, Model model) {
		System.out.println("deleteCheck_qna()");
		
		HttpSession httpsession = request.getSession();
		model.addAttribute("qna_no", request.getParameter("qna_no"));
		
		return "delete_QnA_check";
	}
	
	// myInfoQnA delete
	@RequestMapping("/qna_delete.four")
	public String qna_delete(HttpServletRequest request, Model model) {
		System.out.println("qna_delete()");
		
		HttpSession httpsession = request.getSession();
		
		Dao_myinfo_QnA dao = sqlSession.getMapper(Dao_myinfo_QnA.class);
		dao.myInfoQnaDeleteDao(request.getParameter("qna_no"));

		return "delete_QnA_Completed";
	}
	
	/*
	 *  21.07.06 효경 - myInfoReview
	 */
	
	// myInfoReview List - 내가 작성한 reviewList
	@RequestMapping("/myinfo_review.four")
	public String myInfo_review(HttpServletRequest request, Model model) {
		System.out.println("myInfo_review()");
		
		HttpSession httpsession = request.getSession();
		model.addAttribute("request", request);
		
		command = new MyinfoReviewCommand();
		command.execute(sqlSession, model, httpsession);
		
		return "view_myinfoReview";
	}
	
	// review detailView
	@RequestMapping("/detail_review.four")
	public String detail_review(HttpServletRequest request, Model model) {
		System.out.println("detail_review()");
		
		HttpSession httpsession = request.getSession();
		
		Dao_myinfo_Review dao = sqlSession.getMapper(Dao_myinfo_Review.class);
		model.addAttribute("detailViewReview", dao.myInfoReviewDetailDao(request.getParameter("rentalNo")));
		
		return "detailView_Review";
	}
	
	// myInfoQnA modify
	@RequestMapping("/modify_review.four")
	public String modify_review(HttpServletRequest request, Model model) {
		System.out.println("modify_review()");
		
		HttpSession httpsession = request.getSession();
		
		model.addAttribute("rentalNo", request.getParameter("rentalNo"));
		model.addAttribute("reviewScore", request.getParameter("reviewScore"));
		model.addAttribute("reviewContent", request.getParameter("reviewContent"));
		model.addAttribute("reviewFilePath", request.getParameter("reviewFilePath"));
		
		return "modify_Review";
	}
	
	@RequestMapping("/review_modify.four")
	public String review_modify(MultipartHttpServletRequest mtfRequest, Model model) {
		System.out.println("review_modify()");
		
		HttpSession httpsession = mtfRequest.getSession();
		
		model.addAttribute("mtfRequest", mtfRequest);
		
		command = new ModifyReviewCommand();
		command.execute(sqlSession, model, httpsession);
		
		return "modify_Review_Completed";
	}

	// review Delete check
	@RequestMapping("/deleteCheck_review.four")
	public String deleteCheck_review(HttpServletRequest request, Model model) {
		System.out.println("deleteCheck_review()");
		
		HttpSession httpsession = request.getSession();
		model.addAttribute("rentalNo", request.getParameter("rentalNo"));
		
		return "delete_Review_check";
	}
	
	// review delete
	@RequestMapping("/review_delete.four")
	public String review_delete(HttpServletRequest request, Model model) {
		System.out.println("deleteCheck_review()");
		
		HttpSession httpsession = request.getSession();
		
		Dao_myinfo_Review dao = sqlSession.getMapper(Dao_myinfo_Review.class);
		dao.myInfoReviewDeleteDao(request.getParameter("rentalNo"));

		return "delete_Review_Completed";
	}
	
	/*
	 *  21.07.06 효경
	 *  나의 예약 리스트
	 */
	
	// myInfo rental List(예정된예약) 보여주기
	@RequestMapping("/myinfo_rental_scheduled.four")
	public String myinfo_rental_scheduled(HttpServletRequest request, Model model) {
		System.out.println("myinfo_rental_scheduled()");
		
		HttpSession httpsession = request.getSession();
		
		model.addAttribute("request", request);
		
		command = new MyinfoRentalScheduledCommand();
		command.execute(sqlSession, model, httpsession);
	
		
		return "myinfoRentalList_scheduled";
	}
	
	// myInfo rental List(이전 예약) 보여주기
	@RequestMapping("/myinfo_rental_previous.four")
	public String myinfo_rental_previous(HttpServletRequest request, Model model) {
		System.out.println("myinfo_rental_previous()");
		
		HttpSession httpsession = request.getSession();
		
		return "myinfoRentalList_previous";
	}
	
}

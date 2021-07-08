package com.team4.ysms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team4.ysms.command.HostQnACommand;
import com.team4.ysms.command.QnACommand;
import com.team4.ysms.command.ReviewCommand;
import com.team4.ysms.command.SCommand;
import com.team4.ysms.common.LoginedUserInfo;
import com.team4.ysms.dao.Dao_Host_QnA;
import com.team4.ysms.dao.Dao_QnA;

@Controller
public class QnaReviewController {
	
	@Autowired
	private SqlSession sqlSession;
	
	SCommand command = null;
	String user_id = LoginedUserInfo.id;
	
	/*
	 * 21.07.05 hyokyeong
	 * share detailView - QnA
	 */
	
	@RequestMapping("/qna")
	public String quaList(HttpServletRequest request, Model model) {
		System.out.println("qnaList()");
		
		HttpSession httpsession = request.getSession();
		model.addAttribute("request", request);
		
		command = new QnACommand();
		command.execute(sqlSession, model, httpsession);
		
		return "view_QnA";
	}
	
	@RequestMapping("/write_qna")
	public String write_qna(HttpServletRequest request, Model model) {
		System.out.println("write_qna()");
		
		HttpSession httpsession = request.getSession();
		model.addAttribute("place_no", request.getParameter("place_no"));
		
		return "write_QnA";
	}
	
	@RequestMapping("/qna_write")
	public String qna_write(HttpServletRequest request, Model model) {
		System.out.println("qna_write()");
		
		HttpSession httpsession = request.getSession();
		
		Dao_QnA dao = sqlSession.getMapper(Dao_QnA.class);
		dao.qnaWriteDao(request.getParameter("qnaContent"), user_id, request.getParameter("place_no"));
		
		return "write_QnA_Completed";
	}
	
	/*
	 *   21.07.06
	 *   host QnA CRUD 
	 */
	
	// hostQnA list
	@RequestMapping("/host_qna")
	public String host_qna(HttpServletRequest request, Model model) {
		System.out.println("host_qna()");
		
		HttpSession httpsession = request.getSession();
		model.addAttribute("request", request);
		
		command = new HostQnACommand();
		command.execute(sqlSession, model, httpsession);
		
		return "view_Host_QnA";
	}
	
	// hostQnA write
	@RequestMapping("/host_write_qna")
	public String host_write_qna(HttpServletRequest request, Model model) {
		System.out.println("host_write_qna()");
		
		HttpSession httpsession = request.getSession();
		
		Dao_Host_QnA dao = sqlSession.getMapper(Dao_Host_QnA.class);
		model.addAttribute("qnaDetail", dao.hostQnADetailDao(request.getParameter("qna_no")));
		
		return "Host_Write_QnA";
	}
	
	@RequestMapping("/host_qna_write")
	public String host_qna_write(HttpServletRequest request, Model model) {
		System.out.println("host_qna_write()");
		
		HttpSession httpsession = request.getSession();
		
		Dao_Host_QnA dao = sqlSession.getMapper(Dao_Host_QnA.class);
		dao.hostQnaWriteDao(request.getParameter("qna_no"), request.getParameter("qnaAnswer"));
		
		return "Host_Write_QnA_Completed";
	}

	// hostQnA modify
	@RequestMapping("/host_modify_qna")
	public String host_modify_qna(HttpServletRequest request, Model model) {
		System.out.println("host_modify_qna()");
		
		HttpSession httpsession = request.getSession();
		
		Dao_Host_QnA dao = sqlSession.getMapper(Dao_Host_QnA.class);
		model.addAttribute("qnaDetail", dao.hostQnADetailDao(request.getParameter("qna_no")));
		
		return "Host_Modify_QnA";
	}
	
	@RequestMapping("/host_qna_modify")
	public String host_qna_modify(HttpServletRequest request, Model model) {
		System.out.println("host_qna_modify()");
		
		HttpSession httpsession = request.getSession();
		
		Dao_Host_QnA dao = sqlSession.getMapper(Dao_Host_QnA.class);
		dao.hostQnaModifyDao(request.getParameter("qna_no"), request.getParameter("qnaAnswer"));
		
		return "Host_Modify_QnA_Completed";
	}
	
	// hostQnA delete
	@RequestMapping("/host_deleteCheck_qna")
	public String host_deleteCheck_qna(HttpServletRequest request, Model model) {
		System.out.println("host_deleteCheck_qna()");
		
		HttpSession httpsession = request.getSession();
		model.addAttribute("qna_no", request.getParameter("qna_no"));
		
		return "Host_Delete_QnA_check";
	}
	
	@RequestMapping("/host_qna_delete")
	public String host_qna_delete(HttpServletRequest request, Model model) {
		System.out.println("host_qna_delete()");
		
		HttpSession httpsession = request.getSession();
		
		Dao_Host_QnA dao = sqlSession.getMapper(Dao_Host_QnA.class);
		dao.hostQnaDeleteDao(request.getParameter("qna_no"));

		return "Host_Delete_QnA_Completed";
	}
	
	/*
	 * 21.07.06 hyokyeong
	 * share detail view - review
	 * 
	 */
	
	@RequestMapping("/review")
	public String reviewList(HttpServletRequest request, Model model) {
		System.out.println("reviewList()");
		
		HttpSession httpsession = request.getSession();
		model.addAttribute("request", request);
		
		command = new ReviewCommand();
		command.execute(sqlSession, model, httpsession);
		
		return "view_Review";
	}
	
}
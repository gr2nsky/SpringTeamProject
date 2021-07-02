package com.team4.ysms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.ysms.dao.Dao_myinfo_QnA;
import com.team4.ysms.dto.Dto_QnA;

public class DetailViewQnACommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		int qna_no = Integer.parseInt(request.getParameter("qna_no"));
		System.out.println(qna_no);
		
		Dao_myinfo_QnA dao = new Dao_myinfo_QnA();
		Dto_QnA dtoQna = dao.detailViewQnA(qna_no);
		
		request.setAttribute("qnaDetail", dtoQna);

	}

}

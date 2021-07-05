package com.team4.ysms.dao;

import java.util.ArrayList;

import com.team4.ysms.dto.Dto_Paging;
import com.team4.ysms.dto.Dto_QnA;

/* 
	-----------------------------
	21.07.05 hyokyeong
	-----------------------------
*/

public interface Dao_myinfo_QnA {

	// 전체 검색
	public ArrayList<Dto_QnA> myInfoQnAListDao(String user_id, int requestPage, int numOfTuplePerPage);
	
	// 게시물 수 확인
	public Dto_Paging myInfoQnaListCountDao(String user_id);
	
	// detailView
	public Dto_QnA myInfoQnADetailDao(String qna_no);
	
	// modify
	public void myInfoQnaModifyDao(String qna_no, String qnaContent);
	
	// delete
	public void myInfoQnaDeleteDao(String qna_no);
	
	
}// end line

package com.team4.ysms.dao;

import java.util.ArrayList;

import com.team4.ysms.dto.Dto_Paging;
import com.team4.ysms.dto.Dto_QnA;
	
public interface Dao_Host_QnA {
	
	// 전체 검색
	public ArrayList<Dto_QnA> hostQnaListDao(int place_no, int requestPage, int listCount);
	
	// 게시물 수 확인
	public Dto_Paging hostQnaListCountDao(int place_no);
	
	// s.title가져오기
	public Dto_QnA hostQnaShareTitleDao(int place_no);
	
	// detailView
	public Dto_QnA hostQnADetailDao(String qna_no);
	
	// write
	public void hostQnaWriteDao(String qna_no, String qnaAnswer);
	
	// modify
	public void hostQnaModifyDao(String qna_no, String qnaAnswer);
	
	// delete
	public void hostQnaDeleteDao(String qna_no);
	

}//end line


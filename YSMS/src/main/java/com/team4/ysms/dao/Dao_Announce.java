package com.team4.ysms.dao;

import java.util.ArrayList;

import com.team4.ysms.dto.Dto_Announce;

public interface Dao_Announce {
	
	// 순서는  param과 일치!
	
		// 공지사항 List 보이기
		public ArrayList<Dto_Announce> announceList(String search_title, String search_content, int requestPage, int numOfTuplePerPage);
		
		// 공지사항 쓰기
		public void announceWrite(String title, String content, String user_id);
		
		// 공지사항 내용 보이기
		public Dto_Announce announceContent_view(String str_no);
		
		// 공지사항 삭제
		public void announceDelete(String str_no);
		
		// 공지사항 수정
		public void announceModify(String title, String content, String str_no);
		
		// 공지사항 페이지 분할
		public int countTuple(String search_title, String search_content);
		
		
} //  interface Dao_Announce

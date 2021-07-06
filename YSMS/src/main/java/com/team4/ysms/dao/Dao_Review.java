package com.team4.ysms.dao;

import java.util.ArrayList;

import com.team4.ysms.dto.Dto_Paging;
import com.team4.ysms.dto.Dto_Review;

public interface Dao_Review {
	
	// 전체 검색
	public ArrayList<Dto_Review> reviewListDao(int place_no, int requestPage, int listCount);
	
	// 게시물 수 확인
	public Dto_Paging reviewListCountDao(int place_no);
	
	
}//end line
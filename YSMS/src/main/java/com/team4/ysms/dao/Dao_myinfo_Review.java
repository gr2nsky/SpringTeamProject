package com.team4.ysms.dao;

import java.util.ArrayList;

import com.team4.ysms.dto.Dto_Paging;
import com.team4.ysms.dto.Dto_Review;

public interface Dao_myinfo_Review {

	// 전체 검색
	public ArrayList<Dto_Review> myInfoReviewListDao(String user_id, int requestPage, int numOfTuplePerPage);
	
	// 게시물 수 확인
	public Dto_Paging myInfoReviewListCountDao(String user_id);
	
	// detailView
	public Dto_Review myInfoReviewDetailDao(String rentalNo);
	
	// modify
	public void myInfoReviewModifyDao(String reviewContent, String reviewScore, String reviewFilepath, String rentalNo);
	
	// delete
	public void myInfoReviewDeleteDao(String rentalNo);
	
	// write
	public void myInfoReviewWriteDao(String reviewContent, String reviewScore, String reviewFilepath, String rentalNo);
}

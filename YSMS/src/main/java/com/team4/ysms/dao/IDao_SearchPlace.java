package com.team4.ysms.dao;

import java.util.ArrayList;

import com.team4.ysms.dto.Dto_Paging;
import com.team4.ysms.dto.Dto_SearchPlace;

public interface IDao_SearchPlace {
	// 게시물 수 확인
	public Dto_Paging ListCountDao();
	
	// 전체검색 (전체 내용 보기)
	public ArrayList<Dto_SearchPlace> searchPlacelistDao(int requestPage, int numOfTuplesPerPage);
	
	// 검색 결과
	public ArrayList<Dto_SearchPlace> searchPlaceResult(String inputCategory, String inputLocation, String inputDate, int requestPage, int numOfTuplesPerPage);

	
	

}

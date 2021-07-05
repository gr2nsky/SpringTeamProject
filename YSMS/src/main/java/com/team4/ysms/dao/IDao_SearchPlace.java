package com.team4.ysms.dao;

import java.util.ArrayList;

import com.team4.ysms.dto.Dto_SearchPlace;

public interface IDao_SearchPlace {
	
	
	
	// 전체검색 (전체 내용 보기)
	public ArrayList<Dto_SearchPlace> searchPlacelistDao();
	
	// 검색 결과
	public ArrayList<Dto_SearchPlace> searchPlaceResult(String inputCategory, String inputLocation, String inputDate, int requestPage, int numOfTuplesPerPage);
	

}

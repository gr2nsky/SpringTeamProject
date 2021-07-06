package com.team4.ysms.dao;

import java.util.ArrayList;

import com.team4.ysms.dto.Dto_Paging;
import com.team4.ysms.dto.Dto_Rental;

public interface Dao_myinfo_Rental {
	
	// 예정된 예약 리스트
	public ArrayList<Dto_Rental> myinfoRentalScheduledListDao(String user_id, int requestPage, int numOfTuplePerPage);
	
	// 예정된 예약 개수
	public Dto_Paging myinfoRentalScheduledListCountDao(String user_id);
	
	// 지난 예약 리스트
	public ArrayList<Dto_Rental> myinfoRentalPreviousListDao(String user_id, int requestPage, int numOfTuplePerPage);
	
	// 지난 예약 개수
	public Dto_Paging myinfoRentalPreviousListCountDao(String user_id);
	
	
}//end line
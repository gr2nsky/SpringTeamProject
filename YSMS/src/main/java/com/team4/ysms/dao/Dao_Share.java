package com.team4.ysms.dao;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.team4.ysms.dto.Dto_Share;



public interface Dao_Share {
	
	public void writeSpaceDao(String postCode, String address1, String address2, String capacity, String category);
		
	public void writeDetailDao(String title, String user_id, String introduce, String price, String filePath, int place_no, String startTime, String endTime, String dayLimit);

	public int find_PlaceNoDao(String postCode, int capacity, int category);
	
	//  Share List 를 불러오기 위한 Method 
	public ArrayList<Dto_Share> shareListDao(String id, int requestPage, int numOfTuplePerPage);
	
	//  Paging을 위한 Method
	public int countTuple(String id);
	
	public Dto_Share shareContentDao(int contentNo);
	
	//  content.jsp 내용을 위한 Method
	public Dto_Share shareDetailDao(int place_no);

	//  삭제를 위한 Method
	public void deleteShareDao(int no);
	public void deletePlaceDao(int place_no);
	
	//  수정을 위한 Method
	public void modifyShareDao(int no, String title, String introduce, String filePath, String price, String startTime, String endTime, String dayLimit);
	
	//placeNo으로 찾는 쿼리
	public Dto_Share rDetail(int placeNo);
	
}

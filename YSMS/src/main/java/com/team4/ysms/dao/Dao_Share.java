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
	public int countTuple();
	
	public Dto_Share shareContentDao(int contentNo);
	
	//  content.jsp 내용을 위한 Method
	public Dto_Share shareDetailDao(int place_no);

	//  삭제를 위한 Method
	public void deleteShareDao(int no);
	public void deletePlaceDao(int place_no);
	
	//  수정을 위한 Method
	public void modifyShareDao(int no, String title, String introduce, String filePath, String price, String startTime, String endTime, String dayLimit);
	
	
//	
//	/*
//	 * 추가 : 2021-05-27 윤재필
//	 * placeNo으로 찾도록 추가작
//	 */
//	public Dto_Share rDetail(int placeNo) {
//		System.out.println("* * Start Method : detail * *");
//		
//		Dto_Share dto = null;
//		String query = "SELECT * FROM place, share WHERE place_no = ? AND place.no = share.place_no ";
//		
//		
//		Connection conn = null;
//		PreparedStatement psmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			psmt = conn.prepareStatement(query);
//			psmt.setInt(1, placeNo);
//			rs = psmt.executeQuery();
//			
//			if (rs.next()) {
//				int no = rs.getInt("share.no");
//				int capacity = rs.getInt("capacity");
//				int category = rs.getInt("category");
//				int price = rs.getInt("price");
//				int startTime = rs.getInt("startTime");
//				int endTime = rs.getInt("endTime");
//				String title = rs.getString("title");
//				String user_id = rs.getString("user_id");
//				String introduce = rs.getString("introduce");
//				String postCode = rs.getString("postCode");
//				String address1 = rs.getString("address1");
//				String address2 = rs.getString("address2");
//				Timestamp registrationDate = rs.getTimestamp("registrationDate");
//				Timestamp removeDate = rs.getTimestamp("removeDate");
//				String filePath = rs.getString("filePath");
//				String dayLimit = rs.getString("dayLimit");
//				int place_no = rs.getInt("place_no");
//				
//				dto = new Dto_Share(no, capacity, category, price, startTime, endTime, 
//						title, user_id, introduce, 
//						postCode, address1, address2, 
//						registrationDate, removeDate, 
//						filePath, dayLimit, place_no);
//				
//				System.out.println("  - share no : " + no);
//				System.out.println("  - place no : " + place_no);
//				System.out.println("  - user_id : " + user_id);
//				System.out.println(" Data load success : detail ");
//			}
//			
//		} catch (Exception e) { 
//			System.out.println("  - Data load Fail : detail ");
//			e.printStackTrace();
//		} finally {
//			try {
//				if (rs != null)
//					rs.close();
//				if (psmt != null)
//					psmt.close();
//				if (conn != null)
//					conn.close();
//			} catch (Exception e) {
//				System.out.println("< rs, psmt, conn close Fail>");
//				e.printStackTrace();
//			}
//		}
//		return dto;
//	}
//	
}

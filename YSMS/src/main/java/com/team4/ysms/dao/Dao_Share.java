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
	
	public Dto_Share shareDetailDao(int place_no);
	
//
//	// [Paging 03]
//	//detail view를 위해 튜플의 모든 정보를 가져온다.
//	public Dto_Share content(int contentNo) {
//		Dto_Share dto = null;
//		String query = "SELECT * FROM share where no = ?";
//
//		Connection conn = null;
//		PreparedStatement psmt = null;
//		ResultSet rs = null;
//
//		try {
//			conn = dataSource.getConnection();
//			psmt = conn.prepareStatement(query);
//			psmt.setInt(1, contentNo);
//			rs = psmt.executeQuery();
//
//			if (rs.next()) {
//				int no = rs.getInt("no");
//				String title = rs.getString("title");
//				String user_id = rs.getString("user_id");
//				String introduce = rs.getString("introduce");
//				String updateDate = rs.getString("updateDate");
//				String filePath = rs.getString("filePath");
//
//				dto = new Dto_Share(no, title, user_id, introduce, updateDate, filePath);
//				System.out.println(" Data load success : content ");
//			}
//
//		} catch (Exception e) { 
//			System.out.println(" Data load Fail : content ");
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
//	// place와 share 에 있는 모든 정보 가져오기
//	public Dto_Share detail(int placeNo) {
//		System.out.println("* * Start Method : detail * *");
//		
//		Dto_Share dto = null;
//		String query01 = "SELECT * FROM place, share WHERE place.no = share.place_no ";
//		String query02 = "AND share.no = ?";
//		
//		
//		Connection conn = null;
//		PreparedStatement psmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			psmt = conn.prepareStatement(query01+query02);
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
//							  title, user_id, introduce, 
//							  postCode, address1, address2, 
//							  registrationDate, removeDate, 
//							  filePath, dayLimit, place_no);
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
//	
//	/* Update Method : use detail dto
//	 *  - 2021.05.18 12:00
//	 *  - Park Jaewon
//	 */
//	
//	public void update(int no, String title, String introduce, String filePath, String price, String startTime, String endTime, String dayLimit) {
//		System.out.println("* * Start Method : update * *");
//		
//		System.out.println(no);
//		
//		// 시간당 가격
//		int temp00 = Integer.parseInt(price);
//		// 시작시간 : 등록자가 수정
//		int temp01 = Integer.parseInt(startTime);
//		// 종료시간 : 등록자가 수정 
//		int temp02 = Integer.parseInt(endTime);
//		
//		
//		System.out.println("  - share no : " + no);
//		System.out.println("  - Query start");
//		
//		String query01 = "UPDATE share SET ";
//		String query02 = "title = ?, introduce = ?, filePath = ? ,";
//		String query03 = "price = ?, startTime = ?, endTime = ?, dayLimit = ? ";
//		String query04 = "WHERE share.no = ?";
//
//		Connection conn = null;
//		PreparedStatement psmt = null;
//
//		try {
//			conn = dataSource.getConnection();
//			psmt = conn.prepareStatement(query01 + query02 + query03 + query04);
//
//			psmt.setString(1, title);
//			psmt.setString(2, introduce);
//			psmt.setString(3, filePath);
//			psmt.setInt(4, temp00);
//			psmt.setInt(5, temp01);
//			psmt.setInt(6, temp02);
//			psmt.setString(7, dayLimit);
//			psmt.setInt(8, no);
//
//			psmt.executeUpdate();
//			
//			System.out.println("  - changed price : " + temp00);
//			System.out.println("  - Data update Success");
//		} catch (Exception e) {
//			System.out.println("  - Data update Fail");
//			e.printStackTrace();
//		} finally {
//			try {
//				if (psmt != null)
//					psmt.close();
//				if (conn != null)
//					conn.close();
//				System.out.println("< psmt, conn close Success : update >");
//			} catch (Exception e) {
//				System.out.println("< psmt, conn close Fail : update >");
//				e.printStackTrace();
//			}
//		}
//	}	
//	
//	/* Delete command : ready to delete ( find place_no from share )
//	 *  - 2021.05.18 14:50
//	 *  - Park Jaewon
//	 */
//	
//	
//	public int Find_placeNo_From_share(int no) {
//		System.out.println("* * Start Method : Find_placeNo_From_share * *");
//		int place_no = 0;
//		
//		System.out.println("  - place_no :  " + place_no);
//		System.out.println("  - Query Start");
//			
//		String query01 = "SELECT place_no FROM share WHERE no = ? ";
//		
//		Connection conn = null;
//		PreparedStatement psmt = null;
//		ResultSet rs = null;
//			
//			try {
//				conn = dataSource.getConnection();
//				psmt = conn.prepareStatement(query01);
//				psmt.setInt(1, no);
//				rs = psmt.executeQuery();
//				
//				while (rs.next()) {
//					place_no =  rs.getInt("place_no");
//					
//					System.out.println("   - place_no (Query Comp) :  " + place_no);
//					System.out.println(" Data load success : detail ");
//				}
//				
//			} catch (Exception e) { 
//				System.out.println("  - Data load Fail : detail ");
//				e.printStackTrace();
//			} finally {
//				try {
//					if (rs != null)
//						rs.close();
//					if (psmt != null)
//						psmt.close();
//					if (conn != null)
//						conn.close();
//				} catch (Exception e) {
//					System.out.println("< rs, psmt, conn close Fail>");
//					e.printStackTrace();
//				}
//			}
//			return place_no;
//	}
//	
//	public void deleteShare(int no) {
//		System.out.println("* * Start Method : deleteShare * *");
//		String query = "DELETE FROM share WHERE no = ?";
//		
//		Connection conn = null;
//		PreparedStatement psmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			psmt = conn.prepareStatement(query);
//			psmt.setInt(1, no);
//			psmt.executeUpdate();
//			System.out.println("  - Data delete from Share : Success >");
//		} catch (Exception e) {
//			System.out.println("  - Data delete from Share : Fail >");
//			e.printStackTrace();
//		} finally {
//			try {
//				if(psmt != null)
//					psmt.close();
//				if(conn != null)
//					conn.close();
//				System.out.println("< psmt, conn close Success : deleteShare >");
//			} catch (Exception e) {
//				System.out.println("< psmt, conn close Fail : deleteShare >");
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	public void deletePlace(int place_no) {
//		System.out.println("* * Start Method : deletePlace * *");
//		
//		System.out.println("  - place_no : " + place_no);
//		
//		
//		System.out.println("  - Query start");
//		
//		String query01 = "UPDATE place SET ";
//		String query02 = "removeDate = now() ";
//		String query03 = "WHERE no = ?";
//
//		Connection conn = null;
//		PreparedStatement psmt = null;
//
//		try {
//			conn = dataSource.getConnection();
//			psmt = conn.prepareStatement(query01 + query02 + query03);
//
//			psmt.setInt(1, place_no);
//
//			psmt.executeUpdate();
//			
//			System.out.println("  - Data update Success : deletePlace // add removeDate ");
//		} catch (Exception e) {
//			System.out.println("  - Data update Fail : deletePlace // add removeDate ");
//			e.printStackTrace();
//		} finally {
//			try {
//				if (psmt != null)
//					psmt.close();
//				if (conn != null)
//					conn.close();
//				System.out.println("< psmt, conn close Success : update >");
//			} catch (Exception e) {
//				System.out.println("< psmt, conn close Fail : update >");
//				e.printStackTrace();
//			}
//		}
//	}	
	
	
	
	
}

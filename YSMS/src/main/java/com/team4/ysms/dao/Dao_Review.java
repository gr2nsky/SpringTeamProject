package com.team4.ysms.dao;

import java.util.ArrayList;

import com.team4.ysms.dto.Dto_Paging;
import com.team4.ysms.dto.Dto_Review;

public interface Dao_Review {
	
	// 전체 검색
	public ArrayList<Dto_Review> reviewListDao(int place_no, int requestPage, int listCount);
	
	// 게시물 수 확인
	public Dto_Paging reviewListCountDao(int place_no);
	
//	/*
//	 * 리뷰쓰기
//	 * DB update : rental table
//	 * 21.05.23 hyokyeong
//	 */
//	
//	// query
////	UPDATE rental 
////	SET reviewContent = ?, reviewScore = ?, reviewSubmitDate = now(), reviewUpdateDate = now(), filePath = ?
////	WHERE no = ?;
//	public void writeReview(int rentalNo, String reviewContent, String reviewFilePath, int reviewScore) {
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		
//		try {
//			connection = dataSource.getConnection();
//			
//			String query1 = "UPDATE rental SET reviewContent = ?, reviewScore = ?, filePath = ?, ";
//			String query2 = "reviewSubmitDate = now(), reviewUpdateDate = now(), reviewRemoveDate = null WHERE no = ? ";
//			
//			preparedStatement = connection.prepareStatement(query1+query2);
//			preparedStatement.setString(1, reviewContent);
//			preparedStatement.setInt(2, reviewScore);
//			preparedStatement.setString(3, reviewFilePath);
//			preparedStatement.setInt(4, rentalNo);
//			
//			preparedStatement.executeUpdate();
//			
//		
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(preparedStatement != null) preparedStatement.close();
//				if(connection != null) connection.close();
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	} // End writeReview **********
//	
//	
	
//	// review modify
//	public void modifyReview(int rentalNo, String reviewContent, String reviewFilePath, int reviewScore) {
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		
//		try {
//			connection = dataSource.getConnection();
//			
//			String query1 = "UPDATE rental SET reviewContent = ?, reviewScore = ?, filePath = ?, ";
//			String query2 = "reviewUpdateDate = now() WHERE no = ? ";
//			
//			preparedStatement = connection.prepareStatement(query1+query2);
//			preparedStatement.setString(1, reviewContent);
//			preparedStatement.setInt(2, reviewScore);
//			preparedStatement.setString(3, reviewFilePath);
//			preparedStatement.setInt(4, rentalNo);
//			
//			preparedStatement.executeUpdate();
//			
//		
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(preparedStatement != null) preparedStatement.close();
//				if(connection != null) connection.close();
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	} // End modify Review **********
//	

	
}//end line
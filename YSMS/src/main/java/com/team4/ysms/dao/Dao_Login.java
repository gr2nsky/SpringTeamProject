/*
 * 2021 5 24
 * login_try 에서 추가적인 유저정보 주입하도록 수정
 * 
 */

package com.team4.ysms.dao;

import com.team4.ysms.dto.Dto_Login;

public interface Dao_Login {
	
	public Dto_Login tryToLogin(String inputID, String inputPW);
	public Dto_Login IDdupleCheck(String inputID);
	

//	public String IDdupleCheck(String inputID) {
//		String result = "unuseable";
//		String query = "SELECT id FROM user WHERE id = ?";
//
//		Connection conn = null;
//		PreparedStatement psmt = null;
//		ResultSet rs = null;
//
//		try {
//			conn = dataSource.getConnection();
//			psmt = conn.prepareStatement(query);
//			psmt.setString(1, inputID);
//			rs = psmt.executeQuery();
//
//			if (!rs.next()) {
//				result = "useable";
//				System.out.println("this id possible to use.");
//			} else {
//				result = "unuseable";
//				System.out.println("this id is already in use.");
//			}
//		} catch (Exception e) {
//			System.out.println("duplecheck fail");
//			e.printStackTrace();
//		} finally {
//			try {
//				if (rs != null)
//					rs.close();
//				if (psmt != null)
//					psmt.close();
//				if (conn != null)
//					conn.close();
//				System.out.println("< rs, psmt, conn close success>");
//			} catch (Exception e) {
//				System.out.println("< rs, psmt, conn close Fail>");
//			}
//		}
//
//		return result;
//	}
//
//	public String emailDupleCheck(String inputEmail) {
//		String result = "unuseable";
//		String query = "SELECT email FROM user WHERE email = ?";
//
//		Connection conn = null;
//		PreparedStatement psmt = null;
//		ResultSet rs = null;
//
//		try {
//			conn = dataSource.getConnection();
//			psmt = conn.prepareStatement(query);
//			psmt.setString(1, inputEmail);
//			rs = psmt.executeQuery();
//
//			if (!rs.next()) {
//				result = "useable";
//				System.out.println("this email possible to use.");
//			} else {
//				result = "unuseable";
//				System.out.println("this email is already in use.");
//			}
//		} catch (Exception e) {
//			System.out.println("email duplecation check fail");
//			e.printStackTrace();
//		} finally {
//			try {
//				if (rs != null)
//					rs.close();
//				if (psmt != null)
//					psmt.close();
//				if (conn != null)
//					conn.close();
//				System.out.println("< rs, psmt, conn close success>");
//			} catch (Exception e) {
//				System.out.println("< rs, psmt, conn close Fail>");
//			}
//		}
//
//		return result;
//	}
//
//	public String signUp(String id, String name, String pw, String email, String phone, int status, String birthday,
//			String filePath) {
//
//		String query1 = "INSERT INTO user (id, name, pw, email, phone, status, birthday, filePath, signDate) ";
//		String query2 = "VALUES (?, ?, ?, ?, ?, ?, ?, ?, now())";
//		String query = query1 + query2;
//		String result = "false";
//
//		Connection conn = null;
//		PreparedStatement psmt = null;
//
//		try {
//			conn = dataSource.getConnection();
//			psmt = conn.prepareStatement(query);
//
//			psmt.setString(1, id);
//			psmt.setString(2, name);
//			psmt.setString(3, pw);
//			psmt.setString(4, email);
//			psmt.setString(5, phone);
//			psmt.setInt(6, status);
//			psmt.setString(7, birthday);
//			psmt.setString(8, filePath);
//			psmt.executeUpdate();
//			result = "true";
//		} catch (Exception e) {
//			System.out.println("<data insert Fail>");
//			e.printStackTrace();
//		} finally {
//			try {
//				if (psmt != null)
//					psmt.close();
//				if (conn != null)
//					conn.close();
//			} catch (Exception e) {
//				System.out.println("< psmt, conn close Fail>");
//				e.printStackTrace();
//			}
//		}
//		return result;
//	}
//
//	public String findAccount(String inputedEmail) {
//		String query = "SELECT id FROM user WHERE email = ?";
//		String userID = null;
//
//		Connection conn = null;
//		PreparedStatement psmt = null;
//		ResultSet rs = null;
//
//		try {
//			conn = dataSource.getConnection();
//			psmt = conn.prepareStatement(query);
//			psmt.setString(1, inputedEmail);
//			rs = psmt.executeQuery();
//
//			if (rs.next()) {
//				userID = rs.getString("id");
//				System.out.println("findAccount success");
//			}
//		} catch (Exception e) {
//			System.out.println("findAccount fail");
//			e.printStackTrace();
//		} finally {
//			try {
//				if (rs != null)
//					rs.close();
//				if (psmt != null)
//					psmt.close();
//				if (conn != null)
//					conn.close();
//				System.out.println("< rs, psmt, conn close success>");
//			} catch (Exception e) {
//				System.out.println("< rs, psmt, conn close Fail>");
//			}
//		}
//
//		return userID;
//	}
//
//	public String findAccount(String inputedEmail, String inputedID) {
//		String query = "SELECT pw FROM user WHERE email = ? AND id = ?";
//		String userPW = null;
//
//		Connection conn = null;
//		PreparedStatement psmt = null;
//		ResultSet rs = null;
//
//		try {
//			conn = dataSource.getConnection();
//			psmt = conn.prepareStatement(query);
//			psmt.setString(1, inputedEmail);
//			psmt.setString(2, inputedID);
//			rs = psmt.executeQuery();
//
//			if (rs.next()) {
//				userPW = rs.getString("pw");
//				System.out.println("findAccount success");
//			}
//		} catch (Exception e) {
//			System.out.println("findAccount fail");
//			e.printStackTrace();
//		} finally {
//			try {
//				if (rs != null)
//					rs.close();
//				if (psmt != null)
//					psmt.close();
//				if (conn != null)
//					conn.close();
//				System.out.println("< rs, psmt, conn close success>");
//			} catch (Exception e) {
//				System.out.println("< rs, psmt, conn close Fail>");
//			}
//		}
//
//		return userPW;
//	}

}
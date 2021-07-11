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
	public Dto_Login emailDupleCheck(String inputEmail);
	public int signUp(String id, String name, String pw, String email, String phone, int status, String birthday,String filePath);



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
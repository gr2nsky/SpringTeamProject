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
	public int signUp(String id, String name, String pw, String email, String phone, int status, String birthday, String filePath);
	public Dto_Login findAccountID(String inputedEmail);
	public Dto_Login findAccountPW(String inputedEmail, String inputedID);

}
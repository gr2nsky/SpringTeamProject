package com.team4.ysms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.team4.ysms.common.LoginedUserInfo;
import com.team4.ysms.dto.Dto_Login;
import com.team4.ysms.dto.Dto_SignUp;

public interface Dao_MyInfo_UpdateProfile {
	
	public Dto_Login getUserInfo(String inputId);
	public int updateUserInfo1(String id, String phone, String filePath);
	public int updateUserInfo2(String id, String pw, String phone, String filePath);

}

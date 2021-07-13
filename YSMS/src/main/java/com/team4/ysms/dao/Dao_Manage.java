package com.team4.ysms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.team4.ysms.dto.Dto_SignUp;

public interface Dao_Manage {
	
	public int countTuple();
	public ArrayList<Dto_SignUp> getAllUserList(int requestPage, int numOfTuplePerPage);
	public int deleteUsers(String selectedUsers);
	public Dto_SignUp getUserInfo(int no);
		
}

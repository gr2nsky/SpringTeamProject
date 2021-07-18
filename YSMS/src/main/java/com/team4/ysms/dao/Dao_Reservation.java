package com.team4.ysms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.team4.ysms.common.LoginedUserInfo;
import com.team4.ysms.dto.Dto_Payment;
import com.team4.ysms.dto.Dto_Reservation_rental;
import com.team4.ysms.dto.Dto_Reservation_rentalDetail;

public interface Dao_Reservation {

	public ArrayList<Dto_Reservation_rentalDetail> refineSharesDetail(int place_no);
	public ArrayList<Dto_Reservation_rental> refineShares(int place_no);
	public int payment(String checkInDate, int startTime, int endTime, int price, String user_id, 
			int place_no, String resName, String resPhone, String resEmail, int resCapacity);
	public Dto_Payment findRental(int no);
	
}


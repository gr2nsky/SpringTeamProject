package com.team4.ysms.dto;

import java.sql.Timestamp;

public class Dto_Reservation_rental {
	// 누가 예약했는지는 중요하지 않으므로 렌탈 테이블 데이터만 가져오도록 한다.
	// 필요한것 : 예약날자, 시작, 이용시간(종료시간 - 시작시간 : 이건 dao가 계산),

	Timestamp checkInDate;
	int startTime;
	int endTime;
	int month;
	int date;
	int usingTime;
	
	public Dto_Reservation_rental(){ }
	
	
	
	public Dto_Reservation_rental(Timestamp checkInDate, int startTime, int endTime) {
		this.checkInDate = checkInDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}



	public Dto_Reservation_rental(int month, int date, int startTime, int usingTime) {
		this.month = month;
		this.date = date;
		this.startTime = startTime;
		this.usingTime = usingTime;
	}
	
	

	public Timestamp getCheckInDate() {
		return checkInDate;
	}



	public void setCheckInDate(Timestamp checkInDate) {
		this.checkInDate = checkInDate;
	}



	public int getEndTime() {
		return endTime;
	}



	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}



	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getUsingTime() {
		return usingTime;
	}

	public void setUsingTime(int usingTime) {
		this.usingTime = usingTime;
	}
	
	public String getInfo() {
		return month + "/" + date + " : " + startTime + " 부터 " + usingTime + "시간";
	}
}

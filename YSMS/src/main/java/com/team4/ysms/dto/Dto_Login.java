package com.team4.ysms.dto;

import java.sql.Timestamp;

public class Dto_Login {
	
	int no;
	String id;
	String name;
	String email;
	String phone;
	int status;
	Timestamp birthday;
	Timestamp filePath;
	Timestamp signDate;
	Timestamp cancelDate;
	
	public Dto_Login() {
		
	}
	
	public Dto_Login(String id) {
		this.id = id;
	}


	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getBirthday() {
		return birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public Timestamp getFilePath() {
		return filePath;
	}

	public void setFilePath(Timestamp filePath) {
		this.filePath = filePath;
	}

	public Timestamp getSignDate() {
		return signDate;
	}

	public void setSignDate(Timestamp signDate) {
		this.signDate = signDate;
	}

	public Timestamp getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Timestamp cancelDate) {
		this.cancelDate = cancelDate;
	}
	
	
	
}

package com.team4.ysms.dto;


public class Dto_Sign {
	String id;
	String name;
	String pw;
	String email;
	String phone;
	int status;
	String birthday;
	String filePath;
	String signDate;
	String cancelDate;
	
	public Dto_Sign(String id, String name, String pw, String email, String phone, int status, String birthday,
			String filePath, String signDate) {
		super();
		this.id = id;
		this.name = name;
		this.pw = pw;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.birthday = birthday;
		this.filePath = filePath;
		this.signDate = signDate;
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

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}
	
	
}

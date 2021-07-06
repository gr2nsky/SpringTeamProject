package com.team4.ysms.dto;

import java.util.Date;

public class Dto_Review {

	
	int rentalNo;
	String rentalDate;
	String checkInDate;
	int rentalStartTime;
	int rentalEndTime;
	String rentalCancellationDate;
	int rentalPrice;
	String rentalUser_id;
	int rentalPlace_no;
	String reviewContent;
	int reviewScore;
	String reviewSubmitDate;
	String reviewUpdateDate;
	String reviewRemoveDate;
	String reviewFilePath;
	String userFilePath;
	String reviewPlaceName ;

	
	// Constructor
	public Dto_Review() {
		// TODO Auto-generated constructor stub
	}
	
	// share-detail Review List
	public Dto_Review(String userFilePath, String rentalUser_id, String reviewContent,int reviewScore, String reviewUpdateDate,
			String reviewRemoveDate, String reviewFilePath) {
		super();
		this.rentalUser_id = rentalUser_id;
		this.reviewContent = reviewContent;
		this.reviewScore = reviewScore;
		this.reviewUpdateDate = reviewUpdateDate;
		this.reviewRemoveDate = reviewRemoveDate;
		this.reviewFilePath = reviewFilePath;
		this.userFilePath = userFilePath;
	}
	
	

	// myinfo review list
	public Dto_Review(int rentalNo, String reviewPlaceName, String reviewContent, int reviewScore, String reviewUpdateDate, String reviewFilePath) {
		super();
		this.rentalNo = rentalNo;
		this.reviewContent = reviewContent;
		this.reviewScore = reviewScore;
		this.reviewUpdateDate = reviewUpdateDate;
		this.reviewFilePath = reviewFilePath;
		this.reviewPlaceName = reviewPlaceName;
	}

	// review Detail
	public Dto_Review(String reviewPlaceName, String reviewContent, int reviewScore, String reviewUpdateDate, String reviewFilePath) {
		super();
		this.reviewContent = reviewContent;
		this.reviewScore = reviewScore;
		this.reviewUpdateDate = reviewUpdateDate;
		this.reviewFilePath = reviewFilePath;
		this.reviewPlaceName = reviewPlaceName;
	}


	public int getRentalNo() {
		return rentalNo;
	}


	public void setRentalNo(int rentalNo) {
		this.rentalNo = rentalNo;
	}


	public String getRentalDate() {
		return rentalDate;
	}


	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}


	public String getCheckInDate() {
		return checkInDate;
	}


	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}


	public int getRentalStartTime() {
		return rentalStartTime;
	}


	public void setRentalStartTime(int rentalStartTime) {
		this.rentalStartTime = rentalStartTime;
	}


	public int getRentalEndTime() {
		return rentalEndTime;
	}


	public void setRentalEndTime(int rentalEndTime) {
		this.rentalEndTime = rentalEndTime;
	}


	public String getRentalCancellationDate() {
		return rentalCancellationDate;
	}


	public void setRentalCancellationDate(String rentalCancellationDate) {
		this.rentalCancellationDate = rentalCancellationDate;
	}


	public int getRentalPrice() {
		return rentalPrice;
	}


	public void setRentalPrice(int rentalPrice) {
		this.rentalPrice = rentalPrice;
	}


	public String getRentalUser_id() {
		return rentalUser_id;
	}


	public void setRentalUser_id(String rentalUser_id) {
		this.rentalUser_id = rentalUser_id;
	}


	public int getRentalPlace_no() {
		return rentalPlace_no;
	}


	public void setRentalPlace_no(int rentalPlace_no) {
		this.rentalPlace_no = rentalPlace_no;
	}


	public String getReviewContent() {
		return reviewContent;
	}


	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}


	public int getReviewScore() {
		return reviewScore;
	}


	public void setReviewScore(int reviewScore) {
		this.reviewScore = reviewScore;
	}


	public String getReviewSubmitDate() {
		return reviewSubmitDate;
	}


	public void setReviewSubmitDate(String reviewSubmitDate) {
		this.reviewSubmitDate = reviewSubmitDate;
	}


	public String getReviewUpdateDate() {
		return reviewUpdateDate;
	}


	public void setReviewUpdateDate(String reviewUpdateDate) {
		this.reviewUpdateDate = reviewUpdateDate;
	}


	public String getReviewRemoveDate() {
		return reviewRemoveDate;
	}


	public void setReviewRemoveDate(String reviewRemoveDate) {
		this.reviewRemoveDate = reviewRemoveDate;
	}


	public String getReviewFilePath() {
		return reviewFilePath;
	}


	public void setReviewFilePath(String reviewFilePath) {
		this.reviewFilePath = reviewFilePath;
	}


	public String getUserFilePath() {
		return userFilePath;
	}


	public void setUserFilePath(String userFilePath) {
		this.userFilePath = userFilePath;
	}


	public String getReviewPlaceName() {
		return reviewPlaceName;
	}


	public void setReviewPlaceName(String reviewPlaceName) {
		this.reviewPlaceName = reviewPlaceName;
	}
	
	
}

	
	
package com.team4.ysms.dto;

public class Dto_Paging {
	
	int totalPage;
	
	public Dto_Paging() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Dto_Paging(int totalPage) {
		super();
		this.totalPage = totalPage;
	}



	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	

}

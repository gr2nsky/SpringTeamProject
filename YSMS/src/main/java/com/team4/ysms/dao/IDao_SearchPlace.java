package com.team4.ysms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.team4.ysms.dto.Dto_Paging;
import com.team4.ysms.dto.Dto_SearchPlace;

public interface IDao_SearchPlace {
	// 게시물 수 확인
	public Dto_Paging ListCountDao();
	
	// 전체검색 (전체 내용 보기)
	public ArrayList<Dto_SearchPlace> searchPlacelistDao(int requestPage, int numOfTuplesPerPage);
	
	// 검색 결과
	public ArrayList<Dto_SearchPlace> searchPlaceResult(String inputCategory, String inputLocation, String inputDate, int offset, int numOfTuplesPerPage);
	public Dto_Paging searchPlaceResultPaging(String inputCategory, String inputLocation);
	
	// 검색 결과(카테고리 = 전체)
	public ArrayList<Dto_SearchPlace> searchPlaceResultAll(String inputCategory, String inputLocation, String inputDate, int offset, int numOfTuplesPerPage);
	public Dto_Paging searchPlaceResulAllPaging(String inputCategory, String inputLocation);

	//검색 결과 목록 출력
//	public ArrayList<Dto_SearchPlace> SearchLocation(String inputCategory, String inputLocation, String inputDate, int requestPage, int numOfTuplesPerPage){ // 검색
//	public ArrayList<Dto_SearchPlace> searchPlaceResult(String inputCategory, String inputLocation, String inputDate, int requestPage, int numOfTuplesPerPage){ // 검색
//		ArrayList<Dto_SearchPlace> dtos = new ArrayList<Dto_SearchPlace>();
//		Connection connection = null;// java.sql
//		PreparedStatement preparedStatement = null; // java.sql
//		ResultSet resultSet = null; // java.sql	
//		
//		String useCategory = inputCategory;
//		String useDate = inputDate;
//		
//		System.out.println("<<<SearchLocation>>>");
//		System.out.println("inputed data : " + inputCategory + ", " + inputLocation + ", " + requestPage + ", " + numOfTuplesPerPage);
//		
//		try {
//			connection = dataSource.getConnection(); // 연결
//			
//			
//			String search1 = "SELECT DISTINCT s.no, s.filePath, p.category, s.title, p.address1, p.address2, s.price, s.daylimit ";
//			String search2 = "FROM share as s, place as p, qna_review as q ";
//			String search3 = "WHERE p.no = s.place_no and p.removeDate is null ";
//			String search4 = "";
//			if(useCategory.equals("0")) {
//				useCategory = "is not null"; // null값이 아닌 값을 전부
//				search4 = "and p.address1 like '%" + inputLocation + "%' and p.category " + useCategory +" limit ?, ?";
//						
//			} else {
//				search4 = "and p.address1 like '%" + inputLocation + "%' and p.category = '" + useCategory +"' limit ?, ?";
//			}
//			System.out.println("useCategory : " + useCategory);
//		String search = search1 + search2 + search3 + search4;
//			
//			int offset = requestPage-1;
//			System.out.println(requestPage + "페이지 출력(dao)");
//			
//		preparedStatement = connection.prepareStatement(search); // 쿼리문 연결
//			
//			// 0을 나누면 에러가 발생하므로 예외처리
//			if(offset == 0) {
//			preparedStatement.setInt(1, offset);
//			}else {
//				preparedStatement.setInt(1, offset * numOfTuplesPerPage);
//			}
//			preparedStatement.setInt(2, numOfTuplesPerPage);
//			
//			resultSet = preparedStatement.executeQuery(); // 쿼리문 실행
//			
//		while(resultSet.next()) {
//				
//			String filePath = resultSet.getString("s.filePath"); //이미지파일 불러오기
//			String category = resultSet.getString("p.category");//category 숫자값 -> 문자로 변환
//			
//			switch(category) {
//				case "1" :
//					category = "휴식";
//				break;
//				case "2" :
//				category = "파티";
//					break;
//				case "3" : 
//					category = "공부";
//					break;
//				case "4" :
//					category = "회의";
//					break;
//				default : 
//					break;
//				
//				}
//				int no = resultSet.getInt("s.no");
//				String title = resultSet.getString("s.title");
//				String address1 = resultSet.getString("p.address1");
//				String address2 = resultSet.getString("p.address2");
//				int price = resultSet.getInt("s.price");
//				String dayLimit = resultSet.getString("s.dayLimit");
//				
//				
//					
//				Dto_SearchPlace dto = new Dto_SearchPlace(no, filePath, category, title, address1,address2, price, dayLimit); // ArrayList 생성
//				System.out.println("dto : "  + filePath  + ", "+ category + ", " +title + ", " + address1 + ", " + address2 + "," + price + "," + dayLimit);
//				dtos.add(dto); // ArrayList에 저장(메모리)
//			
//			}
//			System.out.println("success search");
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				if(resultSet!=null) resultSet.close(); // 실행이 잘되었다면, 실행종료
//				if(preparedStatement!=null) preparedStatement.close(); // 실행이 잘되었다면, 실행종료
//				if(connection != null) connection.close(); //실행이 잘되었다면, 실행종료
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//		return dtos;
//		
//	}
		
}




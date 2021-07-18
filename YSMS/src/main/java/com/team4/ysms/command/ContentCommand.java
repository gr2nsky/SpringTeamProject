package com.team4.ysms.command;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.ui.Model;
import com.team4.ysms.common.FilePath;
import com.team4.ysms.dao.Dao_Reservation;
import com.team4.ysms.dao.Dao_Share;
import com.team4.ysms.dto.Dto_Refine_rentalDetail;
import com.team4.ysms.dto.Dto_Reservation_rentalDetail;
import com.team4.ysms.dto.Dto_Share;


public class ContentCommand implements SCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		FilePath file = new FilePath();

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		Dao_Share dao = sqlSession.getMapper(Dao_Share.class);
		Dto_Share dto = dao.shareDetailDao(no);
		
		System.out.println(no);
		if(dto.getFilePath() != null) {
			String filePath = dto.getFilePath();
			String fileName = filePath.substring(file.Image.length());
			model.addAttribute("fileName", fileName);
		}
		
		model.addAttribute("DETAIL", dto);
		System.out.println("@@@@@@@@@@@@@dayLimit : " + dto.getDayLimit());
		model.addAttribute("dayLimit", dto.getDayLimit());
		System.out.println(no);	
		

		int sNo = no;
		Dto_Share detail = dto;
		
		DecimalFormat df = new DecimalFormat("00");
        Calendar currentCalendar = Calendar.getInstance();
        int month  = Integer.parseInt(df.format(currentCalendar.get(Calendar.MONTH) + 1));
		
        //dto를 이번달과 다음달로 분류
		ArrayList<Dto_Reservation_rentalDetail> thisMonthDtos = new ArrayList<Dto_Reservation_rentalDetail>();
		ArrayList<Dto_Reservation_rentalDetail> nextMonthDtos = new ArrayList<Dto_Reservation_rentalDetail>();
		
		// 예약일자,Dto_Reservation_rental 쌍의 LinkedHashMap : 순서를 보장한다. 정렬후 사용하므로 순서 보장이 되는쪽이 효율
		LinkedHashMap<Integer, Dto_Refine_rentalDetail> thisMonthResMap = 
				new LinkedHashMap<Integer, Dto_Refine_rentalDetail>();
		LinkedHashMap<Integer, Dto_Refine_rentalDetail> nextMonthResMap = 
				new LinkedHashMap<Integer, Dto_Refine_rentalDetail>();
		
		//map을 jsonArray로 변환
		JSONArray thisMonthResData = new JSONArray();
		JSONArray nextMonthResData = new JSONArray();
		
		//예약이 존재하는 날자 저
		ArrayList<Integer> thisMonthResDate = new ArrayList<Integer>();
		ArrayList<Integer> nextMonthResDate = new ArrayList<Integer>();

		// 성공적으로 로드를 못할 때도 존재하니 no 일치하는지 검사
		if (sNo == detail.getNo()) {
			detail.printAll();
			Dao_Reservation resDao = sqlSession.getMapper(Dao_Reservation.class);
			ArrayList<Dto_Reservation_rentalDetail> preResDtos = resDao.refineSharesDetail(detail.getPlace_no());
			//DAO에 연산작업을 시켜놓은 바람에 작업 한단계를 더 추가 - 2021-07-18
			ArrayList<Dto_Reservation_rentalDetail> resDtos = new ArrayList<Dto_Reservation_rentalDetail>();
			for (Dto_Reservation_rentalDetail preDto : preResDtos) {
				Timestamp checkInDate = preDto.getCheckInDate();
				System.out.println("checkInDate : " + checkInDate);
				int startTime = preDto.getStartTime();
				int endTime = preDto.getEndTime();

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(checkInDate);
				
				int tMonth = calendar.get(Calendar.MONTH) + 1;
				int tDate = calendar.get(Calendar.DATE);
				int usingTime = endTime - startTime;
				
				Dto_Reservation_rentalDetail useDto = 
						new Dto_Reservation_rentalDetail(preDto.getNo(), preDto.getResName(), preDto.getResEmail(), preDto.getResPhone(), 
								preDto.getResCapacity(), preDto.getResPrice(), tMonth, tDate, startTime, usingTime);
				resDtos.add(useDto);
				System.out.println(month + " / " + tDate + " : " + startTime + " + " + usingTime);
			}
			
			//dto를 이번달과 다음달로 분류
			for(Dto_Reservation_rentalDetail resDto : resDtos) {
				System.out.println("이번달 : " + month + "   dto의 달 : " + resDto.getMonth());
				if (resDto.getMonth() == month) {
					thisMonthDtos.add(resDto);
				} else {
					nextMonthDtos.add(resDto);
				}
			}
			
			//각 dtos에서 예약일자 추출해 하나의 날자로 통합
			for (Dto_Reservation_rentalDetail resDto : thisMonthDtos) {
				Dto_Refine_rentalDetail refineData;
				//예약일자가 map에 존재하지 않는다면 추가
				if (thisMonthResMap.containsKey(resDto.getDate()) == false) {
					//map에 넣을 dto 생성
					refineData = new Dto_Refine_rentalDetail();
				} else {
					//이 경우는 이미 map에 존재하므로, 불러온다
					refineData = thisMonthResMap.get(resDto.getDate());
				}
				refineData.insertData(resDto.getNo(), resDto.getResName(), resDto.getResEmail(), resDto.getResPhone(),
						resDto.getResCapacity(), resDto.getResPrice(), detail.getStartTime(), resDto.getStartTime(), resDto.getUsingTime());
				//해당 키값의 벨류를 갱신시켜준다. (put은 갱신 및 추가 둘다 사용 가능
				thisMonthResMap.put(resDto.getDate(), refineData);
			}
			
			for (Dto_Reservation_rentalDetail resDto : nextMonthDtos) {
				Dto_Refine_rentalDetail refineData;
				//예약일자가 map에 존재하지 않는다면 추가
				if (nextMonthResMap.containsKey(resDto.getDate()) == false) {
					//map에 넣을 dto 생성
					refineData = new Dto_Refine_rentalDetail();
				} else {
					//이 경우는 이미 map에 존재하므로, 불러온다
					refineData = nextMonthResMap.get(resDto.getDate());
				}
				refineData.insertData(resDto.getNo(), resDto.getResName(), resDto.getResEmail(), resDto.getResPhone(),
						resDto.getResCapacity(), resDto.getResPrice(), detail.getStartTime(), resDto.getStartTime(), resDto.getUsingTime());
				//해당 키값의 벨류를 갱신시켜준다. (put은 갱신 및 추가 둘다 사용 가능
				nextMonthResMap.put(resDto.getDate(), refineData);
			}
			
			//map에서 json으로 변환
			for(Map.Entry<Integer, Dto_Refine_rentalDetail> refineData :thisMonthResMap.entrySet()) {
				Dto_Refine_rentalDetail resDto = refineData.getValue();
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("date", refineData.getKey());
				jsonObject.put("no", resDto.getNo());
				jsonObject.put("resName", resDto.getResName());
				jsonObject.put("resEmail", resDto.getResEmail());
				jsonObject.put("resPhone", resDto.getResPhone());
				jsonObject.put("resCapacity", resDto.getResCapacity());
				jsonObject.put("resPrice", resDto.getResPrice());
				jsonObject.put("startNum", resDto.getStartNum());
				jsonObject.put("shareTime", resDto.getShareTime());
				jsonObject.put("totalShareTime", resDto.getTotalShareTime());
				thisMonthResData.put(jsonObject);
				
				if( 0 < resDto.getTotalShareTime()) {
					System.out.println("java 예약 있는날: " + refineData.getKey());
					thisMonthResDate.add(refineData.getKey());
				}
			}
			
			for(Map.Entry<Integer, Dto_Refine_rentalDetail> refineData :nextMonthResMap.entrySet()) {
				Dto_Refine_rentalDetail resDto = refineData.getValue();
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("date", refineData.getKey());
				jsonObject.put("no", resDto.getNo());
				jsonObject.put("resName", resDto.getResName());
				jsonObject.put("resEmail", resDto.getResEmail());
				jsonObject.put("resPhone", resDto.getResPhone());
				jsonObject.put("resCapacity", resDto.getResCapacity());
				jsonObject.put("resPrice", resDto.getResPrice());
				jsonObject.put("startNum", resDto.getStartNum());
				jsonObject.put("shareTime", resDto.getShareTime());
				jsonObject.put("totalShareTime", resDto.getTotalShareTime());
				thisMonthResData.put(jsonObject);
				
				if( 0 < resDto.getTotalShareTime()) {
					System.out.println("java 예약 있는날: " + refineData.getKey());
					nextMonthResDate.add(refineData.getKey());
				}
			}
			
			// dto를 이번달과 다음달로 나눠서 전송
			model.addAttribute("thisMonthResData", thisMonthResData);
			model.addAttribute("nextMonthResData", nextMonthResData);
			
			Integer[] thisMonthResDateList = thisMonthResDate.toArray(new Integer[thisMonthResDate.size()]);
			Integer[] nextMonthResDateList = nextMonthResDate.toArray(new Integer[nextMonthResDate.size()]);
			
			model.addAttribute("thisMonthResDateList", thisMonthResDateList);
			model.addAttribute("nextMonthResDateList", nextMonthResDateList);
			model.addAttribute("error", 0);
			System.out.println("place no이 동일하므로 다음작업을 수행합니다.");
		} else {
			System.out.println("시스템 오류. 데이터를 로드하는데 문제가 발생했습니다.");
			//error 전송
			model.addAttribute("error", 1);
		}
	}


}

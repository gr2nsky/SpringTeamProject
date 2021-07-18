package com.team4.ysms.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team4.ysms.common.PaymentInfo;
import com.team4.ysms.common.ReservationInfo;
import com.team4.ysms.dao.Dao_Reservation;
import com.team4.ysms.dto.Dto_Payment;

public class PaymentSubmitCommand implements SCommand{

	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int pNo = Integer.parseInt(request.getParameter("pNo"));
		Dto_Payment dto = PaymentInfo.dto_payment;
		
		if(pNo !=dto.getPlace_no()) {
			System.out.println("치명적인 오류. 결제정보가 올바르지 않습니다.");
			return;
		}

		Dao_Reservation dao = sqlSession.getMapper(Dao_Reservation.class);
		int result = dao.payment(dto.getCheckInDate(), dto.getStartTime(), dto.getEndTime(), 
				dto.getPrice(), dto.getUser_id(), dto.getPlace_no(), dto.getResName(), dto.getResPhone(), dto.getResEmail(), dto.getResCapacity());

		if(result == 1) {
			System.out.println("결재정보 등록성공");
		}else{
			System.out.println("결재정보 등록실패");
		}
		
		model.addAttribute("paymentResult", result);
		model.addAttribute("paymentInfo", dto);
		model.addAttribute("DETAIL", ReservationInfo.detail);
	}

}

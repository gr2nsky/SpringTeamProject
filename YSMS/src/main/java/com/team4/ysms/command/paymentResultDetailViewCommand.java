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
import com.team4.ysms.dao.Dao_Share;
import com.team4.ysms.dto.Dto_Payment;
import com.team4.ysms.dto.Dto_Share;

public class paymentResultDetailViewCommand implements SCommand {
	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int rentalNo = Integer.parseInt(request.getParameter("hn"));
		System.out.println("paymentResultDetailViewCommand : " + rentalNo);
		
		
		Dao_Reservation resDao = sqlSession.getMapper(Dao_Reservation.class);
		Dto_Payment payDto = resDao.findRental(rentalNo);
		PaymentInfo.dto_payment = payDto;
		
		Dao_Share detailDao = sqlSession.getMapper(Dao_Share.class);
		Dto_Share detailDto = detailDao.rDetail(payDto.getPlace_no());
		ReservationInfo.detail = detailDto;
		
		
		model.addAttribute("DETAIL", detailDto);
		model.addAttribute("DTO", payDto);
	}

}

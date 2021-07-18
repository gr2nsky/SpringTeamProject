package com.team4.ysms.command;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team4.ysms.common.PaymentInfo;
import com.team4.ysms.common.ReservationInfo;
import com.team4.ysms.dto.Dto_Payment;
import com.team4.ysms.dto.Dto_Share;

public class PaymentResultCheckCommand implements SCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		Dto_Share detail = ReservationInfo.detail;
		Dto_Payment dto = PaymentInfo.dto_payment;
		
		model.addAttribute("DETAIL", detail);
		model.addAttribute("DTO", dto);
	}

}

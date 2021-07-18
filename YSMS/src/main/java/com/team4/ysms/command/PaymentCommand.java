/*
 * create 20210524 - 윤재
 * payment.jsp에 알맞은 정보전달을 위한 command
 * 
 */
package com.team4.ysms.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team4.ysms.common.LoginedUserInfo;
import com.team4.ysms.common.PaymentInfo;
import com.team4.ysms.dto.Dto_Payment;

public class PaymentCommand implements SCommand {


	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String productName = request.getParameter("productName");
		String userName = request.getParameter("userName");
		String userPhone = request.getParameter("userPhone");
		String userEmail = request.getParameter("userEmail");
		String selectedDate = request.getParameter("selectedDate");
		String selectedTime = request.getParameter("selectedTime");
		int placeNo = Integer.parseInt(request.getParameter("placeNo"));
		int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		
		String[] startAndEndTime = selectedTime.replace(" ", "").split("~");
		String[] startTimes = startAndEndTime[0].split(":");
		String[] endTimes = startAndEndTime[1].split(":");
		int startTime = Integer.parseInt(startTimes[0]);
		int endTime = Integer.parseInt(endTimes[0]);
		System.out.println("start : " + startTime + ", end :  " + endTime);
		System.out.println(selectedDate + ", " + startTime + ", " + endTime + ", " + totalPrice + ", " + LoginedUserInfo.id + ", " + placeNo);

		Dto_Payment dto = new Dto_Payment(selectedDate, startTime, endTime, totalPrice, LoginedUserInfo.id, placeNo, userName, userPhone, userEmail, capacity);
		PaymentInfo.dto_payment = dto;

		
		model.addAttribute("placeNo", placeNo);
		model.addAttribute("productName", productName);
		model.addAttribute("userName", userName);
		model.addAttribute("userPhone", userPhone);
		model.addAttribute("userEmail", userEmail);
		model.addAttribute("totalPrice", totalPrice);
	}

}

package com.team4.ysms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team4.ysms.command.LoginCommand;
import com.team4.ysms.command.PaymentCommand;
import com.team4.ysms.command.PaymentResultCheckCommand;
import com.team4.ysms.command.PaymentSubmitCommand;
import com.team4.ysms.command.ReservationCommand;
import com.team4.ysms.command.SCommand;

@Controller
public class reservationController {
	@Autowired
	private SqlSession sqlSession;
	SCommand command = null;
	
	@RequestMapping("/reservation")
	public String reservation(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		HttpSession httpSession = request.getSession();

		command = new ReservationCommand();
		command.execute(sqlSession, model, httpSession);
		
		return "reservation";
	}
	
	@RequestMapping("/payment")
	public String payment(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		HttpSession httpSession = request.getSession();

		command = new PaymentCommand();
		command.execute(sqlSession, model, httpSession);
		
		return "payment";
	}
	
	@RequestMapping("/successPayment")
	public String successPayment(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		HttpSession httpSession = request.getSession();

		command = new PaymentSubmitCommand();
		command.execute(sqlSession, model, httpSession);
		return "paymentResult";
	}
	
	@RequestMapping("/paymentResultCheck")
	public String paymentResultCheck(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		HttpSession httpSession = request.getSession();

		command = new PaymentResultCheckCommand();
		command.execute(sqlSession, model, httpSession);
		
		return "paymentResultCheck";
	}

}



package com.team4.ysms.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.team4.ysms.command.AuthEmailRequestCommand;
import com.team4.ysms.command.DupleEmailCheckCommand;
import com.team4.ysms.command.DupleIDCheckCommand;
import com.team4.ysms.command.FindAccountCommand;
import com.team4.ysms.command.LoginCommand;
import com.team4.ysms.command.SCommand;
import com.team4.ysms.command.SignUpInputCommand;


@Controller
public class LoginController {
	@Autowired
	private SqlSession sqlSession;
	
	SCommand command = null;
	
	@RequestMapping("/login_try.four")
	public String writeForm(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		HttpSession httpSession = request.getSession();
			
		command = new LoginCommand();
		command.execute(sqlSession, model, httpSession);
		
		return "loginForm";
	}
	
	//작업이 워낙 적어서 커맨드 제거하고 컨트롤러에서 제어
	@RequestMapping("/logout.four")
	public String logOut(HttpServletRequest request, Model model) {
		
		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("loginedUserID");
		//로그아웃 작업을 수행했다는 토큰
		httpSession.setAttribute("tryLogout", "1");
		
		return "mainPage";
	}
	
	@RequestMapping("/signUpInput.four")
	public String signUpInput(MultipartHttpServletRequest mtfRequest, Model model) {
		model.addAttribute("mtfRequest", mtfRequest);
		HttpSession httpSession = mtfRequest.getSession(); 
		
		command = new SignUpInputCommand();
		command.execute(sqlSession, model, httpSession);
		return "mainPage";
	}

	@RequestMapping("confirmID.four")
	public String confirmID(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		HttpSession httpSession = request.getSession(); 
		
		command = new DupleIDCheckCommand();
		command.execute(sqlSession, model, httpSession);
		return "confirmID";
	}
	
	@RequestMapping("confirmEmail.four")
	public String confirmEmail(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		HttpSession httpSession = request.getSession(); 
		
		command = new DupleEmailCheckCommand();
		command.execute(sqlSession, model, httpSession);
		return "confirmEmail";
	}
	
	@RequestMapping("requestAuthEmail.four")
	public String requestAuthEmail(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		HttpSession httpSession = request.getSession(); 
		
		command = new AuthEmailRequestCommand();
		command.execute(sqlSession, model, httpSession);
		return "requestAuthEmail";
	}
	
	@RequestMapping("findAccount.four")
	public String findAccount(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		HttpSession httpSession = request.getSession(); 
		
		command = new FindAccountCommand();
		command.execute(sqlSession, model, httpSession);
		return "requestFindAccountEmail";
	}

}
 
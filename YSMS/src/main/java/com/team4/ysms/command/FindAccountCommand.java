package com.team4.ysms.command;

import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;

import com.team4.ysms.common.ShareVar_login;
import com.team4.ysms.dao.Dao_Login;

public class FindAccountCommand implements SCommand {
	
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		
		String inputedEmail = null;
		String inputedID = null;
		String result = null;
		Dao_Login dao = new Dao_Login();
		String mailSendReuslt = "false";
		
		
		inputedEmail = request.getParameter("email");
		inputedID = request.getParameter("id");
		
		if (inputedID == null || inputedID.equals("")) {
			result = dao.findAccount(inputedEmail);
			mailSendReuslt = sendEmail(inputedEmail, result, 0);
		} else {
			result = dao.findAccount(inputedEmail, inputedID);
			mailSendReuslt = sendEmail(inputedEmail, result, 1);
		}
		
		model.addAttribute("mailSendReuslt", mailSendReuslt);
		System.out.println("mailSendReuslt : " + mailSendReuslt);
		
	}
	
	public String sendEmail(String inputedEmail, String result, int type) {
		if(result == null || result.equals("")) {
			return "false";
		}
		
		String subject = "안녕하세요. 너공나공의 계정찾기 결과를 알려드립니다.";
		String content = "";
		// 메일 내용
		if(type == 0) {
			content = "아이디 :" + result;
		} else {
			content = "비밀번호 :" + result;
		}
		String from = "dbswovlf2009@gmail.com";
		String to = inputedEmail;
		
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, "UTF-8");
			mailHelper.setFrom(from);
			mailHelper.setTo(to);
			mailHelper.setSubject(subject);
			mailHelper.setText(content);
			mailSender.send(mail);
			return "true";
		} catch(Exception e) {
			e.printStackTrace();
			return "false";
		}
	}

}

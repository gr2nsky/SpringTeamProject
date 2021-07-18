package com.team4.ysms.command;

import java.util.Random;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.team4.ysms.common.ShareVar_login;


public class AuthEmailRequestCommand implements SCommand{
	
	private JavaMailSender mailSender;
	
	@Override
	public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		mailSender = (JavaMailSender)map.get("mailSender");
		
		String AuthenticationKey = authCodeMaker();
		String subject = "안녕하세요. 너공나공의 인증메일입니다.";
		String content ="인증코드는 [ " + AuthenticationKey + " ]입니다.";
		String from = "dbswovlf2009@gmail.com";
		String to = request.getParameter("email");
		
		// email 전송
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, "UTF-8");
			mailHelper.setFrom(from);
			mailHelper.setTo(to);
			mailHelper.setSubject(subject);
			mailHelper.setText(content);
			mailSender.send(mail);
			System.out.println("이메일 전송 : " + AuthenticationKey);
			
			ShareVar_login sv = ShareVar_login.getInstance();
			sv.authEamilCode = AuthenticationKey;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
// 인증 번호 생성기
	public String authCodeMaker() {
		String authCode = null;
		
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
			int rIndex = rnd.nextInt(3);
			switch (rIndex) {
			case 0:
				// a-z
				temp.append((char) ((int) (rnd.nextInt(26)) + 97));
				break;
			case 1:
				// A-Z
				temp.append((char) ((int) (rnd.nextInt(26)) + 65));
				break;
			case 2:
				// 0-9
				temp.append((rnd.nextInt(10)));
				break;
			}
		}
		
		authCode = temp.toString();
		System.out.println(authCode);
		
		return authCode;
	}
}

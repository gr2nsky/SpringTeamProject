package com.team4.ysms.command;

import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface SCommand {

	void execute(SqlSession sqlSession, Model model, HttpSession httpSession);

}

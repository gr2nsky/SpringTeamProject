package com.team4.ysms.command;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface SCommand {


	void execute(SqlSession sqlSession, Model model);

	
}

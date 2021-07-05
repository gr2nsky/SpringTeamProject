package com.team4.ysms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShareSpaceController {

	@RequestMapping("/write_space.four")
	public String writeSpace() {
		
		return "writeSpace";
	}
	
}

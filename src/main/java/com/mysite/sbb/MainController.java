package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller		//spring framework에 객체화해서 RAM에 로드: bean(객체) 등록
public class MainController {
	
	//http://localhost:8585
	@GetMapping ("/")
	public String main() {
		
		return "redirect:/question/list";
	}

}

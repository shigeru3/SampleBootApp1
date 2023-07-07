package com.example.sample1app;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	private boolean flag = false;
	@RequestMapping(value = "/")
	public ModelAndView index(ModelAndView mav) {
		flag = !flag;
		mav.addObject("title", "Groovy templates");
		mav.addObject("msg", "This is sample message");
		mav.addObject("flag", flag);
		mav.setViewName("index");
		return mav;
	}
}

package com.example.sample1app;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	@RequestMapping("/{num}")
	public ModelAndView index(@PathVariable int num,  ModelAndView mav) {
		int res = 0;
		for (int i = 1; i <= num; i++) {
			res += i;
		}
		mav.addObject("msg", "Total: " + res);
		mav.setViewName("index");
		return mav;
	}
}

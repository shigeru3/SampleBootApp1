package com.example.sample1app;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.addObject("msg", "HelloController.index()");
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value = "/other")
	public String other() {
		return "redirect:/";
	}

	@RequestMapping(value = "/home")
	public String home() {
		return "forward:/";
	}
}

package com.example.sample1app;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	@RequestMapping(value = "/")
	public ModelAndView index(ModelAndView mav) {
		String msg = """
				<div class="border border-primary">
					<h2>Message</h2>
					<p>This is sample message</p>
				</div>
				""";
		mav.addObject("msg", msg);
		mav.setViewName("index");
		return mav;
	}
}

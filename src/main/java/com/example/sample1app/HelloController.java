package com.example.sample1app;

import java.io.IOException;
import java.io.Writer;

import com.samskivert.mustache.Mustache.Lambda;
import com.samskivert.mustache.Template.Fragment;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	@RequestMapping(value = "/")
	public ModelAndView index(ModelAndView mav) {
		mav.addObject("title", "Lambda sample");
		mav.addObject("msg", "This message is wrote by using Lambda");

		Lambda fn = new Lambda() {
			@Override
			public void execute(Fragment frag, Writer out) throws IOException {
				out.write("<div class=\"alert alert-primary\">");
				frag.execute(out);
				out.write("</div>");
			}
		};
		mav.addObject("fn", fn);

		mav.setViewName("index");
		return mav;
	}
}

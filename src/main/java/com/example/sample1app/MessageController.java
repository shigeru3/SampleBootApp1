package com.example.sample1app;

import com.example.sample1app.repositories.MessageRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/msg")
public class MessageController {
	@Autowired
	MessageRepository repository;

	@Autowired
	PersonDAOMessageImpl dao;

	@PersistenceContext
	EntityManager entityManager;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(
			ModelAndView mav,
			@ModelAttribute("formModel") Message message) {
		mav.addObject("title", "Message");
		mav.addObject("msg", "message sample");
		mav.addObject("formModel", message);
		List<Message> list = (List<Message>)dao.getAll();
		mav.addObject("data", list);
		mav.setViewName("messages/index");
		return mav;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@Transactional
	public ModelAndView msgform(
			ModelAndView mav,
			@ModelAttribute("formModel") Message message) {
		message.setDatetime(Calendar.getInstance().getTime());
		repository.saveAndFlush(message);
		mav.addObject("title", "Message");
		mav.addObject("msg", "accept new message");
		return new ModelAndView("redirect:/msg/");
	}
}

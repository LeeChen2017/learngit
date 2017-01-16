package com.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/hello")
public class DemoController {
	@RequestMapping(value="/world")
	public String hello(HttpServletRequest req,HttpServletResponse res,Model model){
		ModelAndView mv = new ModelAndView();
		System.out.println("tst");
		mv.addObject("message","Hello SpringMVC!");
		model.addAttribute("message","hello spring");
		return "hello";
	}
}

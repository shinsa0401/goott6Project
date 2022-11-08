package com.boritgogae.board.tip.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice 
public class CommonException {
	
	@ExceptionHandler(Exception.class) 
	public ModelAndView commonException(Exception ex) {
		
		String msg = ex.getMessage(); 
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("commonError");
		mav.addObject("error", msg);
		
		
		return mav;
	}
}

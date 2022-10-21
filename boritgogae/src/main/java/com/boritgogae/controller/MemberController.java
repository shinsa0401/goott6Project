package com.boritgogae.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boritgogae.service.MemberService;

@RequestMapping(value = "/member/*")
@Controller
public class MemberController {
	
	@Inject
	private MemberService memService;

}

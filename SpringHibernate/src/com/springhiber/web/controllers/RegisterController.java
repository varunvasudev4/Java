package com.springhiber.web.controllers;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springhiber.web.dto.LoginIn;
import com.springhiber.web.dto.RegisterIn;
import com.springhiber.web.model.services.RegisterService;
import com.springhiber.web.utils.TwilioSms;

@Controller
public class RegisterController {
	
	@Autowired
	private RegisterService service;
	
	@RequestMapping("/regform")
	public ModelAndView regform() {
		return new ModelAndView("register");
	}
	
	@RequestMapping("/loginform")
	public ModelAndView loginform() {
		return new ModelAndView("login");
	}
	
	@RequestMapping("/update")
	public ModelAndView update() {
		return new ModelAndView("update");
	}
	
	@RequestMapping("/forgotpass")
	public ModelAndView forgotpass() {
		return new ModelAndView("forgotpass");
	}

	@PostMapping("/register")
	public ModelAndView reg(RegisterIn in,HttpServletRequest request) {
		System.out.println(in);
		String data = "Registered ";
		HttpSession session = request.getSession(false);
		RegisterIn temp = (RegisterIn) session.getAttribute("user");
		if(temp!=null && temp.getEmail().equals(in.getEmail()) ) {
			in.setRid(((RegisterIn)session.getAttribute("user")).getRid());
			data = "Updated ";
		}
		RegisterIn registerIn = service.save(in);
		session.setAttribute("user", registerIn);
		
		return new ModelAndView("conform", "data", data+registerIn.getUsername());
	}
	
	@PostMapping("/login")
	public ModelAndView login(LoginIn in,HttpServletRequest request) {
		RegisterIn registerIn = service.login(in);
		HttpSession session = request.getSession();
		if(registerIn!=null) {
			session.setAttribute("user", registerIn);
			return new ModelAndView("conform","data","Welcome "+registerIn.getUsername());
		}
		else {
			session.setAttribute("email", in.getEmail());
			return new ModelAndView("login" , "error" , "User Not Found");
		}
	}
	
	@PostMapping("/reset")
	public ModelAndView reset(LoginIn in,HttpServletRequest request) {
		RegisterIn registerIn = service.getByEmail(in.getEmail());
		if(registerIn!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", registerIn);
			int temppass = new Random().nextInt(9999);
			registerIn.setPassword(temppass+"");
			reg(registerIn, request);
			TwilioSms.sendSms(registerIn.getContactnumber()+"", registerIn.getPassword());
		}
		return new ModelAndView("login" , "reset" , "Try to login with new password");
	}
	
	
}

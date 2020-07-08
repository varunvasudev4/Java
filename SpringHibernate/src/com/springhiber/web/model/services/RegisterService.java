package com.springhiber.web.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springhiber.web.dto.LoginIn;
import com.springhiber.web.dto.RegisterIn;
import com.springhiber.web.model.dao.RegisterDao;

@Service
public class RegisterService {

	@Autowired
	private RegisterDao dao;
	
	public RegisterIn save(RegisterIn in) {
		return dao.save(in);
	}
	
	public RegisterIn login(LoginIn in) {
		return dao.login(in);
	}
	
	public RegisterIn getByEmail(String email) {
		return dao.getByEmail(email);
	}
}

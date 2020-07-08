package org.hiber.covid.utils;

import java.util.Random;

import org.hiber.covid.dao.PersonDAO;
import org.hiber.covid.dto.Address;
import org.hiber.covid.dto.Person;
import org.hibernate.Session;

public class TestPur {

	public static void main(String[] args) {
		PersonDAO dao = new PersonDAO();
		System.out.println(dao.sameplace());
	}

}

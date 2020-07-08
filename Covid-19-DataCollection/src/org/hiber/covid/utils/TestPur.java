package org.hiber.covid.utils;

import java.util.Random;

import org.hiber.covid.dto.Address;
import org.hiber.covid.dto.Person;
import org.hibernate.Session;

public class TestPur {

	public static void main(String[] args) {
		Session session = SessionFactoryManger.getFactory().openSession();
		Person per = (Person) session.createQuery("from Person where addr=1588").getResultList().get(0);
		System.out.println(per);
	}

}

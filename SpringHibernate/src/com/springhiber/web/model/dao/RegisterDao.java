package com.springhiber.web.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springhiber.web.dto.LoginIn;
import com.springhiber.web.dto.RegisterIn;

@Repository
public class RegisterDao {

	@Autowired
	private SessionFactory sessionFactory;
	


	public RegisterIn save(RegisterIn in) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		RegisterIn registerIn = (RegisterIn) session.merge(in);
		session.getTransaction().commit();
		session.close();
		return registerIn;
	}
	
	public RegisterIn login(LoginIn in) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from RegisterIn where email=:email and password=:pass");
		query.setParameter("email", in.getEmail());
		query.setParameter("pass", in.getPassword());
		RegisterIn registerIn = null;
		try {
			registerIn = (RegisterIn) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return registerIn;
	}
	
	public RegisterIn getById(int id) {

		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from RegisterIn where rid=:id");
		query.setParameter("id", id);
		RegisterIn registerIn = null;
		try {
			registerIn = (RegisterIn) query.uniqueResult();
			System.out.println(registerIn);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return registerIn;
	}
	
	public RegisterIn getByEmail(String email) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from RegisterIn where email=:email");
		query.setParameter("email", email);
		RegisterIn registerIn = null;
		try {
			registerIn = (RegisterIn) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return registerIn;
	}
}

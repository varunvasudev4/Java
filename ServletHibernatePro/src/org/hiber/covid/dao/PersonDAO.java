package org.hiber.covid.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hiber.covid.dto.Address;
import org.hiber.covid.dto.Person;
import org.hiber.covid.dto.VisitedPlaces;
import org.hiber.covid.utils.SessionFactoryManger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;


public class PersonDAO {
	
	
	
	public void save(Person person) {
		
		Session session = SessionFactoryManger.getFactory().openSession();
		
		Query query = session.createQuery("from Address where hn=:hn");
		query.setParameter("hn", person.getAddr().getHn());
		System.out.println("Search for Same Address");
		List<Address> addrs = query.list();
		
		for(Address temp : addrs) {
			if(person.getAddr().equals(temp)) {
				person.setAddr(temp);
				System.out.println("Address Shared");
				break;
			}
		}
		
		query = session.createQuery("from Person where addr=:addr");
		query.setParameter("addr", person.getAddr());
		System.out.println(person.getAddr().getAid());
		try {
			System.out.println("Seach for Duplicate person");
			List<Person> persons = query.list();
		for (Person person2 : persons) {
			if(person.equals(person2)) {
				System.out.println(person2);
				person=person2;
				System.out.println("Duplicate Person Found");
				break;
			}
		}
		}catch (Exception e) {	}
		
		session.beginTransaction();
		
		session.saveOrUpdate(person);
		
		session.getTransaction().commit();
		
		session.close();
		
	}
	
	public Person getById(String id) {
		Session session = SessionFactoryManger.getFactory().openSession();
		
		Query query = session.createQuery("from Person where id=:id");
		query.setParameter("id", id);
		
		Person person = (Person) query.uniqueResult();
		
		session.close();
		
		return person;
		//return session.get(Person.class, id);
		
	}
	
	public List<Person> showAll() {
		Session session = SessionFactoryManger.getFactory().openSession();
		
		Query query = session.createQuery("from Person");
		
		List<Person> persons = query.list();
		
		session.close();
		
		return persons;
		
	}
	
	public List<Person> showPositive() {
		Session session = SessionFactoryManger.getFactory().openSession();
		
		Query query = session.createQuery("from Person where status=true");	
		
		List<Person> persons = query.getResultList();
		
//		Criteria criteria = session.createCriteria(Person.class);
//		
//		Criterion post = Restrictions.eq("status", true);
//		
//		criteria.add(post);
//		
//		List<Person> persons = criteria.list();
		
		session.close();
		
		return persons;
		
	}
	
	public void deleteByIdo(Person per) {
		Session session = SessionFactoryManger.getFactory().openSession();
		
		session.beginTransaction();
		session.delete(per);
		session.getTransaction().commit();
		session.close();
	}
	
	public void deleteById(String id) {
		Session session = SessionFactoryManger.getFactory().openSession();
		int aid = getById(id).getAddr().getAid();
		
		session.beginTransaction();
		Query query = session.createQuery("delete from Person where id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
		session.getTransaction().commit();
		
		session.beginTransaction();
		try {
			System.out.println("Try to delete Address "+ aid);
			query = session.createQuery("delete from Address where aid=:aid");
			query.setParameter("aid", aid);
			query.executeUpdate();
			session.getTransaction().commit();
			System.out.println("Address Deleted");
		}catch (Exception e) {
			System.out.println("Shared Address");
		}
		session.close();
		
	}
	
	public List<VisitedPlaces> vplss() {
		Session session = SessionFactoryManger.getFactory().openSession();
		List<VisitedPlaces> places = session.createQuery("from VisitedPlaces").list();
		session.close();
		return places;
	}
	
	public Map<String, List<String>> sameplace() {
		Session session = SessionFactoryManger.getFactory().openSession();
		
		List<Integer> pls = session.createQuery("select id from VisitedPlaces").list();
		
		Map<String, List<String>> spprs = new HashMap<String, List<String>>();
		
		for(int pid : pls) {
			String pltemp = (String) session.createQuery("select place from VisitedPlaces where id="+pid).uniqueResult();
			List<String> prtemp = session.createSQLQuery("select name from people p,visited_places v,pers_vpls pv where pv.person_id=p.person_id and pv.pid=v.pid and pv.pid="+pid).list();
			if(prtemp.size()>1)
				spprs.put(pltemp, prtemp);
		}
		return spprs;
	}

}

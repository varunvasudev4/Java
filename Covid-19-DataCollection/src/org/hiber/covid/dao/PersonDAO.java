package org.hiber.covid.dao;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.xml.crypto.Data;

import org.hiber.covid.dto.Address;
import org.hiber.covid.dto.Person;
import org.hiber.covid.dto.VisitedPlaces;
import org.hiber.covid.utils.SessionFactoryManger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class PersonDAO {
	
	Session session = SessionFactoryManger.getFactory().openSession();
	
	public void save(Person person) {
		
		ListIterator li = session.createQuery("from Address").list().listIterator();
		
		Address temp = null;
		while(li.hasNext())
		{
			temp = (Address) li.next();
			if(person.getAddr().equals(temp)) {
				break;
			}
			temp = null;
		}
		
		if(temp!=null) {
			person.setAddr(temp);
		}
//		boolean already=false;
//		List<Person> persons = session.createQuery("from Person where id like '"
//											+person.getAddr().getPlace().substring(0, 2).toUpperCase()
//											+person.getAddr().getStreet().substring(0, 2).toUpperCase()
//											+"%'").getResultList();
//		
//		for(Person tempP : persons) {
//			if(person.equals(tempP)) {
//				person=tempP;
//				break;
//			}
//		}
		
		
		session.beginTransaction();
		
		session.saveOrUpdate(person);
		session.createSQLQuery("delete from visited_places where person_id is null").executeUpdate();
		
		session.getTransaction().commit();
		
	}
	
	public Person getById(String id) {
		
		Query query = session.createQuery("from Person where id=:id");
		query.setParameter("id", id);
		
		return (Person) query.uniqueResult();
		//return session.get(Person.class, id);
	
	}
	
	public List<Person> showAll() {
		
		return session.createQuery("from Person").getResultList();
		
		//return session.createSQLQuery("select * from people").list();
		
	}
	
	public List<Person> showPositive() {
		
		return session.createQuery("from Person where status=true5").getResultList();
		
	}
	
	
	public void deleteById(String id) {
		
		Person temp = getById(id);
		
		session.beginTransaction();
		
		try {
			Query query = session.createQuery("from Person where addr=:n");
			Address tempAdd = temp.getAddr();
			query.setParameter("n", tempAdd);
			query.uniqueResult();
			session.delete(temp);
		} catch (Exception e) {

			Query query = session.createQuery("delete from Person where id=:id");
			query.setParameter("id", id);
			query.executeUpdate();
			
		}
		
		session.getTransaction().commit();
		//session.delete(per);
	}
	
	public List<Map> positivePlaces() {
		
		Query query = session.createSQLQuery("select vp.place,p.name from people p , visited_places vp where p.person_id=vp.person_id and vp.place in\r\n" + 
				"(select place from visited_places group by place having count(place)>1);");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		 
		return query.list();
		
	}
	
}

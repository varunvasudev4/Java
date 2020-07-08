package org.hiber.covid.utils;

import java.io.Serializable;

import org.hiber.covid.dto.Person;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.Query;



public class CustomGenForPid implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
		
		String newPnid;
		String pl=((Person)obj).getAddr().getPlace().substring(0, 2).toUpperCase();
		String st=((Person)obj).getAddr().getStreet().substring(0, 2).toUpperCase();
		
		Query query = session.createQuery("select max(pnid) from Person where pnid like '"+pl+st+"%'");
		//query.setParameter("plst",pl+st );
		
		String pnid = (String) (query.uniqueResult());
		
		if(pnid!=null) {
			int tempnum=Integer.parseInt(pnid.substring(4));
			newPnid = pl+st+(++tempnum);
		}else {
			newPnid = pl+st+1000;
		}
		
		return newPnid;
	}

}

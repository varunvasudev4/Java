package org.hiber.covid.utils;

import java.io.Serializable;

import org.hiber.covid.dto.Person;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomGenForPid implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor si, Object obj) throws HibernateException {
		
		String newPnid;
		String pl=((Person)obj).getAddr().getPlace().substring(0, 2).toUpperCase();
		String st=((Person)obj).getAddr().getStreet().substring(0, 2).toUpperCase();
		
		String pnid = (String) (si.createQuery("select "
				+ "max(pnid) from Person where pnid like '"+pl+st+"%'").getResultList()).get(0);
		
		if(pnid!=null) {
			int tempnum=Integer.parseInt(pnid.substring(4));
			newPnid = pl+st+(++tempnum);
		}else {
			newPnid = pl+st+1000;
		}
		
		return newPnid;
	}

}

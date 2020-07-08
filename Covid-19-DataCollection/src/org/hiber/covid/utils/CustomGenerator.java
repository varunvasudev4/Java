package org.hiber.covid.utils;

import java.io.Serializable;
import java.util.Random;

import org.hiber.covid.dto.Person;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor si, Object obj) throws HibernateException {
		
		Random rd = new Random();
		while(true){
		int temp = rd.nextInt(9999);
		if(temp<1000) continue;
		return temp;
		//arg0.createQuery(arg0)
		}
	}

}

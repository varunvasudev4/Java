package org.hiber.covid.dto;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "address")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Address implements Serializable{
	
	@Id
	@GenericGenerator(name = "custom", strategy = "org.hiber.covid.utils.CustomGenerator")
	@GeneratedValue(generator = "custom")
	@Column(name = "address_id")
	private int aid;
	
	@Column(name = "house_name_or_house_name")
	private String hn;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "place")
	private String place;
	
	@Column(name = "pin")
	private int pin;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getHn() {
		return hn;
	}

	public void setHn(String hn) {
		this.hn = hn;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hn == null) ? 0 : hn.hashCode());
		result = prime * result + pin;
		result = prime * result + ((place == null) ? 0 : place.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (hn == null) {
			if (other.hn != null)
				return false;
		} else if (!hn.equals(other.hn))
			return false;
		if (pin != other.pin)
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}


	

}

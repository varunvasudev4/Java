package org.hiber.covid.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.query.criteria.internal.expression.BinaryArithmeticOperation;
import org.hibernate.type.BinaryType;

@Entity
@Table(name="people")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Person implements Serializable {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "customgen", strategy = "org.hiber.covid.utils.CustomGenForPid")
	@GeneratedValue(generator = "customgen")
	@Column(name = "person_id")
	private String pnid;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "gender")
	private char sex;
	
	@Column(name = "age")
	private int age;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address addr;
	
	@Column(name = "contact_number")
	private long contactnum;
	
	@Column(name = "covid_Status")
	private Boolean status;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "pers_vpls" 
		, joinColumns = {@JoinColumn(name="person_id")} 
		, inverseJoinColumns = {@JoinColumn(name="pid")})
	private List<VisitedPlaces> visitedPlaces;


	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPnid() {
		return pnid;
	}

	public void setPnid(String pnid) {
		this.pnid = pnid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getContactnum() {
		return contactnum;
	}

	public void setContactnum(long contactnum) {
		this.contactnum = contactnum;
	}

	public Address getAddr() {
		return addr;
	}

	public void setAddr(Address addr) {
		this.addr = addr;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public List<VisitedPlaces> getVisitedPlaces() {
		return visitedPlaces;
	}

	public void setVisitedPlaces(List<VisitedPlaces> visitedPlaces) {
		this.visitedPlaces = visitedPlaces;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + (int) (contactnum ^ (contactnum >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + sex;
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
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (contactnum != other.contactnum)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sex != other.sex)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [pnid=" + pnid + ", name=" + name + ", sex=" + sex + ", age=" + age + ", addr=" + addr
				+ ", contactnum=" + contactnum + ", status=" + status + ", visitedPlaces=" + visitedPlaces + "]";
	}
	
	

}

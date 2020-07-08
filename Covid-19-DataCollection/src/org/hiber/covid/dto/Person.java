package org.hiber.covid.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.query.criteria.internal.expression.BinaryArithmeticOperation;
import org.hibernate.type.BinaryType;

@Entity
@Table(name="people")
public class Person implements Serializable {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "customgen", strategy = "org.hiber.covid.utils.CustomGenForPid")
	@GeneratedValue(generator = "customgen")
	@Column(name = "person_id")
	private String pnid;
	
	@Column(name = "name")
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address addr;
	
	@Column(name = "contact_number")
	private long contactnum;
	
	@Column(name = "covid_Status")
	private Boolean status;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
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


	public List<VisitedPlaces> getVisitedPlaces() {
		return visitedPlaces;
	}

	public void setVisitedPlaces(List<VisitedPlaces> visitedPlaces) {
		this.visitedPlaces = visitedPlaces;
	}


	public String show() {
		return "\n"+name+"\t["+pnid+"]\n"
			  + addr.show()+"\n"
			  + "Phone : "+contactnum+"\n"
			  + "Covid Status ["+(status?"+":"-")+"ve]\n"
			  + (status?"Visited Places\n"+placeslist():"")
			  ;
	}
	
	private String placeslist() {
		String list ="";
		int i=1;
		for(VisitedPlaces temp : visitedPlaces) {
			list+=i+"."+temp.getPlace()+"\n";
			i++;
		}
		
		return list;
	}
	@Override
	public String toString() {
		return pnid + " , " + name + " , " + addr.getAid() +" , "+contactnum +" , ["+ (status?"X":" ") + "]"+"   ,   "+(visitedPlaces!=null?visitedPlaces.size():0);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addr == null) ? 0 : addr.hashCode());
		result = prime * result + (int) (contactnum ^ (contactnum >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((visitedPlaces == null) ? 0 : visitedPlaces.hashCode());
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
		if (addr == null) {
			if (other.addr != null)
				return false;
		} else if (!addr.equals(other.addr))
			return false;
		if (contactnum != other.contactnum)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (visitedPlaces == null) {
			if (other.visitedPlaces != null)
				return false;
		} else if (!visitedPlaces.equals(other.visitedPlaces))
			return false;
		return true;
	}

	
}

package org.hiber.covid.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="visited_places")
public class VisitedPlaces implements Serializable {

	@Id
	@GenericGenerator(name = "vauto", strategy = "increment" )
	@GeneratedValue(generator = "vauto")
	@Column(name="pid")
	private int id;
	
	@Column(name = "place")
	private String place;
	
	@Column(name = "dist")
	private String dist;
	
	@Column(name = "state")
	private String state;

	public VisitedPlaces() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public VisitedPlaces(String place, String dist, String state) {
		super();
		this.place = place;
		this.dist = dist;
		this.state = state;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Place=" + place + "\nDist=" + dist + "\nState=" + state;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dist == null) ? 0 : dist.hashCode());
		result = prime * result + ((place == null) ? 0 : place.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		VisitedPlaces other = (VisitedPlaces) obj;
		if (dist == null) {
			if (other.dist != null)
				return false;
		} else if (!dist.equals(other.dist))
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	
	
	
	
}

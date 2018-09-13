package com.teszt.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table (name = "sailboats")
public class Sailboat {

	@Id
	@GeneratedValue
	private Long id;

	private boolean state;
	
	@Column(unique = true, nullable = false)
	private String name;
	
	private byte capacity;
	
	@JsonBackReference
	@OneToMany(mappedBy = "user")
	private List<Reservation> reservations;
	
	private Sailboat() {}

	public Sailboat(String sailboatName, byte sailboatCapacity, boolean sailboatState) {
		this.name=sailboatName;
		this.capacity=sailboatCapacity;
		this.state=sailboatState;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getCapacity() {
		return capacity;
	}

	public void setCapacity(byte capacity) {
		this.capacity = capacity;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	
}

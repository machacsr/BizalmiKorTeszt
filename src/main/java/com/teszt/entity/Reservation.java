package com.teszt.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "reservations" )
public class Reservation {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Sailboat sailboat;
	
	private Date start;
	
	private Date end;
	
	private byte personsInNumber;
	
	private Reservation() {}

	public Reservation(Sailboat sailboat, User user, Date startTime, Date endTime, String persons) {
		this.sailboat = sailboat;
		this.user = user;
		this.start = startTime;
		this.end = endTime;
		this.personsInNumber = Byte.parseByte(persons);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Sailboat getSailboat() {
		return sailboat;
	}

	public void setSailboat(Sailboat sailboat) {
		this.sailboat = sailboat;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public byte getPersonsInNumber() {
		return personsInNumber;
	}

	public void setPersonsInNumber(byte personsInNumber) {
		this.personsInNumber = personsInNumber;
	}
	
	
	
}

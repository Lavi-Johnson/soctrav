package com.yanni.sotrav.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="rooms", catalog = "social_traveler")
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "room_id", unique = true, nullable = false)
	private long id;
		  
	@Column(name = "room_name", nullable = false, length=50)
	private String room_name;
		  
	@Column(name="location_id", nullable = false)
	private long location_id;
		
	@Column(name = "update_dt", nullable = false)
	private Date update_dt;
	
	@Column(name = "user_id", nullable = false)
	private Long user_id;

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public long getLocation_id() {
		return location_id;
	}

	public void setLocation_id(long location_id) {
		this.location_id = location_id;
	}

	public Date getUpdate_dt() {
		return update_dt;
	}

	public void setUpdate_dt(Date update_dt) {
		this.update_dt = update_dt;
	}

	public Room() { }
		 
	public Room(long id) { 
		this.id = id;
	}	

}

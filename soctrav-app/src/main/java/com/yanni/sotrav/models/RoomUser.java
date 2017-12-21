package com.yanni.sotrav.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="room_user", catalog = "social_traveler")
public class RoomUser {
	public RoomUser(){
		
	}
	public RoomUser(long rid, long uid, long stat){
		room_id=rid;
		user_id=uid;
		status=stat;
		update_dt=new Date();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "room_user_id", unique = true, nullable = false)
	private long id;
		  
	@Column(name = "room_id", nullable = false)
	private long room_id;
	
	@Column(name = "user_id", nullable = false)
	private long user_id;
	
	@Column(name = "status", nullable = false)
	private long status;
	
	@Column(name = "update_dt", nullable = false)
	private Date update_dt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRoom_id() {
		return room_id;
	}

	public void setRoom_id(long room_id) {
		this.room_id = room_id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public Date getUpdate_dt() {
		return update_dt;
	}

	public void setUpdate_dt(Date update_dt) {
		this.update_dt = update_dt;
	}

}

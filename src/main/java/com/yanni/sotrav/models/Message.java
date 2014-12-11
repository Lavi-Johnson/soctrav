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
@Table(name="messages", catalog = "social_traveler")
public class Message {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "message_id", unique = true, nullable = false)
		private long id;
			  
		@Column(name = "room_id")
		private Integer room_id;
		
		@Column(name = "user_id")
		private Integer user_id;
			  
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public Integer getRoom_id() {
			return room_id;
		}

		public void setRoom_id(Integer room_id) {
			this.room_id = room_id;
		}

		public Integer getUser_id() {
			return user_id;
		}

		public void setUser_id(Integer user_id) {
			this.user_id = user_id;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Date getUpdate_dt() {
			return update_dt;
		}

		public void setUpdate_dt(Date update_dt) {
			this.update_dt = update_dt;
		}

		@Column(name = "message", nullable = false)
		private String message;
		
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name="update_dt", nullable = false)
		private Date update_dt;

		public Message() { }
			 
		public Message(long id) { 
			this.id = id;
		}	
}

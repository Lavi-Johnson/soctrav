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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class User
 * <br/>
 * Represents an User for this web application.
 */
@Entity
@Table(name="users", catalog = "social_traveler")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", unique = true, nullable = false)
	private long id;
	
	@Column(name = "user_pass", nullable = false, length=64)
	private String user_pass;
	  
	@Column(name = "user_name", nullable = false, length=50)
	private String user_name;

	@Column(name = "first_name", nullable = false, length=50)
	private String first_name;
	
	@Column(name = "middle_name", nullable = false, length=50)
	private String middle_name="";
	
	@Column(name = "last_name", nullable = false, length=50)
	private String last_name;

	@Column(name = "user_email", nullable = false, length=100)
	private String user_email;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="user_registered", nullable = false)
	private Date user_registered;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_dt", nullable = false)
	private Date update_dt;
	  
	@Column(name="user_activation_key", nullable = false, length=60)
	private String user_activation_key;
	  
	@Column(name="status", nullable = false, length = 1)
	private Integer status;
	  
	@Column(name = "alias", nullable = false, length=255)
	private String alias;
	
	@Column(name = "user_type_id", nullable = false)
	private Integer user_type_id;
	  
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUser_pass() {
		return user_pass;
	}
	
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	
	public String getUser_email() {
		return user_email;
	}
	
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	public Date getUser_registered() {
		return user_registered;
	}
	
	public void setUser_registered(Date user_registered) {
		this.user_registered = user_registered;
	}
	
	public String getUser_activation_key() {
		return user_activation_key;
	}
	
	public void setUser_activation_key(String user_activation_key) {
		this.user_activation_key = user_activation_key;
	}
	
	public Integer getUser_status() {
		return status;
	}
	
	public void setUser_status(Integer user_status) {
		this.status = user_status;
	}
	 
	public Integer getUser_type_id() {
		return user_type_id;
	}

	public void setUser_type_id(Integer user_type_id) {
		this.user_type_id = user_type_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Date getLast_updated() {
		return update_dt;
	}

	public void setLast_updated(Date update_dt) {
		this.update_dt = update_dt;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public User() { }
	 
	public User(long id) { 
	  this.id = id;
	}
	 
	public User(String email, String name) {
	  this.user_email = email;
	  this.user_name = name;
	}

} // class User

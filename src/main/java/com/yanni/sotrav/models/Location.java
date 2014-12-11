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
@Table(name="locations", catalog = "social_traveler")
public class Location {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "location_id", unique = true, nullable = false)
		private long id;
		
		@Column(name = "location_name", nullable = false, length=50)
		private String location_name;
		  
		@Column(name="status", nullable = false, length = 1)
		private Integer status;
		
		@Column(name = "city", nullable = false, length=50)
		private String city;
		   
		@Column(name = "province_name", nullable = false, length=50)
		private String province_name;
		
		@Column(name = "country", nullable = false, length=20)
		private String country;
		
		@Column(name = "zipcode", length=15)
		private String zipcode;
		
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name="joined_dt", nullable = false)
		private Date joined_dt;
		
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name="unjoined_dt", nullable = false)
		private Date unjoined_dt;
		
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name="update_dt", nullable = false)
		private Date update_dt;
		  
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getLocation_name() {
			return location_name;
		}

		public void setLocation_name(String location_name) {
			this.location_name = location_name;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getProvince_name() {
			return province_name;
		}

		public void setProvince_name(String province_name) {
			this.province_name = province_name;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getZipcode() {
			return zipcode;
		}

		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}

		public Date getJoined_dt() {
			return joined_dt;
		}

		public void setJoined_dt(Date joined_dt) {
			this.joined_dt = joined_dt;
		}

		public Date getUnjoined_dt() {
			return unjoined_dt;
		}

		public void setUnjoined_dt(Date unjoined_dt) {
			this.unjoined_dt = unjoined_dt;
		}

		public Date getLast_updated() {
			return update_dt;
		}

		public void setLast_updated(Date update_dt) {
			this.update_dt = update_dt;
		}

		public Location() { }
		 
		public Location(long id) { 
		  this.id = id;
		}
}

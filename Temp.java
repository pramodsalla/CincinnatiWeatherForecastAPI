package com.cloud.compute;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Temp_Details")
public class Temp {

	@Id
	@GeneratedValue
	private long Id;
	
   private String date;
   private int tMax;
   private int tMin;
public Temp(String date, int tMax, int tMin) {
	super();
	this.date = date;
	this.tMax = tMax;
	this.tMin = tMin;
}
public Temp() {
	super();
}
@JsonIgnore
@JsonProperty(value = "Id")
public long getId() {
	return Id;
}
public void setId(long id) {
	Id = id;
}

@JsonProperty("DATE")
public String getDate() {
	return date.replace("-", "");
}
public void setDate(String date) {
	this.date = date;
}
@JsonProperty("TMAX")
public int gettMax() {
	return tMax;
}
public void settMax(int tMax) {
	this.tMax = tMax;
}
@JsonProperty("TMIN")
public int gettMin() {
	return tMin;
}
public void settMin(int tMin) {
	this.tMin = tMin;
}
   
   
	
	
}

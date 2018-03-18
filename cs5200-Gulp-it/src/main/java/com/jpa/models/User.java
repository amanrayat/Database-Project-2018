package com.jpa.models;

import java.sql.Date;

public class User {
private int id;
private String firstName;
private String lastName;
private String username;
private String password;
private Date DOB;

public User(String firstName, String lastName, String username, String password) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.username = username;
	this.password = password;
}
public User(String firstName, String lastName, String username, String password, Date dOB) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.username = username;
	this.password = password;
	DOB = dOB;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Date getDOB() {
	return DOB;
}
public void setDOB(Date dOB) {
	DOB = dOB;
}



}
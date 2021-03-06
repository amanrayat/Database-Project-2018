package com.jpa.models;

public class Feedback {

	private int id;

	private String comment;
	private boolean favourite;
	private int Restaurant;
	private int Customer;
	
	@Override
	public String toString() {
		if(favourite==false) {
			return comment+"\n";
		}
		else if(comment==null) {
			return favourite+"\n";
		}
		else {
		return comment+" "+favourite+"\n";
		}
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public boolean isFavourite() {
		return favourite;
	}
	public void setFavourite(boolean favourite) {
		this.favourite = favourite;
	}
	public int getRestaurant() {
		return Restaurant;
	}
	public void setRestaurant(int restaurant) {
		Restaurant = restaurant;
	}
	public int getCustomer() {
		return Customer;
	}
	public void setCustomer(int customer) {
		Customer = customer;
	}
	public Feedback() {
		super();
	}
	
	public Feedback(String comment, boolean favourite) {
		super();
		this.comment = comment;
		this.favourite = favourite;
	}
	
	public Feedback(int id, String comment, int restaurant, int customer) {
		super();
		this.id = id;
		this.comment = comment;
		Restaurant = restaurant;
		Customer = customer;
	}
	
	public Feedback(int id, boolean favourite, int restaurant, int customer) {
		super();
		this.id = id;
		this.favourite = favourite;
		Restaurant = restaurant;
		Customer = customer;
	}
	
	public Feedback(String comment, boolean favourite, int restaurant, int customer) {
		super();
		this.comment = comment;
		this.favourite = favourite;
		Restaurant = restaurant;
		Customer = customer;
	}
	public Feedback(int id, String comment, boolean favourite, int restaurant, int customer) {
		super();
		this.id = id;
		this.comment = comment;
		this.favourite = favourite;
		Restaurant = restaurant;
		Customer = customer;
	}


}

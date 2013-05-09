package service;

import people.Customer;

public class Mail {
	private String day;
	private String toAddress;
	private String id;
	private Customer from;
	private int priority;
	
	public Mail(String day, String toAddress, String id, Customer from,
			int priority) {
		this.day = day;
		this.toAddress = toAddress;
		this.id = id;
		this.from = from;
		this.priority = priority;
	}

	public String getDay() {
		return day;
	}

	public String getToAddress() {
		return toAddress;
	}

	public String getId() {
		return id;
	}

	public Customer getFrom() {
		return from;
	}

	public int getPriority() {
		return priority;
	}
	
	public double getCost(){
		Cost c = new Cost(this);
		
		return c.getPrice();
	}
	
}

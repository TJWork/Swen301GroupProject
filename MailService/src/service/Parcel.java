package service;

import people.Customer;


public class Parcel extends Mail{
	private double weight;
	

	public Parcel(String day, String toAddress, String id, Customer from,
			int priority) {
		super(day, toAddress, id, from, priority);
		
	}
	
	public double getWeight() {
		return weight;
	}

	
	
}

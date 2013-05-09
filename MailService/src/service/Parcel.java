package service;

import people.Customer;


public class Parcel extends Mail{



	public Parcel(String day, String toAddress, String from, int priority) {
		super(day, toAddress, from, priority);
		// TODO Auto-generated constructor stub
	}


	public Parcel(String day, String toAddress, String from, int priority,
			int customerID) {
		super(day, toAddress, from, priority, customerID);
		// TODO Auto-generated constructor stub
	}

	private double weight;
	

	
	public double getWeight() {
		return weight;
	}

	
	
}

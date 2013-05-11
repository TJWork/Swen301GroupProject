package service;

import people.Customer;


public class Parcel extends Mail{

	private double weight;
	private double volume;

	public Parcel(String day, String toAddress, String from, String weight, String volume, String priority) {
		super(day, toAddress, from, priority);
		this.weight = Double.parseDouble(weight);
		this.volume = Double.parseDouble(volume);
	}


	public Parcel(String day, String toAddress, String from, String priority,
			int customerID) {
		super(day, toAddress, from, priority, customerID);
		// TODO Auto-generated constructor stub
	}
	
	public Parcel(String day, String toAddress, String from, String weight, String volume, int priority) {
		super(day, toAddress, from, priority);
		this.weight = Double.parseDouble(weight);
		this.volume = Double.parseDouble(volume);
	}


	public Parcel(String day, String toAddress, String from, int priority,
			int customerID) {
		super(day, toAddress, from, priority, customerID);
		// TODO Auto-generated constructor stub
	}	
	public Parcel() {
		super();
		this.weight = 0;
		this.volume = 0;
	}


	public double getWeight() {
		return this.weight;
	}

	public double getVolume() {
		return this.volume;
	}


	public void setWeight(double weight) {
		this.weight = weight;
	}


	public void setVolume(double volume) {
		this.volume = volume;
	}
	
	public void setWeight(String weight) {
		
		this.weight = Double.parseDouble(weight);
	}


	public void setVolume(String volume) {
		this.volume = Double.parseDouble(volume);
	}	
	
	public String toString(){
		return super.toString() + "  :  weight: " + this.weight + "  :  volume: " + this.volume;
		
		
	}
}

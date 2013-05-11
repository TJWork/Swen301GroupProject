package service;

import people.Customer;


public class Parcel extends Mail{

	private String weight;
	private String volume;
	
	public Parcel(String day, String toAddress, String from, String weight, String volume, int priority) {
		super(day, toAddress, from, priority);
		this.weight = weight;
		this.volume = volume;
	}


	public Parcel(String day, String toAddress, String from, String weight, String volume, String priority) {
		super(day, toAddress, from, priority);
		this.weight = weight;
		this.volume = volume;
	}

	public double getWeight() {
		return Double.parseDouble(weight);
	}

	public String volume(){
		return this.volume;
	}
	
	public String[] getData(){
		return new String[]{this.getDay(), this.getToAddress(), this.getFrom(), this.weight, this.volume, "" + this.getPriority()};
	}
}

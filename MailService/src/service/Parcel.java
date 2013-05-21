package service;

import people.Customer;


public class Parcel extends Mail{

	private String weight;
	private String volume;


	public Parcel(String day, String toAddress, String from, String weight, String volume, String cost, String priority) {
		super(day, toAddress, from, cost, priority);
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
		return new String[]{this.getDay(), this.getToAddress(), this.getFrom(), 
				this.weight, this.volume, "" + this.getCost(), "" + this.getPriority()};
	}
	
	public String toString(){
		return (
					"Day: " + super.getDay() + "\n" + 
					"Destination: " + super.getToAddress() + "\n" + 
					"Origin: " + super.getFrom() + "\n" + 
					"Priority: " + super.getPriority() + "\n" + 
					"Weight: " + this.getWeight() + "\n" +
					"Volume: " + this.volume() +"\n"+
					"Cost: " +  super.getCost()
					);
		
	}
}

package service;

import people.Customer;

public class Mail {
	private String day;
	private String toAddress;
	private String from;
	private double priority;
	private double cost;
	
	public Mail(String day, String toAddress, String from, double cost, int priority) {
		this.day = day;
		this.toAddress = toAddress;
		this.from = from;
		this.priority = priority;
		this.cost = cost;
	}

	public Mail(String day, String toAddress, String from, String cost, String priority) {
		this.day = day;
		this.toAddress = toAddress;
		this.from = from;
		this.priority = Double.parseDouble(priority);
		this.cost = Double.parseDouble(cost);
	}	


	public String getDay() {
		return day;
	}

	public String getToAddress() {
		return toAddress;
	}

	public String getFrom() {
		return from;
	}

	public double getPriority() {
		return priority;
	}

	public double getCost(){
		return this.cost;
	}

	
	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	public String[] getData(){
		return new String[]{this.day, this.toAddress, this.from, Double.toString(this.getCost()), "" + this.priority};
	}
	
	public String toString(){
		return (
				"Day: " + this.getDay() + "\n" + 
				"Destination: " + this.getToAddress() + "\n" + 
				"Origin: " + this.getFrom() + "\n" + 
				"Cost: " +  this.getCost() + "\n" + 
				"Priority: " + this.getPriority()
				);
		
		
	}

}
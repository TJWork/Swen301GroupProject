package service;

import java.util.Comparator;

import file.XMLWorker;

import people.Customer;

public class Mail {
	private String day;
	private String toAddress;
	private String from;
	private int customerID;
	private int priority;
	
	public Mail(){
		this.day = "";
		this.toAddress = "";
		this.from = "";
		this.customerID = -1;
		this.priority = -1;
	}
	
	public Mail(String day, String toAddress, String from, String priority) {
		this.day = day;
		this.toAddress = toAddress;
		this.from = from;
		this.priority = Integer.parseInt(priority);
	}

	public Mail(String day, String toAddress, String from, String priority, int customerID) {
		this.day = day;
		this.toAddress = toAddress;
		this.from = from;
		this.customerID = customerID;
		this.priority = Integer.parseInt(priority);
	}
	
	public Mail(String day, String toAddress, String from, int priority) {
		this.day = day;
		this.toAddress = toAddress;
		this.from = from;
		this.priority = priority;
	}

	public Mail(String day, String toAddress, String from, int priority, int customerID) {
		this.day = day;
		this.toAddress = toAddress;
		this.from = from;
		this.customerID = customerID;
		this.priority = priority;
	}
	/*
	 * ##########################################################################################
	 * 
	 * 			Getters Setters
	 * 
	 * ##########################################################################################
	 * 
	 */
	
	
	public String getDay() {
		return day;
	}

	public String getToAddress() {
		return toAddress;
	}


	public String getFrom(){
		return this.from;
	}
	
	public int getCustomerID() {
		return customerID;
	}

	public int getPriority() {
		return priority;
	}
	
	public void setDay(String day) {
		this.day = day;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public void setPriority(String priority) {
		this.priority = Integer.parseInt(priority);
	}
	
	public String toString(){
		return this.day + " :  To: " + this.toAddress + "  :  From: " + this.from + "  :  Priority: " + this.priority;
	}
	

	/*
	 * ##########################################################################################
	 * 
	 * 			Comparators
	 * 
	 * ##########################################################################################
	 * 
	 */
	public static Comparator<Mail> mailDateAscending(){
		return new Comparator<Mail>(){
			@Override
			public int compare(Mail o1, Mail o2) {
				return XMLWorker.parseDate(o1.getDay()).compareTo(XMLWorker.parseDate(o2.getDay()));
			}
		};
	}
	
	public static Comparator<Mail> mailDateDescending(){
		return new Comparator<Mail>(){
			@Override
			public int compare(Mail o1, Mail o2) {
				return XMLWorker.parseDate(o2.getDay()).compareTo(XMLWorker.parseDate(o1.getDay()));
			}
		};
	}	
	
	
	public static Comparator<Mail> mailPriorityAscending(){
		return new Comparator<Mail>(){
			@Override
			public int compare(Mail o1, Mail o2) {
				return o1.getPriority() - o2.getPriority();
			}
		};
	}
	
	
	
	public static Comparator<Mail> mailPriorityDescending(){
		return new Comparator<Mail>(){
			@Override
			public int compare(Mail o1, Mail o2) {
				return o2.getPriority() - o1.getPriority();
			}
		};
	}
	
}

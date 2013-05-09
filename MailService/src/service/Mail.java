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

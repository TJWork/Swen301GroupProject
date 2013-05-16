package service;

import people.Customer;

public class Mail {
private String day;
private String toAddress;
private String from;
private double priority;

public Mail(String day, String toAddress, String from, int priority) {
this.day = day;
this.toAddress = toAddress;
this.from = from;
this.priority = priority;
}

public Mail(String day, String toAddress, String from, String priority) {
this.day = day;
this.toAddress = toAddress;
this.from = from;
this.priority = Double.parseDouble(priority);
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
Cost c = new Cost(this);

return c.getPrice();
}

public String[] getData(){
return new String[]{this.day, this.toAddress, this.from, "" + this.priority};
}

}
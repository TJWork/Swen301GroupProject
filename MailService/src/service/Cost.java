package service;

public class Cost {
	double costVal;
	boolean mail;
	Mail m;
	Parcel p;
	
	public Cost(Mail m){
		this.m = m;
		mail = true;
		calculateMailCost();
	}
	
	public Cost (Parcel p){
		this.p = p;
		mail = false;
		calculatePackageCost();
	}
	
	private void calculatePackageCost() {
		int distance = m.getToAddress().length() - m.getToAddress().length(); //just uses difference in adress string until proper distances are added
		distance = Math.abs(distance);
		costVal = p.getWeight() *p.getPriority() * distance;
	}

	private void calculateMailCost() {
		int distance = m.getToAddress().length() - m.getToAddress().length(); //just uses difference in adress string until proper distances are added
		distance = Math.abs(distance);
		this.costVal = distance * m.getPriority();
	}

	public double getPrice() {
		return costVal*1.125;
		
	}

}

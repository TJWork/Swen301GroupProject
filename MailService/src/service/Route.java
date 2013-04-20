package service;

public class Route {
	private String destination;
	private Depot[] changePoints;
	
	
	
	public Route(String destination, Depot[] changePoints) {
		super();
		this.destination = destination;
		this.changePoints = changePoints;
	}
	
	
	
	public Route(String destination) {
		super();
		this.destination = destination;
	}
	
	

	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Depot[] getChangePoints() {
		return changePoints;
	}
	public void setChangePoints(Depot[] changePoints) {
		this.changePoints = changePoints;
	}
	
	
}

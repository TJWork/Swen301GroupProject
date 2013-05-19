package service;

public class Route {

	private String origin;
	private String destination;
	private String company;
	private int maxWeight;
	private int maxVolume;
	private double weightCost;//Cost modifier for weight
	private double volumeCost;//Cost modifier for Volume
	private double mailCost;//Standard mail price
	private int frequency;//how often a delivery takes place
	private int estimatedDeliveryTime;//how long delivery willl take

	public Route(String origin, String destination, String company,
			int maxWeight, int maxVolume, double weightCost, double volumeCost, double mailCost,
			int frequency, int estimatedDeliveryTime)  {
		this.origin = origin;
		this.destination = destination;
		this.company = company;
		this.maxWeight = maxWeight;
		this.maxVolume = maxVolume;
		this.weightCost = weightCost;
		this.volumeCost = volumeCost;
		this.mailCost = mailCost;
		this.frequency = frequency;
		this.estimatedDeliveryTime = estimatedDeliveryTime;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public String getCompany() {
		return company;
	}

	public int getMaxWeight() {
		return maxWeight;
	}

	public int getMaxVolume() {
		return maxVolume;
	}

	public double getWeightCost() {
		return weightCost;
	}

	public double getVolumeCost() {
		return volumeCost;
	}

	public int getFrequency() {
		return frequency;
	}

	public double getCostOnWeight(double weightValue) {
		return weightValue*weightCost;
	}

	public double getCostOnVolume(double volValue) {
		return volValue*volumeCost;
	}

	public int calculateDeliveryTime(){ //return delivery time in hours
		return 1/2*frequency+estimatedDeliveryTime;
	}

}

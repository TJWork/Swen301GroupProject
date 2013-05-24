package service;

public class Route {

	private String origin;
	private String destination;
	private String company;
	private String priority;
	private int maxWeight; //Maximum weight
	private int maxVolume; //Maximum volume
	private double weightCost;//Cost modifier for weight
	private double volumeCost;//Cost modifier for Volume
	private double mailCost;//Standard mail price
	private int frequency;//how often a delivery takes place
	private int estimatedDeliveryTime;//how long delivery willl take

	public Route(String origin, String destination, String company,
			int maxWeight, int maxVolume, double weightCost, double volumeCost, double mailCost,
			int frequency, int estimatedDeliveryTime, String priority)  {
		this.origin = origin;
		this.destination = destination;
		this.company = company;
		this.maxWeight = maxWeight;
		this.maxVolume = maxVolume;
		this.weightCost = weightCost;
		this.volumeCost = volumeCost;
		this.mailCost = mailCost;
		this.frequency = frequency;
		this.priority = priority;
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

	public String getPriority(){
		return priority;
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

	public double getMailCost(){
		return mailCost;
	}

	private double getCostOnWeight(double weightValue) {
		return weightValue*weightCost;
	}

	private double getCostOnVolume(double volValue) {
		return volValue*volumeCost;
	}

	public double getCost(double weight, double volume) {
		double costOnWeight = getCostOnWeight(weight);
		double costOnVol = getCostOnVolume(volume);
		if(costOnWeight>costOnVol)
			return costOnWeight;
		else
			return costOnVol;
	}


	public double calculateDeliveryTime(){ //return delivery time in hours
		return 1/2*frequency+estimatedDeliveryTime;
	}

	public String[] getData(){

		return new String[]{this.origin, this.destination, this.company,
				"" +this.maxWeight, "" +this.maxVolume, "" +this.weightCost,
				"" +this.volumeCost,"" + this.mailCost, "" +this.frequency,
				"" +this.estimatedDeliveryTime, this.priority};
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Route [origin=" + origin + ", destination=" + destination
				+ ", company=" + company + ", priority=" + priority
				+ ", maxWeight=" + maxWeight + ", maxVolume=" + maxVolume
				+ ", weightCost=" + weightCost + ", volumeCost=" + volumeCost
				+ ", mailCost=" + mailCost + ", frequency=" + frequency
				+ ", estimatedDeliveryTime=" + estimatedDeliveryTime + "]";
	}


}

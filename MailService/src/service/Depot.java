package service;

public class Depot {
	private String address;
	private String companyName;
	
	
	public Depot() {
	
	}
	
	public Depot(String companyName) {
		this.companyName = companyName;
	}
	
	public Depot(String address, String companyName) {
		this.address = address;
		this.companyName = companyName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
	
}

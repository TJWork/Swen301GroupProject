package people;

public class Customer {
	private String name;
	private String address;
	private int id;
	
	public Customer(String name, String address, int id) {
		this.name = name;
		this.address = address;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

}

package people;

public class Staff {
	private int id;
	private String name;
	private String password;
	public Staff(int id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean checkPassword(String pass){
		return true;
	}
	
	
}

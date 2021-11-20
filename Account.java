//Author: Carter Morgan
abstract class Account {
	private String username;
	private String password;
	private int id;
	
	//Constructor for account
	public Account(int id, String username,String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public String getID() {
		return Integer.toString(id);
	}
	
	//username getter
	public String getUsername() {
		return username;	
	}
	
	//password getter
	public String getPassword() {
		return this.password;		
	}
	
	//verify password
	public Boolean verifyPassword(String password) {
		if(this.password.equals(password)) return true;
		else return false;
		
	}
	
	//prints username and class type
	public String toString() {
		String result = "Username: " + username + ", " + this.getClass();
		return result;
	}
	
	//sets a new password
	public void setPassword(String password) {
		this.password = password;
	}
}

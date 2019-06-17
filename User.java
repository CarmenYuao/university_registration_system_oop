
public class User implements java.io.Serializable{
	protected String username;
	protected String password;
	protected String firstName;
	protected String lastName;
	//default constructor
	public User() {
		
	}
	//constructor 
	public User(String username,String password,String fn,String ln) {
		this.username=username;
		this.password=password;
		this.firstName=fn;
		this.lastName=ln;
	}
	//view all course
	public void viewcourselist() {
		System.out.println("NO VIEW");
	}
}

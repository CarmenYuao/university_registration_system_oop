import java.util.ArrayList;
public class Student extends User implements StudentInterf,java.io.Serializable {
	//constructor
	private ArrayList<Course> studentCourse = new ArrayList<Course>();
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	
	
	public Student(String username,String password,String fn,String ln) {
		this.username=username;
		this.password=password;
		this.firstName=fn;
		this.lastName=ln;
	}
	public Student() {
		
	}
	public void addedtocourse(Course cc) {
		studentCourse.add(cc);
	}
	
	public void withdrawcourse(Course c) {
		studentCourse.remove(c);
	}
    public void registeredcourse() {
    	for (Course c1:studentCourse) {
    		System.out.println(c1.getName());
    	}
    }

   
	public void viewcourselist() {
		for (Course c7: studentCourse) {
			System.out.println(c7.getName());
		}
		
	}
	
	//getters and setters
	public ArrayList<Course> getStudentCourse() {
		return studentCourse;
	}
	public void setStudentCourse(ArrayList<Course> studentCourse) {
		this.studentCourse = studentCourse;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	
	

}

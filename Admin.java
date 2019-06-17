import java.util.ArrayList;

public class Admin extends User implements AdminInterf,java.io.Serializable {
	//constructor 
	public Admin(String username,String password,String fn,String ln) {
		super(username,password,fn,ln);
	}
	
	//create a new course
	public static Course createCourse(String name, String id, int maxS, String instructor, int section, String location) {
		 Course newCourse = new Course(name,id,maxS,instructor, section,location);
		 return newCourse;
	}
	
	//check whether the user name & password are valid 
	public static boolean login(String username, String psw) {
		if (username.equals("Admin") && psw.equals("Admin001")) {
			return true;
		}
		else {
			System.out.println("Invalid username and password.");
			return false;
		}
	}
	
	//register a student
	public Student registerS(String username,String password,String fn,String ln) {
		Student addeds = new Student(username,password,fn,ln);
		return addeds;
	}
	
	
	


}

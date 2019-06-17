import java.util.ArrayList;
import java.util.*;

public class Course implements Comparable<Course>,java.io.Serializable {
	//field
	private String name;
	private String id;
	private int maxS;
	private int currentS;
	ArrayList<String> namelist = new ArrayList<String>();
	private String instructor;
	private int section;
	private String location;
	ArrayList<Student> cstudentlist= new ArrayList<Student>();
	
	//constructor
	public Course(String name, String id, int maxS,String instructor, int section, String location) {
		this.name = name;
		this.id = id;
		this.maxS = maxS;
		this.currentS = 0;
		this.instructor = instructor;
		this.section = section;
		this.location = location;
	}
	//remove student from the course
	public void removestudnet(Student stud) {
		cstudentlist.remove(stud);
		namelist.remove(stud.getFirstName()+" "+stud.getLastName());
	}
	//add student to the course
	public void addStudent(Student stud) {
		this.cstudentlist.add(stud);
		this.namelist.add(stud.getFirstName()+" "+stud.getLastName());////////////////
	}
	//Course management 4
	public void displayInfo(){
		System.out.println("Course Name:"+this.name+"|"+"Course ID:"+this.id+"|"+"Maxium number of student:"+this.maxS+"|"+"Current number of students:"+this.currentS+"|"+"Course instructor:"+this.instructor+"|"+"Section:"+this.section+"|"+"Location"+this.location);
		System.out.println("Student name list:");
		if (namelist==null) {
			System.out.println("Empty");
		}
		else {
			for (String s:namelist) {
				System.out.println(s+"|");
			}
		}
	}
	//student view all course
	public void studentviewall() {
		System.out.println("Course Name:"+this.name+"|"+"Course ID:"+this.id+"|"+"Maxium number of student:"+this.maxS+"|"+"Current number of students:"+
				this.currentS+"Course instructor:"+this.instructor+"|"+"Section:"+this.section+"|"+"Location"+this.location);
	}
	
	//report viewall
	public void viewall() {
		System.out.println( "current number of students registered:"+this.currentS+"|"+"maximum number of students:"+this.maxS);
		System.out.println("enrolled student's names and ids:");
		for(Student ss: cstudentlist) {
			System.out.println(ss.getFirstName()+" "+ss.getLastName()+" "+ss.getUsername());
		}
	}
	
	///
	public void displayStudentName() {
		if(cstudentlist.size()!=0) {
			for(int i =0;i<cstudentlist.size();i++) {
				namelist.add(cstudentlist.get(i).getFirstName()+" "+cstudentlist.get(i).getLastName());
				System.out.println(cstudentlist.get(i).getFirstName()+" "+cstudentlist.get(i).getLastName());
			}
		}
	}
	
	
	//comparator implementation to sort course object based on current number of students registered
	public static class Coursebycs implements Comparator<Course>{
		public int compare(Course c1,Course c2) {
			return c1.currentS >c2.currentS ? 1 : (c1.currentS < c2.currentS ? -1 :0);
		}
	}
	
	//print()
	public void print() {
		System.out.println("Course Name:"+this.name+"|"+"Course ID:"+this.id+"|");
	}
	
	
	//setter and getters
    public String getName() {
			return name;
    }
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMaxS() {
		return maxS;
	}
	public void setMaxS(int maxS) {
		this.maxS = maxS;
	}
	public int getCurrentS() {
		return currentS;
	}
	public void setCurrentS(int currentS) {
		this.currentS = currentS;
	}
	public ArrayList<String> getNamelist() {
		return namelist;
	}
	public void setNamelist(ArrayList<String> namelist) {
		this.namelist = namelist;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}


	@Override
	public int compareTo(Course o) {
		// TODO Auto-generated method stub
		return 0;
	}

}

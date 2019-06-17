import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Mainprogram {
	public static void main(String[] args) throws FileNotFoundException{
		//arraylist of course
		ArrayList<Student> studentlist = new ArrayList<Student>();
		ArrayList<Course> courselist = new ArrayList<Course>();
		ArrayList<Course> fullcourse = new ArrayList<Course>();
		

		
		//first time open or not 
		Scanner scan = new Scanner(System.in);
		System.out.println("Is this your first time enter the program ? Yes or No");
		
		
		if (scan.nextLine().equals("Yes")) {
		   /// first time 
		    String file = "src/MyUniversityCourses.csv";
			String input = new Scanner(new File(file)).useDelimiter("\\A").next();
			input = input.replace("\n",",");
			StringTokenizer strTokens = new StringTokenizer(input,",");
			
			for (int i = 0;i<8;i++) {
				strTokens.nextToken();
			}
			while (strTokens.hasMoreTokens()) {
				String courseName = strTokens.nextToken();
				String couseid = strTokens.nextToken();
				int maxs = Integer.parseInt(strTokens.nextToken());
				int currents = Integer.parseInt(strTokens.nextToken());
				ArrayList<String> nl ;
				strTokens.nextToken();
				String instructor = strTokens.nextToken();
				int section =Integer.parseInt(strTokens.nextToken());
				String location = strTokens.nextToken();
				Course c = new Course(courseName,couseid,maxs,instructor,section,location);
				courselist.add(c);	
			}
		}
		
		else {
			//after first time
			try {
				
				FileInputStream fis = new FileInputStream("Course.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				
				courselist =(ArrayList<Course>) ois.readObject();
				ois.close();
				fis.close();
		
				FileInputStream fiss = new FileInputStream("Student.ser");
				ObjectInputStream oiss = new ObjectInputStream(fiss);
				//deserialize 
				
				studentlist=(ArrayList<Student>) oiss.readObject();
			     
				oiss.close();
				fiss.close();
			   
		    }
			catch(EOFException e) {
				System.err.println(e);
			}
		    catch(IOException ioe) {
			    ioe.printStackTrace();
			    return;
		    }
			catch(ClassNotFoundException cnfe) {
			     cnfe.printStackTrace();
			     return;
		   }
		
		
	   }
	   
		//admin or student 
		System.out.println("Are you an admin or student? Entert Admin or Student:");
		
		//admin
		
		//login check
		if (scan.nextLine().equals("Admin")) {
			System.out.println("Enter username:");
			String username = scan.nextLine();
			System.out.println("Enter passward:");
			String apsw = scan.nextLine();
		    boolean exit = false;
			while(Admin.login(username,apsw)&& (exit!=true)){
				System.out.println("Course Management or Reports? Enter Course Management or Reports:");
				if (scan.nextLine().equals("Course Management")) {
					System.out.println("1.Create a new course"+"\n"+"2.Delete a course"+"\n"+"3.Edit a course"+"\n"+"4.Display information for a given course"+"\n"+"5.Register a student"+"\n"+"6.Exit");
					System.out.println("which operation? Enter 1,2,3,4,5,or 6:");
					int i = Integer.parseInt(scan.nextLine());
					switch(i) 
					{
					case 1: //1.Create a new course
						System.out.println("Enter Course Name:");
						String name = scan.nextLine();
						System.out.println("Enter Course ID:");
						String id = scan.nextLine();
						System.out.println("Enter Maxium Number of Students:");
						int maxS =Integer.parseInt(scan.nextLine());
						System.out.println("Enter Instructor:");
						String instructor = scan.nextLine();
						System.out.println("Section:");
						int section = Integer.parseInt(scan.nextLine());
						System.out.println("Location:");
						String location = scan.nextLine();
						
						courselist.add(Admin.createCourse(name,id,maxS,instructor,section,location));
						break;
					case 2:  //2.Delete a course
						System.out.println("Enter the Course Name of the course that you want to delete:");
						String removeCourse = scan.nextLine();
						System.out.println("Enter the Course Section Number:");
						int removeSection = Integer.parseInt(scan.nextLine());
						for (int k =0; k< courselist.size();k++) {
							if ((courselist.get(k).getName().equals(removeCourse)) &&(courselist.get(k).getSection()==removeSection)) {
								courselist.remove(k);
							}
						}
						break;
					case 3:  //3.Edit a course
						System.out.println("Enter the name of the course that you want to edit:");
						String editCourse = scan.nextLine();
						System.out.println("Enter the course section number:");
						int editsection = Integer.parseInt(scan.nextLine());
						int editIndex = 0;/////?????
						for (int j =0; j< courselist.size();j++) {
							if (courselist.get(j).getName().equals(editCourse) && (courselist.get(j).getSection()==editsection)) {
								editIndex=j;
							}
						}
						Course editedCourse = courselist.get(editIndex);
						System.out.println("Enter Maxium Number of Students:");
						editedCourse.setMaxS(Integer.parseInt(scan.nextLine()));
						System.out.println("Enter Current Number of Students:");
						editedCourse.setCurrentS(Integer.parseInt(scan.nextLine()));
						System.out.println("Enter Instructor:");
						editedCourse.setInstructor(scan.nextLine());
						System.out.println("Section:");
						editedCourse.setSection(Integer.parseInt(scan.nextLine()));
						System.out.println("Location:");
						editedCourse.setLocation(scan.nextLine());
						break;
					case 4:  //4.Display information for a given course (by course ID)
						
						
						System.out.println("Enter course ID:");
						String displayid = scan.nextLine();
						int displayIndex = 0;
						for (int m =0; m< courselist.size();m++) {
							if (courselist.get(m).getId().equals(displayid)) {
								displayIndex=m;
							}
						}
						courselist.get(displayIndex).displayInfo();
						break;
					case 5://5.Register a student
						System.out.println("Enter Student's First Name:");
						String sfn = scan.nextLine();
						System.out.println("Enter student's last name:");
						String sln = scan.nextLine();
						System.out.println("Enter student's username:");
						String sun = scan.nextLine();
						System.out.println("Enter student's password:");
						String spw = scan.nextLine();
						studentlist.add(new Student (sun,spw,sfn,sln));
						
						
						break;
					case 6://exit
						exit=true;
						break;
					
					}
				}
				else {
					System.out.println("1.View all courses"+"\n"+"2.View all courses that are FULL"+"\n"+"3.Write to a file the list of course that are full"+"\n"+"4.View the names of the students that are registered in a specific course"+"\n"+"5.View the list of courses that a given student is registered in"+"\n"+"6.Sort"+"\n"+"7.exit");
					System.out.println("which operation? Enter 1,2,3,4,5,6,or 7:");
					int b = Integer.parseInt(scan.nextLine());
					switch(b) 
					{
					 case 1://1.View all courses
						 for (Course c3: courselist) {
							 System.out.println(c3.getName());
							 c3.viewall();
						 }
						 break;
					 case 2: //2.View all courses that are FULL 
						 for(Course c4:courselist) {
							 if((c4.getMaxS()==c4.getCurrentS()) && (fullcourse.contains(c4)==false)){
								 fullcourse.add(c4);
							 }
						 }	 
					    for (Course c5:fullcourse) {
						     System.out.println(c5.getName());
					     } 
						 break;
					 case 3: //3.Write to a file the list of course that are full
						 try {
							 FileWriter fileWriter = new FileWriter("FullCourse.txt");
							 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
							 for (Course c6: fullcourse) {
								 bufferedWriter.write(c6.getName());
								 bufferedWriter.newLine();
							  }
							  bufferedWriter.close();
						     }
						 catch (IOException exk) {
							 System.out.println( "Error writing file '" + "FullCourse.txt" + "'");
						     exk.printStackTrace();

						 }
						 break;
					 case 4://4.View the names of the students that are registered in a specific course
						 System.out.println("Enter course name:");
						 String specificc = scan.nextLine();
						 System.out.println("Enter the course section number:");
						 int spcsection = Integer.parseInt(scan.nextLine());
						 int spcindex=0;
						 for (int p=0;p<courselist.size();p++) {
							 if ((courselist.get(p).getName().equals(specificc)) && (courselist.get(p).getSection()==spcsection)) {
								 spcindex=p;
							 }		 
						 }
						 courselist.get(spcindex).displayStudentName();
						 break;
					 case 5://5.View the list of courses that a given student is registered in
						 System.out.print("Enter student first name: ");
						 String sfnn = scan.nextLine();
						 System.out.print("Enter student last name:");
						 String slnn = scan.nextLine();
						 int givensindex = 0;
						 for (int q = 0;q<studentlist.size();q++) {
							 if(studentlist.get(q).getLastName().equals(slnn)&&studentlist.get(q).getFirstName().equals(sfnn)) {
								 givensindex=q;
							 }
						 }
						 studentlist.get(givensindex).viewcourselist();
						 break;
					 case 6:///sort course based on current number of students registered 
						 Collections.sort(courselist, new Course.Coursebycs());
						 break;
					 case 7://exit
						 exit = true;
						 break;	 	 
					}	
				}
			}
		}
		
		//if is a student 
		else {
			//login check
			System.out.println("Enter student username:");
			String susername = scan.nextLine();
			System.out.println("Enter student passward:");
			String spsw = scan.nextLine();
		    boolean sexit = false;
		    boolean login = false;
		    for(int i = 0;i<studentlist.size();i++) {
		    	if(susername.equals(studentlist.get(i).getUsername()) && spsw.equals(studentlist.get(i).getPassword())) {
		    		login = true;
		    		System.out.println("Student login successfully");
		    	}
		    }
		    if(login==false) {
		    	System.out.print("invalid password and username");
		    }
			while(login==true && sexit!=true) {
				System.out.println("Course Managemnet:");
				System.out.println("1.View all courses"+"\n"+"2.View all courses that are not full"+"\n"+"3.Register in a course"+"\n"+"4.Withdrawfromacourse"+"\n"+"5.View all courses that the current student is registered in"+"\n"+"6.Exit");
				System.out.println("which operation? Enter 1,2,3,4,5,or 6:");
				int choice = Integer.parseInt(scan.nextLine());
				switch(choice)
				{
				  case 1://View all courses
					  for (Course cc:courselist) {
						  cc.studentviewall();
					  }
					  break;
				  case 2://View all courses that are not full
					  for (Course ccc:courselist) {						  if (ccc.getCurrentS()< ccc.getMaxS()) {
							  ccc.studentviewall();
						  }
					  }
					  break;
				  case 3://Register in a course
					  System.out.println("Enter Course Name:");
					  String cname = scan.nextLine();
					  System.out.println("Enter Course section:");
					  int csection = Integer.parseInt(scan.nextLine());
					  System.out.println("Enter first Name:");
					  String sfn = scan.nextLine();
					  System.out.println("Enter last name:");
					  String sln = scan.nextLine();
					  for (int i=0;i<courselist.size();i++) {
						  if (courselist.get(i).getName().equals(cname)&& courselist.get(i).getSection()==csection) {
							  for (int j = 0;j<studentlist.size();j++) {
								  if((studentlist.get(j).getFirstName().equals(sfn))&&(studentlist.get(j).getLastName().equals(sln))){
									  
									  courselist.get(i).addStudent(studentlist.get(j));
									
									  studentlist.get(j).addedtocourse(courselist.get(i));
								  }
							  }
						  }
					  }
					  break;
				  case 4://Withdraw from a course
					  System.out.println("Enter Course Name:");
					  String wcname = scan.nextLine();
					  System.out.println("Enter first Name:");
					  String ssfn = scan.nextLine();
					  System.out.println("Enter last name:");
					  String ssln = scan.nextLine();
					  for(int i = 0;i<courselist.size();i++) {
						  if (courselist.get(i).getName().equals(wcname)) {
							  for(int j=0;j<studentlist.size();j++) {
								  if(studentlist.get(j).getFirstName().equals(ssfn)&&studentlist.get(j).getLastName().equals(ssln)) {
									  courselist.get(i).removestudnet(studentlist.get(j));
									  studentlist.get(j).withdrawcourse(courselist.get(i));
								  }
							  }  
						  }
					  }
					  break;
				  case 5://View all courses that the current student is registered in
					  for(int i=0;i<studentlist.size();i++) {
						  if(studentlist.get(i).getPassword().equals(spsw)&&studentlist.get(i).getUsername().equals(susername)) {
							  studentlist.get(i).registeredcourse();
						  }
					  }
					  break;
				  case 6://exit
					  sexit = true;
					  break;
				}
			}
		}
		
		//serialize ArrayList of course into a file & serialize ArrayList of student to a file 
		
		try {
			FileOutputStream fos = new FileOutputStream("Course.ser");
			FileOutputStream foss = new FileOutputStream("Student.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			ObjectOutputStream ooss = new ObjectOutputStream(foss);
			
			oos.writeObject(courselist);
		
			
		    ooss.writeObject(studentlist);
			
			
			oos.close();
			fos.close();
			ooss.close();
			foss.close();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		
	    
		
		
		
		
	}
}

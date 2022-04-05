package com.cs157c.team2.courseRecommender;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Record;

import static org.neo4j.driver.Values.parameters;

import java.util.Scanner;

/**
 * This is a basic application to test the main database operations using a Java application.
 * The following operations shall be tested:
 * 1) Creating a node with properties (signing up + logging in)
 * 2) Creating a relationship between nodes (selecting interests + showing which classes a faculty member teaches)
 * 3) Finding nodes based on relationships of a target node (recommend courses to students)
 */

// Resources:
// https://neo4j.com/docs/java-manual/current/get-started/#java-driver-get-started-hello-world-example
// https://neo4j.com/docs/api/java-driver/current/
public class Recommender implements AutoCloseable {
	
	private static Driver driver;
	private static Scanner scanner;
	private static boolean loggedIn;
	private static String userType;
	private static int userId;
	
	public Recommender(String uri, String user, String password) {
		driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
		scanner = new Scanner(System.in);
		loggedIn = false;
		userType = "";
		userId = -1;
	}
	
	@Override
	public void close() throws Exception {
		scanner.close();
		driver.close();		
	}

    /* -------------------- Methods for functional requirements ------------------------ */
    
	// TODO have sign up and login methods with no parameters be used to get user input, then
	// create signup/login methods that accept parameters that is called from signUp()/login()
	// this way, hardcoded values can be used for testing
	
    // sign up
    private static void signUp() {
    	
    	System.out.println("SIGN UP");
    	System.out.println("--------------------------------");
    	
    	// TODO implement checks for correct input / create only 2 options for users to select with frontend
    	System.out.print("Are you signing up as a [Student] or as [Faculty]? ");
    	String type = scanner.next();
    	scanner.nextLine();
    	
    	System.out.print("Input your name: ");
    	String name = scanner.nextLine();
    	
    	// TODO check that this id is not in the database already, since ids are unique
    	System.out.print("Input your SJSU ID: ");
    	int id = scanner.nextInt();
    	scanner.nextLine();
    	
    	System.out.print("Input your password: ");
    	int password = scanner.nextInt();
    	scanner.nextLine();
    	
    	try (Session session = driver.session()) {
    	
    		if (type.equals("Student")) {
    			session.writeTransaction(tx -> 
    			tx.run("CREATE (a:Student {name: $name, id: $id, password: $password})", 
    					parameters("type", type, "name", name, "id", id, "password", password)));
    		} else {
    			session.writeTransaction(tx -> 
    			tx.run("CREATE (a:Faculty {name: $name, id: $id, password: $password})", 
    					parameters("type", type, "name", name, "id", id, "password", password)));
    		}

    	}
    	
    	System.out.println("Successfully signed up.");
    
    }
    
    // log in
    private static void login() {
    	
    	System.out.println("\nLOGIN");
    	System.out.println("--------------------------------");
    	
    	System.out.print("Are you a [Student] or [Faculty]? ");
    	String type = scanner.next();
    	scanner.nextLine();
    	
    	System.out.print("Input your SJSU ID: ");
    	int id = scanner.nextInt();
    	scanner.nextLine();
    	
    	System.out.print("Input your password: ");
    	int password = scanner.nextInt();
    	scanner.nextLine();
    	
    	try (Session session = driver.session()) {
    		
    		Result result;
    		
    		if (type.equals("Student")) {
    			result = session.run("MATCH (a:Student {id: $id, password: $password}) RETURN a",
        				parameters("id", id, "password", password));
    		} else {
    			result = session.run("MATCH (a:Faculty {id: $id, password: $password}) RETURN a",
        				parameters("id", id, "password", password));
    		} 		
    		
    		if (result.hasNext()) {
    			Record record = result.next();
    			System.out.println("Successfully logged in.\n");
    			loggedIn = true;
    			userType = type;
    			userId = id;
    		} else {
    			System.out.println("Invalid ID or password.\n");
    		}
    		
    	}
    
    }
    
    // admin can add interests to the system
    private static void addNewInterest() {
    	
    	System.out.println("\nADDING INTEREST TO THE SYSTEM");
    	System.out.println("--------------------------------");
    	
    	System.out.print("Enter the interest: ");
    	String interest = scanner.nextLine(); 
    	
    	// add the interest if it's not already in the database
    	try (Session session = driver.session()) {
    		
    		Result result = session.run("MATCH (d:Domain {topic: $interest}) RETURN d", parameters("interest", interest));
    		
    		if (result.hasNext()) {
    			System.out.println("That interest/domain is already in the database.\n");
    		} else {
    			session.writeTransaction(tx -> 
    			tx.run("CREATE (a:Domain {topic: $interest})", parameters("interest", interest)));
    			System.out.println("Successfully added a new interest to the database.\n");
    		}
    		
    	}    	
    	
    }
    
    // student can select interests
    private static void selectInterest() {
    	
    	System.out.println("\nSELECTING INTEREST");
    	System.out.println("--------------------------------");
    	
    	System.out.print("Choose an interest: ");
    	String interest = scanner.nextLine(); 
    	
    	try (Session session = driver.session()) {
    	
    		// https://neo4j.com/docs/java-manual/current/cypher-workflow/
    		session.writeTransaction(tx ->
    			tx.run("MATCH (a:Student {id: $id}) " +
    					"MATCH (b:Domain {topic: $interest}) " + 
    					"CREATE (a)-[:INTERESTED_IN]->(b)",
    				parameters("id", userId, "interest", interest)));
    	
			System.out.println("Successfully added the domain to your list of interests.\n");

    	}
    	
    }
    
    // faculty can add courses
    // faculty can add a course to the system
    private static void addNewCourse() {
    
    	System.out.println("\nADDING A COURSE TO THE SYSTEM");
    	System.out.println("--------------------------------");
    	
    	System.out.print("Enter the course id: ");
    	String courseId = scanner.nextLine(); 
    	
    	System.out.print("Enter the course name: ");
    	String courseName = scanner.nextLine(); 
    	
    	System.out.print("Enter the domain that this course covers: ");
    	String domain = scanner.nextLine(); 
    	
    	try (Session session = driver.session()) {
    		
    		// add the course
    		session.writeTransaction(tx ->
    		tx.run("CREATE (c:Course {course_id: $courseId, course_name: $courseName})", 
    				parameters("courseId", courseId, "courseName", courseName)));
    		
    		// create the relationship between the course and the domain
    		session.writeTransaction(tx ->
    		tx.run("MATCH (a:Domain {topic: $domain}) " +
    				"MATCH (b:Course {course_id: $courseId}) " +
    				"CREATE (a)-[:IS_COVERED_BY]->(b)", 
    				parameters("domain", domain, "courseId", courseId)));
    	
    	}
    	
    	System.out.println("Successfully added the course to the system.\n");
    	
    }
    
    // faculty can mark a class as they teach it
    
    // faculty can mark the courses that they teach
    private static void teachesCourse() {

    	System.out.println("\nFACULTY MARKING A COURSE AS ONE THAT THEY TEACH");
    	System.out.println("---------------------------------------------------");
    	
    	System.out.print("Enter the course id: ");
    	String courseId = scanner.nextLine(); 
    	
		try (Session session = driver.session()) {
        	
    		session.writeTransaction(tx ->
    		tx.run("MATCH (a:Faculty {id: $id}) " +
    				"MATCH (b:Course {course_id: $courseId) " +
    				"CREATE (a)-[:TEACHES]->(b)",
    				parameters("id", userId, "courseId", courseId)));
    		
    	}
		
		System.out.println("Successfully marked the course as one that you teach.\n");
    	
    }
    
    // student can get recommendations based on their interests
    private static void getRecommendations() {
    	// TODO
    }
    
    // faculty can view all of the courses they teach
    
    // students can view their list of interests
    
	/* main method */
	public static void main(String[] args) throws Exception {
        
    	try (Recommender rec = new Recommender("bolt://localhost:7687", "neo4j", "password")) {
    		
    		// user has to sign up or log in
    		while (!loggedIn) {
    		
    			System.out.print("[SignUp] or [Login] (enter \"Quit\" to quit): ");
    			String input = scanner.next();
    			System.out.println();
    			
    			if (input.equals("Login")) {
    				login();
    			} else if (input.equals("SignUp")) {
    				signUp();
    				login();
    			} else {
    				System.out.println("Quitting application.");
    				return;
    			}
    			
    		}
    		
    		// user is logged in
    		if (userType.equals("Student")) { // example of marking a domain or faculty member as interested in
				
    			addNewInterest();
    			selectInterest();
    			
			} else if (userType.equals("Faculty")) { // example of adding a course and marking a course as one they teach
				
				addNewCourse();
				teachesCourse();
				
			}
    		
    		rec.close();
    		
    	}
  
    }
    
}

/**
 * @author Benjamin Hendrix
 * @class CAP4601 Intro to AI
 * @date 01 May 2021
 * 
 * UserDatabase is a container ArrayList for all of the Users
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads in the file UserList.txt and parses the information for each user
 * Stores this information in ArrayLists
 */
public class UserDatabase {
	ArrayList<User> Users = new ArrayList<User>();
	
	UserDatabase () throws FileNotFoundException {
		File file = new File("UserList.txt");
		Scanner scnr = new Scanner(file);
		scnr.useDelimiter(",|\\n|\\r");
		String next = scnr.nextLine();
		String name;
		int[] ratings = new int[15];
		
		while (scnr.hasNext()) {
			next = scnr.next();
			if (next.isEmpty()) {
				next = scnr.next();
			}
			name = next;
			for (int i=0; i < 15; i++) {
				ratings[i] = scnr.nextInt();
			}
			User user = new User(name, ratings);
			AddUser(user);
		}
		scnr.close();
	}
	
	/**
	 * Well formats the List of Users and returns a string of them
	 */
	public String toString() {
		String to_string = "";
		for (int i=0; i < Users.size(); i++) {
			to_string += Users.get(i).GetName();
			if (i != Users.size() - 1) {
				to_string +=", ";
			}
		}
		return to_string;
	}
	
	/**
	 * Checks if the named passed is a name within the UserDatabase
	 * @param name the name to be checked
	 * @return true or false if the name was found or not respectively
	 */
	int isValidUser (String name) {
		int index = -1;
		
		for (int i=0; i < Users.size(); i++) {
			if (Users.get(i).GetName().equals(name)) {
				index = i;
			}
		}
		if (index < 0) {
			System.out.println("Could not find that user");
		}
		return index;
	}
	
	ArrayList<User> GetUsers() {return Users;}
	void AddUser(User u) {Users.add(u);}
}

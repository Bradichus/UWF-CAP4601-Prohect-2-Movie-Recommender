import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserDatabase {
	
	ArrayList<User> Users = new ArrayList<User>();
	
	UserDatabase() throws FileNotFoundException {
		File file = new File("UserList.txt");
		Scanner scnr = new Scanner(file);
		scnr.useDelimiter(",|\\n|\\r");
		String next = scnr.nextLine();
		String name;
		int[] ratings = new int[15];
		
		while(scnr.hasNext())
		{
			next = scnr.next();
			if(next.isEmpty()) {
				break;
			}
			name = next;
			for(int i=0; i < 15; i++) {
				ratings[i] = scnr.nextInt();
			}
			User user = new User(name, ratings);
			AddUser(user);
		}
		scnr.close();
	}
	
	ArrayList<User> GetUsers() {return Users;}
	
	void AddUser(User u) {Users.add(u);}
}

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	MovieDatabase movies;
	UserDatabase users;
	
	/**
	 * 
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String args[]) throws FileNotFoundException {
		Menu myRecommender = new Menu();
	}
	
	/**
	 * @throws FileNotFoundException 
	 * 
	 */
	public Menu() throws FileNotFoundException {
		try {
			this.movies = new MovieDatabase();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			this.users = new UserDatabase();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		MainMenu();
	}
	
	/**
	 * 
	 */
	public void MainMenu() {
		Scanner scnr = new Scanner(System.in);
		boolean quit = false;
		int option;
		System.out.println("  -=| MOVIE RECOMMENDER |=-  ");
		System.out.println("");
		
		do {
			do {
				MenuOptions();
				option = scnr.nextInt();
			} while(!IsValidOption(option));
			
			switch(option) {
			case 1:
				//Print Moive Database
				break;
			case 2:
				//Print User Database
				break;
			case 3:
				//Add a user
				users.AddUser(CreateUser(scnr));
				break;
			case 4:
				//Recommend a movie for user
				break;
			case 5:
				quit = true;
			}
		} while(!quit);
		
		scnr.close();
	}
	
	public User CreateUser(Scanner s) {
		User u;
		
		System.out.print("What is the user's name: ");
		String name = s.nextLine();
		
		u = new User(name);
		
		return u;
	}
	
	/**
	 * 
	 */
	public void MenuOptions() {
		System.out.println("1) View the Movie Database");
		System.out.println("2) View the User Database");
		System.out.println("3) Add a new user to the User Database");
		System.out.println("4) Ask for a user's next recommended movie");
		System.out.println("5) Quit");
		System.out.println("");
		System.out.print("Enter an option number to proceed: ");
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean IsValidOption(int x) {
		if(0 < x && x < 6) {
			return true;
		}
		System.out.println("~> Enter a valid number from the list");
		return false;
	}
}

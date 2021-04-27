import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	ArrayList<MovieDatabase> movies;
	ArrayList<UserDatabase> users;
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		Menu myRecommender = new Menu();
	}
	
	/**
	 * 
	 */
	public Menu() {
		this.movies = new ArrayList<MovieDatabase>();
		this.users = new ArrayList<UserDatabase>();
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

/**
 * @author Benjamin Hendrix and Brandon Benthal 
 * @class CAP4601 Intro to AI
 * @date 01 May 2021
 * 
 * Menu acts as the main driver for the program.
 * All user interaction with the program comes from this class
 */

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Menu {
	MovieDatabase movies;
	UserDatabase users;
	CorrelationAgent myAgent = new CorrelationAgent();
	
	/**
	 * Create Menu object and proceeeds to load main driver for moive recommender system
	 * @param args no current args parsed
	 * @throws FileNotFoundException 
	 */
	public static void main (String args[]) throws FileNotFoundException {
		Menu myRecommender = new Menu();
	}
	
	/**
	 * Default Constructor
	 * Calls MainMenu which is the driver for this movie recommender
	 * @throws FileNotFoundException if the UserList.txt or MovieList.txt is not found
	 */
	Menu () throws FileNotFoundException {
		if (LoadMovieDatabase() && LoadUserDatabase()) {
			MainMenu();
		}
		else {
			System.out.println("Please create a MovieList.txt and/or UserList.txt file");
		}
	}
	
	/**
	 * Creates the MovieDatabase object which reads in the data from file by default
	 * @return true or false if the Movie database was successfully loaded or not
	 */
	boolean LoadMovieDatabase () {
		try {
			movies = new MovieDatabase();
		}
		catch (FileNotFoundException e) {
			System.out.println("MovieList.txt not found");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Creates the UserDatabase object which reads in the data from file by default
	 * @return true or false if the User database was successfully loaded or not
	 */
	boolean LoadUserDatabase () {
		try	{
			users = new UserDatabase();
		}
		catch (FileNotFoundException e) {
			System.out.println("UserList.txt not found");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * Main driver for Movie Recommender
	 * Contains the main logic for displaying menu options, recording choices, and calling appropriate functions
	 */
	void MainMenu () {
		Scanner scnr = new Scanner(System.in);
		boolean quit = false;
		int option;
		
		System.out.println("  -=| MOVIE RECOMMENDER |=-  ");
		System.out.println("");
		
		do {
			do {
				MenuOptions();
				option = scnr.nextInt();
				scnr.nextLine();
			} while (!IsValidOption(option));
			
			switch (option) {
			case 1:
				PrintMovieDatabase();
				break;
			case 2:
				PrintUserDatabase();
				break;
			case 3:
				CreateUser(scnr);
				break;
			case 4:
				AddRatingToUser(scnr);
				break;
			case 5:
				RecommendMovieToUser(scnr);
				isRecommendationGood(scnr);
				break;
			case 6:
				quit = true;
			}
		} while (!quit);
		
		printFeedbackReport();
		System.out.println("\n==========    END     ==========");
		scnr.close();
	}
	
	/**
	 * Prints Feedback from CorrelationAgent on the accuracy of the movies recommended 
	 */
	void printFeedbackReport () {
		System.out.println("\n==========  FEEDBACK  ==========");
		System.out.println("The feedback report for Movie Recommender was as follows: ");
		System.out.println("Positive recommendations: "+myAgent.getFeedbackScore());
		System.out.println("Negative recommendations: "+(myAgent.getFeedbackCount()-myAgent.getFeedbackScore()));
		System.out.println("Total recommendations: "+myAgent.getFeedbackCount());
		System.out.print("Percent correct recommendations: ");
		System.out.printf("%.2f", myAgent.getFeedbackAveragePercent());
	}
	
	/**
	 * Asks user for feedback on if the movie recommended was a good one
	 * Passes feedback to CorrelationAgent to include in feddback report at end
	 * @param s is a Scanner object to record user input
	 */
	void isRecommendationGood (Scanner s) {
		boolean feedback;
		String answer;
		
		System.out.println("Do you like this movie recommendation, yes or no?");
		System.out.print("Enter y/n: ");
		answer = s.next();
		System.out.println("");
		
		if(answer.contains("y") || answer.contains("Y")) {
			System.out.println("Positive feedback received\n");
			feedback = true;
		}
		else {
			System.out.println("Negative feedback received\n");
			feedback = false;
		}
		
		myAgent.receiveFeedback(feedback);
	}
	
	/**
	 * Asks the user for a name and validates against the database
	 * Calls a SetARating function off of the selected user to record a new rating
	 * @param s  is a Scanner object to record user input
	 */
	void AddRatingToUser (Scanner s) {
		String name;
		int index;
		
		System.out.println("\n========== Add Rating ==========");
		
		do {
			System.out.println(users.toString());
			System.out.print("\nWhich user do you want to add a rating to: ");
			name = s.nextLine();
			index = users.isValidUser(name);
		} while (index < 0);
		
		users.GetUsers().get(index).SetARating(s, movies);
	}
	
	/**
	 * Asks the user for a name and create a new user in the database
	 * @param s is a Scanner object to record user input
	 */
	void CreateUser (Scanner s) {
		System.out.print("\nWhat is the user's name: ");
		String name = s.nextLine();
		users.AddUser(new User(name));
		System.out.println("");
	}
	
	/**
	 * Asks for a user name and checks if valid from the database
	 * Calls CorrelationAgent to find the recommended movie and prints to the screen
	 * @param s is a Scanner object to record user input
	 */
	void RecommendMovieToUser (Scanner s) {
		String name;
		int index;
		
		System.out.println("\n======== Movie Recommend =======");
		
		do {
			System.out.println(users.toString());
			System.out.print("\nWhich user do you want to recommend a movie to: ");
			name = s.nextLine();
			index = users.isValidUser(name);
		} while (index < 0);
		
		System.out.println("Recommendation: "+ myAgent.RecommendMovieToUser(users.GetUsers().get(index), users, movies, index));
		System.out.println("");
	}
	
	/**
	 * Prints the movie database and their details
	 */
	void PrintMovieDatabase () {
		System.out.println("\n========== Movie List ==========");
		for (int i=0; i < movies.getMovies().size(); i++) {
			System.out.println(""+ (i+1)+") "+movies.getMovies().get(i).toString());
		}
		System.out.println("");
	}
	
	/**
	 * Prints the user database and their ratings
	 */
	void PrintUserDatabase () {
		System.out.println("\n========== Users List ==========");	
		for (int i=0; i < users.GetUsers().size(); i++) {
			System.out.println(""+(i+1)+") "+users.GetUsers().get(i).toString());
		}
		System.out.println("");
	}
	
	/**
	 * Prints all of the current menu options to the screen
	 */
	void MenuOptions () {
		System.out.println("========== Main  Menu ==========");
		System.out.println("1) View the Movie Database");
		System.out.println("2) View the User Database");
		System.out.println("3) Add a new user to the User Database");
		System.out.println("4) Add a rating to a user");
		System.out.println("5) Recommend movie for user");
		System.out.println("6) Quit");
		System.out.println("");
		System.out.print("Enter an option number to proceed: ");
	}
	
	/**
	 * Evaluates if the menu choice was within the selection list
	 * @return true or false based on if was within range or not respectively 
	 */
	boolean IsValidOption (int x) {
		if(0 < x && x < 7) {
			return true;
		}
		System.out.println("~> Enter a valid number from the list\n");
		
		return false;
	}
}

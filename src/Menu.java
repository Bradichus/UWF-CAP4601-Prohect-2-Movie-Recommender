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
		if(LoadMovieDatabase() && LoadUserDatabase()) {
			MainMenu();
		}
		else {
			System.out.println("Please create a MovieList.txt and UserList.txt file");
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean LoadMovieDatabase() {
		try {
			this.movies = new MovieDatabase();
		} catch (FileNotFoundException e) {
			System.out.println("MovieList.txt not found");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean LoadUserDatabase() {
		try {
			this.users = new UserDatabase();
		} catch (FileNotFoundException e) {
			System.out.println("UserList.txt not found");
			e.printStackTrace();
			return false;
		}
		return true;
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
				PrintMovieDatabase();
				break;
			case 2:
				//Print User Database
				PrintUserDatabase();
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
		System.out.println("\n==========    END     ==========");
		scnr.close();
	}
	
	/**
	 * 
	 */
	public void PrintMovieDatabase()
	{
		System.out.println("\n========== Movie List ==========");
		for(int i=0; i < movies.getMovies().size(); i++)
		{
			System.out.println(""+ (i+1)+") "+movies.getMovies().get(i).toString());
//			System.out.println(", Avg Rating: "+movies.getMovies().get(i).getRating());
		}
		System.out.println("================================\n");
	}
	
	/**
	 * 
	 */
	public void PrintUserDatabase()
	{
		System.out.println("\n========== Users List ==========");	
		for(int i=0; i < users.GetUsers().size(); i++)
		{
			System.out.println(""+(i+1)+") "+users.GetUsers().get(i).toString());
			//System.out.println("");
		}
		System.out.println("================================\n");
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	public User CreateUser(Scanner s) {
		User u;
		
		System.out.print("What is the user's name: ");
		String name = s.nextLine();
		
		u = new User(name);
		System.out.println("");
		return u;
	}
	
	/**
	 * 
	 */
	public void MenuOptions() {
		System.out.println("========== Main  Menu ==========");
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

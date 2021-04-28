import java.io.FileNotFoundException;
import java.util.Scanner;

public class Menu {
	MovieDatabase movies;
	UserDatabase users;
	CorrelationAgent myAgent = new CorrelationAgent();
	
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
				scnr.nextLine();
			} while(!IsValidOption(option));
			
			switch(option) {
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
				break;
			case 6:
				quit = true;
			}
		} while(!quit);
		System.out.println("\n==========    END     ==========");
		scnr.close();
	}
	
	/**
	 * 
	 * @param s
	 */
	public void AddRatingToUser(Scanner s)
	{
		String name;
		int index;
		System.out.println("\n========== Add Rating ==========");
		
		do {
			System.out.println(users.toString());
			System.out.print("\nWhich user do you want to add a rating to: ");
			name = s.nextLine();
			name = name.strip();
			index = users.isValidUser(name);
		} while(index < 0);
		
		users.GetUsers().get(index).SetARating(s, movies);
	}
	
	/**
	 * 
	 * @param s
	 */
	public void CreateUser(Scanner s)
	{
		System.out.print("\nWhat is the user's name: ");
		String name = s.nextLine();
		users.AddUser(new User(name));
		System.out.println("");
	}
	
	/**
	 * 
	 * @param s
	 */
	public void RecommendMovieToUser(Scanner s)
	{
		String name;
		int index;
		System.out.println("\n======== Movie Recommend =======");
		
		do {
			System.out.println(users.toString());
			System.out.print("\nWhich user do you want to recommend a movie to: ");
			name = s.nextLine();
			name = name.strip();
			index = users.isValidUser(name);
		} while(index < 0);
		System.out.println("Recommendation: "+ myAgent.RecommendMovieToUser(users.GetUsers().get(index), users, movies, index));
		System.out.println("");
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
		}
		System.out.println("");
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
		}
		System.out.println("");
	}
	
	/**
	 * 
	 */
	public void MenuOptions() {
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
	 * 
	 * @return
	 */
	public boolean IsValidOption(int x) {
		if(0 < x && x < 7) {
			return true;
		}
		System.out.println("~> Enter a valid number from the list\n");
		return false;
	}
}

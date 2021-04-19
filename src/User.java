/**
 * Project 2 Movie Recommender
 * CAP4601 Intro to AI
 * 
 * @author Benjamin Hendrix
 * 
 * User class manages the name and ratings of each user for each movie
 *
 */
public class User {
	static final String DEFAULT_NAME = "<NO_NAME>";
	String name;
	static final int DEFAULT_NUM_MOVIES = 5;
	int numMovies = 5;
	int[] ratings = new int [numMovies];
	
	/**
	 * Default constructor (chained constructor)
	 * @see Parameterized constructor with one argument of String name 
	 */
	public User()
	{
		this(DEFAULT_NAME);
	}
	
	/**
	 * One parameterized constructor
	 * @param name
	 * @param ratings is initialized to -1 as a stub
	 */
	public User(String name)
	{
		this.SetName(name);
		this.SetNumMovies(DEFAULT_NUM_MOVIES);
		this.DefineRatings();
	}
	
	/**
	 * Two parameterized constructor
	 * @param name is passed as an argument to SetName 
	 * @param ratings is passed as an argument to SetRatings
	 */
	public User(String name, int[] ratings)
	{
		this.SetName(name);
		this.SetNumMovies(ratings.length);
		this.SetRatings(ratings);
	}
	
	/**
	 * Defines the ratings array to the size of movies
	 * Initializes the ratings to a standardized score
	 */
	private void DefineRatings()
	{
		this.ratings = new int [this.GetNumMovies()];
		this.SetInitialRatings();
	}
	
	/**
	 * Initializes all of the moves to a standardized scored of -1
	 */
	private void SetInitialRatings()
	{
		for(int i=0; i < this.GetNumMovies(); ++i)
		{
			this.SetSingleRating(i, -1);
		}
	}
	
	/**
	 * Mutator
	 * @param name is the name of the user
	 */
	public void SetName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Accessor
	 * @return the name of the user
	 */
	public String GetName()
	{
		return this.name;
	}
	
	/**
	 * Mutator
	 * @param numMovies the number of movies to be rated
	 */
	public void SetNumMovies(int numMovies)
	{
		this.numMovies = numMovies;
	}
	
	/**
	 * Accessor
	 * @return the number of movies that can be rated
	 */
	public int GetNumMovies()
	{
		return this.numMovies;
	}
	
	/**
	 * Mutator
	 * @param ratings is the values rated for each movie
	 */
	void SetRatings(int[] ratings)
	{
		this.ratings = ratings;
	}
	
	/**
	 * Accessor
	 * @return the ratings array which is the values rated for each movie
	 */
	int[] GetRatings()
	{
		return this.ratings;
	}
	
	/**
	 * Mutator
	 * @param loc the location on the ratings array to set
	 * @param rating the value to set on the ratings array
	 */
	void SetSingleRating(int loc, int rating)
	{
		this.ratings[loc] = rating;
	}
	
	/**
	 * Accessor
	 * @param loc the location on the ratings array
	 * @return the value at the location in the ratings array
	 */
	int GetSingleRating(int loc)
	{
		return this.ratings[loc];
	}
}

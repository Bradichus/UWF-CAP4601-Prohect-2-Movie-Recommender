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
	String name;
	int numMovies = 5;
	int[] ratings = new int [numMovies];
	
	/**
	 * Default constructor
	 * @param name is initialized to <NO_NAME> as a stub
	 * @param ratings is initialized to -1 as a stub 
	 */
	public User()
	{
		this.SetName("<NO_NAME>");
		for(int i=0; i < this.GetNumMovies(); ++i)
		{
			this.SetSingleRating(i, -1);
		}
	}
	
	/**
	 * One parameterized constructor
	 * @param name
	 * @param ratings is initialized to -1 as a stub
	 */
	public User(String name)
	{
		this.SetName(name);
		for(int i=0; i < this.GetNumMovies(); ++i)
		{
			this.SetSingleRating(i, -1);
		}
	}
	
	/**
	 * Two parameterized constructor
	 * @param name is passed as an argument to SetName 
	 * @param ratings is passed as an argument to SetRatings
	 */
	public User(String name, int[] ratings)
	{
		this.SetName(name);
		this.SetRatings(ratings);
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

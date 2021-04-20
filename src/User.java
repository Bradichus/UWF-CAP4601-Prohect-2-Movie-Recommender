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
	static final int DEFAULT_NUM_MOVIES = 10;
	int[] ratings = new int [DEFAULT_NUM_MOVIES];
	
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
		for(int i=0; i < DEFAULT_NUM_MOVIES; ++i)
		{
			this.ratings[i] = -1;
		}
	}
	
	/**
	 * Two parameterized constructor
	 * @param name is passed as an argument to SetName 
	 * @param ratings is passed as an argument to initialize the ratings
	 */
	public User(String name, int[] ratings)
	{
		this.SetName(name);
		for(int i=0; i < DEFAULT_NUM_MOVIES; ++i)
		{
			this.ratings[i] = ratings[i];
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
}

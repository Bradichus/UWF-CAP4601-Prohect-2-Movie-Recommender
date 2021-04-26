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
	static final int DEFAULT_NUM_MOVIES = 15;
	double[] ratings = new double [DEFAULT_NUM_MOVIES];
	double[] avgRatingsMinusMean = new double [DEFAULT_NUM_MOVIES];
	boolean[] hasRatedMovie = new boolean [DEFAULT_NUM_MOVIES];
	
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
	 * @param ratings is initialized to 0 as a stub
	 */
	public User(String name)
	{
		this.SetName(name);
		for(int i=0; i < DEFAULT_NUM_MOVIES; ++i)
		{
			this.ratings[i] = 0.0;
			this.hasRatedMovie[i] = false;
		}
	}
	
	/**
	 * Two parameterized constructor
	 * @param name is passed as an argument to SetName 
	 * @param ratings is passed as an argument to initialize the ratings
	 */
	public User(String name, double[] ratings)
	{
		this.SetName(name);
		for(int i=0; i < DEFAULT_NUM_MOVIES; ++i)
		{
			this.ratings[i] = ratings[i];
			if(ratings[i] != 0.0)
			{
				this.hasRatedMovie[i] = true;
			}
			else
			{
				this.hasRatedMovie[i] = false;
			}
		}
	}
	
	public void CalculateAvgRatingsMinusMean()
	{
		double average = this.GetAverageRatings();
		
		for(int i=0; i < User.DEFAULT_NUM_MOVIES; i++)
		{
			if(this.ratings[i] != 0.0)
			{
				this.avgRatingsMinusMean[i] = this.ratings[i] - average;
			}
			else
			{
				this.avgRatingsMinusMean[i] = 0.0;
			}
		}
	}

	public void AddRatingToMovie(int loc, double rating)
	{
		this.ratings[loc] = rating;
		this.hasRatedMovie[loc] = true;
	}
	
	/**
	 * 
	 * @return
	 */
	public double GetAverageRatings()
	{
		double sum = 0.0;
		int count = 0;
		
		for(int i=0; i < User.DEFAULT_NUM_MOVIES; i++)
		{
			if(this.ratings[i] != 0.0)
			{
				sum += this.ratings[i];
				count++;
			}
		}
		
		if(count == 0)
		{
			return 0.0;
		}
		
		return sum / count;
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
	void SetRatings(double[] ratings)
	{
		this.ratings = ratings;
	}
	
	/**
	 * Accessor
	 * @return the ratings array which is the values rated for each movie
	 */
	double[] GetRatings()
	{
		return this.ratings;
	}
	
	double[] GetAvgRatingsMinusMean()
	{
		return this.avgRatingsMinusMean;
	}
	
	boolean[] GetHasRatedMovie()
	{
		return this.hasRatedMovie;
	}
}

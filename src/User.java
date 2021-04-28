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
	int[] ratings = new int [DEFAULT_NUM_MOVIES];
	int[] avgRatingsMinusMean = new int [DEFAULT_NUM_MOVIES];
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
			this.ratings[i] = 0;
		}
		CheckHasRatedMovie();
		CalculateAvgRatingsMinusMean();
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
		CheckHasRatedMovie();
		CalculateAvgRatingsMinusMean();
	}
	
	/**
	 * 
	 * @param string
	 * @param length
	 * @return
	 */
	public String fixedLengthString(String string, int length)
	{
	    return String.format("%1$"+length+ "s", string);
	}
	
	/**
	 * 
	 * @return
	 */
	public String toString()
	{
		String to_string = "";
		to_string += fixedLengthString(GetName(), 6);
		to_string += " - ";
		for(int i=0; i < GetRatings().length; i++)
		{
			if(GetRatings()[i] < 10) {
				to_string += "0";
			}
			to_string += GetRatings()[i];
			if(i != GetRatings().length - 1)
			{
				to_string += ", ";
			}
		}
		
		return to_string;
	}
	
	/**
	 * 
	 */
	public void CalculateAvgRatingsMinusMean()
	{
		int average = this.GetAverageRatings();
		
		for(int i=0; i < User.DEFAULT_NUM_MOVIES; i++)
		{
			if(this.ratings[i] != 0)
			{
				this.avgRatingsMinusMean[i] = this.ratings[i] - average;
			}
			else
			{
				this.avgRatingsMinusMean[i] = 0;
			}
		}
	}

	/**
	 * 
	 * @param loc
	 * @param rating
	 */
	public void AddRatingToMovie(int loc, int rating)
	{
		this.ratings[loc] = rating;
		CheckHasRatedMovie();
		CalculateAvgRatingsMinusMean();
	}
	
	/**
	 * 
	 * @return
	 */
	public int GetAverageRatings()
	{
		int sum = 0;
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
			return 0;
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
	public void SetRatings(int[] ratings)
	{
		this.ratings = ratings;
		CheckHasRatedMovie();
	}
	
	/**
	 * 
	 */
	public void CheckHasRatedMovie()
	{
		for(int i=0; i<DEFAULT_NUM_MOVIES; i++)
		{
			this.hasRatedMovie[i] = (ratings[i] != 0) ? true : false;
		}
	}
	
	/**
	 * 
	 */
	public void PrintRatings()
	{
		for(int i=0; i< DEFAULT_NUM_MOVIES; i++)
		{
			System.out.println("Movie "+i+" ratings is "+ratings[i]);
		}
	}
	
	/**
	 * Accessor
	 * @return the ratings array which is the values rated for each movie
	 */
	public int[] GetRatings()
	{
		return this.ratings;
	}
	
	/**
	 * 
	 * @return
	 */
	public int[] GetAvgRatingsMinusMean()
	{
		return this.avgRatingsMinusMean;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean[] GetHasRatedMovie()
	{
		return this.hasRatedMovie;
	}
}

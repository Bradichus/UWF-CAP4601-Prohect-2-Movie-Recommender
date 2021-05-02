/**
 * @author Brandon Benthal & Benjamin Hendrix
 * @class CAP4601 Intro to AI
 * @date 01 May 2021
 * 
 * User class manages the name and ratings of each user for each movie
 */

import java.util.Scanner;

public class User {
	String name;
	static final String DEFAULT_NAME = "<NO_NAME>";
	static final int DEFAULT_NUM_MOVIES = 15;
	int[] ratings = new int [DEFAULT_NUM_MOVIES];
	int[] avgRatingsMinusMean = new int [DEFAULT_NUM_MOVIES];
	boolean[] hasRatedMovie = new boolean [DEFAULT_NUM_MOVIES];
	
	public User () {
		this(DEFAULT_NAME);
	}
	
	User (String name) {
		SetName(name);
		for (int i=0; i < DEFAULT_NUM_MOVIES; ++i) {
			ratings[i] = 0;
		}
		CheckHasRatedMovie();
		CalculateAvgRatingsMinusMean();
	}
	
	User (String name, int[] newRatings) {
		SetName(name);
		for (int i=0; i < DEFAULT_NUM_MOVIES; ++i) {
			ratings[i] = newRatings[i];
		}
		CheckHasRatedMovie();
		CalculateAvgRatingsMinusMean();
	}
	
	/**
	 * Formats the output of a string passed to be a minimum length
	 * Used in comjunction with toString 
	 * @param string any string that needs a minimum length for output formatting
	 * @param length the minimum length the string should be
	 * @return the formatted string
	 */
	String fixedLengthString (String string, int length) {
	    return String.format("%1$"+length+ "s", string);
	}
	
	/**
	 * Collects the name of the user and their ratings to be return
	 * @return the well formatted user information in a string
	 */
	@Override
	public String toString () {
		String to_string = "";
		to_string += fixedLengthString(GetName(), 6) + " - ";
		for (int i=0; i < GetRatings().length; i++) {
			if (GetRatings()[i] < 10) {
				to_string += "0";
			}
			to_string += GetRatings()[i];
			
			if (i != GetRatings().length - 1) {
				to_string += ", ";
			}
		}
		return to_string;
	}
	
	/**
	 * Prompts user for a movie title to set a rating for
	 * Validates the movie title occurs in the database
	 * Prompts the user for the new rating
	 * Validates the rating is valid
	 * Sets the rating for the user
	 * @param s a Scanner object to parse user input
	 * @param imdb the current MovieDatabase
	 */
	void SetARating (Scanner s, MovieDatabase imdb) {
		String title;
		int index, rating;
		
		do {
			System.out.println("Which movie would you like to rate?");
			System.out.println(imdb.toString());
			title = s.nextLine();
			index = imdb.isValidMovie(title);
		} while (index < 0);
		
		do {
			System.out.print("What is your rating for "+imdb.getMovies().get(index).getName()+": ");
			rating = s.nextInt();
			s.nextLine();
			
			if (!(0 < rating && rating < 100)) {
				System.out.println("\nNot a valid rating, please rate from 0 to 100");
			}
		} while (!(0 <= rating && rating <= 100));
		
		ratings[index] = rating;
		System.out.println("");
	}
	
	/**
	 * Gathers the user's ratings, averages them, and minuses the mean from each rating
	 * Does not factor in non rated movies i.e. rating 0
	 * Stores the caluated the results for each movie in an array for the user
	 */
	void CalculateAvgRatingsMinusMean () {
		int average = GetAverageRatings();
		
		for (int i=0; i < User.DEFAULT_NUM_MOVIES; i++) {
			if (ratings[i] != 0) {
				avgRatingsMinusMean[i] = ratings[i] - average;
			}
			else {
				avgRatingsMinusMean[i] = 0;
			}
		}
	}

	/**
	 * Passes the location and the new rating to be stored in the user's rating array
	 * @param loc the index in the movie ratings array to set the new rating
	 * @param newRating the rating to be assigned in the ratings array
	 */
	void AddRatingToMovie (int loc, int newRating) {
		ratings[loc] = newRating;
		CheckHasRatedMovie();
		CalculateAvgRatingsMinusMean();
	}
	
	/**
	 * Calculates the average of the user's rated movies
	 * Does not account for non-rated mollvies i.e. rated 0
	 * @return the calculated average of the users' ratings
	 */
	int GetAverageRatings () {
		int sum = 0;
		int count = 0;
		
		for (int i=0; i < User.DEFAULT_NUM_MOVIES; i++) {
			if (ratings[i] != 0.0) {
				sum += ratings[i];
				count++;
			}
		}
		
		if (count == 0) {
			return 0;
		}
		
		return sum / count;
	}
	
	/**
	 * Checks if a user has rated a movie or not
	 * A non-rated movie has a score of 0
	 * Sets a true value in an array if the user has rated the movie
	 */
	void CheckHasRatedMovie () {
		for (int i=0; i<DEFAULT_NUM_MOVIES; i++) {
			hasRatedMovie[i] = (ratings[i] != 0) ? true : false;
		}
	}

	void PrintRatings () {
		for (int i=0; i< DEFAULT_NUM_MOVIES; i++) {
			System.out.println("Movie "+i+" ratings is "+ratings[i]);
		}
	}
	
	void SetRatings (int[] ratings) {
		this.ratings = ratings;
		CheckHasRatedMovie();
	}
	
	void SetName (String name) {
		this.name = name;
	}
	
	String GetName () {
		return this.name;
	}
	
	int[] GetRatings () {
		return this.ratings;
	}
	
	int[] GetAvgRatingsMinusMean () {
		return this.avgRatingsMinusMean;
	}
	
	boolean[] GetHasRatedMovie () {
		return this.hasRatedMovie;
	}
}

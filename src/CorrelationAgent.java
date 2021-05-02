/**
 * @author Benjamin Hendrix
 * @class CAP4601 Intro to AI
 * @date 01 May 2021
 * 
 * CorrelationAgent calculates movie recommendations based on User-to-User collaborative filtering
 */

import java.lang.Math; // Import for Math.sqrt() method

public class CorrelationAgent {
	int feedbackScore;
	int feedbackCount;
	final int INITIAL_FEEDBACK_SCORE = 0;
	final int INITIAL_FEEDBACK_COUNT = 0;
	
	/**
	 * Default constructor
	 * Initializes the feedback score and count to zero
	 */
	CorrelationAgent () {
		setFeedbackScore(INITIAL_FEEDBACK_SCORE);
	}
	
	/**
	 * The main driver for which a movie is recommended
	 * Will start with User-to_user recommendation first
	 * @param user @param user the one for which we want to recommend a movie to
	 * @param userDB is the UserDatabase
	 * @param imdb is the MovieDatabase
	 * @param locOfUserInUsersArray is the location in the UserDatabase ArrayList 
	 * @return
	 */
	String RecommendMovieToUser (User user, UserDatabase userDB, MovieDatabase imdb, int locOfUserInUsersArray) {
		User bestMatch = FindBestMatchingUser(user, userDB, locOfUserInUsersArray);
		int index = UserToUserCollaborativeFiltering(user, bestMatch, imdb);
		int maxScore = bestMatch.GetAvgRatingsMinusMean()[index];
		
		if (maxScore <= 0) {
			index = ItemToItemCollaborativeFiltering(user, imdb);
		}
		return  imdb.getMovies().get(index).getName();
	}
	
	/**
	 * Finds the highest rated movie for a user to watch
	 * @return the movie location in the MovieDatabase for a recommended movie
	 */
	int ItemToItemCollaborativeFiltering (User user, MovieDatabase imdb) {
		int max = Integer.MIN_VALUE;
		int index = 0;
		
		for (int i=0; i < imdb.getMovies().size(); i++) {
			if (!user.GetHasRatedMovie()[i] && max < (int)imdb.getMovies().get(i).getRating()) {
				max = (int)imdb.getMovies().get(i).getRating();
				index = i;
			}
		}
		return index;
	}
	
	/**
	 * Finds the most similar user and recommends a movie
	 * @param user the one for which we want to recommend a movie to
	 * @param bestMatch the most similar other user
	 * @param imdb is the MovieDatabase
	 * @return the movie location in the MovieDatabase for a recommended movie
	 */
	int UserToUserCollaborativeFiltering (User user, User bestMatch, MovieDatabase imdb) {
		double max = Integer.MIN_VALUE;
		int index = 0;
		
		for (int i=0; i < imdb.getMovies().size(); i++) {
			if (user.GetHasRatedMovie()[i] == false) {
				if (max < bestMatch.GetAvgRatingsMinusMean()[i]) {
					max = bestMatch.GetAvgRatingsMinusMean()[i];
					index = i;
				}
			}
		}
		return index;
	}

	/**
	 * Looks at locations in the ArrayList excepts the user's self and finds the best match
	 * The best match is calculated using Centered Cosine Similarity
	 * @param user for which we want to find the best match to
	 * @param indexOfUser is the location in the UserDatabase ArrayList 
	 * @return the User object with the hgihest similarity
	 */
	User FindBestMatchingUser (User user, UserDatabase userDB, int indexOfUser) {
		double num = 0.0;
		double max = Integer.MIN_VALUE;
		User bestMatch = new User();
		
		for (int i = 0; i < userDB.GetUsers().size(); i++) {
			if (i != indexOfUser) {
				num = this.CenteredCosineSimilarityBetweenTwoUsers(user, userDB.GetUsers().get(i));
				if (max < num) {
					max = num;
					bestMatch = userDB.GetUsers().get(i);
				}
			}
		}
		bestMatch.CalculateAvgRatingsMinusMean();
		return bestMatch;
	}
	
	/**
	 * This method does not weigh non rated values in the calculation
	 * The closer to 1.0 this value is, the more similar the users are
	 * @param userA The user for which we want to recommend a movie
	 * @param userB a different user from the database to check for similarity
	 * @return the total similarity value between the users
	 */
	double CenteredCosineSimilarityBetweenTwoUsers (User userA, User userB) {
		double dotProductXY = CalculateVectorDotProduct(userA, userB);
		double magnitudeX = CalculateMagnitudeOfVector(userA);
		double magnitudeY = CalculateMagnitudeOfVector(userB);
		double crossProductXY = magnitudeX * magnitudeY;	
		
		return dotProductXY / crossProductXY;
	}
	
	/**
	 * Used for Centered Cosine Similarity to find the most similar user
	 * @param userA The user for which we want to recommend a movie
	 * @param userB a different user from the database to check for similarity
	 * @return The dot product of the two user's normalized averages
	 */
	double CalculateVectorDotProduct (User userA, User userB) {
		double dotProduct = 0.0;
		
		for (int i=0; i < User.DEFAULT_NUM_MOVIES; i++) {
			dotProduct += (userA.GetAvgRatingsMinusMean()[i] * userB.GetAvgRatingsMinusMean()[i]);
		}
		return dotProduct;
	}
	
	/**
	 * These ratings are already converted using Centered Cosine Similarity 
	 * @param user which needs their magnitude calculated
	 * @return the magnitude of the vector of user's ratings
	 */
	double CalculateMagnitudeOfVector (User user) {
		double magnitude = 0.0;
		
		for (int i=0; i < User.DEFAULT_NUM_MOVIES; i++) {
			magnitude += (user.GetAvgRatingsMinusMean()[i] * user.GetAvgRatingsMinusMean()[i]);
		}
		magnitude = Math.sqrt(magnitude);
		return magnitude;
	}
	

	/**
	 * Increments the feedBack score if positive recommendation
	 * Always increments the total feedback count
	 * @param b is whether the feedback was positive (true) or negative (false)
	 */
	void receiveFeedback (boolean b) {
		if (b == true) {
			incrementFeedbackScore();
		}
		incrementFeedbackCount();
	}
	
	/**
	 * @return the feedback score as a percentage
	 */
	double getFeedbackAveragePercent () {
		if (getFeedbackCount() == 0) {
			return 0.0;
		}
		return 100.0 * getFeedbackScore() / getFeedbackCount();
	}
	
	void setFeedbackScore (int i) {
		feedbackScore = i;
	}
	
	int getFeedbackScore () {
		return feedbackScore;
	}
	
	void incrementFeedbackScore () {
		setFeedbackScore(getFeedbackScore()+1);
	}
	
	void setFeedbackCount (int i) {
		feedbackCount = i;
	}
	
	int getFeedbackCount () {
		return feedbackCount;
	}
	
	void incrementFeedbackCount () {
		setFeedbackCount(getFeedbackCount()+1);
	}
}

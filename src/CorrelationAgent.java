import java.lang.Math; // Import for Math.sqrt() method

public class CorrelationAgent
{
	int feedbackScore;
	int feedbackCount;
	final int INITIAL_FEEDBACK_SCORE = 0;
	final int INITIAL_FEEDBACK_COUNT = 0;
	
	/**
	 * Default constructor
	 */
	public CorrelationAgent() {
		this.setFeedbackScore(INITIAL_FEEDBACK_SCORE);
	}
	
	public void setFeedbackScore(int i) {
		this.feedbackScore = i;
	}
	
	public int getFeedbackScore() {
		return this.feedbackScore;
	}
	public void incrementFeedbackScore() {
		this.setFeedbackScore(this.getFeedbackScore()+1);
	}
	
	public void setFeedbackCount(int i) {
		this.feedbackCount = i;
	}
	
	public int getFeedbackCount() {
		return this.feedbackCount;
	}
	
	public void incrementFeedbackCount() {
		this.setFeedbackCount(this.getFeedbackCount()+1);
	}
	
	public void receiveFeedback(boolean b) {
		if (b == true) {
			incrementFeedbackScore();
		}
		incrementFeedbackCount();
	}
	
	public double getFeedbackAveragePercent() {
		if(this.getFeedbackCount() == 0) {
			return 0.0;
		}
		return 100 * (double)this.getFeedbackScore() / (double)this.getFeedbackCount();
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public String RecommendMovieToUser(User user, UserDatabase userDB, MovieDatabase imdb, int locOfUserInUsersArray) {
		User bestMatch = FindBestMatchingUser(user, userDB, locOfUserInUsersArray);
		int index = UserToUserCollaborativeFiltering(user, bestMatch, imdb);
		int maxScore = bestMatch.GetAvgRatingsMinusMean()[index];
		if(maxScore <= 0) {
			index = ItemToItemCollaborativeFiltering(user, imdb);
		}
		 
		return  imdb.getMovies().get(index).getName();
	}
	
	/**
	 * 
	 * @return
	 */
	public int ItemToItemCollaborativeFiltering(User user, MovieDatabase imdb) {
		int max = Integer.MIN_VALUE;
		int index = 0;
		for(int i=0; i < imdb.getMovies().size(); i++) {
			if(!user.GetHasRatedMovie()[i] && max < (int)imdb.getMovies().get(i).getRating()) {
				max = (int)imdb.getMovies().get(i).getRating();
				index = i;
			}
		}
		
		return index;
	}
	
	/**
	 * 
	 * @param user
	 * @param bestMatch
	 * @return
	 */
	public int UserToUserCollaborativeFiltering(User user, User bestMatch, MovieDatabase imdb) {
		double max = Integer.MIN_VALUE;
		int index = 0;
		for(int i=0; i < imdb.getMovies().size(); i++) {
			if(user.GetHasRatedMovie()[i] == false) {
				if(max < bestMatch.GetAvgRatingsMinusMean()[i]) {
					max = bestMatch.GetAvgRatingsMinusMean()[i];
					index = i;
				}
			}
		}
		
		return index;
	}

	/**
	 * 
	 * @param user
	 * @param locOfUserInUsersArray
	 * @return
	 */
	public User FindBestMatchingUser(User user, UserDatabase userDB, int indexOfUser) {
		double num = 0.0;
		double max = Integer.MIN_VALUE;
		User bestMatch = new User();
		for (int i = 0; i < userDB.GetUsers().size(); i++) {
			if(i != indexOfUser) {
				num = this.CenteredCosineSimilarityBetweenTwoUsers(user, userDB.GetUsers().get(i));
				if(max < num) {
					max = num;
					bestMatch = userDB.GetUsers().get(i);
				}
			}
		}
		bestMatch.CalculateAvgRatingsMinusMean();
		
		return bestMatch;
	}
	
	/**
	 * 
	 * @param userA
	 * @param userB
	 * @return
	 */
	public double CenteredCosineSimilarityBetweenTwoUsers(User userA, User userB) {
		double dotProductXY = CalculateVectorDotProduct(userA, userB);
		double magnitudeX = CalculateMagnitudeOfVector(userA);
		double magnitudeY = CalculateMagnitudeOfVector(userB);
		double crossProductXY = magnitudeX * magnitudeY;
		
		return dotProductXY / crossProductXY;
	}
	
	/**
	 * 
	 * @param userA
	 * @param userB
	 * @return
	 */
	public double CalculateVectorDotProduct(User userA, User userB) {
		double dotProduct = 0.0;
		for(int i=0; i < User.DEFAULT_NUM_MOVIES; i++) {
			dotProduct += (userA.GetAvgRatingsMinusMean()[i] * userB.GetAvgRatingsMinusMean()[i]);
		}
		
		return dotProduct;
	}
	
	/**
	 * Takes the 
	 * @param user
	 * @return the magnitude or length of the vector
	 */
	public double CalculateMagnitudeOfVector(User user) {
		double magnitude = 0.0;
		for(int i=0; i < User.DEFAULT_NUM_MOVIES; i++) {
			magnitude += (user.GetAvgRatingsMinusMean()[i] * user.GetAvgRatingsMinusMean()[i]);
		}
		magnitude = Math.sqrt(magnitude);
		
		return magnitude;
	}
}

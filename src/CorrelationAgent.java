import java.io.FileNotFoundException;
import java.lang.Math; // Import for Math.sqrt() method
import java.util.ArrayList;

public class CorrelationAgent {
	ArrayList<User> users = new ArrayList<User>();
	MovieDatabase movies = new MovieDatabase();
	private final int DEFAULT_NUM_USERS = 2;
	
	/**
	 * Default constructor
	 */
	public CorrelationAgent() throws FileNotFoundException {
		this.InitializeUsers();
	}

	/**
	 * @implNote Sets up the users list with a default amount
	 */
	private void InitializeUsers() {
		for(int i=0; i < DEFAULT_NUM_USERS; i++) {
			this.users.add(new User());
		}
	}
	
	/**
	 * 
	 * @param users
	 */
	public CorrelationAgent(ArrayList<User> users) throws FileNotFoundException {
		this.users = users;
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public String RecommendMovieToUser(User user, int locOfUserInUsersArray) {
		User bestMatch = FindBestMatchingUser(user, locOfUserInUsersArray);
		int index = UserToUserCollaborativeFiltering(user, bestMatch);
		int maxScore = (int)bestMatch.GetAvgRatingsMinusMean()[index];
		if(maxScore <= 0) {
			index = ItemToItemCollaborativeFiltering(user);
		}
		 
		return movies.getMovies().get(index).getName();
	}
	
	/**
	 * 
	 * @return
	 */
	public int ItemToItemCollaborativeFiltering(User user) {
		int max = Integer.MIN_VALUE;
		int index = 0;
		for(int i=0; i < movies.getMovies().size(); i++) {
			if(!user.GetHasRatedMovie()[i] && max < (int)movies.getMovies().get(i).getRating()) {
				max = (int)movies.getMovies().get(i).getRating();
				index = i;
				System.out.println(""+movies.getMovies().get(i).getName()+" ");
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
	public int UserToUserCollaborativeFiltering(User user, User bestMatch) {
		double max = Integer.MIN_VALUE;
		int index = 0;
		for(int i=0; i < movies.getMovies().size(); i++) {
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
	public User FindBestMatchingUser(User user, int indexOfUser) {
		double num = 0.0;
		double max = Integer.MIN_VALUE;
		User bestMatch = new User();
		for (int i = 0; i < users.size (); i++) {
			if(i != indexOfUser) {
				num = this.CenteredCosineSimilarityBetweenTwoUsers(user, users.get(i));
				if(max < num) {
					max = num;
					bestMatch = users.get(i);
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

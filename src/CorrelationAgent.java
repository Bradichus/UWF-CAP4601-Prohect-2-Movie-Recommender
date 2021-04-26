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
	private void InitializeUsers()
	{
		for(int i=0; i < DEFAULT_NUM_USERS; i++)
		{
			this.users.add(new User());
		}
	}
	
	/**
	 * 
	 * @param users
	 */
	public CorrelationAgent(ArrayList<User> users) throws FileNotFoundException
	{
		this.users = users;
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public String RecommendMovieToUser(User user, int locOfUserInUsersArray)
	{
		User bestMatchingUser = new User("Test user");
		double max = 0;
		double temp;
		
		for(int i=0; i<2; i++)
		{
			if(i != locOfUserInUsersArray)
			{
				temp = this.CenteredCosineSimilarityBetweenTwoUsers(user, users.get(i));
				if( max < temp)
				{
					max = temp;
					bestMatchingUser = users.get(i);
				}
			}
		}
		bestMatchingUser.CalculateAvgRatingsMinusMean();

		double highestScore = 0.0;
		int loc = 0;
		for(int i=0; i<User.DEFAULT_NUM_MOVIES; i++) {
			if (user.GetHasRatedMovie()[i] == false) {
				if (highestScore < bestMatchingUser.GetAvgRatingsMinusMean()[i]) {
					highestScore = bestMatchingUser.GetAvgRatingsMinusMean()[i];
					loc = i;
				}
			}
		}
		 
		return movies.getMovies().get(loc).getName();
	}
	
	/**
	 * 
	 * @param userA
	 * @param userB
	 * @return
	 */
	public double CenteredCosineSimilarityBetweenTwoUsers(User userA, User userB)
	{
		double dotProductXY = CalculateVectorDotProduct(userA, userB);
		double magnitudeX = this.CalculateMagnitudeOfVector(userA);
		double magnitudeY = this.CalculateMagnitudeOfVector(userB);
		double crossProductXY = magnitudeX * magnitudeY;
		
		return dotProductXY / crossProductXY;
	}
	
	/**
	 * 
	 * @param userA
	 * @param userB
	 * @return
	 */
	public double CalculateVectorDotProduct(User userA, User userB)
	{
		double dotProduct = 0.0;
		
		for(int i=0; i < User.DEFAULT_NUM_MOVIES; i++)
		{
			dotProduct += (userA.GetAvgRatingsMinusMean()[i] * userB.GetAvgRatingsMinusMean()[i]);
		}
		
		return dotProduct;
	}
	
	/**
	 * Takes the 
	 * @param user
	 * @return the magnitude or length of the vector
	 */
	public double CalculateMagnitudeOfVector(User user)
	{
		double magnitude = 0.0;
		
		for(int i=0; i < User.DEFAULT_NUM_MOVIES; i++)
		{
			magnitude += (user.GetAvgRatingsMinusMean()[i] * user.GetAvgRatingsMinusMean()[i]);
		}
		
		magnitude = Math.sqrt(magnitude);
		
		return magnitude;
	}
}

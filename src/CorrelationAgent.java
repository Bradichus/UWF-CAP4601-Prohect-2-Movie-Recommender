import java.lang.Math; // Import for Math.sqrt() method

public class CorrelationAgent {
	User[] users;
	
	/**
	 * Default constructor
	 */
	public CorrelationAgent()
	{
		// TODO
	}
	
	/**
	 * 
	 * @param users
	 */
	public CorrelationAgent(User[] users)
	{
		this.users = users;
	}
	
	/**
	 * 
	 * @return
	 */
	
	public double GetAverageRatingsForUsers()
	{
		double sum = 0.0;
		int count = 0;
		
		for(int i=0; i < users.length; i++)
		{
			sum += users[i].GetAverageRatings();
			count++;
		}
		
		if(users.length == 0)
		{
			return 0.0;
		}
		
		return sum / count;
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

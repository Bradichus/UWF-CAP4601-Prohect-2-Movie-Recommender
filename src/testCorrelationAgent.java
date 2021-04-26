import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

class testCorrelationAgent {

	@Test
	void testSuppliedDatasetForRecommendations() throws FileNotFoundException {
		/**
		 * @brief Format to test recommender between two user.
		 * X's represent no rating provided for a movie
		 * Y indicates the move that should be recommended
		 *
		 * [ x 37 25 10 x 90 x x 75 x x 90 Y x 90 10 ] User 0
		 * [ x 37 25 10 x 90 x x 75 x x 90 95 x 90 10 ] User 1
		 */

		ArrayList<User> datasetUsers = new ArrayList<User>();
		int DEFAULT_NUM_USERS = 2;
		for(int i=0; i < DEFAULT_NUM_USERS; i++)
		{
			datasetUsers.add(new User());
		}
		datasetUsers.get(0).SetName("Alice");
		datasetUsers.get(1).SetName("Bob");

		datasetUsers.get(0).AddRatingToMovie(1, 37);
		datasetUsers.get(1).AddRatingToMovie(1, 37);
		datasetUsers.get(0).AddRatingToMovie(2, 25);
		datasetUsers.get(1).AddRatingToMovie(2, 25);
		datasetUsers.get(0).AddRatingToMovie(3, 10);
		datasetUsers.get(1).AddRatingToMovie(3, 10);
		datasetUsers.get(0).AddRatingToMovie(5, 90);
		datasetUsers.get(1).AddRatingToMovie(5, 90);
		datasetUsers.get(0).AddRatingToMovie(8, 75);
		datasetUsers.get(1).AddRatingToMovie(8, 75);
		datasetUsers.get(0).AddRatingToMovie(11, 90);
		datasetUsers.get(1).AddRatingToMovie(11, 90);

		// User 0 should be recommended movie 12
		datasetUsers.get(1).AddRatingToMovie(12, 95);
		
		datasetUsers.get(0).AddRatingToMovie(13, 90);
		datasetUsers.get(1).AddRatingToMovie(13, 90);
		datasetUsers.get(0).AddRatingToMovie(14, 10);
		datasetUsers.get(1).AddRatingToMovie(14, 10);

		CorrelationAgent myAgent = new CorrelationAgent(datasetUsers);
//		for (int i=0; i<2; i++)
//		{
//			for(int j=0; j<15; j++)
//			{
//				System.out.println("User "+i+" rated movie "+j+" a score of "+myAgent.users.get(i).GetRatings()[j]);
//			}
//		}
		assertEquals("Toy Story", myAgent.RecommendMovieToUser(datasetUsers.get(0), 0));

	}

}

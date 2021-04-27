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
		 *    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 
		 * [ xx 37 25 10 xx 90 xx xx 75 xx xx 90 YY xx 90 ] User 0
		 * [ xx 37 25 10 xx 90 xx xx 75 xx xx 90 95 xx 90 ] User 1
		 * 
		 * Movie 12 should be the one recommended
		 */

		ArrayList<User> datasetUsers = new ArrayList<User>();
		double[] ratingAlice = {0.0, 37.0, 25.0, 10.0, 0.0, 90.0, 0.0, 0.0, 75.0, 0.0, 0.0, 90.0, 0.00, 0.00, 90.0};
		double[] ratingBob = {0.0, 37.0, 25.0, 10.0, 0.0, 90.0, 0.0, 0.0, 75.0, 0.0, 0.0, 90.0, 95.00, 0.00, 90.0};
		datasetUsers.add(new User("Alice", ratingAlice));
		datasetUsers.add(new User("Bob", ratingBob));
 

		CorrelationAgent myAgent = new CorrelationAgent(datasetUsers);
		assertEquals("Alice", myAgent.users.get(0).GetName());
		assertEquals("Bob", myAgent.users.get(1).GetName());
		assertEquals(myAgent.movies.getMovies().get(12).getName(), myAgent.RecommendMovieToUser(datasetUsers.get(0), 0));

	}

}

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

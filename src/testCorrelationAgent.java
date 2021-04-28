import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

class testCorrelationAgent {

	/**
	 * @brief User-to-user collaborative filtering
	 * Will recommend the highest rated movie from User 2
	 *  which has not been watched by User 1
	 *  
	 * @param X's represent no rating provided for a movie
	 * @param Y indicates the move that should be recommended
	 * 
	 *    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 
	 * [ xx 37 25 10 xx 90 xx xx 75 xx xx 90 YY xx 90 ] User 1
	 * [ xx 37 25 10 xx 90 xx xx 75 xx xx 90 95 xx 90 ] User 2
	 * 
	 * Movie 12 should be the one recommended
	 */
	//@Test
//	void testUserToUserRecommendationWithTwoUsers() throws FileNotFoundException {
//		ArrayList<User> datasetUsers = new ArrayList<User>();
//		int[] ratingAlice = {0, 37, 25, 10, 0, 90, 0, 0, 75, 0, 0, 90, 0, 0, 90};
//		int[] ratingBob = {0, 37, 25, 10, 0, 90, 0, 0, 75, 0, 0, 90, 95, 0, 90};
//		datasetUsers.add(new User("Alice", ratingAlice));
//		datasetUsers.add(new User("Bob", ratingBob));
//		CorrelationAgent myAgent = new CorrelationAgent(datasetUsers);
//		assertEquals("Alice", myAgent.users.get(0).GetName());
//		assertEquals("Bob", myAgent.users.get(1).GetName());
//		assertEquals(myAgent.movies.getMovies().get(12).getName(), myAgent.RecommendMovieToUser(datasetUsers.get(0), 0));
//	}
	
	/**
		 * @brief Item-to-item collaborative filtering
		 * Will recommend the highest average rated movie from Movie database
		 *  which has not been watched by User 1
		 *  
		 * @param X's represent no rating provided for a movie
		 * @param Y indicates the move that should be recommended
		 * 
		 *    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 
		 * [ xx 37 25 10 xx 90 xx YY 75 xx xx 90 95 xx 90 ] User 1
		 * [ 92 89 96 50 85 96 84 94 92 66 93 92 23 43 82 ] Movie Database average ratings       
		 *                                 
		 * Movie 7 should be the one recommended
		 */
//	@Test
//	void testItemToItemRecommendationWithOneUser() throws FileNotFoundException {
//		ArrayList<User> datasetUsers = new ArrayList<User>();
//		int[] ratingAlice = {0, 37, 25, 10, 0, 90, 0, 0, 75, 0, 0, 90, 0, 0, 90};
//		datasetUsers.add(new User("Alice", ratingAlice));
//		CorrelationAgent myAgent = new CorrelationAgent(datasetUsers);
//		assertEquals("Alice", myAgent.users.get(0).GetName());
//		assertEquals(myAgent.movies.getMovies().get(7).getName(), myAgent.RecommendMovieToUser(datasetUsers.get(0), 0));
//	}

}

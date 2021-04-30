import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class testCorrelationAgent {

	/**
	 * @brief User-to-user collaborative filtering
	 * Will recommend the highest rated movie from User 2 (most similar)
	 *  which has not been watched by User 1 (Movie 12)
	 *  
	 * @param X's represent no rating provided for a movie
	 * @param Y indicates the move that should be recommended
	 * 
	 *    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 
	 * [ xx 37 25 10 xx 90 xx xx 75 xx xx 90 YY xx 90 ] User 1
	 * [ xx 37 25 10 xx 90 xx xx 75 xx xx 90 95 xx 90 ] User 2
	 * [ 50 xx 34 68 92 76 xx 38 08 xx 25 74 89 xx 26 ] User 3
	 * [ 97 65 xx 10 xx 95 31 23 xx 44 xx xx 17 42 68 ] User 4
	 * [ 57 23 xx 11 60 17 xx 01 43 11 25 99 xx xx 18 ] User 5
	 * 
	 * @throws FileNotFoundException 
	 */
	@Test
	void testUserToUserRecommendationWithFiveUsers() throws FileNotFoundException {
		CorrelationAgent myAgent = new CorrelationAgent();
		MovieDatabase imdb = new MovieDatabase();
		UserDatabase userDB = new UserDatabase();
		
		assertEquals(imdb.getMovies().get(12).getName(), myAgent.RecommendMovieToUser(userDB.GetUsers().get(0), userDB, imdb, 0));
	}
	
	/**
		 * @brief Item-to-item collaborative filtering
		 * If not positive rated movie can be recommended from most similar user,
		 * it will recommend the highest average rated movie from Movie database
		 *  which has not been watched by User 1
		 *  
		 * @param X's represent no rating provided for a movie
		 * @param Y indicates the move that should be recommended
		 * @param ?'s indicate a possible not watched movie
		 * 
		 *    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 
		 * [ xx 37 25 10 xx 90 xx YY 75 xx xx 90 95 xx 90 ] User 1
		 * [ xx 37 25 10 xx 90 xx xx 75 xx xx 90 95 xx 90 ] User 2
		 * [ 50 xx 34 68 92 76 xx 38 08 xx 25 74 89 xx 26 ] User 3
		 * [ 97 65 xx 10 xx 95 31 23 xx 44 xx xx 17 42 68 ] User 4
		 * [ 57 23 xx 11 60 17 xx 01 43 11 25 99 xx xx 18 ] User 5     
		 * 
		 *   ??          ??    ?? ??    ?? ??       ?? 
		 * [ 92 89 96 50 85 96 84 94 92 66 93 92 23 43 82 ] Movie Ratings
		 * The highest avg rating for a movie not watched is Movie 7, Iron Man - 94
		 * Movie 7 should be the one recommended
		 */
	@Test
	void testItemToItemRecommendation() throws FileNotFoundException {
		CorrelationAgent myAgent = new CorrelationAgent();
		MovieDatabase imdb = new MovieDatabase();
		UserDatabase userDB = new UserDatabase();
		
		// Matching user 1 and user 2's ratings 
		userDB.GetUsers().get(0).SetRatings(userDB.GetUsers().get(1).ratings);
		
		//Removing a negative rating to see if it will recommend a disliked movie
		int newRatings[] = userDB.GetUsers().get(0).ratings;
		newRatings[3] = 0;
		userDB.GetUsers().get(0).SetRatings(newRatings);

		assertEquals(imdb.getMovies().get(7).getName(), myAgent.RecommendMovieToUser(userDB.GetUsers().get(0), userDB, imdb, 0));
	}
}

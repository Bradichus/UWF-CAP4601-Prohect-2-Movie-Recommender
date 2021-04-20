import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testUser
{
	
	@Test
	void testDefaultConstructor()
	{
		final String DEFAULT_NAME = "<NO_NAME>";
		final int numMovies = 10;
		final double initialRating = 0.0;
		User user = new User();
		
		assertEquals(user.GetName(), DEFAULT_NAME);
		assertEquals(User.DEFAULT_NUM_MOVIES, numMovies);
		for(int i=0; i < User.DEFAULT_NUM_MOVIES; ++i)
		{
			assertEquals(user.ratings[i], initialRating);
		}
	}
	
	@Test
	void testDefaultConstructorThenAccessorsAndMutators()
	{
		User user = new User();
		
		final String name = "Steve";
		user.SetName(name);
		assertEquals(user.GetName(), name);
		
		final double[] ratings = {0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};
		for(int i=0; i < User.DEFAULT_NUM_MOVIES; ++i)
		{
			user.ratings[i] = ratings[i];
			assertEquals(user.ratings[i], ratings[i]);
		}
	}
	
	@Test
	void testOneParameterConstructor()
	{
		final String name = "Tom";
		User user = new User(name);
		
		assertEquals(user.GetName(), name);
	}
	
	@Test
	void testFullParameterizedConstructor()
	{
		final String name = "Jack";
		final int numMovies = 10;
		final double[] ratings = {0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0};
		
		User user = new User(name, ratings);
		
		assertEquals(user.GetName(), name);
		assertEquals(User.DEFAULT_NUM_MOVIES, numMovies);
		for(int i=0; i < User.DEFAULT_NUM_MOVIES; ++i)
		{
			assertEquals(user.ratings[i], ratings[i]);
		}
	}
	
	

}

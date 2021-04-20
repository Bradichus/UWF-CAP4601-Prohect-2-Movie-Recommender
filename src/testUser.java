import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testUser
{
	
	@Test
	void testDefaultConstructor()
	{
		final String DEFAULT_NAME = "<NO_NAME>";
		final int numMovies = 10;
		final int initialRating = -1;
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
		
		final int[] ratings = {0,1,2,3,4,5,6,7,8,9};
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
		final int[] ratings = {0,1,2,3,4,5,6,7,8,9};
		
		User user = new User(name, ratings);
		
		assertEquals(user.GetName(), name);
		assertEquals(User.DEFAULT_NUM_MOVIES, numMovies);
		for(int i=0; i < User.DEFAULT_NUM_MOVIES; ++i)
		{
			assertEquals(user.ratings[i], ratings[i]);
		}
	}
	
	

}

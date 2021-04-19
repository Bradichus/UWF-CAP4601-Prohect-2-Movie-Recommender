import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testUser
{
	
	@Test
	void testDefaultConstructor()
	{
		final String DEFAULT_NAME = "<NO_NAME>";
		final int numMovies = 5;
		final int initialRating = -1;
		User user = new User();
		
		assertEquals(user.GetName(), DEFAULT_NAME);
		assertEquals(user.GetNumMovies(), numMovies);
		for(int i=0; i < user.GetNumMovies(); ++i)
		{
			assertEquals(user.GetSingleRating(i), initialRating);
		}
	}
	
	@Test
	void testDefaultConstructorThenAccessorsAndMutators()
	{
		User user = new User();
		
		final String name = "Steve";
		user.SetName(name);
		assertEquals(user.GetName(), name);
		
		final int[] ratings = {0,1,2,3,4};
		for(int i=0; i < user.GetNumMovies(); ++i)
		{
			user.SetSingleRating(i, ratings[i]);
			assertEquals(user.GetSingleRating(i), ratings[i]);
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
		final int numMovies = 5;
		final int[] ratings = {0,1,2,3,4};
		
		User user = new User(name, ratings);
		
		assertEquals(user.GetName(), name);
		assertEquals(user.GetNumMovies(), numMovies);
		for(int i=0; i < user.GetNumMovies(); ++i)
		{
			assertEquals(user.GetSingleRating(i), ratings[i]);
		}
	}
	
	

}

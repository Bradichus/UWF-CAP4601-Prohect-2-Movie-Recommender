import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testMovie {

	@Test
	void testDefaultConstructor() {
		Movie tm = new Movie();
		assertEquals("", tm.getName());
		assertEquals(0, tm.getViews());
		assertEquals(0, tm.getNumTags());
		for (int i = 0; i < 5; i++) {
			assertEquals("", tm.getTags()[i]);
		}
		assertEquals(0, tm.getNumReviews());
		assertEquals(0.0, tm.getRating(), 0.001);
	}

	@Test
	void testNameConstructor() {
		Movie tm1 = new Movie("Real Movie");
		assertEquals("Real Movie", tm1.getName());
		assertEquals(0, tm1.getViews());
		assertEquals(0, tm1.getNumTags());
		for (int i = 0; i < 5; i++) {
			assertEquals("", tm1.getTags()[i]);
		}
		assertEquals(0, tm1.getNumReviews());
		assertEquals(0.0, tm1.getRating(), 0.001);
	}

	@Test
	void testReviewConstructor() {
		Movie tm2 = new Movie("Real Movie", 20, 10, 50);
		assertEquals("Real Movie", tm2.getName());
		assertEquals(20, tm2.getViews());
		assertEquals(0, tm2.getNumTags());
		for (int i = 0; i < 5; i++) {
			assertEquals("", tm2.getTags()[i]);
		}
		assertEquals(10, tm2.getNumReviews());
		assertEquals(100, tm2.getRating(), 0.001);

		Movie tm3 = new Movie("AlsoReal Movie", 20, 10, 0);
		assertEquals(0.0, tm3.getRating(), 0.001);

		Movie tm4 = new Movie ("Unreal Movie", 20, 10, 25);
		assertEquals(50.0, tm4.getRating(), 0.001);

		Movie tm5 = new Movie ("Maybe Real Movie?", 20, 10, 11);
		assertEquals(22.0, tm5.getRating(), 0.001);
	}

	@Test
	void testTagsConstructor() {
		String[] t = {"Good", "Not Bad", "Not Great", "Old", "Ew"};
		Movie tm6 = new Movie("Real Movie", 20, 10, 50, 5, t);
		assertEquals("Real Movie", tm6.getName());
		assertEquals(20, tm6.getViews());
		assertEquals(5, tm6.getNumTags());
		for (int i = 0; i < 5; i++) {
			assertEquals(t[i], tm6.getTags()[i]);
		}
		assertEquals(10, tm6.getNumReviews());
		assertEquals(100, tm6.getRating(), 0.001);

		String[] t1 = {"Good", "Not Bad", "Not Great"};
		Movie tm7 = new Movie("Also Real Movie", 20, 10, 50, 3, t1);
		assertEquals(3, tm7.getNumTags());
		for (int i = 0; i < 3; i++) {
			assertEquals(t1[i], tm7.getTags()[i]);
		}
		for (int i = 3; i < 5; i++) {
			assertEquals("", tm7.getTags()[i]);
		}

		String[] t2 = {};
		Movie tm8 = new Movie("Unreal Movie", 20, 10, 50, 0, t2);
		for (int i = 0; i < 5; i++) {
			assertEquals("", tm8.getTags()[i]);
		}
	}
	
	@Test
	void testRatingConstructor() {
		String[] t = {"Sci-Fi", "Action", "Adventure", "Drama"};
		Movie tm9 = new Movie("Star Wars: Episode IV - A New Hope", 1000, 500, 92.0, 4, t);
		assertEquals("Star Wars: Episode IV - A New Hope", tm9.getName());
		assertEquals(1000, tm9.getViews());
		assertEquals(500, tm9.getNumReviews());
		assertEquals(92, tm9.getRating());
		assertEquals(4, tm9.getNumTags());
		assertEquals("Sci-Fi", tm9.getTags()[0]);
	}

}
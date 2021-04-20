import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class testMovieDatabase {

	@Test
	void testConstructor() throws FileNotFoundException {
		MovieDatabase t = new MovieDatabase();
		
		assertEquals("Star Wars: Episode IV - A New Hope", t.getMovies().get(0).getName());
		assertEquals("The Imitation Game", t.getMovies().get(1).getName());
		assertEquals("Sausage Party", t.getMovies().get(14).getName());
		
		assertEquals(4, t.getMovies().get(14).getNumTags());
		assertEquals("Animated", t.getMovies().get(14).getTags()[0]);
		assertEquals("Rated R", t.getMovies().get(14).getTags()[3]);
	}

}

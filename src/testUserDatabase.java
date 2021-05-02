import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class testUserDatabase {

	@Test
	void testConstructor() throws FileNotFoundException {
		UserDatabase db = new UserDatabase();
		
		assertEquals("Alice", db.GetUsers().get(0).GetName());
		assertEquals("Bob", db.GetUsers().get(1).GetName());
		assertEquals("Earl", db.GetUsers().get(4).GetName());
		
		assertEquals(37, db.GetUsers().get(0).GetRatings()[1]);
		assertEquals(90, db.GetUsers().get(1).GetRatings()[14]);
		assertEquals(0, db.GetUsers().get(4).GetRatings()[2]);
	}

}

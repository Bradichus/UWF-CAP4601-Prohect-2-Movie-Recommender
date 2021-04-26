import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieDatabase {
	
	final int NUM_FILE_HEADER_COLUMNS = 6;
	final int MAX_TAGS = 5;
	
	String filmTitle;
	int numTags;
	String[] tags = new String[MAX_TAGS];
	int numViews;
	int numReviews;
	double rating;
	
	ArrayList<Movie> Movies = new ArrayList<Movie>();
	
	MovieDatabase() throws FileNotFoundException {
		File inputFile = new File("MovieList.txt");
		Scanner s = new Scanner(inputFile);
		s.useDelimiter(",|\\n|\\r");
		String garbo;
		
		for (int i = 0; i < NUM_FILE_HEADER_COLUMNS; i++) {
			garbo = s.next();
		}
		
		String next;
		while(s.hasNext()) 
		{
			next = s.next();
			if (next.isEmpty()) {
				garbo = next;
			}
			else {
				filmTitle = next;
				numTags = s.nextInt();
				for (int i = 0; i < numTags && i < 5; i++) {
					tags[i] = s.next();
				}
				numViews = s.nextInt();
				numReviews = s.nextInt();
				rating = s.nextDouble();
			
				Movie currMovie = new Movie(filmTitle, numViews, numReviews, rating, numTags, tags);
				Movies.add(currMovie);
			}
		} s.close();
	}
	
	ArrayList<Movie> getMovies() {return Movies;}

}
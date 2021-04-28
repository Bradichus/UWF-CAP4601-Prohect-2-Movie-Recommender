public class Movie {
	String name;
	String[] tags = new String[5];
	int numViews;
	final int MAX_TAGS = 5;
	int numTags;
	int numReviews;
	double totalUserScore;
	int maxUserScore;
	final int MAX_RATING = 5;
	double rating;

	/**
	 * 
	 */
	public Movie()
	{
		name = "";
		for (int i = 0; i < MAX_TAGS; i++) {
			tags[i] = "";
		}
		numViews = 0;
		numTags = 0;
		numReviews = 0;
		totalUserScore = 0;
		maxUserScore = numReviews * MAX_RATING;
		rating = 0;
	}

	/**
	 * 
	 * @param name
	 */
	public Movie(String name)
	{
		this.name = name;
		for (int i = 0; i < MAX_TAGS; i++) {
			tags[i] = "";
		}
		numViews = 0;
		numTags = 0;
		numReviews = 0;
		totalUserScore = 0;
		maxUserScore = numReviews * MAX_RATING;
		rating = 0;
	}

	/**
	 * 
	 * @param name
	 * @param numViews
	 * @param numReviews
	 * @param totalUserScore
	 */
	public Movie(String name, int numViews, int numReviews, int totalUserScore)
	{
		this.name = name;
		for (int i = 0; i < MAX_TAGS; i++) {
			tags[i] = "";
		}
		numTags = 0;
		this.numViews = numViews;
		this.numReviews = numReviews;
		this.totalUserScore = totalUserScore;
		this.maxUserScore = this.numReviews * MAX_RATING;
		rating = calculateRating();
	}

	/**
	 * 
	 * @param name
	 * @param numViews
	 * @param numReviews
	 * @param totalUserScore
	 * @param numTags
	 * @param tags
	 */
	public Movie(String name, int numViews, int numReviews, int totalUserScore, int numTags,  String[] tags)
	{
		this.name = name;
		this.numTags = 0;
		for (int i = 0; i < numTags; i++) {
			this.tags[i] = tags[i];
			this.numTags++;
		}
		for (int j = numTags; j < MAX_TAGS; j++) {
			this.tags[j] = "";
		}
		this.numViews = numViews;
		this.numReviews = numReviews;
		this.totalUserScore = totalUserScore;
		this.maxUserScore = this.numReviews * MAX_RATING;
		rating = calculateRating();
	}

	/**
	 * 
	 * @param name
	 * @param numViews
	 * @param numReviews
	 * @param rating
	 * @param numTags
	 * @param tags
	 */
	public Movie(String name, int numViews, int numReviews, double rating, int numTags, String[] tags)
	{
		this.name = name;
		this.numTags = 0;
		for (int i = 0; i < numTags; i++) {
			this.tags[i] = tags[i];
			this.numTags++;
		}
		for (int j = numTags; j < MAX_TAGS; j++) {
			this.tags[j] = "";
		}
		this.numViews = numViews;
		this.numReviews = numReviews;
		this.maxUserScore = this.numReviews * MAX_RATING;
		this.rating = rating;
	}
	
	/**
	 * 
	 */
	public String toString() {
		String to_string = "";
		to_string += getName();
		to_string += " - Avg Rating: ";
		to_string += getRating();
		
		return to_string;
	}

	/**
	 * 
	 * @param newTag
	 * @return
	 */
	public boolean tag(String newTag)
	{
		boolean success = false;
		boolean isNew = false;

		for (int i = 0; i < MAX_TAGS; i++) {
			if (tags[i] == newTag) {
				isNew = true;
			}
		}

		if (numTags < MAX_TAGS && isNew) {
			tags[numTags] = newTag;
			numTags++;
			success = true;
		}

		return success;
	}

	/**
	 * 
	 * @param oldTag
	 * @param newTag
	 * @return
	 */
	public boolean replaceTag(String oldTag, String newTag)
	{
		boolean success = false;
		for (int i = 0; i < MAX_TAGS; i++) {
			if (tags[i] == oldTag && !success)
				tags[i] = newTag;
		}

		return success;
	}

	/**
	 * 
	 * @param oldTag
	 * @return
	 */
	public boolean removeTag(String oldTag)
	{
		boolean success = false;
		for (int i = 0; i < MAX_TAGS; i++) {
			if (tags[i] == oldTag && !success) {
				for (int j = i; j < MAX_TAGS; j++)
				{
					if (j+1 < MAX_TAGS) {
						tags[j] = tags[j+1];
						numTags--;
					}
				}
				success = true;
			}
		}

		return success;
	}

	/**
	 * 
	 * @param newScore
	 */
	public void addReview(int newScore) {
		numReviews += 1;
		totalUserScore += newScore;
		this.rating = calculateRating();
	}

	/**
	 * 
	 * @param rating
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}

	/**
	 * 
	 * @return
	 */
	public double calculateRating() {
		double calculatedRating;
		if (maxUserScore > 0) {
			calculatedRating = ( ( totalUserScore / maxUserScore ) * 100);
		}
		else {
			calculatedRating = 0;
		}
		return calculatedRating;
	}

	public String getName() {return name;};
	public int getNumTags() {return numTags;};
	public String[] getTags() {return tags;};
	public int getViews() {return numViews;};
	public int getNumReviews() {return numReviews;};
	public double getRating() {return rating;};
}
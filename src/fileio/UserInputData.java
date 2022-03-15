package fileio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

/**
 * Information about an user, retrieved from parsing the input test files
 * <p>
 * DO NOT MODIFY
 */
public final class UserInputData {
    /**
     * User's username
     */
    private final String username;
    /**
     * Subscription Type
     */
    private final String subscriptionType;
    /**
     * The history of the movies seen
     */
    private final Map<String, Integer> history;
    /**
     * Movies added to favorites
     */
    private final ArrayList<String> favoriteMovies;

    public ArrayList<Integer> number_of_ratings_movies;

    public ArrayList<ArrayList<Integer>> number_of_ratings_seriale;

    private Integer nr_total_ratinguri_date;

    public Integer getNr_total_ratinguri_date() {
        return nr_total_ratinguri_date;
    }

    public void setNr_total_ratinguri_date(Integer nr_total_ratinguri_date) {
        this.nr_total_ratinguri_date = nr_total_ratinguri_date;
    }

    public ArrayList<Integer> getNumber_of_ratings_movies() {
        return number_of_ratings_movies;
    }

    public void setNumber_of_ratings_movies(ArrayList<Integer> number_of_ratings_movies) {
        this.number_of_ratings_movies = number_of_ratings_movies;
    }

    public ArrayList<ArrayList<Integer>> getNumber_of_ratings_seriale() {
        return number_of_ratings_seriale;
    }

    public void setNumber_of_ratings_seriale(
            ArrayList<ArrayList<Integer>> number_of_ratings_seriale) {
        this.number_of_ratings_seriale = number_of_ratings_seriale;
    }

    public UserInputData(final String username, final String subscriptionType,
                         final Map<String, Integer> history,
                         final ArrayList<String> favoriteMovies) {
        this.username = username;
        this.subscriptionType = subscriptionType;
        this.favoriteMovies = favoriteMovies;
        this.history = history;
        this.number_of_ratings_movies = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            this.number_of_ratings_movies.add(0);
        }
        this.number_of_ratings_seriale = new ArrayList<>();
        for (int j = 0; j < 1000; j++) {
            ArrayList<Integer> array = new ArrayList<>();
            for (int k = 0; k < 1000; k++) {
                array.add(0);
            }
            this.number_of_ratings_seriale.add(array);
        }
        this.nr_total_ratinguri_date = 0;
    }

    public String getUsername() {
        return username;
    }

    public Map<String, Integer> getHistory() {
        return history;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    @Override
    public String toString() {
        return "UserInputData{" + "username='"
                + username + '\'' + ", subscriptionType='"
                + subscriptionType + '\'' + ", history="
                + history + ", favoriteMovies="
                + favoriteMovies + '}';
    }

    public static Comparator<UserInputData> NumberRatingsComparator = new Comparator<UserInputData>() {
        @Override
        public int compare(UserInputData o1, UserInputData o2) {
            int comparatie = o1.getNr_total_ratinguri_date().compareTo(o2.getNr_total_ratinguri_date());
            if (comparatie != 0) {
                return comparatie;
            }
            return o1.getUsername().compareTo(o2.getUsername());
        }
    };
}

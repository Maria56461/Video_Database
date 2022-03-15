package fileio;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * General information about show (video), retrieved from parsing the input test files
 * <p>
 * DO NOT MODIFY
 */
public abstract class ShowInput {
    /**
     * Show's title
     */
    private final String title;
    /**
     * The year the show was released
     */
    private final int year;

    private Double ratingg;

    public Double getRatingg() {
        return ratingg;
    }

    public void setRatingg(Double rating) {
        this.ratingg = rating;
    }

    /**
     * Show casting
     */
    private final ArrayList<String> cast;
    /**
     * Show genres
     */
    private final ArrayList<String> genres;

    public ShowInput(final String title, final int year,
                     final ArrayList<String> cast, final ArrayList<String> genres) {
        this.title = title;
        this.year = year;
        this.cast = cast;
        this.genres = genres;
        this.ratingg = 0.00;
    }

    public final String getTitle() {
        return title;
    }

    public final int getYear() {
        return year;
    }

    public final ArrayList<String> getCast() {
        return cast;
    }

    public final ArrayList<String> getGenres() {
        return genres;
    }

    public static Comparator<ShowInput> RatinggComparator = new Comparator<ShowInput>() {
        public int compare(ShowInput o1, ShowInput o2) {
            int comparatie = o1.getRatingg().compareTo(o2.getRatingg());
            if (comparatie != 0) {
                return comparatie;
            }
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

}

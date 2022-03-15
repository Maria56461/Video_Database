package fileio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Information about a movie, retrieved from parsing the input test files
 * <p>
 * DO NOT MODIFY
 */
public final class MovieInputData extends ShowInput {
    /**
     * Duration in minutes of a season
     */
    private final Integer duration;

    public List<Double> ratings;

    public Double average_rating;

    private Integer numar_voturi;

    private Integer numar_vizualizari;

    public Integer getNumar_vizualizari() {
        return numar_vizualizari;
    }

    public void setNumar_vizualizari(Integer numar_vizualizari) {
        this.numar_vizualizari = numar_vizualizari;
    }

    public Integer getNumar_voturi() {
        return numar_voturi;
    }

    public void setNumar_voturi(Integer numar_voturi) {
        this.numar_voturi = numar_voturi;
    }

    public MovieInputData(final String title, final ArrayList<String> cast,
                          final ArrayList<String> genres, final int year,
                          final Integer duration) {
        super(title, year, cast, genres);
        this.duration = duration;
        this.ratings = new ArrayList<>();
        this.numar_voturi = 0;
        this.numar_vizualizari = 0;
        this.average_rating = 0.00;
    }


    public Integer getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "MovieInputData{" + "title= "
                + super.getTitle() + "year= "
                + super.getYear() + "duration= "
                + duration + "cast {"
                + super.getCast() + " }\n"
                + "genres {" + super.getGenres() + " }\n ";
    }

    public static Comparator<MovieInputData> RatingsComparator = new Comparator<MovieInputData>() {
        @Override
        public int compare(MovieInputData o1, MovieInputData o2) {
            int comparatie = o1.average_rating.compareTo(o2.average_rating);
            if (comparatie != 0) {
                return comparatie;
            }
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    public static Comparator<MovieInputData> FavoriteComparator = new Comparator<MovieInputData>() {
        @Override
        public int compare(MovieInputData o1, MovieInputData o2) {
            int comparatie = o1.getNumar_voturi().compareTo(o2.getNumar_voturi());
            if (comparatie != 0) {
                return comparatie;
            }
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    public static Comparator<MovieInputData> DurationComparator = new Comparator<MovieInputData>() {
        @Override
        public int compare(MovieInputData o1, MovieInputData o2) {
            int comparatie = o1.getDuration().compareTo(o2.getDuration());
            if (comparatie != 0) {
                return comparatie;
            }
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    public static Comparator<MovieInputData> NumberViewsComparator = new Comparator<MovieInputData>() {
        @Override
        public int compare(MovieInputData o1, MovieInputData o2) {
            int comparatie = o1.getNumar_vizualizari().compareTo(o2.getNumar_vizualizari());
            if (comparatie != 0) {
                return comparatie;
            }
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

}

package fileio;

import entertainment.Season;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Information about a tv show, retrieved from parsing the input test files
 * <p>
 * DO NOT MODIFY
 */
public final class SerialInputData extends ShowInput {
    /**
     * Number of seasons
     */
    private final int numberOfSeasons;
    /**
     * Season list
     */
    private final ArrayList<Season> seasons;

    public Double rating;

    private Integer numar_voturi;

    private Integer duration;

    private Integer nr_views;

    public Integer getNr_views() {
        return nr_views;
    }

    public void setNr_views(Integer nr_views) {
        this.nr_views = nr_views;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getNumar_voturi() {
        return numar_voturi;
    }

    public void setNumar_voturi(Integer numar_voturi) {
        this.numar_voturi = numar_voturi;
    }

    public SerialInputData(final String title, final ArrayList<String> cast,
                           final ArrayList<String> genres,
                           final int numberOfSeasons, final ArrayList<Season> seasons,
                           final int year) {
        super(title, year, cast, genres);
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = seasons;
        this.rating = 0.00;
        this.numar_voturi = 0;
        this.duration = 0;
        this.nr_views = 0;
    }

    public int getNumberSeason() {
        return numberOfSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public static Comparator<SerialInputData> RatingsComparator = new Comparator<SerialInputData>() {
        @Override
        public int compare(SerialInputData o1, SerialInputData o2) {
            int comparatie = o1.rating.compareTo(o2.rating);
            if (comparatie != 0) {
                return comparatie;
            }
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    public static Comparator<SerialInputData> FavoriteComparator = new Comparator<SerialInputData>() {
        @Override
        public int compare(SerialInputData o1, SerialInputData o2) {
            int comparatie = o1.numar_voturi.compareTo(o2.numar_voturi);
            if (comparatie != 0) {
                return comparatie;
            }
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    public static Comparator<SerialInputData> DurationComparator = new Comparator<SerialInputData>() {
        @Override
        public int compare(SerialInputData o1, SerialInputData o2) {
            int comparatie = o1.getDuration().compareTo(o2.getDuration());
            if (comparatie != 0) {
                return comparatie;
            }
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    public static Comparator<SerialInputData> NrViewsComparator = new Comparator<SerialInputData>() {
        @Override
        public int compare(SerialInputData o1, SerialInputData o2) {
            int comparatie = o1.getNr_views().compareTo(o2.getNr_views());
            if (comparatie != 0) {
                return comparatie;
            }
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    @Override
    public String toString() {
        return "SerialInputData{" + " title= "
                + super.getTitle() + " " + " year= "
                + super.getYear() + " cast {"
                + super.getCast() + " }\n" + " genres {"
                + super.getGenres() + " }\n "
                + " numberSeason= " + numberOfSeasons
                + ", seasons=" + seasons + "\n\n" + '}';
    }
}

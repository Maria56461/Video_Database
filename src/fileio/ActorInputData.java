package fileio;

import actor.ActorsAwards;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

/**
 * Information about an actor, retrieved from parsing the input test files
 * <p>
 * DO NOT MODIFY
 */
public final class ActorInputData implements Comparable<ActorInputData> {
    /**
     * actor name
     */
    private String name;

    private Integer number_of_awards;

    public Integer getNumber_of_awards() {
        return number_of_awards;
    }

    public void setNumber_of_awards(Integer number_of_awards) {
        this.number_of_awards = number_of_awards;
    }

    /* description of the actor's career
     */
    private String careerDescription;
    /**
     * videos starring actor
     */
    private ArrayList<String> filmography;
    /**
     * awards won by the actor
     */
    public Double media_ratingurilor;

    private Map<ActorsAwards, Integer> awards;

    @Override
    public int compareTo(ActorInputData o) {
        int comparatie = this.media_ratingurilor.compareTo(o.media_ratingurilor);
        if (comparatie != 0) {
            return comparatie;
        }
        return this.getName().compareTo(o.getName());
    }

    public static Comparator<ActorInputData> AwardsComparator = new Comparator<ActorInputData>() {
        @Override
        public int compare(ActorInputData o1, ActorInputData o2) {
            int comparatie = o1.getNumber_of_awards().compareTo(o2.getNumber_of_awards());
            if (comparatie != 0) {
                return comparatie;
            }
            return o1.getName().compareTo(o2.getName());
        }
    };

    public static Comparator<ActorInputData> NameComparator = new Comparator<ActorInputData>() {
        @Override
        public int compare(ActorInputData o1, ActorInputData o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public ActorInputData(final String name, final String careerDescription,
                          final ArrayList<String> filmography,
                          final Map<ActorsAwards, Integer> awards) {
        this.name = name;
        this.careerDescription = careerDescription;
        this.filmography = filmography;
        this.awards = awards;
        this.number_of_awards = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ArrayList<String> getFilmography() {
        return filmography;
    }

    public void setFilmography(final ArrayList<String> filmography) {
        this.filmography = filmography;
    }

    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    public String getCareerDescription() {
        return careerDescription;
    }

    public void setCareerDescription(final String careerDescription) {
        this.careerDescription = careerDescription;
    }

    @Override
    public String toString() {
        return "ActorInputData{"
                + "name='" + name + '\''
                + ", careerDescription='"
                + careerDescription + '\''
                + ", filmography=" + filmography + '}';
    }
}

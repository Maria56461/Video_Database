package fileio;

import entertainment.Season;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueryAverage {
    public String cautare_medie_ratinguri(Input input,
                                          ActionInputData cauta_actori_media_ratingurilor) {
        String mesaj;
        //parcurg lista de actori pentru a calcula
        // pentru fiecare actor media ratingurilor videourilor sale
        for (ActorInputData actorul_i : input.getActors()) {
            //parcurg lista sa de videouri pentru a calcula suma de rating-uri
            Double sum_ratinguri_total_filme = 0.00;
            int numar_filme_rating_nenul = 0;
            Double sum_ratinguri_total_seriale = 0.00;
            int numar_seriale_rating_nenul = 0;
            for (String video_title_i : actorul_i.getFilmography()) {
                //caut titlul in lista de filme
                for (MovieInputData filmul_i : input.getMovies()) {
                    if (filmul_i.getTitle().equals(video_title_i)) {
                        //l-am gasit in lista de filme
                        //i-am aflat media rating-urilor
                        Double suma_ratinguri_filmul_i = 0.00;
                        for (Double ratingul_i : filmul_i.ratings) {
                            suma_ratinguri_filmul_i += ratingul_i;
                        }
                        filmul_i.average_rating = suma_ratinguri_filmul_i / filmul_i.ratings.size();
                        //daca filmul nu a primit rating, media sa va fi NaN
                        if (filmul_i.ratings.size() == 0) {
                            filmul_i.average_rating = 0.00;
                        }
                        sum_ratinguri_total_filme += filmul_i.average_rating;
                        if (filmul_i.average_rating > 0) {
                            numar_filme_rating_nenul++;
                        }
                    }
                }
                for (SerialInputData serialul_i : input.getSerials()) {
                    if (serialul_i.getTitle().equals(video_title_i)) {
                        //l-am gasit in lista de seriale
                        Double suma_ratinguri_serialul_i = 0.00;
                        //parcurg lista sa de sezoane
                        for (Season sezonul_i : serialul_i.getSeasons()) {
                            Double suma_ratinguri_sezonul_i = 0.00;
                            //parcurg lista de ratinguri ale fiecarui sezon
                            for (Double rating_ind : sezonul_i.getRatings()) {
                                suma_ratinguri_sezonul_i += rating_ind;
                            }
                            sezonul_i.average_ratings =
                                    suma_ratinguri_sezonul_i / sezonul_i.getRatings().size();
                            if (sezonul_i.getRatings().size() == 0) {
                                //daca un sezon nu a primit niciun rating
                                sezonul_i.average_ratings = 0.00;
                            }
                            suma_ratinguri_serialul_i += sezonul_i.average_ratings;
                        }
                        serialul_i.rating =
                                suma_ratinguri_serialul_i / serialul_i.getNumberSeason();
                        if (serialul_i.rating > 0) {
                            numar_seriale_rating_nenul++;
                        }
                        sum_ratinguri_total_seriale += serialul_i.rating;
                    }
                }
            }
            actorul_i.media_ratingurilor = (sum_ratinguri_total_filme + sum_ratinguri_total_seriale)
                    / (numar_filme_rating_nenul + numar_seriale_rating_nenul);
            if (numar_filme_rating_nenul + numar_seriale_rating_nenul == 0) {
                //daca actorul nu a primit nota la niciun video in care a jucat
                actorul_i.media_ratingurilor = 0.00;
            }
        }
        //sortez actorii dupa cele doua criterii
        if (cauta_actori_media_ratingurilor.getSortType().equals("asc")) {
            Collections.sort(input.getActors());
        } else {
            Collections.sort(input.getActors(), Collections.reverseOrder());
        }
        mesaj = "Query result: [";
        int a = 0;

        List<ActorInputData> result = new ArrayList<>();

        for (ActorInputData actor : input.getActors()) {
            if (actor.media_ratingurilor > 0) {
                result.add(actor);
            }
        }

        for (int i = 0; i < Integer.min(cauta_actori_media_ratingurilor.getNumber(), result.size()); i++) {
            if (i == Integer.min(cauta_actori_media_ratingurilor.getNumber(), result.size()) - 1) {
                mesaj += result.get(i).getName();
            } else {
                mesaj += result.get(i).getName();
                mesaj += ", ";
            }
        }

        mesaj += "]";
        return mesaj;
    }
}

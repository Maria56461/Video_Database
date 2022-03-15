package fileio;

import entertainment.Season;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueryVideosRating {
    public String cauta_videouri_rating(Input input, ActionInputData cauta_videouri_grade) {
        String mesaj = "Query result: [";

        //setez rating-urile filmelor si serialelor
        for (ActorInputData actorul_i : input.getActors()) {
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

        if (cauta_videouri_grade.getObjectType().equals("movies")) {

            List<MovieInputData> lista_filme_cautate = new ArrayList<>();
            for (MovieInputData filmul_i : input.getMovies()) {
                //parcurg lista de filme
                //ca sa le gasesc pe cele de genul si anul respectiv
                int nr_genuri = 0;
                if (cauta_videouri_grade.getFilters().get(1).get(0) != null) {
                    for (String genul_filmului_i : filmul_i.getGenres()) {
                        //parcurg lista de genuri
                        for (String gen_comanda_film : cauta_videouri_grade.getFilters().get(1)) {
                            if (genul_filmului_i.equals(gen_comanda_film)) {
                                //daca filmul are genul din comanda
                                if (cauta_videouri_grade.getFilters().get(0).get(0) != null) {
                                    if (Integer.toString(filmul_i.getYear())
                                            .equals(cauta_videouri_grade.getFilters().get(0)
                                                    .get(0))) {
                                        //daca are si anul din comanda
                                        nr_genuri++;
                                    }
                                } else {
                                    nr_genuri++;
                                }
                            }
                        }
                    }
                   if (nr_genuri == cauta_videouri_grade.getFilters().get(1).size()) {
                       if (filmul_i.average_rating > 0) {
                           lista_filme_cautate.add(filmul_i);
                       }
                   }
                }
                if (cauta_videouri_grade.getFilters().get(1).get(0) == null) {
                    //daca filtrul de gen este nul
                    if (cauta_videouri_grade.getFilters().get(0).get(0) == null) {
                        //daca si filtrul de an este nul
                        lista_filme_cautate.add(filmul_i);
                    } else {
                        //daca filtrul de an este nenul
                        if (Integer.toString(filmul_i.getYear())
                                .equals(cauta_videouri_grade.getFilters().get(0)
                                        .get(0))) {
                            lista_filme_cautate.add(filmul_i);
                        }
                    }
                }
            }

            //sortez filmele dupa rating
            if (cauta_videouri_grade.getSortType().equals("asc")) {
                Collections.sort(lista_filme_cautate, MovieInputData.RatingsComparator);
            } else {
                Collections.sort(lista_filme_cautate, MovieInputData.RatingsComparator);
                Collections.reverse(lista_filme_cautate);
            }

            for (int i = 0; i < Integer.min(cauta_videouri_grade.getNumber(), lista_filme_cautate.size()); i++) {
                if (lista_filme_cautate.size() > i) {
                    if (i == Integer.min(cauta_videouri_grade.getNumber(),
                            lista_filme_cautate.size()) - 1) {
                        mesaj += lista_filme_cautate.get(i).getTitle();
                    } else {
                        mesaj += lista_filme_cautate.get(i).getTitle();
                        mesaj += ", ";
                    }
                }
            }

            mesaj += "]";
            return mesaj;
            }
            if (cauta_videouri_grade.getObjectType().equals("shows")) {

                List<SerialInputData> lista_seriale_cautate = new ArrayList<>();

                for (SerialInputData serialul_i : input.getSerials()) {
                    //parcurg lista de seriale
                    int numar_genuri = 0;
                    if (cauta_videouri_grade.getFilters().get(1).get(0) != null) {
                        //daca filtrul de gen este nenul
                        for (String genul_serialului_i : serialul_i.getGenres()) {
                            //genurile serialului i
                            for (String gen_comanda_serial : cauta_videouri_grade.getFilters()
                                    .get(1)) {
                                //genurile din comanda
                                if (genul_serialului_i.equals(gen_comanda_serial)) {
                                    //daca serialul are genul din comanda
                                    if (cauta_videouri_grade.getFilters().get(0).get(0) != null) {
                                        //daca anul este nenul
                                        if (Integer.toString(serialul_i.getYear())
                                                .equals(cauta_videouri_grade.getFilters().get(0)
                                                        .get(0))) {
                                            //daca are si anul din comanda
                                            numar_genuri++;
                                        }
                                    } else {
                                        numar_genuri++;
                                    }
                                }
                            }
                        }
                        if (numar_genuri == cauta_videouri_grade.getFilters().get(1).size()) {
                           // System.out.println(serialul_i.rating);
                            if (serialul_i.rating > 0) {
                                lista_seriale_cautate.add(serialul_i);
                            }
                        }
                    }
                    if (cauta_videouri_grade.getFilters().get(1).get(0) == null) {
                        //daca filtrul de gen este nul
                        if (cauta_videouri_grade.getFilters().get(0).get(0) == null) {
                            //daca si filtrul de an este nul
                            lista_seriale_cautate.add(serialul_i);
                        } else {
                            //daca filtrul de an este nenul
                            if (Integer.toString(serialul_i.getYear())
                                    .equals(cauta_videouri_grade.getFilters().get(0)
                                            .get(0))) {
                                lista_seriale_cautate.add(serialul_i);
                            }
                        }
                    }
                }

                //sortez serialele dupa rating
                if (cauta_videouri_grade.getSortType().equals("asc")) {
                    Collections.sort(lista_seriale_cautate, SerialInputData.RatingsComparator);
                } else {
                    Collections.sort(lista_seriale_cautate, SerialInputData.RatingsComparator);
                    Collections.reverse(lista_seriale_cautate);
                }

                //formarea mesajului
                for (int i = 0; i < Integer.min(cauta_videouri_grade.getNumber(), lista_seriale_cautate.size()); i++) {
                    if (lista_seriale_cautate.size() > i) {
                        if (i == Integer.min(cauta_videouri_grade.getNumber(),
                                lista_seriale_cautate.size()) - 1) {
                            mesaj += lista_seriale_cautate.get(i).getTitle();
                        } else {
                            mesaj += lista_seriale_cautate.get(i).getTitle();
                            mesaj += ", ";
                        }
                    }
                }

                mesaj += "]";
                return mesaj;
        }

        return " ";
    }

}

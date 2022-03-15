package fileio;

import java.util.List;
import java.util.Map;

public final class CommandRating {

    /**
     * @param utilizatori      lista de users din input
     * @param comandaratingg actiunea
     * @param movies filmele din input
     * @param serials serialele din input
     * @return mesaj
     */
    public String comanda_rating(final List<UserInputData> utilizatori,
                                 final List<MovieInputData> movies,
                                 final List<SerialInputData> serials,
                                 final ActionInputData comandaratingg) {
        String mesaj;

        //pe fiecare linie, nr-ul de rating-uri ale sezoanelor unui serial
        //parcurg lista de utilizatori pana gasesc numele
        // utilizatorului care da comanda
        for (UserInputData utilizator : utilizatori) {
            if (utilizator.getUsername().
                    equals(comandaratingg.getUsername())) {
                //daca am gasit numele utilizatorului in lista
                //parcurg lista sa de videouri vizualizate (istoricul)
                Map<String, Integer> istoric = utilizator.getHistory();
                for (Map.Entry<String, Integer> obiect : istoric.entrySet()) {
                    //daca gasesc la vizualizate videoul meu din comanda
                    if (obiect.getKey().equals(comandaratingg.getTitle())) {
                        if (comandaratingg.getSeasonNumber() == 0) {
                            //daca este film
                            for (int j = 0; j < movies.size(); j++) {
                                //il caut in lista de filme
                                if (movies.get(j).getTitle().
                                        equals(comandaratingg.getTitle())) {
                                    //daca il gasesc printre filme
                                    if (utilizator.
                                            getNumber_of_ratings_movies().
                                            get(j) == 0) {
                                        //daca nu i s-a mai dat nota
                                        movies.get(j).ratings.
                                                add(comandaratingg.getGrade());
                                        utilizator.
                                                getNumber_of_ratings_movies().
                                                set(j, 1);
                                        mesaj = "success -> "
                                                + movies.get(j).getTitle()
                                                + " was rated with "
                                                + comandaratingg.getGrade()
                                                + " by "
                                                + comandaratingg.getUsername();
                                        return mesaj;
                                    } else {
                                        mesaj = "error -> "
                                                + comandaratingg.getTitle()
                                                + " has been already rated";
                                        return mesaj;
                                    }
                                }
                            }
                            mesaj = "Acest film nu exista!";
                            return mesaj;
                        }
                        //daca este serial
                        if (comandaratingg.getSeasonNumber() > 0) {
                            for (int k = 0; k < serials.size(); k++) {
                                //il caut in lista de seriale
                                if (serials.get(k).getTitle().
                                        equals(comandaratingg.getTitle())) {
                                    //daca il gasesc printre seriale
                                    for (int l = 0; l < serials.get(k).
                                            getNumberSeason(); l++) {
                                        //parcurg lista de sezoane
                                        if (serials.get(k).getSeasons().
                                                get(l).getCurrentSeason()
                                                == comandaratingg.
                                                getSeasonNumber()) {
                                            //daca gasesc sezonul
                                            // din comanda mea
                                            if (utilizator.
                                                    getNumber_of_ratings_seriale()
                                                    .get(k)
                                                    .get(l) == 0) {
                                                //daca nu i s-a mai dat nota
                                                // sezonului respectiv
                                                serials.get(k).getSeasons()
                                                        .get(l).getRatings()
                                                        .add(comandaratingg.
                                                                getGrade());
                                                utilizator.
                                                        getNumber_of_ratings_seriale()
                                                        .get(k).set(l, 1);
                                                //i-am dat o nota sezonului l
                                                mesaj = "success -> "
                                                        + serials.get(k)
                                                        .getTitle()
                                                        + " was rated with "
                                                        + comandaratingg.
                                                        getGrade()
                                                        + " by "
                                                        + comandaratingg.
                                                        getUsername();
                                                return mesaj;
                                            }
                                            mesaj =
                                                    "error -> "
                                                            + serials.get(k)
                                                            .getTitle()
                                                            + " has been"
                                                            + " already rated";
                                            return mesaj;
                                        }
                                    }
                                    mesaj = "Acest sezon nu exista!";
                                    return mesaj;
                                }
                            }
                            mesaj = "Acest serial nu exista!";
                            return mesaj;
                        }
                    }
                }
                mesaj = "error -> " +  comandaratingg.getTitle()
                        + " is not seen";
                return mesaj;
            }
        }
        mesaj = "Acest utilizator nu exista!";
        return mesaj;
    }
}

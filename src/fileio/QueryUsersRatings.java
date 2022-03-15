package fileio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueryUsersRatings {
    public String cauta_users(Input input, ActionInputData cauta_users) {
        String mesaj = "Query result: [";
        for (UserInputData userul_i : input.getUsers()) {
            //parcurg lista de utilizatori
            //calculez numarul de ratinguri pt. seriale
            int nr_ratinguri_sezoane = 0;
            for (int i = 0; i < userul_i.getNumber_of_ratings_seriale().size(); i++) {
                //pentru fiecare serial care a primit rating
                for (int j = 0; j < userul_i.getNumber_of_ratings_seriale().size(); j++) {
                    //pentru fiecare sezon
                    if (userul_i.getNumber_of_ratings_seriale().get(i).get(j) == 1) {
                        //daca a primit nota
                        nr_ratinguri_sezoane++;
                    }
                }
            }
            //calculez numarul de ratinguri pt. filme
            int nr_ratinguri_filme = 0;
            for (int i = 0; i < userul_i.getNumber_of_ratings_movies().size(); i++) {
                //pentru fiecare film
                if (userul_i.getNumber_of_ratings_movies().get(i) == 1) {
                    nr_ratinguri_filme++;
                }
            }
            userul_i.setNr_total_ratinguri_date(nr_ratinguri_filme + nr_ratinguri_sezoane);
        }
        List<UserInputData> lista = new ArrayList<>();
        for (int i = 0; i < input.getUsers().size(); i++) {
            if (input.getUsers().get(i).getNr_total_ratinguri_date() > 0) {
                lista.add(input.getUsers().get(i));
            }
        }
        //sortez userii dupa numarul de ratinguri date
        if (cauta_users.getSortType().equals("desc")) {
            //sortez lista descrescator
            Collections.sort(lista, UserInputData.NumberRatingsComparator);
            Collections.reverse(lista);
        } else {
            Collections.sort(lista, UserInputData.NumberRatingsComparator);
        }

        //formez mesajul
        for (int i = 0; i < cauta_users.getNumber(); i++) {
            if (lista.size() > i) {
                mesaj += lista.get(i).getUsername();
                if (i !=
                        Integer.min(cauta_users.getNumber() - 1, lista.size() - 1)) {
                    mesaj += ", ";
                }
            }
        }
        mesaj += "]";
        return mesaj;
    }

}

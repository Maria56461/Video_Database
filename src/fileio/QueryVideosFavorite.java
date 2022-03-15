package fileio;

import entertainment.Season;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueryVideosFavorite {
    public String cauta_videouri_favorite(Input input, ActionInputData cauta_videouri_favorite) {
        String mesaj = "Query result: [";

        if (cauta_videouri_favorite.getObjectType().equals("movies")) {
            List<MovieInputData> lista_filme_favorite = new ArrayList<>();
            for (MovieInputData filmul_i : input.getMovies()) {
                //parcurg lista de filme
                int nr_genuri = 0;
                //cate genuri din comanda mea are filmul i
                for (UserInputData userul_i : input.getUsers()) {
                    //parcurg lista de utilizatori
                    for (String favoritul_i : userul_i.getFavoriteMovies()) {
                        //parcurg lista de favorite a utilizatorului i
                        if (favoritul_i.equals(filmul_i.getTitle())) {
                            //daca filmul i este favorit al unui utilizator
                            filmul_i.setNumar_voturi(filmul_i.getNumar_voturi() + 1);
                        }
                    }
                }
                if (cauta_videouri_favorite.getFilters().get(1).get(0) != null) {
                    for (String genul_filmului_i : filmul_i.getGenres()) {
                        //parcurg lista de genuri ale filmului
                        for (String gen_comanda : cauta_videouri_favorite.getFilters().get(1)) {
                            //parcurg lista de genuri din comanda
                            if (genul_filmului_i.equals(gen_comanda)) {
                                //daca filmul are genul din comanda
                                if (cauta_videouri_favorite.getFilters().get(0).get(0) != null) {
                                    //daca filtrul de an nu este nul
                                    if (Integer.toString(filmul_i.getYear())
                                            .equals(cauta_videouri_favorite.getFilters().get(0)
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
                    if (nr_genuri == cauta_videouri_favorite.getFilters().get(1).size()) {
                        if (filmul_i.getNumar_voturi() > 0) {
                            //adaug filmul in lista
                            lista_filme_favorite.add(filmul_i);
                        }
                    }
                }
                if (cauta_videouri_favorite.getFilters().get(1).get(0) == null){
                    //daca filtrul de gen este nul
                    if (cauta_videouri_favorite.getFilters().get(0).get(0) == null) {
                        //daca si filtrul de an este nul
                        lista_filme_favorite.add(filmul_i);
                    } else {
                        //daca filtrul de an este nenul
                        if (Integer.toString(filmul_i.getYear())
                                .equals(cauta_videouri_favorite.getFilters().get(0)
                                        .get(0))){
                            lista_filme_favorite.add(filmul_i);
                        }
                    }
                }
            }
            if (cauta_videouri_favorite.getSortType().equals("desc")) {
                //sortez lista dupa campul "numar_voturi" descrescator
                Collections.sort(lista_filme_favorite, MovieInputData.FavoriteComparator);
                Collections.reverse(lista_filme_favorite);
            } else {
                Collections.sort(lista_filme_favorite, MovieInputData.FavoriteComparator);
            }
            //formez mesajul
            for (int i = 0; i < cauta_videouri_favorite.getNumber(); i++) {
                if (lista_filme_favorite.size() > i) {
                    mesaj += lista_filme_favorite.get(i).getTitle();
                    if (i != Integer.min(cauta_videouri_favorite.getNumber() - 1, lista_filme_favorite.size() - 1)) {
                        mesaj += ", ";
                    }
                }
            }
            mesaj += "]";
            return mesaj;
        }
        if (cauta_videouri_favorite.getObjectType().equals("shows")) {
            List<SerialInputData> lista_seriale_favorite = new ArrayList<>();
            for (SerialInputData serialul_i : input.getSerials()) {
                //parcurg lista de seriale
                int numar_genuri = 0;
                for (UserInputData userul_i : input.getUsers()) {
                    //parcurg lista de utilizatori
                    for (String favoritul_i : userul_i.getFavoriteMovies()) {
                        //parcurg lista de favorite a utilizatorului i
                        if (favoritul_i.equals(serialul_i.getTitle())) {
                            //daca serialul i este favorit al unui utilizator
                            serialul_i.setNumar_voturi(serialul_i.getNumar_voturi() + 1);
                        }
                    }
                }
                if (cauta_videouri_favorite.getFilters().get(1).get(0) != null) {
                    for (String genul_serialului_i : serialul_i.getGenres()) {
                        //parcurg lista de genuri ale serialului
                        for (String gen_comanda_serial : cauta_videouri_favorite.getFilters()
                                .get(1)) {
                            if (genul_serialului_i.equals(gen_comanda_serial)) {
                                //daca serialul are genul din comanda
                                if (cauta_videouri_favorite.getFilters().get(0).get(0) != null) {
                                    //daca anul este nenul
                                    if (Integer.toString(serialul_i.getYear())
                                            .equals(cauta_videouri_favorite.getFilters().get(0)
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
                    if (numar_genuri == cauta_videouri_favorite.getFilters().get(1).size()) {
                        if (serialul_i.getNumar_voturi() > 0) {
                            //daca are macar un vot
                            lista_seriale_favorite.add(serialul_i);
                        }
                    }
                }
                if (cauta_videouri_favorite.getFilters().get(1).get(0) == null){
                    //daca filtrul de gen este nul
                    if (cauta_videouri_favorite.getFilters().get(0).get(0) == null) {
                        //daca si filtrul de an este nul
                        lista_seriale_favorite.add(serialul_i);
                    } else {
                        //daca filtrul de an este nenul
                        if (Integer.toString(serialul_i.getYear())
                                .equals(cauta_videouri_favorite.getFilters().get(0)
                                        .get(0))){
                            lista_seriale_favorite.add(serialul_i);
                        }
                    }
                }
            }

            if (cauta_videouri_favorite.getSortType().equals("desc")) {
                //sortez lista dupa campul "numar_voturi" descrescator
                Collections.sort(lista_seriale_favorite, SerialInputData.FavoriteComparator);
                Collections.reverse(lista_seriale_favorite);
            } else {
                Collections.sort(lista_seriale_favorite, SerialInputData.FavoriteComparator);
            }
            //formez mesajul
            for (int i = 0; i < cauta_videouri_favorite.getNumber(); i++) {
                if (lista_seriale_favorite.size() > i) {
                    mesaj += lista_seriale_favorite.get(i).getTitle();
                    if (i != Integer.min(cauta_videouri_favorite.getNumber() - 1,
                            lista_seriale_favorite.size() - 1)) {
                        mesaj += ", ";
                    }
                }
            }
            mesaj += "]";
            return mesaj;
        }
        return "Actiune necorespunzatoare! ";
    }
}

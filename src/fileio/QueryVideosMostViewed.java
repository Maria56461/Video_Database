package fileio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class QueryVideosMostViewed {
    public String cauta_videouri_vizualizari(Input input, ActionInputData cauta_videouri_most_viewed) {
        String mesaj = "Query result: [";
        if (cauta_videouri_most_viewed.getObjectType().equals("shows")) {
            List<SerialInputData> lista_seriale_most_viewed = new ArrayList<>();
            for (SerialInputData serialul_i : input.getSerials()) {
                //parcurg lista de seriale
                //pentru a calcula numarul de vizualizari al ficarui serial
                int nr_genuri = 0;
                //cate genuri din comanda mea are serialul i
                for (UserInputData userul_i : input.getUsers()) {
                    //parcurg lista de utilizatori
                    for (Map.Entry<String, Integer> obiectul_i : userul_i.getHistory().entrySet()) {
                        //parcurg istoricul utilizatorului
                        if (serialul_i.getTitle().equals(obiectul_i.getKey())) {
                            //daca serialul a fost vizualizat
                            serialul_i
                                    .setNr_views(serialul_i.getNr_views() + obiectul_i.getValue());
                        }
                    }
                }
                if (cauta_videouri_most_viewed.getFilters().get(1).get(0) != null) {
                    for (String genul_serialului_i : serialul_i.getGenres()) {
                        //parcurg lista de genuri ale serialului
                        for (String gen_comanda_serial : cauta_videouri_most_viewed.getFilters()
                                .get(1)) {
                            //parcurg lista de genuri din comanda
                            if (genul_serialului_i.equals(gen_comanda_serial)) {
                                //daca serialul are genul din comanda
                                if (cauta_videouri_most_viewed.getFilters().get(0).get(0) != null) {
                                    if (Integer.toString(serialul_i.getYear())
                                            .equals(cauta_videouri_most_viewed.getFilters().get(0)
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
                    if (nr_genuri == cauta_videouri_most_viewed.getFilters().get(1).size()) {
                        //daca serialul are toate genurile din comanda
                        if (serialul_i.getNr_views() > 0) {
                            lista_seriale_most_viewed.add(serialul_i);
                        }
                    }
                }
                if (cauta_videouri_most_viewed.getFilters().get(1).get(0) == null) {
                    if (cauta_videouri_most_viewed.getFilters().get(0).get(0) == null) {
                        lista_seriale_most_viewed.add(serialul_i);
                    } else {
                        if (Integer.toString(serialul_i.getYear()).equals(cauta_videouri_most_viewed.getFilters().get(0).get(0))) {
                            lista_seriale_most_viewed.add(serialul_i);
                        }
                    }
                }
            }
            //sortez lista corespunzator
            if (cauta_videouri_most_viewed.getSortType().equals("desc")) {
                //sortez lista dupa campul "duration" descrescator
                Collections.sort(lista_seriale_most_viewed, SerialInputData.NrViewsComparator);
                Collections.reverse(lista_seriale_most_viewed);
            } else {
                Collections.sort(lista_seriale_most_viewed, SerialInputData.NrViewsComparator);
            }
            //formez mesajul
            for (int i = 0; i < cauta_videouri_most_viewed.getNumber(); i++) {
                if (lista_seriale_most_viewed.size() > i) {
                    mesaj += lista_seriale_most_viewed.get(i).getTitle();
                    if (i != Integer.min(cauta_videouri_most_viewed.getNumber() - 1,
                            lista_seriale_most_viewed.size() - 1)) {
                        mesaj += ", ";
                    }
                }
            }
            mesaj += "]";
            return mesaj;
        }
        if (cauta_videouri_most_viewed.getObjectType().equals("movies")) {
            List<MovieInputData> lista_filme_most_viewed = new ArrayList<>();
            for (MovieInputData filmul_i : input.getMovies()) {
                //parcurg lista de filme
                //pentru a calcula numarul de vizualizari al ficarui film
                int numar_genuri = 0;
                //cate genuri din comanda mea are filmul i
                for (UserInputData userul_i : input.getUsers()) {
                    //parcurg lista de utilizatori
                    for (Map.Entry<String, Integer> obiectul_i : userul_i.getHistory().entrySet()) {
                        //parcurg istoricul utilizatorului
                        if (filmul_i.getTitle().equals(obiectul_i.getKey())) {
                            //daca filmul a fost vizualizat
                            filmul_i.setNumar_vizualizari(
                                    filmul_i.getNumar_vizualizari() + obiectul_i.getValue());
                        }
                    }
                }
                if (cauta_videouri_most_viewed.getFilters().get(1).get(0) != null) {
                    for (String genul_filmului_i : filmul_i.getGenres()) {
                        //parcurg lista de genuri ale filmului
                        for (String gen_comanda_film : cauta_videouri_most_viewed.getFilters()
                                .get(1)) {
                            //parcurg lista de genuri din comanda
                            if (genul_filmului_i.equals(gen_comanda_film)) {
                                //daca filmul are genul din comanda
                                if (cauta_videouri_most_viewed.getFilters().get(0).get(0) != null) {
                                    if (Integer.toString(filmul_i.getYear())
                                            .equals(cauta_videouri_most_viewed.getFilters().get(0)
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
                    if (numar_genuri == cauta_videouri_most_viewed.getFilters().get(1).size()) {
                        //daca filmul are toate genurile din comanda
                        if (filmul_i.getNumar_vizualizari() > 0) {
                            lista_filme_most_viewed.add(filmul_i);
                        }
                    }
                }
                if (cauta_videouri_most_viewed.getFilters().get(1).get(0) == null) {
                    if (cauta_videouri_most_viewed.getFilters().get(0).get(0) == null) {
                        lista_filme_most_viewed.add(filmul_i);
                    } else {
                        if (Integer.toString(filmul_i.getYear()).equals(cauta_videouri_most_viewed.getFilters().get(0).get(0))) {
                            lista_filme_most_viewed.add(filmul_i);
                        }
                    }
                }
            }
            //sortez lista corespunzator
            if (cauta_videouri_most_viewed.getSortType().equals("desc")) {
                //sortez lista dupa campul "duration" descrescator
                Collections.sort(lista_filme_most_viewed, MovieInputData.NumberViewsComparator);
                Collections.reverse(lista_filme_most_viewed);
            } else {
                Collections.sort(lista_filme_most_viewed, MovieInputData.NumberViewsComparator);
            }
            //formez mesajul
            for (int i = 0; i < cauta_videouri_most_viewed.getNumber(); i++) {
                if (lista_filme_most_viewed.size() > i) {
                    mesaj += lista_filme_most_viewed.get(i).getTitle();
                    if (i != Integer.min(cauta_videouri_most_viewed.getNumber() - 1,
                            lista_filme_most_viewed.size() - 1)) {
                        mesaj += ", ";
                    }
                }
            }
            mesaj += "]";
            return mesaj;
        }
        return "Comanda necorespunzatoare!";
    }
}
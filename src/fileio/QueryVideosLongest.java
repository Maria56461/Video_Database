package fileio;

import entertainment.Season;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueryVideosLongest {
    public String cauta_videouri_durata(Input input, ActionInputData cauta_videouri_lungime) {
        String mesaj = "Query result: [";
        if (cauta_videouri_lungime.getObjectType().equals("shows")) {
            List<SerialInputData> lista_seriale_durata = new ArrayList<>();
            for (SerialInputData serialul_i : input.getSerials()) {
                //parcurg lista de seriale
                //pentru a calcula durata ficarui serial
                int nr_genuri = 0;
                //cate genuri din comanda mea are serialul i
                for (Season sezonul_i : serialul_i.getSeasons()) {
                    //parcurg lista de sezoane ale serialului i
                    serialul_i.setDuration(serialul_i.getDuration() + sezonul_i.getDuration());
                }
                if (cauta_videouri_lungime.getFilters().get(1).get(0) != null) {
                    for (String genul_serialului_i : serialul_i.getGenres()) {
                        //parcurg lista de genuri ale serialului
                        for (String gen_comanda_serial : cauta_videouri_lungime.getFilters()
                                .get(1)) {
                            //parcurg lista de genuri din comanda
                            if (genul_serialului_i.equals(gen_comanda_serial)) {
                                //daca serialul are genul din comanda
                                if (cauta_videouri_lungime.getFilters().get(0).get(0) != null) {
                                    //daca filtrul de an nu este nul
                                    if (Integer.toString(serialul_i.getYear())
                                            .equals(cauta_videouri_lungime.getFilters().get(0)
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
                    if (nr_genuri == cauta_videouri_lungime.getFilters().get(1).size()) {
                        //daca serialul are toate genurile din comanda
                        if (serialul_i.getDuration() > 0) {
                            lista_seriale_durata.add(serialul_i);
                        }
                    }
                }
                if (cauta_videouri_lungime.getFilters().get(1).get(0) == null){
                    //daca filtrul de gen este nul
                    if (cauta_videouri_lungime.getFilters().get(0).get(0) == null) {
                        //daca si filtrul de an este nul
                        lista_seriale_durata.add(serialul_i);
                    } else {
                        //daca filtrul de an este nenul
                        if (Integer.toString(serialul_i.getYear())
                                .equals(cauta_videouri_lungime.getFilters().get(0)
                                        .get(0))){
                            lista_seriale_durata.add(serialul_i);
                        }
                    }
                }
            }
            //sortez lista corespunzator
            if (cauta_videouri_lungime.getSortType().equals("desc")) {
                //sortez lista dupa campul "duration" descrescator
                Collections.sort(lista_seriale_durata, SerialInputData.DurationComparator);
                Collections.reverse(lista_seriale_durata);
            } else {
                Collections.sort(lista_seriale_durata, SerialInputData.DurationComparator);
            }
            //formez mesajul
            for (int i = 0; i < cauta_videouri_lungime.getNumber(); i++) {
                if (lista_seriale_durata.size() > i) {
                    mesaj += lista_seriale_durata.get(i).getTitle();
                    if (i != Integer.min(cauta_videouri_lungime.getNumber() - 1, lista_seriale_durata.size() - 1)) {
                        mesaj += ", ";
                    }
                }
            }
            mesaj += "]";
            return mesaj;
        }
        if (cauta_videouri_lungime.getObjectType().equals("movies")) {
            List<MovieInputData> lista_filme_durata = new ArrayList<>();
            for (MovieInputData filmul_i : input.getMovies()) {
                //parcurg lista de filme
                int numar_genuri = 0;
                //cate genuri din comanda mea are filmul i
                if (cauta_videouri_lungime.getFilters().get(1).get(0) != null) {
                    for (String genul_filmului_i : filmul_i.getGenres()) {
                        //parcurg lista de genuri ale filmului
                        for (String gen_comanda_film : cauta_videouri_lungime.getFilters().get(1)) {
                            //parcurg lista de genuri din comanda
                            if (genul_filmului_i.equals(gen_comanda_film)) {
                                //daca filmul are genul din comanda
                                if (cauta_videouri_lungime.getFilters().get(0).get(0) != null) {
                                    //daca filtrul de an nu este nul
                                    if (Integer.toString(filmul_i.getYear())
                                            .equals(cauta_videouri_lungime.getFilters().get(0)
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
                    if (numar_genuri == cauta_videouri_lungime.getFilters().get(1).size()) {
                        //daca filmul are toate genurile din comanda
                        if (filmul_i.getDuration() > 0) {
                            lista_filme_durata.add(filmul_i);
                        }
                    }
                }
                if (cauta_videouri_lungime.getFilters().get(1).get(0) == null){
                    //daca filtrul de gen este nul
                    if (cauta_videouri_lungime.getFilters().get(0).get(0) == null) {
                        //daca si filtrul de an este nul
                        lista_filme_durata.add(filmul_i);
                    } else {
                        //daca filtrul de an este nenul
                        if (Integer.toString(filmul_i.getYear())
                                .equals(cauta_videouri_lungime.getFilters().get(0)
                                        .get(0))){
                            lista_filme_durata.add(filmul_i);
                        }
                    }
                }
            }
            //sortez lista corespunzator
            if (cauta_videouri_lungime.getSortType().equals("desc")) {
                //sortez lista dupa campul "duration" descrescator
                Collections.sort(lista_filme_durata, MovieInputData.DurationComparator);
                Collections.reverse(lista_filme_durata);
            } else {
                Collections.sort(lista_filme_durata, MovieInputData.DurationComparator);
            }
            //formez mesajul
            for (int i = 0; i < cauta_videouri_lungime.getNumber(); i++) {
                if (lista_filme_durata.size() > i) {
                    mesaj += lista_filme_durata.get(i).getTitle();
                    if (i != Integer.min(cauta_videouri_lungime.getNumber() - 1,
                            lista_filme_durata.size() - 1)) {
                        mesaj += ", ";
                    }
                }
            }
            mesaj += "]";
            return mesaj;
        }
        return mesaj;
    }
}

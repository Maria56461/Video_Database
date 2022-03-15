package fileio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RecommendationFavorite {
    public String recomanda_favorite(Input input, ActionInputData recomandare_favorite) {
        QueryVideosFavorite cauta_favorite = new QueryVideosFavorite();
        ActionInputData actiune1 = new ActionInputData(0, " ", "shows", null, "desc", " ", null,  3, null, null);
        ActionInputData actiune2 = new ActionInputData(1, " ", "movies", null, "desc", " ", null, 4, null, null);
        cauta_favorite.cauta_videouri_favorite(input, actiune1);
        cauta_favorite.cauta_videouri_favorite(input, actiune2);
        //cele doua actiuni seteaza numarul de voturi ale filmelor si serialelor
        List<ShowInput> nevizualizate = new ArrayList<>() ;
        String mesaj = "FavoriteRecommendation result: ";

        for (UserInputData userul_i : input.getUsers()) {
            if (userul_i.getUsername().equals(recomandare_favorite.getUsername())) {
                for (MovieInputData filmul_i : input.getMovies()) {
                    //parcurg lista de filme din baza de date
                    int aux = 0;
                    for (Map.Entry<String, Integer> obiectul_i_din_istoric : userul_i.getHistory()
                            .entrySet()) {
                        //parcurg istoricul utilizatorului
                        if (obiectul_i_din_istoric.getKey().equals(filmul_i.getTitle())) {
                            //daca filmul i este vizualizat
                            //System.out.println(filmul_i.getTitle());
                            break;
                        }
                        aux++;
                        if (aux == userul_i.getHistory().size()) {
                            //daca filmul nu a fost vizualizat
                            nevizualizate.add(filmul_i);
                        }
                    }
                }
                for (SerialInputData serialul_i : input.getSerials()) {
                    //parcurg lista de seriale din baza de date
                    // System.out.println(serialul_i.getTitle());
                    int b = 0;
                    for (Map.Entry<String, Integer> obiectul_i_din_istoric : userul_i.getHistory()
                            .entrySet()) {
                        //parcurg istoricul utilizatorului
                        if (obiectul_i_din_istoric.getKey().equals(serialul_i.getTitle())) {
                            //daca serialul i este vizualizat
                            //System.out.println(serialul_i.getTitle());
                            break;
                        }
                        b++;
                        if (b == userul_i.getHistory().size()) {
                            //daca serialul nu a fost vizualizat
                            nevizualizate.add(serialul_i);
                        }
                    }
                }
            }
        }

        List<Integer> lista_voturi = new ArrayList<>();
        //lista cu nr-ul de voturi al fiecarui video nevizualizat

        for (ShowInput videoul_i : nevizualizate) {
            if (videoul_i instanceof MovieInputData) {
                lista_voturi.add(((MovieInputData) videoul_i).getNumar_voturi());
            } else if (videoul_i instanceof  SerialInputData) {
                lista_voturi.add(((SerialInputData) videoul_i).getNumar_voturi());
            }
        }
        Collections.sort(lista_voturi);

        if (lista_voturi.size() > 0) {
            int nr_maxim_voturi = lista_voturi.get(lista_voturi.size() - 1);
            for (ShowInput video : nevizualizate) {
                if (video instanceof MovieInputData) {
                    if (((MovieInputData) video).getNumar_voturi() == nr_maxim_voturi) {
                        mesaj += video.getTitle();
                        return mesaj;
                    }
                } else if (video instanceof SerialInputData) {
                    if (((SerialInputData) video).getNumar_voturi() == nr_maxim_voturi) {
                        mesaj += video.getTitle();
                        return mesaj;
                    }
                }
            }
        } else {
            mesaj = "FavoriteRecommendation cannot be applied!";
        }

        return mesaj;
    }
}

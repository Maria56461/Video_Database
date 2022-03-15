package fileio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommendationPopular {
    public String recomanda_popular(Input input, ActionInputData recomandare_popular) {
        QueryVideosMostViewed cauta_most_viewed = new QueryVideosMostViewed();
        ActionInputData actiune1 = new ActionInputData(0, " ", "shows", null, "desc", " ", null,  3, null, null);
        ActionInputData actiune2 = new ActionInputData(1, " ", "movies", null, "desc", " ", null,  3, null, null);
        cauta_most_viewed.cauta_videouri_vizualizari(input, actiune1);
        cauta_most_viewed.cauta_videouri_vizualizari(input, actiune2);
        //cele doua actiuni seteaza numarul de vizualizari ale filmelor si serialelor
        int nr_vizualizari_TV_movie = 0;
        int nr_vizualizari_drama = 0;
        int nr_vizualizari_fantasy = 0;
        int nr_vizualizari_comedy = 0;
        int nr_vizualizari_family = 0;
        int nr_vizualizari_war = 0;
        int nr_vizualizari_sci_fi_fantasy = 0;
        int nr_vizualizari_crime = 0;
        int nr_vizualizari_animation = 0;
        int nr_vizualizari_science_fiction = 0;
        int nr_vizualizari_action = 0;
        int nr_vizualizari_horror = 0;
        int nr_vizualizari_mistery = 0;
        int nr_vizualizari_western = 0;
        int nr_vizualizari_adventure = 0;
        int nr_vizualizari_action_adventure = 0;
        int nr_vizualizari_romance = 0;
        int nr_vizualizari_thriller = 0;
        int nr_vizualizari_kids = 0;
        int nr_vizualizari_history = 0;
        Map<Integer, String> vizualizari = new HashMap<Integer, String>();
        //nr. vizualizari pt fiecare gen

        for (MovieInputData filmul_i : input.getMovies()) {
            //parcurg lista de filme din baza de date
           for (String genul_i : filmul_i.getGenres()) {
               //parcurg lista de genuri ale filmului
               if (genul_i.equals("Action & Adventure")) {
                   nr_vizualizari_action_adventure += filmul_i.getNumar_vizualizari();
               }
               if (genul_i.equals("Animation")) {
                   nr_vizualizari_animation += filmul_i.getNumar_vizualizari();
               }
               if (genul_i.equals("Drama")) {
                   nr_vizualizari_drama += filmul_i.getNumar_vizualizari();
               }
               if (genul_i.equals("Mystery")) {
                   nr_vizualizari_mistery += filmul_i.getNumar_vizualizari();
               }
               if (genul_i.equals("Comedy")) {
                   nr_vizualizari_comedy += filmul_i.getNumar_vizualizari();
               }
               if (genul_i.equals("Romance")) {
                   nr_vizualizari_romance += filmul_i.getNumar_vizualizari();
               }
               if (genul_i.equals("Crime")) {
                   nr_vizualizari_crime += filmul_i.getNumar_vizualizari();
               }
               if (genul_i.equals("Horror")) {
                   nr_vizualizari_horror += filmul_i.getNumar_vizualizari();
               }
               if (genul_i.equals("Thriller")) {
                   nr_vizualizari_thriller += filmul_i.getNumar_vizualizari();
               }
               if (genul_i.equals("Family")) {
                   nr_vizualizari_family += filmul_i.getNumar_vizualizari();
               }
               if (genul_i.equals("Fantasy")) {
                   nr_vizualizari_fantasy += filmul_i.getNumar_vizualizari();
               }
               if (genul_i.equals("TV Movie")) {
                   nr_vizualizari_TV_movie += filmul_i.getNumar_vizualizari();
               }
               if (genul_i.equals("History")) {
                   nr_vizualizari_history += filmul_i.getNumar_vizualizari();
               }
               if (genul_i.equals("War")) {
                   nr_vizualizari_war += filmul_i.getNumar_vizualizari();
               }
               if (genul_i.equals("Sci-Fi & Fantasy")) {
                   nr_vizualizari_sci_fi_fantasy += filmul_i.getNumar_vizualizari();
               }
               if (genul_i.equals("Adventure")) {
                   nr_vizualizari_adventure += filmul_i.getNumar_vizualizari();
               }
               if (genul_i.equals("Action")) {
                   nr_vizualizari_action += filmul_i.getNumar_vizualizari();
               }
               if (genul_i.equals("Kids")) {
                   nr_vizualizari_kids += filmul_i.getNumar_vizualizari();
               }
               if (genul_i.equals("Science Fiction")) {
                   nr_vizualizari_science_fiction += filmul_i.getNumar_vizualizari();
               }
               if (genul_i.equals("Western")) {
                   nr_vizualizari_western += filmul_i.getNumar_vizualizari();
               }
           }
        }

        for (SerialInputData serialul_i : input.getSerials()) {
            //parcurg lista de seriale din baza de date
            for (String genul_i : serialul_i.getGenres()) {
                //parcurg lista de genuri ale serialului i
                if (genul_i.equals("Action & Adventure")) {
                    nr_vizualizari_action_adventure += serialul_i.getNr_views();
                }
                if (genul_i.equals("Animation")) {
                    nr_vizualizari_animation += serialul_i.getNr_views();
                }
                if (genul_i.equals("Drama")) {
                    nr_vizualizari_drama += serialul_i.getNr_views();
                }
                if (genul_i.equals("Mystery")) {
                    nr_vizualizari_mistery += serialul_i.getNr_views();
                }
                if (genul_i.equals("Comedy")) {
                    nr_vizualizari_comedy += serialul_i.getNr_views();
                }
                if (genul_i.equals("Romance")) {
                    nr_vizualizari_romance += serialul_i.getNr_views();
                }
                if (genul_i.equals("Crime")) {
                    nr_vizualizari_crime += serialul_i.getNr_views();
                }
                if (genul_i.equals("Horror")) {
                    nr_vizualizari_horror += serialul_i.getNr_views();
                }
                if (genul_i.equals("Thriller")) {
                    nr_vizualizari_thriller += serialul_i.getNr_views();
                }
                if (genul_i.equals("Family")) {
                    nr_vizualizari_family += serialul_i.getNr_views();
                }
                if (genul_i.equals("Fantasy")) {
                    nr_vizualizari_fantasy += serialul_i.getNr_views();
                }
                if (genul_i.equals("TV Movie")) {
                    nr_vizualizari_TV_movie += serialul_i.getNr_views();
                }
                if (genul_i.equals("History")) {
                    nr_vizualizari_history += serialul_i.getNr_views();
                }
                if (genul_i.equals("War")) {
                    nr_vizualizari_war += serialul_i.getNr_views();
                }
                if (genul_i.equals("Sci-Fi & Fantasy")) {
                    nr_vizualizari_sci_fi_fantasy += serialul_i.getNr_views();
                }
                if (genul_i.equals("Adventure")) {
                    nr_vizualizari_adventure += serialul_i.getNr_views();
                }
                if (genul_i.equals("Action")) {
                    nr_vizualizari_action += serialul_i.getNr_views();
                }
                if (genul_i.equals("Kids")) {
                    nr_vizualizari_kids += serialul_i.getNr_views();
                }
                if (genul_i.equals("Science Fiction")) {
                    nr_vizualizari_science_fiction += serialul_i.getNr_views();
                }
                if (genul_i.equals("Western")) {
                    nr_vizualizari_western += serialul_i.getNr_views();
                }
            }
        }
        vizualizari.put(nr_vizualizari_TV_movie, "TV Movie");
        vizualizari.put(nr_vizualizari_action, "Action");
        vizualizari.put(nr_vizualizari_action_adventure, "Action & Adventure");
        vizualizari.put(nr_vizualizari_adventure, "Adventure");
        vizualizari.put(nr_vizualizari_animation, "Animation");
        vizualizari.put(nr_vizualizari_comedy, "Comedy");
        vizualizari.put(nr_vizualizari_crime, "Crime");
        vizualizari.put(nr_vizualizari_drama, "Drama");
        vizualizari.put(nr_vizualizari_family, "Family");
        vizualizari.put(nr_vizualizari_fantasy, "Fantasy");
        vizualizari.put(nr_vizualizari_history, "History");
        vizualizari.put(nr_vizualizari_horror, "Horror");
        vizualizari.put(nr_vizualizari_kids, "Kids");
        vizualizari.put(nr_vizualizari_mistery, "Mistery");
        vizualizari.put(nr_vizualizari_romance, "Romance");
        vizualizari.put(nr_vizualizari_sci_fi_fantasy, "Sci-Fi & Fantasy");
        vizualizari.put(nr_vizualizari_science_fiction, "Science Fiction");
        vizualizari.put(nr_vizualizari_thriller, "Thriller");
        vizualizari.put(nr_vizualizari_war, "War");
        vizualizari.put(nr_vizualizari_western, "Western");
        List<Integer> lista = new ArrayList<>();
        lista.add(nr_vizualizari_adventure);
        lista.add(nr_vizualizari_crime);
        lista.add(nr_vizualizari_romance);
        lista.add(nr_vizualizari_action_adventure);
        lista.add(nr_vizualizari_action);
        lista.add(nr_vizualizari_animation);
        lista.add(nr_vizualizari_comedy);
        lista.add(nr_vizualizari_drama);
        lista.add(nr_vizualizari_family);
        lista.add(nr_vizualizari_fantasy);
        lista.add(nr_vizualizari_history);
        lista.add(nr_vizualizari_horror);
        lista.add(nr_vizualizari_kids);
        lista.add(nr_vizualizari_mistery);
        lista.add(nr_vizualizari_sci_fi_fantasy);
        lista.add(nr_vizualizari_science_fiction);
        lista.add(nr_vizualizari_TV_movie);
        lista.add(nr_vizualizari_thriller);
        lista.add(nr_vizualizari_war);
        lista.add(nr_vizualizari_western);

        Collections.sort(lista);
        String cel_mai_popular_gen = vizualizari.get(lista.get(19));
        List<String> genuri_populare = new ArrayList<>();
        //lista cu toate genurile de la cel mai popular in jos
        for (int i = 19; i >= 0; i--) {
            genuri_populare.add(vizualizari.get(lista.get(i)));
        }
        List<ShowInput> nevizualizate = new ArrayList<>() ;
        String mesaj = "PopularRecommendation result: ";

        for (UserInputData userul_i : input.getUsers()) {
            if (userul_i.getUsername().equals(recomandare_popular.getUsername())) {
                for (MovieInputData filmul_i : input.getMovies()) {
                    //parcurg lista de filme din baza de date
                    int aux = 0;
                    for (Map.Entry<String, Integer> obiectul_i_din_istoric : userul_i.getHistory()
                            .entrySet()) {
                        //parcurg istoricul utilizatorului
                        if (obiectul_i_din_istoric.getKey().equals(filmul_i.getTitle())) {
                            //daca filmul i este vizualizat
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
                    int b = 0;
                    for (Map.Entry<String, Integer> obiectul_i_din_istoric : userul_i.getHistory()
                            .entrySet()) {
                        //parcurg istoricul utilizatorului
                        if (obiectul_i_din_istoric.getKey().equals(serialul_i.getTitle())) {
                            //daca serialul i este vizualizat
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

        if (nevizualizate.size() > 0) {
            for (String gen : genuri_populare) {
                //parcurg lista de genuri in functie de popularitate
                for (ShowInput video : nevizualizate) {
                    //parcurg lista de nevizualizate
                    for (String genul_i : video.getGenres()) {
                        //parcurg lista de genuri ale videoului i
                        if (gen.equals(genul_i)) {
                            //daca am gasit un video nevizualizat din genul cel mai popular
                            mesaj += video.getTitle();
                            break;
                        }
                    }
                    if (mesaj.equals("PopularRecommendation result: ") == false) {
                        return mesaj;
                    }
                }
            }
        }
        return "PopularRecommendation cannot be applied!";
    }

}

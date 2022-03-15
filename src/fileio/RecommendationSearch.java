package fileio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RecommendationSearch {
    public String recomanda_gen(Input input, ActionInputData recomandare_gen) {
        List<ShowInput> nevizualizate = new ArrayList<>() ;
        String mesaj = "SearchRecommendation result: [";

        //lista cu toate videourile nevizualizate de userul din comanda
       for (UserInputData userul_i : input.getUsers()) {
           if (userul_i.getUsername().equals(recomandare_gen.getUsername())) {
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

        List<ShowInput> result = new ArrayList<>();
        for (ShowInput videoul_i : nevizualizate) {
            for (String genul_i : videoul_i.getGenres()) {
                if (genul_i.equals(recomandare_gen.getGenre())) {
                    result.add(videoul_i);
                }
            }
        }
        //am format o lista cu toate videourile nevizualizate din genul cerut
        if (result.size() == 0) {
            mesaj = "SearchRecommendation cannot be applied!";
            return mesaj;
        }
        for (ShowInput video : result) {
            if (video instanceof MovieInputData) {
                video.setRatingg(((MovieInputData) video).average_rating);
            } else if (video instanceof  SerialInputData) {
                video.setRatingg(((SerialInputData) video).rating);
            }
        }
        Collections.sort(result, ShowInput.RatinggComparator);

        for (int k = 0; k < result.size(); k++) {
            mesaj += result.get(k).getTitle();
            if (k != result.size() - 1) {
                mesaj += ", ";
            } else {
                mesaj += "]";
            }
        }

        return mesaj;
    }
}

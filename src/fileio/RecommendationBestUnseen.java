package fileio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecommendationBestUnseen {
    public String recomanda_best_unseen(Input input, ActionInputData recomandare_best_unseen) {

        List<ShowInput> nevizualizate = new ArrayList<>() ;
        String mesaj = "BestRatedUnseenRecommendation result: ";

        //lista cu toate videourile nevizualizate de userul din comanda
        for (UserInputData userul_i : input.getUsers()) {
            if (userul_i.getUsername().equals(recomandare_best_unseen.getUsername())) {
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

        for (int i = 0; i < nevizualizate.size(); i++) {
            if (nevizualizate.get(i) instanceof MovieInputData) {
                nevizualizate.get(i).setRatingg(((MovieInputData) nevizualizate.get(i)).average_rating);
            } else if (nevizualizate.get(i) instanceof SerialInputData){
                nevizualizate.get(i).setRatingg(((SerialInputData) nevizualizate.get(i)).rating);
            }
        }
        Double best_rating = 0.00;
        if (nevizualizate.size() > 0) {
            for (int i = 0; i < nevizualizate.size(); i++) {
                if (nevizualizate.get(i).getRatingg() > best_rating) {
                    best_rating = nevizualizate.get(i).getRatingg();
                }
            }
        }

        //gasesc cel mai bun rating
        if (nevizualizate.size() > 0) {
            for (int i = 0; i < nevizualizate.size(); i++) {
                if (nevizualizate.get(i).getRatingg().equals(best_rating)) {
                    mesaj += nevizualizate.get(i).getTitle();
                    break;
                }
            }
            return mesaj;
        }

    return "BestRatedUnseenRecommendation cannot be applied!";
    }
}

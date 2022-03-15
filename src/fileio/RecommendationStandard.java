package fileio;

import java.util.Map;

public class RecommendationStandard {
    public ShowInput recomanda_primul_nevazut(Input input, ActionInputData recomandare_standard) {
        ShowInput all_videos_viewed = new MovieInputData(" ", null, null, 0, 0);
        ShowInput invalid_user = new MovieInputData(" ", null, null, 1, 0);
        for (UserInputData userul_i : input.getUsers()) {
            //parcurg lista de utilizatori din input
            if (userul_i.getUsername().equals(recomandare_standard.getUsername())) {
                //cand gasesc in input utilizatorul din comanda
                for (MovieInputData filmul_i : input.getMovies()) {
                    //parcurg lista de filme din baza de date
                    int a = 0;
                    for (Map.Entry<String, Integer> obiectul_i_din_istoric : userul_i.getHistory()
                            .entrySet()) {
                        //parcurg istoricul utilizatorului
                        if (obiectul_i_din_istoric.getKey().equals(filmul_i.getTitle())) {
                            //daca filmul i este vizualizat
                            break;
                        }
                        a++;
                        if (a == userul_i.getHistory().size()) {
                            //daca filmul nu a fost vizualizat
                            return filmul_i;
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
                            return serialul_i;
                        }
                    }
                }
                return all_videos_viewed;
            }
        }
        return invalid_user;
    }
}

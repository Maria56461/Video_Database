package entertainment;

import fileio.ActionInputData;
import fileio.UserInputData;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CommandFavorite {

    /**
     * @param utilizatori      lista de users din input
     * @param comfav actiunea
     * @return mesaj
     */
    @SuppressWarnings("checkstyle:JavadocMethod")
    public final String comandafavorite(final List<UserInputData> utilizatori,
                                        final ActionInputData comfav) {
        String mesaj;
        //parcurg lista de utilizatori
        // pana gasesc numele utilizatorului care da comanda
        for (UserInputData utilizator : utilizatori) {
            if (utilizator.getUsername().equals(comfav.getUsername())) {
                //daca am gasit numele utilizatorului in lista
                //parcurg lista sa de videouri vizualizate
                Map<String, Integer> istoric = utilizator.getHistory();
                for (Map.Entry<String, Integer> obiect : istoric.entrySet()) {
                    //daca gasesc printre vizualizate videoul din comanda mea
                    //parcurg lista de favorite si verific
                    // daca este adaugat deja sau nu
                    if (obiect.getKey().equals(comfav.getTitle())) {
                        for (String favorit : utilizator.getFavoriteMovies()) {
                            //daca il gasesc la favorite,
                            // returnez mesajul de eroare- deja adaugat
                            if (Objects.equals(favorit, comfav.getTitle())) {
                                mesaj = "error -> "
                                        + comfav.getTitle()
                                        + " is already in favourite list";
                                return mesaj;
                            }
                        }
                        //il adaug la favorite si returnez mesajul de succes
                        utilizator.getFavoriteMovies().add(obiect.getKey());
                        mesaj = "success -> " + comfav.getTitle()
                                + " was added as favourite";
                        return mesaj;
                    }
                }
                //intoarce mesajul de eroare - nevizualizat
                mesaj = "error -> " + comfav.getTitle()
                        + " is not seen";
                return mesaj;
            }
        }
        return "Utilizatorul din comanda nu exista";
    }
}

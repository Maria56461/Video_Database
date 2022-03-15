package entertainment;

import fileio.ActionInputData;
import fileio.UserInputData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandView {

    public String comanda_vizualizare(List<UserInputData> utilizatori,
                                      ActionInputData comanda_view) {
        String mesaj;
        //parcurg lista de utilizatori pana gasesc numele utilizatorului care da comanda
        for (UserInputData utilizatorul_i : utilizatori) {
            if (utilizatorul_i.getUsername().equals(comanda_view.getUsername())) {
                //daca am gasit numele utilizatorului in lista
                //parcurg lista sa de videouri vizualizate
                Map<String, Integer> istoric = utilizatorul_i.getHistory();
                for (Map.Entry<String, Integer> obiectul_i : istoric.entrySet()) {
                    //daca gasesc la vizualizate videoul meu din comanda
                    //incrementez nr. de vizualizari
                    if (obiectul_i.getKey().equals(comanda_view.getTitle())) {
                        obiectul_i.setValue(obiectul_i.getValue() + 1);
                        mesaj = "success -> " + comanda_view.getTitle() +
                                " was viewed with total views of " + obiectul_i.getValue();
                        return mesaj;
                    }
                }
                //daca nu l-am gasit la vizualizate
                istoric.put(comanda_view.getTitle(), 1);
                mesaj = "success -> " + comanda_view.getTitle() +
                        " was viewed with total views of " + 1;
                return mesaj;
            }
        }
        return "Utilizatorul dat nu exista!";
    }
}

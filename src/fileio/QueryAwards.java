package fileio;

import actor.ActorsAwards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class QueryAwards {

    public String cautare_premii(Input input, ActionInputData cauta_actori_premii) {
        String mesaj = "Query result: [";
        ArrayList<ActorInputData> actori_cautati = new ArrayList<>();
        //parcurg lista de actori pentru a cauta
        //premiile din comanda mea
        for (ActorInputData actorul_i : input.getActors()) {
            //parcurg lista sa de premii
            int nr = 0;
            //numarul de premii din comanda pe care le are actorul_i
            for (Map.Entry<ActorsAwards, Integer> premiu : actorul_i.getAwards().entrySet()) {
                //parcurg lista de premii din comanda
                actorul_i.setNumber_of_awards(actorul_i.getNumber_of_awards() + premiu.getValue());
                for (String premiu_cautat : cauta_actori_premii.getFilters().get(3)) {
                    if (premiu_cautat.equals(premiu.getKey().toString())) {
                        //daca premiul din lista actorului se gaseste in comanda mea
                        nr++;
                    }
                }
            }
            if (nr == cauta_actori_premii.getFilters().get(3).size()) {
                //daca actorul a castigat toate premiile din comanda
                actori_cautati.add(actorul_i);
            }
        }
        //mai trebuie sa sortez lista de actori cautati
        if (cauta_actori_premii.getSortType().equals("asc")) {
            Collections.sort(actori_cautati, ActorInputData.AwardsComparator);
        } else {
            Collections.sort(actori_cautati, ActorInputData.AwardsComparator);
            Collections.reverse(actori_cautati);
        }
        //mai trebuie doar sa formez mesajul
        for (int i = 0; i < actori_cautati.size(); i++) {
            mesaj += actori_cautati.get(i).getName();
            if (i != actori_cautati.size() - 1) {
                mesaj += ", ";
            }
        }
        mesaj = mesaj + "]";
        return mesaj;
    }
}

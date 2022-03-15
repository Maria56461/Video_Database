package fileio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QueryFilterDescription {
    public String cautare_cuvinte_cheie(Input input, ActionInputData cauta_descriere) {
        String mesaj = "Query result: [";
        ArrayList<ActorInputData> actori_cautati = new ArrayList<>();
        //parcurg lista de actori pt. a cauta cuvintele cheie in descrierea fiecaruia
        for (ActorInputData actorul_i : input.getActors()) {
            int nr = 0;
            List<String> cuvinte = new ArrayList<>();
            //numarul de cuvinte diferite din comanda pe care le contine descrierea actorului_i
            //parcurg descrierea fiecarui actor cuvant cu cuvant
            for (String descrierea_i : actorul_i.getCareerDescription().split("[\\W]")) {
                //parcurg cuvintele cheie din comanda
                for (String cuvantul_i : cauta_descriere.getFilters().get(2)) {
                    //daca un cuvant se gaseste si in comanda si in descrierea actorului
                    if (cuvantul_i.equalsIgnoreCase(descrierea_i)) {
                        cuvinte.add(cuvantul_i);
                    }
                }
            }
            Set<String> set = new HashSet<>(cuvinte);
            List<String> cuvinte2 = new ArrayList<>(set);
            //eliminare duplicate
            nr = cuvinte2.size();
            if (nr == cauta_descriere.getFilters().get(2).size()) {
                //daca toate cuvintele cheie sunt in descrierea actorului
                actori_cautati.add(actorul_i);
            }
        }
        //trebuie sa sortez actorii alfabetic
        if (cauta_descriere.getSortType().equals("asc")) {
            //sortez crescator
            Collections.sort(actori_cautati, ActorInputData.NameComparator);
        } else {
            //sortez descrescator
            Collections.sort(actori_cautati, ActorInputData.NameComparator);
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

Barbu Maria-Alexandra, 325CD
Tema1 - OOP
----------------------------------------------------------------------
Am facut urmatoarele adaugiri in clasele din schelet:

    * clasa Season - "Double average_ratings" = media aritmetica a
                      ratingurilor unui sezon
    * clasa ActorInputData - "public Double media_ratingurilor" =
                      media ratingurilor videourilor in care actorul
                      respectiv a jucat
                             "private Integer number_of_awards" (cu
                      getter si setter) = numarul total de premii
                      ale unui actor
                             "public int compareTo(ActorInputData o)" =
                      functie care compara doi actori dupa criteriile
                      "media ratingurilor" si "ordinea alfabetica a
                      numelor" in aceasta ordine
                             "public static Comparator<ActorInputData>
                             AwardsComparator" = un obiect de tip
                      Comparator, de fapt o clasa interna anonima care
                      contine metoda "compare" ce compara doi actori
                      dupa criteriile "number of awards" si "ordinea
                      alfabetica a numelor", in aceasta ordine
                             "public static Comparator<ActorInputData>
                             NameComparator" = un obiect de tip
                      Comparator, contine metoda "compare" ce compara
                      doi actori in functie de ordinea alfabetica a
                      numelor
    * clasa MovieInputData - "public Double average_rating" = media
                      tuturor ratingurilor unui film
                             "private Integer numar_voturi" (plus
                             getter si setter) = numarul total de
                      liste de "favorite" in care un film se gaseste
                             "private Integer numar_vizualizari"
                             (plus getter si setter) = numarul total
                      de vizualizari ale unui film
                             "public static Comparator<MovieInputData>
                             RatingsComparator" = obiect de tip
                      Comparator care incapsuleaza metoda "compare" ce
                      compara doua filme dupa criteriile "average
                      rating" si "ordinea alfabetica a titlurilor", in
                      aceasta ordine
                             "public static Comparator<MovieInputData>
                             FavoriteComparator" = obiect de tip
                      Comparator care incapsuleaza metoda "compare" ce
                      compara doua filme dupa criteriile "numar de
                      voturi" si "ordinea alfabetica a titlurilor", in
                      aceasta ordine
                             "public static Comparator<MovieInputData>
                             DurationComparator" = obiect de tip
                      Comparator care incapsuleaza metoda "compare" ce
                      compara doua filme dupa criteriile "durata" si
                      "ordinea alfabetica a titlurilor", in aceasta
                      ordine
                             "public static Comparator<MovieInputData>
                             NumberViewsComparator" = obiect de tip
                      Comparator care incapsuleaza metoda "compare" ce
                      compara doua filme dupa criteriile "numar total
                      de vizualizari" si "ordinea alfabetica a
                      titlurilor", in aceasta ordine
    * clasa SerialInputData - "public Double rating" = ratingul mediu al
                      unui serial
                              "private Integer numar_voturi" (plus
                              getter si setter) = numarul de aparitii
                      ale serialului in listele de "favorite" ale
                      utilizatorilor
                              "private Integer duration" (plus getter si
                              setter) = durata totala a unui serial
                              "private Integer nr_views" (plus getter si
                              setter) = numarul total de vizualizari ale
                      serialului respectiv
                              "public static Comparator<SerialInputData>
                              RatingsComparator" - contine metoda
                      "compare" care compara doua seriale dupa criteriile
                      "rating" si "ordinea alfabetica a titlurilor", in
                      aceasta ordine
                              "public static Comparator<SerialInputData>
                              FavoriteComparator" - contine metoda
                      "compare" care compara doua seriale dupa criteriile
                      "numar de voturi" si "ordinea alfabetica a
                      titlurilor", in aceasta ordine
                              "public static Comparator<SerialInputData>
                              DurationComparator" - contine metoda
                      "compare" care compara doua seriale dupa criteriile
                      "durata" si "ordinea alfabetica a titlurilor", in
                      aceasta ordine
                              "public static Comparator<SerialInputData>
                              NrViewsComparator" - contine metoda
                      "compare" care compara doua seriale dupa criteriile
                      "numar vizualizari" si "ordinea alfabetica a
                      titlurilor", in aceasta ordine
    * clasa ShowInput - "private Double ratingg" (plus getter si setter) =
                      rating-ul mediu al unui video
                              "public static Comparator<ShowInput>
                              RatinggComparator" - contine metoda
                      "compare" care compara doua videouri dupa criteriile
                      "rating" si "ordinea alfabetica a titlurilor", in
                      aceasta ordine
    * clasa UserInputData - "public ArrayList<Integer>
                             number_of_ratings_movies" (plus getter si
                             setter) = un ArrayList initializat cu valori
                      nule care contine "1" pe pozitia "i" daca al "i"-lea
                      film din lista de filme a primit rating de la userul
                      respectiv
                            "public ArrayList<ArrayList<Integer>>
                            number_of_ratings_seriale" (plus getter si
                            setter) = un ArrayList de ArrayList-uri
                      initializat cu valori nule care contine "1" pe randul
                      "i" si coloana "j" daca al "i"-lea serial din lista
                      de seriale a primit rating pentru sezonul "j"
                            "private Integer nr_total_ratinguri_date" (plus
                            getter si setter) = numarul de ratinguri date
                      pentru filme si seriale de catre userul respectiv
                            "public static Comparator<UserInputData>
                            NumberRatingsComparator" = contine metoda
                      "compare" ce compara doi useri dupa criteriile
                      "numar total de ratinguri date" si "ordinea alfabetica
                      a username-ului", in aceasta ordine

Am implementat urmatoarele noi clase:

    * clasa CommandFavorite = contine metoda "comandafavorite" care gaseste in
        lista de utilizatori din input utilizatorul care da comanda, apoi
        parcurge lista sa de videouri vizualizate. Daca videoul din parametrii
        comenzii a fost vizualizat si nu apare in lista de "favorite",
        realizeaza inserarea sa.

    * clasa CommandView - metoda "comanda_vizualizare" - incrementeaza numarul
        de vizualizari ale unui video

    * clasa CommandRating - metoda "comanda_rating" - adauga rating unui film/
        sezon vizualizat, actualizeaza campurile de "number_of_ratings_movies"
        si "number_of_ratings_seriale" ale userului

    * clasa QueryAverage - contine metoda "cautare_medie_ratinguri" - seteaza
        campurile "rating" (la seriale), "average_ratings" la sezoane,
        "average_rating" la filme si "media_ratingurilor" la actori.
        Realizeaza sortarea prin intermediul Comparatorului.

    * clasa QueryAwards - metoda "cautare_premii" - calculeaza numarul total
        de premii ale unui actor, cauta in lista sa de premii acele premii
        specificate in comanda, seteaza campul "number_of_awards", foloseste
        Comparator pentru a realiza sortarea ceruta

    * clasa QueryFilterDescription - contine metoda "cautare_cuvinte_cheie" -
        cauta in descrierea actorului cuvintele cheie din comanda, le adauga
        pe cele gasite intr-o lista, elimina duplicatele din lista prin
        intermediul clasei HashSet. Daca lista contine exact atatea cuvinte
        cate sunt in filtrul comenzii, adaug actorul intr-o lista de actori
        cautati, pe care in final o sortez folosind un Comparator. Am
        folosit metoda "split" pentru a parcurge cuvant cu cuvant descrierea
        fiecarui actor.

    * clasa QueryVideosRating - contine metoda "cauta_videouri_rating" - mai
        intai seteaza rating-ul mediu pentru fiecare video (exact ca in metoda
        din QueryAverage), apoi parcurge lista de filme/seriale si le adauga
        intr-o lista pe cele care au anul si genul din comanda. Am considerat
        si cazurile in care ambele filtre sunt nule (sau cel putin unul dintre
        ele). In final, metoda sorteaza filmele/ serialele folosind un
        Comparator.

    * clasa QueryVideosFavorite - contine metoda "cauta_videouri_favorite"
        care parcurge lista de filme/seriale, apoi parcurge toate listele
        de "favorite" si numara aparitiile unui video prin liste. Seteaza
        campul "numar de voturi" atat pentru filme, cat si pentru seriale.
        Adaug intr-o lista toate filmele/ serialele care au toate genurile
        si anul din comanda (din nou, cazuri pentru filtre nule) si sortez
        lista prin intermediul Comparatorului dupa cele doua criterii din
        enunt.

    * clasa QueryVideosLongest - contine metoda "cauta_videouri_durata",
        care seteaza campul de "durata" al fiecarui serial si, exact ca la
        metodele precedente, adauga intr-o lista toate filmele/serialele care
        respecta cele doua filtre (an si genuri). In final, sortare.

    * clasa QueryVideosMostViewed - contine metoda "cauta_videouri_vizualizari",
        care seteaza numarul total de vizualizari al fiecarui film/serial,
        adauga intr-o lista filmele/ serialele care au anul si genurile din
        filtre, in final realizeaza o sortare avand ca prim criteriu numarul
        de vizualizari.

    * clasa QueryUsersRatings - contine metoda "cauta_users", care seteaza
        campul "nr_total_ratinguri_date" al fiecarui user si realizeaza o
        sortare a utilizatorilor in functie de numarul de ratinguri date.

    * clasa RecommendationStandard - contine metoda "recomanda_primul_nevazut"
        care parcurge lista de filme, apoi pe cea de seriale din baza de date
        pana cand gaseste un video nevizualizat

    * clasa RecommendationBestUnseen- contine metoda "recomanda_best_unseen"
        care formeaza o lista cu toate videourile nevizualizate de userul
        din comanda, seteaza campul de "rating" din clasa ShowInput,
        calculeaza cel mai bun rating si intoarce primul video din baza de
        date care are ratingul egal cu cel calculat.

    * clasa RecommendationPopular - contine metoda "recomanda_popular", care
        seteaza numarul de vizualizari ale fiecarui film/serial, apoi
        calculeaza numarul de vizualizari pentru fiecare gen in parte
        si introduce aceste informatii intr-un Map de tipul (gen, nr.
        vizualizari). Adauga si intr-o lista numarul de vizualizari pt.
        fiecare gen, iar prin intermediul acesteia construieste o alta
        lista cu toate genurile de videouri, de la cel mai popular gen
        in jos. Realizeaza o lista cu toate videourile nevizualizate de
        userul din parametrii actiunii, apoi cauta primul video
        nevizualizat din cel mai popular gen (daca exista, daca nu, trece
        la urmatorul gen din lista samd).

    * clasa RecommendationFavorite - contine metoda "recomanda_favorite",
        care construieste o lista cu toate videourile nevizualizate de
        userul din parametrii actiunii, apoi o lista cu numarul de aparitii
        in listele de "favorite" ale fiecarui video dintre cele
        nevizualizate, sorteaza lista si apoi gaseste primul video din
        baza de date care are acel numar maxim de voturi calculat anterior.

    * clasa RecommendationSearch - contine metoda "recomanda_gen", care
        construieste o lista cu toate videourile nevizualizate de userul
        din comanda, apoi o alta lista cu toate videourile nevizualizate
        din genul cerut, in final sorteaza lista dupa rating.

In clasa Main am parcurs lista de comenzi din input, si, in functie
de parametrii actiunii respective, am apelat functia corespunzatoare
dintr-una din clasele create si mentionate mai sus. Mesajul intors de
fiecare actiune a fost adaugat in "arrayResult" prin intermediul
functiei "writeFile".




package main;

import checker.Checkstyle;
import checker.Checker;
import common.Constants;
import entertainment.CommandFavorite;
import entertainment.CommandView;
import fileio.ActionInputData;
import fileio.CommandRating;
import fileio.Input;
import fileio.InputLoader;
import fileio.QueryAverage;
import fileio.QueryAwards;
import fileio.QueryFilterDescription;
import fileio.QueryUsersRatings;
import fileio.QueryVideosFavorite;
import fileio.QueryVideosLongest;
import fileio.QueryVideosMostViewed;
import fileio.QueryVideosRating;
import fileio.RecommendationBestUnseen;
import fileio.RecommendationFavorite;
import fileio.RecommendationPopular;
import fileio.RecommendationSearch;
import fileio.RecommendationStandard;
import fileio.ShowInput;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * The entry point to this homework. It runs the checker
 * that tests your implentation.
 */
@SuppressWarnings({"checkstyle:LineLength", "checkstyle:JavadocStyle"})
public final class Main {
    /**.
     * for coding style
     */
    private Main() {
    }

    /**.
     * Call the main checker and the coding style checker
     *
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {

            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        checker.iterateFiles(Constants.RESULT_PATH,
                Constants.REF_PATH, Constants.TESTS_PATH);
        Checkstyle test = new Checkstyle();
        test.testCheckstyle();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();
        String mesaj;
        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();

        //parcurg lista de comenzi din input
        for (ActionInputData actiunea_i : input.getCommands()) {
            if (actiunea_i.getActionType().equals("command")) {
                if (actiunea_i.getType().equals("favorite")) {
                    //daca am de adugat la favorite
                    CommandFavorite object_command_favorite =
                            new CommandFavorite();
                    mesaj = object_command_favorite.
                            comandafavorite(input.getUsers(), actiunea_i);
                    arrayResult.add(fileWriter.writeFile(actiunea_i.
                            getActionId(), " ", mesaj));
                } else if (actiunea_i.getType().equals("view")) {
                    //daca am de adaugat la vizualizare
                    CommandView object_command_view = new CommandView();
                    mesaj = object_command_view.
                            comanda_vizualizare(input.getUsers(), actiunea_i);
                    arrayResult.add(fileWriter.writeFile(actiunea_i.
                            getActionId(), " ", mesaj));
                } else if (actiunea_i.getType().equals("rating")) {
                    //daca am de dat o nota unui film/sezon
                    CommandRating object_command_rating = new CommandRating();
                    mesaj = object_command_rating
                            .comanda_rating(input.getUsers(), input.getMovies(),
                                    input.getSerials(), actiunea_i);
                    arrayResult.add(fileWriter.writeFile(actiunea_i.
                            getActionId(), " ", mesaj));
                }
            } else if (actiunea_i.getActionType().equals("query")) {
                if (actiunea_i.getObjectType().equals("actors")) {
                    if (actiunea_i.getCriteria().equals("average")) {
                        //daca caut actori sortati dupa media ratingurilor
                        // video-urilor in care au jucat
                        QueryAverage object_query_average = new QueryAverage();
                        mesaj = object_query_average.
                                cautare_medie_ratinguri(input, actiunea_i);
                        arrayResult.add(fileWriter.writeFile(actiunea_i.
                                getActionId(), " ", mesaj));
                    }
                    if (actiunea_i.getCriteria().equals("awards")) {
                        QueryAwards obiect_cautare_premii = new QueryAwards();
                        mesaj = obiect_cautare_premii.cautare_premii(input,
                                actiunea_i);
                        arrayResult.add(fileWriter.writeFile(actiunea_i.
                                getActionId(), " ", mesaj));
                    }
                    if (actiunea_i.getCriteria().equals("filter_description")) {
                        QueryFilterDescription obiect_cautare_cuvinte_descriere
                                = new QueryFilterDescription();
                        mesaj = obiect_cautare_cuvinte_descriere
                                .cautare_cuvinte_cheie(input, actiunea_i);
                        arrayResult.add(fileWriter.writeFile(actiunea_i.
                                getActionId(), " ", mesaj));
                    }
                }
                if (actiunea_i.getObjectType().equals("movies")
                        || actiunea_i.getObjectType().equals("shows")) {
                    if (actiunea_i.getCriteria().equals("ratings")) {
                        QueryVideosRating object_cautare_videouri_rating
                                = new QueryVideosRating();
                        mesaj = object_cautare_videouri_rating.
                                cauta_videouri_rating(input, actiunea_i);
                        arrayResult.add(fileWriter.writeFile(actiunea_i.
                                getActionId(), " ", mesaj));
                    }
                    if (actiunea_i.getCriteria().equals("favorite")) {
                        QueryVideosFavorite obiect_cautare_videouri_favorite
                                = new QueryVideosFavorite();
                        mesaj = obiect_cautare_videouri_favorite.
                                cauta_videouri_favorite(input, actiunea_i);
                        arrayResult.add(fileWriter.writeFile(actiunea_i.
                                getActionId(), " ", mesaj));
                    }
                    if (actiunea_i.getCriteria().equals("longest")) {
                        QueryVideosLongest obiect_cautare_videouri_durata
                                = new QueryVideosLongest();
                        mesaj = obiect_cautare_videouri_durata.
                                cauta_videouri_durata(input, actiunea_i);
                        arrayResult.add(fileWriter.writeFile(actiunea_i.
                                getActionId(), " ", mesaj));
                    }
                    if (actiunea_i.getCriteria().equals("most_viewed")) {
                        QueryVideosMostViewed
                                obiect_cautare_videouri_vizualizari
                                = new QueryVideosMostViewed();
                        mesaj = obiect_cautare_videouri_vizualizari.
                                cauta_videouri_vizualizari(input, actiunea_i);
                        arrayResult.add(fileWriter.writeFile(actiunea_i.
                                getActionId(), " ", mesaj));
                    }
                }
                if (actiunea_i.getObjectType().equals("users")) {
                    if (actiunea_i.getCriteria().equals("num_ratings")) {
                        QueryUsersRatings obiect_cautare_useri
                                = new QueryUsersRatings();
                        mesaj = obiect_cautare_useri.
                                cauta_users(input, actiunea_i);
                        arrayResult.add(fileWriter.writeFile(actiunea_i.
                                getActionId(), " ", mesaj));
                    }
                }
            } else if (actiunea_i.getActionType().equals("recommendation")) {
                if (actiunea_i.getType().equals("standard")) {
                    RecommendationStandard obiect_recomandare_standard
                            = new RecommendationStandard();
                    ShowInput result = obiect_recomandare_standard.
                            recomanda_primul_nevazut(input, actiunea_i);
                    if (result.getYear() == 0) {
                        mesaj = "StandardRecommendation cannot be applied!";
                    } else if (result.getYear() == 1) {
                        mesaj = "StandardRecommendation cannot be applied!";
                    } else {
                        mesaj = "StandardRecommendation result: "
                                + result.getTitle();
                    }
                    arrayResult.add(fileWriter.writeFile(actiunea_i.
                            getActionId(), " ", mesaj));
                }
                if (actiunea_i.getType().equals("best_unseen")) {
                    RecommendationBestUnseen obiect_recomandare_best_unseen
                            = new RecommendationBestUnseen();
                    mesaj = obiect_recomandare_best_unseen.
                            recomanda_best_unseen(input, actiunea_i);
                    arrayResult.add(fileWriter.writeFile(actiunea_i.
                            getActionId(), " ", mesaj));
                }
                if (actiunea_i.getType().equals("popular")) {
                    RecommendationPopular obiect_recomandare_popular
                            = new RecommendationPopular();
                    for (UserInputData utilizatorul_i : input.getUsers()) {
                        if (utilizatorul_i.getUsername().
                                equals(actiunea_i.getUsername())) {
                            if (utilizatorul_i.getSubscriptionType().
                                    equals("BASIC")) {
                                mesaj = "PopularRecommendation"
                                        + " cannot be applied!";
                                arrayResult.add(fileWriter.writeFile(actiunea_i.
                                        getActionId(), " ", mesaj));
                            } else {
                                mesaj = obiect_recomandare_popular.
                                        recomanda_popular(input, actiunea_i);
                                arrayResult.add(fileWriter.writeFile(actiunea_i.
                                        getActionId(), " ", mesaj));
                            }
                        }
                    }
                }
                if (actiunea_i.getType().equals("favorite")) {
                    RecommendationFavorite obiectrecomandarefavorite
                            = new RecommendationFavorite();
                    for (UserInputData utilizatorul_i : input.getUsers()) {
                        if (utilizatorul_i.getUsername().
                                equals(actiunea_i.getUsername())) {
                            if (utilizatorul_i.getSubscriptionType()
                                    .equals("BASIC")) {
                                mesaj = "FavoriteRecommendation"
                                        + " cannot be applied!";
                                arrayResult.add(fileWriter.writeFile(actiunea_i.
                                        getActionId(), " ", mesaj));
                            } else {
                                mesaj = obiectrecomandarefavorite.
                                        recomanda_favorite(input, actiunea_i);
                                arrayResult.add(fileWriter.
                                        writeFile(actiunea_i.getActionId(),
                                                " ", mesaj));
                            }
                        }
                    }
                }
                if (actiunea_i.getType().equals("search")) {
                    RecommendationSearch obiectrecomandaregen
                            = new RecommendationSearch();
                    for (UserInputData utilizator : input.getUsers()) {
                        if (utilizator.getUsername().
                                equals(actiunea_i.getUsername())) {
                            if (utilizator.getSubscriptionType().
                                    equals("BASIC")) {
                                mesaj = "SearchRecommendation"
                                        + " cannot be applied!";
                                arrayResult.add(fileWriter.writeFile(actiunea_i.
                                        getActionId(), " ", mesaj));
                            } else {
                                mesaj = obiectrecomandaregen.
                                        recomanda_gen(input, actiunea_i);
                                arrayResult.add(fileWriter.writeFile(actiunea_i.
                                        getActionId(), " ", mesaj));
                            }
                        }
                    }
                }
            }
        }
        fileWriter.closeJSON(arrayResult);
    }
}

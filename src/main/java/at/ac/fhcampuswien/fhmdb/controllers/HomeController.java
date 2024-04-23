package at.ac.fhcampuswien.fhmdb.controllers;

import at.ac.fhcampuswien.fhmdb.MovieAPI;
import at.ac.fhcampuswien.fhmdb.database.*;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class HomeController implements Initializable {
    @FXML
    public JFXButton filterBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;


    @FXML
    public JFXComboBox<String> genreComboBox;
    public JFXComboBox<String> releaseYearComboBox;
    public JFXComboBox ratingComboBox;


    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();



    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();


    private ObservableList<Movie> sfm = FXCollections.observableArrayList();

    private boolean filtered = false;

    FilteredList<Movie> filteredMovies;
    FilteredList<Movie> filteredMovies2;
    @FXML
    private VBox homeView;
    private DatabaseManager dbManager;
    private final MovieCell.ClickEventHandler<Movie> onAddToWatchlistClicked = (clickedItem) -> {
        MovieRepository movieRepository = new MovieRepository();

        try {
            // Convert Movie to MovieEntity
            MovieEntity movieEntity = new MovieEntity();
            movieEntity.setTitle(clickedItem.getTitle());
            movieEntity.setGenres(clickedItem.getGenre().stream().collect(Collectors.joining(", ")));
            movieEntity.setDescription(clickedItem.getDescription());
            movieEntity.setReleaseYear(clickedItem.getReleaseYear());


            // Add more fields if needed

            // Add the movie to the MovieEntity table and WatchlistMovieEntity table
            movieRepository.addMovieToWatchlist(movieEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

    public HomeController() {

    }

    public HomeController(VBox homeView) {
        this.homeView = homeView;

    }
    public void getMoviesFromDatabase() {
        try {
            MovieRepository movieRepository = new MovieRepository();
            List<MovieEntity> movies = movieRepository.getAllMovies();

            for (MovieEntity movie : movies) {
                // Verarbeiten Sie die Ergebnisse hier und fügen Sie sie Ihrer Benutzeroberfläche hinzu
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveMovieToDatabase(Movie movie) {
        try {
            MovieRepository movieRepository = new MovieRepository();
            MovieEntity movieEntity = new MovieEntity();
            // Setzen Sie die restlichen Werte hier
            movieEntity.setTitle(movie.getTitle());
            movieRepository.addMovie(movieEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void showWatchlistScreen(ActionEvent event) throws IOException {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/at/ac/fhcampuswien/fhmdb/watchlist-view.fxml"));
                Parent root = fxmlLoader.load();



                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    Movie convertMovieEntityToMovie(WatchlistMovieEntity movie) throws SQLException {
        MovieRepository movieRepository = new MovieRepository();
        MovieEntity movieEntity = movieRepository.findById(movie.getId());

        if (movieEntity == null) {
            throw new SQLException("No movie found with the provided ID");
        }

        Movie realMovie = new Movie();
        realMovie.setTitle(movieEntity.getTitle());
        realMovie.setGenre(Arrays.asList(movieEntity.getGenres().split(", ")));
        realMovie.setDescription(movieEntity.getDescription());
        realMovie.setReleaseYear(movieEntity.getReleaseYear());
        // Set the rest of the values here
        return realMovie;
    }

    ///at/ac/fhcampuswien/fhmdb/watchlist-view.fxml
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            DatabaseManager dbManager = DatabaseManager.getDatabase();
            // Jetzt können Sie dbManager verwenden, um auf die Methoden und Felder der Klasse DatabaseManager zuzugreifen.
        } catch (Exception e) {
            e.printStackTrace();
        }
        homeView = new VBox();
        Scene scene = new Scene(homeView);
        MovieAPI movieAPI = null;
        try {
            movieAPI = new MovieAPI();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String movieId = "8ca193d8-7879-42ed-820e-6230b52746a3";

        try {
            Movie movie = movieAPI.getMovieById(movieId);

            System.out.println("Gefundener Film: " + movie.getTitle());
        } catch (IOException e) {
            e.printStackTrace();

        }

        long count = 0;
        List<Movie> movies = null;
        try {

            movies = movieAPI.getMovies(null,null, 0, null, null, null, 0, 0.0);






            /*movies.stream()
                    .forEach(movie -> System.out.println(movie.getTitle() + movie.getId()));*/




            count = movies.stream().count();
            System.out.println("Number of movies: " + count);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*
        for(int i = 0; i < movies.size(); i++){
            Movie movie = movies.get(i);
            MovieEntity movieEntity = new MovieEntity();
            // Setzen Sie die Eigenschaften von movieEntity basierend auf dem movie-Objekt
            movieEntity.setApiId(movie.getId());
            movieEntity.setTitle(movie.getTitle());
            movieEntity.setGenres(movie.getGenre().stream().collect(Collectors.joining(", ")));
            movieEntity.setDescription(movie.getDescription());
            movieEntity.setReleaseYear(movie.getReleaseYear());
            movieEntity.setImgUrl(movie.getImgUrl());
            movieEntity.setLengthInMinutes(movie.getLengthInMinutes());
            movieEntity.setRatingFrom(movie.getRating());
            // Fügen Sie das movieEntity-Objekt in die Datenbank ein
            MovieRepository movieRepository;
            try {
                movieRepository = new MovieRepository();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                movieRepository.addMovie(movieEntity);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }*/

        observableMovies.addAll(movies);
        /*MovieRepository movieRepository = null;
        try {
            movieRepository = new MovieRepository();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Movie movie : movies) {
            MovieEntity movieEntity = new MovieEntity();
            // Setzen Sie die Eigenschaften von movieEntity basierend auf dem movie-Objekt
            movieEntity.setApiId(movie.getId());
            movieEntity.setTitle(movie.getTitle());
            movieEntity.setGenres(movie.getGenre().stream().collect(Collectors.joining(", ")));
            movieEntity.setDescription(movie.getDescription());
            movieEntity.setReleaseYear(movie.getReleaseYear());
            movieEntity.setImgUrl(movie.getImgUrl());
            movieEntity.setLengthInMinutes(movie.getLengthInMinutes());
            movieEntity.setRatingFrom(movie.getRating());
            // Fügen Sie das movieEntity-Objekt in die Datenbank ein
            try {
                movieRepository.addMovie(movieEntity);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }*/

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell(onAddToWatchlistClicked)); // use custom cell factory to display data


        // Initialize genre filter items

        List<String> genresss = movies.stream()
                .flatMap(movie -> movie.getGenre().stream()) // Vereint alle Genre-Listen in einen Stream
                .distinct() // Entfernt Duplikate
                .collect(Collectors.toList());
        genreComboBox.getItems().addAll(genresss);
        genreComboBox.getItems().add("Without genre");

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        releaseYearComboBox.setPromptText("Filter by Release Year");

        List<Integer> releaseYears = movies.stream().map(Movie::getReleaseYear).distinct().collect(Collectors.toList());
        releaseYearComboBox.getItems().addAll(releaseYears.stream().map(Object::toString).collect(Collectors.toList()));
        releaseYearComboBox.getItems().add("Without release year");
        ratingComboBox.setPromptText("Filter by Rating");
        List<Double> ratings = movies.stream().map(Movie::getRating).distinct().collect(Collectors.toList());
        ratingComboBox.getItems().addAll(ratings);
        ratingComboBox.getItems().add("Without rating");



        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here
        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if (sortBtn.getText().equals("Sort (asc)")) {
                // TODO sort observableMovies ascending
                sortBtn.setText("Sort (desc)");

                sortMovies(true);


            } else {
                // TODO sort observableMovies descending
                sortBtn.setText("Sort (asc)");
                sortMovies(false);

            }
        });
        // Filter button event handler
        filterBtn.setOnAction(actionEvent -> {


            if (genreComboBox.getValue() == "Without genre") {
                genreComboBox.setValue(null);
                genreComboBox.setPromptText("Filter by Genre");
                movieListView.setItems(observableMovies);
                applyFilters();
            }else if(releaseYearComboBox.getValue() == "Without release year") {
                releaseYearComboBox.setValue(null);
                releaseYearComboBox.setPromptText("Filter by Release Year");
                movieListView.setItems(observableMovies);
                applyFilters();
            } else if(ratingComboBox.getValue() == "Without rating") {
                ratingComboBox.setValue(null);
                ratingComboBox.setPromptText("Filter by Rating");
                movieListView.setItems(observableMovies);
                applyFilters();
            } else {
                applyFilters();
            }

        });

        // Apply filters when pressing Enter in search field
        searchField.setOnAction(actionEvent -> {



            if (genreComboBox.getValue() == "Without genre") {
                genreComboBox.setValue(null);
                genreComboBox.setPromptText("Filter by Genre");
                movieListView.setItems(observableMovies);
                applyFilters();
            }else if(releaseYearComboBox.getValue() == "Without release year") {
                releaseYearComboBox.setValue(null);
                releaseYearComboBox.setPromptText("Filter by Release Year");
                movieListView.setItems(observableMovies);
                applyFilters();
            } else if(ratingComboBox.getValue() == "Without rating") {
                ratingComboBox.setValue(null);
                ratingComboBox.setPromptText("Filter by Rating");
                movieListView.setItems(observableMovies);
                applyFilters();
            } else {

                applyFilters();
            }

        });
    }



    public void applyFilters() {

        String query = searchField.getText().toLowerCase();
        String selectedGenre = genreComboBox.getValue();
        int selectedReleaseYear;
        double selectedRating;

        filteredMovies = observableMovies.filtered(movie ->

                movie.getTitle().toLowerCase().contains(query) && movie.getTitle().toLowerCase().matches(".*\\b" + query + "\\b.*") && movie.getGenre().stream().anyMatch(genre -> genre.equalsIgnoreCase(selectedGenre))
                        || movie.getTitle().toLowerCase().contains(query) && movie.getTitle().toLowerCase().matches(query + "\\b.*") && movie.getGenre().stream().anyMatch(genre -> genre.equalsIgnoreCase(selectedGenre))
                        || movie.getTitle().toLowerCase().contains(query) && movie.getTitle().toLowerCase().matches(".*\\b" + query) && movie.getGenre().stream().anyMatch(genre -> genre.equalsIgnoreCase(selectedGenre))

                        || movie.getTitle().toLowerCase().contains(query) && movie.getTitle().toLowerCase().matches(".*\\b" + query + "\\b.*") && selectedGenre == null
                        || movie.getTitle().toLowerCase().contains(query) && movie.getTitle().toLowerCase().matches(".*\\b" + query) && selectedGenre == null
                        || movie.getTitle().toLowerCase().contains(query) && movie.getTitle().toLowerCase().matches(query + "\\b.*") && selectedGenre == null
                        ||
                        movie.getDescription().toLowerCase().contains(query) &&
                                movie.getDescription().toLowerCase().matches(".*\\b" + query + "\\b.*") && selectedGenre == null
                        || movie.getDescription().toLowerCase().contains(query) &&
                        movie.getDescription().toLowerCase().matches(query + "\\b.*") && selectedGenre == null
                        || movie.getDescription().toLowerCase().contains(query) &&
                        movie.getDescription().toLowerCase().matches(".*\\b" + query) && selectedGenre == null
                        || query.isEmpty() && movie.getGenre().stream().anyMatch(genre -> genre.equalsIgnoreCase(selectedGenre)) ||
                        movie.getDescription().toLowerCase().contains(query) &&
                                movie.getDescription().toLowerCase().matches(".*\\b" + query + "\\b.*") && movie.getGenre().stream().anyMatch(genre -> genre.equalsIgnoreCase(selectedGenre))
                        || movie.getDescription().toLowerCase().contains(query) &&
                        movie.getDescription().toLowerCase().matches(query + "\\b.*") && movie.getGenre().stream().anyMatch(genre -> genre.equalsIgnoreCase(selectedGenre))
                        || movie.getDescription().toLowerCase().contains(query) &&
                        movie.getDescription().toLowerCase().matches(".*\\b" + query) && movie.getGenre().stream().anyMatch(genre -> genre.equalsIgnoreCase(selectedGenre)));

        if (releaseYearComboBox.getValue() == null && ratingComboBox.getValue() == null) {
            filteredMovies2 = filteredMovies;
        } else if (releaseYearComboBox.getValue() == null && ratingComboBox.getValue() != null) {
            selectedRating = Double.parseDouble( ratingComboBox.getValue().toString());
            filteredMovies2 = filteredMovies.filtered(movie -> movie.getRating() >= selectedRating);
        } else if (releaseYearComboBox.getValue() != null && ratingComboBox.getValue() == null) {
            selectedReleaseYear = Integer.parseInt(releaseYearComboBox.getValue());
            filteredMovies2 = filteredMovies.filtered(movie -> movie.getReleaseYear() == selectedReleaseYear);
        } else if (releaseYearComboBox.getValue() != null && ratingComboBox.getValue() != null){
            selectedReleaseYear = Integer.parseInt(releaseYearComboBox.getValue());
            selectedRating = Double.parseDouble( ratingComboBox.getValue().toString());
            filteredMovies2 = filteredMovies.filtered(movie -> movie.getReleaseYear() == selectedReleaseYear && movie.getRating() >= selectedRating);
        }







        SortedList<Movie> sortedFilteredMovies = new SortedList<>(filteredMovies2);

        sfm.clear();
        sfm.addAll(sortedFilteredMovies);




        movieListView.setItems(sortedFilteredMovies);

        if (movieListView.getItems().size() > 4) { // Set the height of the ListView to 4 items
            VBox.setVgrow(movieListView, Priority.ALWAYS);

        } else if(movieListView.getItems().size() <= 4) {

            movieListView.setPrefHeight(movieListView.getItems().size() * 100 + 20);
            VBox.setVgrow(movieListView, Priority.NEVER);


        }
        ObservableList<Movie> items = movieListView.getItems();
        for (Movie movie : items) {
            //System.out.println(movie.getTitle());
        }
        filtered = true;


    }

    public void sortMovies(boolean ascending) {
        Comparator<Movie> comparator = Comparator.comparing(Movie::getTitle);
        if (!ascending) {
            comparator = comparator.reversed();

        }
        if (filtered == true) {
            sfm.sort(comparator);

            movieListView.setItems(sfm);
        } else {

            observableMovies.sort(comparator);
            movieListView.setItems(observableMovies); // Update the ListView with sorted items
        }
    }
    public String getMostPopularActor(List<Movie> movies) {
        if (movies == null || movies.isEmpty()) {
            return null; // or return a default value if appropriate
        }


        Map<String, Long> actorCounts = movies.stream()
                .flatMap(movie -> movie.getMainCast().stream())
                .collect(Collectors.groupingBy(actor -> actor, Collectors.counting()));

        // Schauspieler mit der höchsten Häufigkeit
        return actorCounts.entrySet().stream()
                .max((entry1, entry2) -> Long.compare(entry1.getValue(), entry2.getValue()))
                .map(Map.Entry::getKey)
                .orElse(null); // Rückgabe null, wenn keine Filme vorhanden sind
    }
    public int getLongestMovieTitle(List<Movie> movies) {
        // den längsten Titel
        Optional<String> longestTitle = movies.stream()
                .map(Movie::getTitle)
                .max(Comparator.comparingInt(String::length));

        // Länge des längsten Titels zurück, oder 0, wenn keine Filme vorhanden sind
        return longestTitle.map(String::length).orElse(0);
    }
    public long countMoviesFrom(List<Movie> movies, List<String> directors) {
        // nach dem Regisseur
        long count = movies.stream()
                .filter(movie -> directors.equals(movie.getDirector()))
                .count();

        return count;
    }
    public List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        //  nach dem Veröffentlichungsjahr
        List<Movie> filteredMovies = movies.stream()
                .filter(movie -> movie.getReleaseYear() >= startYear && movie.getReleaseYear() <= endYear)
                .collect(Collectors.toList());

        return filteredMovies;
    }
    public void setObservableMovies(ObservableList<Movie> mockMovies) {
        observableMovies.addAll(mockMovies);
    }
    public FilteredList<Movie> getFilteredMovies() {
        return filteredMovies;
    }
    public ObservableList<Movie> getSFMMovies() {
        return sfm;
    }
public ObservableList<Movie> getObservableMovies() {
        return observableMovies;
    }



}

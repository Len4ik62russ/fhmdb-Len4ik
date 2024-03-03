package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class HomeController implements Initializable {
    @FXML
    public JFXButton filterBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox<String> genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();



    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();
    private ObservableList<Movie> sfm = FXCollections.observableArrayList();

    private boolean filtered = false;
    // automatically updates corresponding UI elements when underlying data changes
    FilteredList<Movie> filteredMovies;
    //private Scene scene;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        observableMovies.addAll(allMovies);// add dummy data to observable list
        //observableMovies = observableMovies.stream().distinct().collect(Collectors.toCollection(FXCollections::observableArrayList));



        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data
        /*HBox contentHBox = new HBox(movieListView);
        MovieCell.layout.getChildren().add(contentHBox);
        if (movieListView.getItems().size() > 10) { // Beispiel: Zeigen Sie an, dass die contentHBox wächst, wenn mehr als 10 Elemente vorhanden sind
            VBox.setVgrow(contentHBox, Priority.ALWAYS);
        } else {
            VBox.setVgrow(contentHBox, Priority.NEVER);
        }*/

        // Initialize genre filter items
        List<String> genres = allMovies.stream().map(Movie::getGenre).distinct().collect(Collectors.toList());
        genreComboBox.getItems().addAll(genres);
        genreComboBox.getItems().add("Without genre");

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        //this.scene = movieListView.getScene();


        // TODO add event handlers to buttons and call the regarding methods
        // either set event handlers in the fxml file (onAction) or add them here
        // Sort button example:
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
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
            if(genreComboBox.getValue() == "Without genre"){
                genreComboBox.setValue(null);
                genreComboBox.setPromptText("Filter by Genre");
                movieListView.setItems(observableMovies);
                applyFilters();
            }else{
            applyFilters();
            }
            System.out.println("Filter button clicked");
        });

        // Apply filters when pressing Enter in search field
        searchField.setOnAction(actionEvent -> {
            System.out.println("Enter pressed");
            //searchQuery();


                applyFilters();


        });
    }



    private void applyFilters() {

        String query =  searchField.getText().toLowerCase();
        String selectedGenre = genreComboBox.getValue();

        filteredMovies = observableMovies.filtered(movie ->

               movie.getTitle().toLowerCase().contains(query) && movie.getTitle().toLowerCase().matches(".*\\b" + query + "\\b.*") && movie.getGenre().equalsIgnoreCase(selectedGenre)
                       || movie.getTitle().toLowerCase().contains(query) && movie.getTitle().toLowerCase().matches(query + "\\b.*") && movie.getGenre().equalsIgnoreCase(selectedGenre)
                       || movie.getTitle().toLowerCase().contains(query) && movie.getTitle().toLowerCase().matches(".*\\b" + query) && movie.getGenre().equalsIgnoreCase(selectedGenre)

                       || movie.getTitle().toLowerCase().contains(query) && movie.getTitle().toLowerCase().matches(".*\\b" + query + "\\b.*") && selectedGenre == null
                       || movie.getTitle().toLowerCase().contains(query) && movie.getTitle().toLowerCase().matches(".*\\b" + query) && selectedGenre == null
                       || movie.getTitle().toLowerCase().contains(query) && movie.getTitle().toLowerCase().matches(query + "\\b.*") && selectedGenre == null
                       ||
                       movie.getDescription().toLowerCase().contains(query) &&
                       movie.getDescription().toLowerCase().matches(".*\\b" + query + "\\b.*") && selectedGenre == null
                       ||  movie.getDescription().toLowerCase().contains(query) &&
                       movie.getDescription().toLowerCase().matches(query + "\\b.*") && selectedGenre == null
                       || movie.getDescription().toLowerCase().contains(query) &&
                       movie.getDescription().toLowerCase().matches(".*\\b" + query) && selectedGenre == null
                       || query.isEmpty() && movie.getGenre().equalsIgnoreCase(selectedGenre) ||
                       movie.getDescription().toLowerCase().contains(query) &&
                               movie.getDescription().toLowerCase().matches(".*\\b" + query + "\\b.*") && movie.getGenre().equalsIgnoreCase(selectedGenre)
        || movie.getDescription().toLowerCase().contains(query) &&
                movie.getDescription().toLowerCase().matches(query + "\\b.*") && movie.getGenre().equalsIgnoreCase(selectedGenre)
                ||  movie.getDescription().toLowerCase().contains(query) &&
                       movie.getDescription().toLowerCase().matches(".*\\b" + query) && movie.getGenre().equalsIgnoreCase(selectedGenre));



        //observableMovies.removeAll();
        //original(query.isEmpty() ||(movie.getTitle().toLowerCase().contains(query)) || movie.getDescription().toLowerCase().contains(query)) &&
        //original(movie.getGenre().equals(selectedGenre) || selectedGenre == null || selectedGenre.isEmpty()));

        //movieListView.setItems(filteredMovies);

        SortedList<Movie> sortedFilteredMovies = new SortedList<>(filteredMovies);


        sfm.addAll(filteredMovies);

        //sortedFilteredMovies.comparatorProperty().bind(movieListView.comparatorProperty()); // Corrected line
        //sortedFilteredMovies.setComparator(Comparator.comparing(Movie::getTitle));

        //Comparator<Movie> comparator = Comparator.comparing(Movie::getTitle);
        //sortedFilteredMovies.setComparator(comparator);

        movieListView.setItems(sortedFilteredMovies);
        /*Set<Movie> uniqueMovies = new HashSet<>(movieListView.getItems());
        List<Movie> uniqueMovieList = new ArrayList<>(uniqueMovies);
        movieListView.setItems(FXCollections.observableArrayList(uniqueMovieList));*/
        if (movieListView.getItems().size() > 4) { // Beispiel: Zeigen Sie an, dass die contentHBox wächst, wenn mehr als 10 Elemente vorhanden sind
            VBox.setVgrow(movieListView, Priority.ALWAYS);
            //scene.getWindow().setHeight(890);
        } else if(movieListView.getItems().size() <= 4){
            //movieListView.setPrefHeight(Math.min(movieListView.getItems().size() * 55, 150)); // Adjust the height as needed
            movieListView.setPrefHeight(Math.min(movieListView.getItems().size() * 200, 100));// Adjust the height as needed


            VBox.setVgrow(movieListView, Priority.NEVER);

        }
        ObservableList<Movie> items = movieListView.getItems();
        for (Movie movie : items) {
            System.out.println(movie.getTitle());
        }
        filtered = true;


    }
    private void sortMovies(boolean ascending) {
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

    /*private void sortFilteredMovies(boolean ascending) {
        Comparator<Movie> comparator = Comparator.comparing(Movie::getTitle);
        if (!ascending) {
            comparator = comparator.reversed();
        }


        sfm.sort(comparator);
        movieListView.setItems(sfm); // Update the ListView with sorted items
    }*/
    /*private void searchQuery() {
        String query = searchField.getText().toLowerCase();
        FilteredList<Movie> filteredMovies = observableMovies.filtered(movie -> movie.getTitle().toLowerCase().contains(query) || movie.getDescription().toLowerCase().contains(query));
        movieListView.setItems(filteredMovies);
    }*/



    }

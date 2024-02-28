package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.CustomPredicate;
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
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list


        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // Initialize genre filter items
        List<String> genres = allMovies.stream().map(Movie::getGenre).distinct().collect(Collectors.toList());
        genreComboBox.getItems().addAll(genres);

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");

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
            applyFilters();
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

        /*if (query.matches(".*\\b" + query + "\\b.*")) {
            System.out.println(query);
            query2 = query;
        } else {
            query2 = null;
        }*/


        String selectedGenre = genreComboBox.getValue();


        FilteredList<Movie> filteredMovies = observableMovies.filtered(movie ->

               movie.getTitle().toLowerCase().contains(query) && movie.getTitle().toLowerCase().matches(".*\\b" + query + "\\b.*") && movie.getGenre().equalsIgnoreCase(selectedGenre) ||
                       movie.getDescription().toLowerCase().contains(query) &&
                       movie.getDescription().toLowerCase().matches(".*\\b" + query + "\\b.*") && selectedGenre == null || query.isEmpty() && movie.getGenre().equalsIgnoreCase(selectedGenre) ||
                       movie.getDescription().toLowerCase().contains(query) &&
                               movie.getDescription().toLowerCase().matches(".*\\b" + query + "\\b.*") && movie.getGenre().equalsIgnoreCase(selectedGenre));

        //original(query.isEmpty() ||(movie.getTitle().toLowerCase().contains(query)) || movie.getDescription().toLowerCase().contains(query)) &&
        //original(movie.getGenre().equals(selectedGenre) || selectedGenre == null || selectedGenre.isEmpty()));


        SortedList<Movie> sortedFilteredMovies = new SortedList<>(filteredMovies);


        sfm.addAll(filteredMovies);
        //sortedFilteredMovies.comparatorProperty().bind(movieListView.comparatorProperty()); // Corrected line
        movieListView.setItems(sortedFilteredMovies);
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

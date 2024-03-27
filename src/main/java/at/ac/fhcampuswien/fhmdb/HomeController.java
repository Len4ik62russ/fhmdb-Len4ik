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
    public JFXComboBox<String> releaseYearComboBox;
    public JFXComboBox ratingComboBox;


    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();



    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();


    private ObservableList<Movie> sfm = FXCollections.observableArrayList();

    private boolean filtered = false;
    // automatically updates corresponding UI elements when underlying data changes
    FilteredList<Movie> filteredMovies;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        observableMovies.addAll(allMovies);// add dummy data to observable list




        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data


        // Initialize genre filter items
        List<String> genres = allMovies.stream().map(Movie::getGenre).distinct().collect(Collectors.toList());
        genreComboBox.getItems().addAll(genres);
        genreComboBox.getItems().add("Without genre");

        // TODO add genre filter items with genreComboBox.getItems().addAll(...)
        genreComboBox.setPromptText("Filter by Genre");
        releaseYearComboBox.setPromptText("Filter by Release Year");
        releaseYearComboBox.getItems().addAll("2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900");
        ratingComboBox.setPromptText("Filter by Rating");
        ratingComboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");


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
            //System.out.println("Filter button clicked");
        });

        // Apply filters when pressing Enter in search field
        searchField.setOnAction(actionEvent -> {
            //System.out.println("Enter pressed");
            //searchQuery();



            if(genreComboBox.getValue() == "Without genre"){
                genreComboBox.setValue(null);
                genreComboBox.setPromptText("Filter by Genre");
                movieListView.setItems(observableMovies);
                applyFilters();
            }else {

                applyFilters();
            }

        });
    }



    public void applyFilters() {

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


        //System.out.println(filteredMovies.size());


        SortedList<Movie> sortedFilteredMovies = new SortedList<>(filteredMovies);

        sfm.clear();
        sfm.addAll(sortedFilteredMovies);
        //System.out.println(sfm.size());



        movieListView.setItems(sortedFilteredMovies);

        if (movieListView.getItems().size() > 4) { // Beispiel: Zeigen Sie an, dass die contentHBox wächst, wenn mehr als 10 Elemente vorhanden sind
            VBox.setVgrow(movieListView, Priority.ALWAYS);
            //scene.getWindow().setHeight(890);
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
        // Zählen Sie die Häufigkeit jedes Schauspielers im mainCast jedes Films
        Map<String, Long> actorCounts = movies.stream()
                .flatMap(movie -> movie.getMainCast().stream())
                .collect(Collectors.groupingBy(actor -> actor, Collectors.counting()));

        // Finden Sie den Schauspieler mit der höchsten Häufigkeit
        return actorCounts.entrySet().stream()
                .max((entry1, entry2) -> Long.compare(entry1.getValue(), entry2.getValue()))
                .map(Map.Entry::getKey)
                .orElse(null); // Rückgabe null, wenn keine Filme vorhanden sind
    }
    public int getLongestMovieTitle(List<Movie> movies) {
        // Finden Sie den längsten Titel
        Optional<String> longestTitle = movies.stream()
                .map(Movie::getTitle) // Angenommen, es gibt eine Methode getTitle() in der Movie-Klasse
                .max(Comparator.comparingInt(String::length));

        // Geben Sie die Länge des längsten Titels zurück, oder 0, wenn keine Filme vorhanden sind
        return longestTitle.map(String::length).orElse(0);
    }
    public long countMoviesFrom(List<Movie> movies, String director) {
        // Filtern Sie die Filme nach dem Regisseur und zählen Sie die Anzahl der Filme
        long count = movies.stream()
                .filter(movie -> director.equals(movie.getDirector())) // Angenommen, es gibt eine Methode getDirector() in der Movie-Klasse
                .count();

        return count;
    }
    public List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        // Filtern Sie die Filme nach dem Veröffentlichungsjahr
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

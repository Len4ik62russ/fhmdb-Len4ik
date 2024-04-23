package at.ac.fhcampuswien.fhmdb.controllers;
import at.ac.fhcampuswien.fhmdb.database.MovieEntity;
import at.ac.fhcampuswien.fhmdb.database.MovieRepository;
import at.ac.fhcampuswien.fhmdb.database.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class WatchlistController {
    @FXML
    private VBox watchlistView;
    @FXML
    private JFXListView  movieListView;
    HomeController homeController = new HomeController();
    public WatchlistController() {
        /*try {
            showWatchlistMovies();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }
    private final MovieCell.ClickEventHandler<Movie> onRemoveClicked = (clickedItem) -> {
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
    public WatchlistController(VBox watchlistView) {
        this.watchlistView = watchlistView;
    }

    @FXML
    public void showHomeScreen() throws IOException {
        Stage stage = (Stage) watchlistView.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/at/ac/fhcampuswien/fhmdb/home-view.fxml")));
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void showWatchlistMovies() throws IOException {
        WatchlistRepository watchlistRepository = null;
        try {
            watchlistRepository = new WatchlistRepository();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<WatchlistMovieEntity> watchlistMovies = null;
        try {
            watchlistMovies = watchlistRepository.getAllWatchlistMovies();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Hier fügen Sie die Filme zur ListView hinzu
        for (WatchlistMovieEntity movie : watchlistMovies) {
            // Konvertieren Sie MovieEntity in Movie und fügen Sie es zur ListView hinzu
            // Sie müssen eine Methode implementieren, die MovieEntity in Movie konvertiert

            Movie movieToAdd = null;
            try {
                movieToAdd = homeController.convertMovieEntityToMovie(movie);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            movieListView.getItems().add(movieToAdd);
            movieListView.setCellFactory(movieListView -> new MovieCell(onRemoveClicked)); // use custom cell factory to display data
        }
    }
}

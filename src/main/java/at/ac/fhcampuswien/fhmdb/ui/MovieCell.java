package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.sql.SQLException;
import java.util.stream.Collectors;

public class MovieCell extends ListCell<Movie> {
    private  final Label title = new Label();
    private  final Label detail = new Label();
    private  final Label genre = new Label();
    private final JFXButton watchlistButton = new JFXButton("Add to Watchlist");


    private final VBox layout = new VBox(title, detail, genre, watchlistButton);
    @FunctionalInterface
    public interface ClickEventHandler<T> {
        void onClick(T t) throws SQLException;
    }
    private ClickEventHandler<Movie> addToWatchlistClicked;

    public MovieCell(ClickEventHandler<Movie> addToWatchlistClicked) {
        super();
        this.addToWatchlistClicked = addToWatchlistClicked;

        watchlistButton.setOnMouseClicked(mouseEvent -> {
            try {
                System.out.println("Add to Watchlist button clicked for movie: " + getItem().getTitle());
                addToWatchlistClicked.onClick(getItem());
                System.out.println("Movie added to watchlist: " + getItem().getTitle());
            } catch (SQLException e) {
                System.out.println("Error adding movie to watchlist: " + e.getMessage());
                throw new RuntimeException(e);
            }
        });
        // ... rest of code
    }

    @Override
    protected void updateItem(Movie movie, boolean empty) {


        super.updateItem(movie, empty);


        if (empty || movie == null) {
            setText(null);
        } else {
            this.getStyleClass().add("movie-cell");

            title.setText(movie.getTitle());
            detail.setText(
                    movie.getDescription() != null
                            ? movie.getDescription()
                            : "No description available"

            );
            genre.setText(movie.getGenre().stream().collect(Collectors.joining(", ")));
            //genre.setText(movie.getGenre());
            // color scheme
            title.getStyleClass().add("text-yellow");
            detail.getStyleClass().add("text-white");
            genre.getStyleClass().add("text-white");
            layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));

            // layout
            title.fontProperty().set(title.getFont().font(20));
            detail.setMaxWidth(this.getScene().getWidth() - 30);
            detail.setWrapText(true);
            genre.fontProperty().set(genre.getFont().font(15));
            layout.setPadding(new Insets(10));
            layout.spacingProperty().set(10);
            layout.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);
            setGraphic(layout);
            ;
        }
    }
}


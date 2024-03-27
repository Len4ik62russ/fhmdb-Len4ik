package at.ac.fhcampuswien.fhmdb;

import com.jfoenix.controls.JFXListView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class FhmdbApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FhmdbApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 890, 620);
        //JFXListView<String> movieListView = new JFXListView<>();
        //StackPane root = new StackPane(movieListView);
        scene.getStylesheets().add(Objects.requireNonNull(FhmdbApplication.class.getResource("styles.css")).toExternalForm());
        stage.setTitle("FHMDb");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}


    /*MovieAPI movieAPI = new MovieAPI();
        try {
                List<Movie> movies = movieAPI.getMovies("userinput", "ACTION");
        for (Movie movie : movies) {
        System.out.println(movie.getTitle());
        }
        } catch (IOException e) {
        e.printStackTrace();
        }*/
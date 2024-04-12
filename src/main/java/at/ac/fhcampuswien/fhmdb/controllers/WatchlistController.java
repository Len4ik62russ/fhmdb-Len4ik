package at.ac.fhcampuswien.fhmdb.controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
public class WatchlistController {
    @FXML
    private VBox watchlistView;
    public WatchlistController() {
    }

    public WatchlistController(VBox watchlistView) {
        this.watchlistView = watchlistView;
    }

    @FXML
    public void showHomeScreen() throws IOException {
        Stage stage = (Stage) watchlistView.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("home-view.fxml")));
        stage.setScene(scene);
        stage.show();
    }
}

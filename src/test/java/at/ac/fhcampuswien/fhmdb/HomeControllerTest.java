package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;




class HomeControllerTest {

    private HomeController controller;
/*
ChatGPT
*/
    @BeforeAll
    static void initJFX() {
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
        System.setProperty("prism.order", "sw");
        System.setProperty("prism.text", "t2k");
        System.setProperty("java.awt.headless", "true");

        // Initialize JavaFX toolkit
        if (Platform.isFxApplicationThread()) {
            return;
        }

        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(() -> latch.countDown());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
/*
End ChatGPT
 */
    @BeforeEach
    void setUp() {
        controller = new HomeController();
        // Initialisieren Sie UI-Elemente, die in der applyFilters-Methode verwendet werden
        TextField searchField = new TextField();
        JFXComboBox<String> genreComboBox = new JFXComboBox<>();

        JFXListView<Movie> movieListView = new JFXListView<>();
        controller.searchField = searchField;
        controller.genreComboBox = genreComboBox;
        controller.movieListView = movieListView;
        // Setzen Sie die Filme im Controller
        List<Movie> mockMovies = Arrays.asList(
                new Movie("Movie1", "Description1", "Action"),
                new Movie("Movie2", "Description2", "Drama"),
                new Movie("Movie3", "Description3", "Drama"),
                new Movie("Movie4", "Description4", "Drama")
                // Weitere Filme hinzufügen, falls erforderlich...
        );
        ObservableList<Movie> observableMovies = FXCollections.observableArrayList(mockMovies);
        controller.setObservableMovies(observableMovies);
    }

    @Test
    void testApplyFilters_QueryInTitle_noGenre() {
        // Setzen Sie die Abfrage in das Suchfeld
        controller.searchField.setText("Movie1");
        // Setzen Sie das GenreComboBox-Feld auf leer
        controller.genreComboBox.setValue(null);
        // Führen Sie die Methode applyFilters() aus
        controller.applyFilters();
        // Überprüfen Sie, ob nur Filme mit der Abfrage im Titel in der gefilterten Liste enthalten sind
        ObservableList<Movie> filteredMovies = controller.getFilteredMovies();
        assertEquals(1, filteredMovies.size());
        assertTrue(filteredMovies.contains(new Movie("Movie1", "Description1", "Action")));
        //assertFalse(filteredMovies.contains(new Movie("Movie2", "Drama", "Description2")));

    }




    @Test
    void testApplyFilters_QueryInDescription_noGenre() {
        // Setzen Sie die Abfrage in das Suchfeld
        controller.searchField.setText("Description2");
        // Setzen Sie das GenreComboBox-Feld auf leer
        controller.genreComboBox.setValue(null);
        // Führen Sie die Methode applyFilters() aus
        controller.applyFilters();
        // Überprüfen Sie, ob nur Filme mit der Abfrage in der Beschreibung in der gefilterten Liste enthalten sind
        ObservableList<Movie> filteredMovies = controller.getFilteredMovies();
        System.out.println("Filtered movies: " + filteredMovies);
        assertTrue(filteredMovies.contains(new Movie("Movie2", "Description2", "Drama")));
        assertEquals(1, filteredMovies.size());

    }


    @Test
    void testApplyFilters_Genre_withNoQuery() {
        // Setzen Sie die Abfrage in das Suchfeld
        controller.searchField.setText("");
        // Setzen Sie das GenreComboBox-Feld auf leer
        controller.genreComboBox.setValue("Drama");
        // Führen Sie die Methode applyFilters() aus
        controller.applyFilters();
        // Überprüfen Sie, ob nur Filme mit der Abfrage in der Beschreibung in der gefilterten Liste enthalten sind
        ObservableList<Movie> filteredMovies = controller.getFilteredMovies();
        System.out.println("Filtered movies: " + filteredMovies);
        assertTrue(filteredMovies.contains(new Movie("Movie2", "Description2", "Drama")));
        assertTrue(filteredMovies.contains(new Movie("Movie3", "Description3", "Drama")));
        assertTrue(filteredMovies.contains(new Movie("Movie4", "Description4", "Drama")));
        assertEquals(3, filteredMovies.size());

    }



    @Test
    void testApplyFilters_Genre_withQuery() {
        // Setzen Sie die Abfrage in das Suchfeld
        controller.searchField.setText("Movie2");
        // Setzen Sie das GenreComboBox-Feld auf leer
        controller.genreComboBox.setValue("Drama");
        // Führen Sie die Methode applyFilters() aus
        controller.applyFilters();
        // Überprüfen Sie, ob nur Filme mit der Abfrage in der Beschreibung in der gefilterten Liste enthalten sind
        ObservableList<Movie> filteredMovies = controller.getFilteredMovies();
        System.out.println("Filtered movies: " + filteredMovies);
        assertTrue(filteredMovies.contains(new Movie("Movie2", "Description2", "Drama")));

        assertEquals(1, filteredMovies.size());

    }


    @Test
    void testSortMoviesAscending() {
        controller.sortMovies(true);
        SortedList<Movie> sortedMovies = new SortedList<>(controller.getObservableMovies());
        assertEquals("Movie1", sortedMovies.get(0).getTitle());
        assertEquals("Movie2", sortedMovies.get(1).getTitle());
        assertEquals("Movie3", sortedMovies.get(2).getTitle());
        assertEquals("Movie4", sortedMovies.get(3).getTitle());
    }
    @Test
    void testSortMoviesDescending() {
        controller.sortMovies(false);
        SortedList<Movie> sortedMovies = new SortedList<>(controller.getObservableMovies());
        assertEquals("Movie4", sortedMovies.get(0).getTitle());
        assertEquals("Movie3", sortedMovies.get(1).getTitle());
        assertEquals("Movie2", sortedMovies.get(2).getTitle());
        assertEquals("Movie1", sortedMovies.get(3).getTitle());
    }


}

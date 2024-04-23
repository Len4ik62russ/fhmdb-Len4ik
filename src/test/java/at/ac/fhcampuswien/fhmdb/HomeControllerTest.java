package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.controllers.HomeController;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
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
}
/*
End ChatGPT
 */
/*
    @BeforeEach
    void setUp() {
        //given
        VBox homeView = null;
        controller = new HomeController(homeView);
        // UI-Elemente, die in der applyFilters-Methode verwendet werden

        TextField searchField = new TextField();
        JFXComboBox<String> releaseYearComboBox = new JFXComboBox<>();
        JFXComboBox<String> genreComboBox = new JFXComboBox<>();
        JFXComboBox<String> ratingComboBox = new JFXComboBox<>();

        JFXListView<Movie> movieListView = new JFXListView<>();
        controller.searchField = searchField;
        controller.genreComboBox = genreComboBox;
        controller.releaseYearComboBox = releaseYearComboBox;
        controller.ratingComboBox = ratingComboBox;

        controller.movieListView = movieListView;
        // die Filme im Controller
        List<Movie> mockMovies = Arrays.asList(
                new Movie("Movie1", "Description1", Arrays.asList("Action", "Drama", "Thriller"), 2002, 9.0, Arrays.asList("Tarantino1"), Arrays.asList("Actor1") ),
                new Movie("Movie222", "Description2", Arrays.asList("Action", "Comedy"), 2010, 7.6, Arrays.asList("Tarantino1"), Arrays.asList("Actor1") ),
                new Movie("Movie33333", "Description3", Arrays.asList("Biography", "Drama"), 1995, 5.0, Arrays.asList("Tarantino1"), Arrays.asList("Actor1") ),
                new Movie("Movie4444444", "Description4", Arrays.asList("Animation", "Comedy"), 1980, 5.5, Arrays.asList("Tarantino2"), Arrays.asList("Actor1") ),
                new Movie("Movie55555555", "Description5", Arrays.asList("Action", "Horror"), 1975, 7.0, Arrays.asList("Tarantino2"), Arrays.asList("Actor2") ),
                new Movie("Movie6666666666", "Description6", Arrays.asList("History", "Drama"), 1960, 8.7, Arrays.asList("Tarantino3"), Arrays.asList("Actor3") ),
                new Movie("Movie777777777777", "Description7", Arrays.asList("Thriller", "Drama"), 1945, 4.5, Arrays.asList("Tarantino3"), Arrays.asList("Actor4") )

        );
        ObservableList<Movie> observableMovies = FXCollections.observableArrayList(mockMovies);
        controller.setObservableMovies(observableMovies);
    }

    @Test
    void testApplyFilters_QueryInTitle_noGenre() {
        // given
        controller.searchField.setText("Movie1");
        controller.genreComboBox.setValue(null);
        // when
        controller.applyFilters();
        // then
        ObservableList<Movie> filteredMovies = controller.getFilteredMovies();
        assertEquals(1, filteredMovies.size());
        assertTrue(filteredMovies.contains(new Movie ("Movie1", "Description1", Arrays.asList("Action", "Drama", "Thriller"), 2002, 9.0, Arrays.asList("Tarantino1"), Arrays.asList("Actor1") )));


    }




    @Test
    void testApplyFilters_QueryInDescription_noGenre() {
        // given
        controller.searchField.setText("Description2");

        controller.genreComboBox.setValue(null);
        // when
        controller.applyFilters();
        // then
        ObservableList<Movie> filteredMovies = controller.getFilteredMovies();
        System.out.println("Filtered movies: " + filteredMovies);
        assertTrue(filteredMovies.contains(new Movie("Movie222", "Description2", Arrays.asList("Action", "Comedy"), 2010, 7.6, Arrays.asList("Tarantino1"), Arrays.asList("Actor1"))));
        assertEquals(1, filteredMovies.size());

    }


    @Test
    void testApplyFilters_Genre_withNoQuery() {
        // given
        controller.searchField.setText("");

        controller.genreComboBox.setValue("Drama");
        // when
        controller.applyFilters();
        // then
        ObservableList<Movie> filteredMovies = controller.getFilteredMovies();
        System.out.println("Filtered movies: " + filteredMovies);
        assertTrue(filteredMovies.contains(new Movie("Movie33333", "Description3", Arrays.asList("Biography", "Drama"), 1995, 5.0, Arrays.asList("Tarantino1"), Arrays.asList("Actor1"))));
        assertTrue(filteredMovies.contains(new Movie("Movie6666666666", "Description6", Arrays.asList("History", "Drama"), 1960, 8.7, Arrays.asList("Tarantino3"), Arrays.asList("Actor3"))));
        assertTrue(filteredMovies.contains(new Movie("Movie777777777777", "Description7", Arrays.asList("Thriller", "Drama"), 1945, 4.5, Arrays.asList("Tarantino3"), Arrays.asList("Actor4"))));
        assertTrue(filteredMovies.contains(new Movie("Movie1", "Description1", Arrays.asList("Action", "Drama", "Thriller"), 2002, 9.0, Arrays.asList("Tarantino1"), Arrays.asList("Actor1") )));
        assertEquals(4, filteredMovies.size());

    }



    @Test
    void testApplyFilters_Genre_withQuery() {
        // given
        controller.searchField.setText("Movie4444444");

        controller.genreComboBox.setValue("Comedy");
        // when
        controller.applyFilters();
        // then
        ObservableList<Movie> filteredMovies = controller.getFilteredMovies();
        System.out.println("Filtered movies: " + filteredMovies);
        assertTrue(filteredMovies.contains(new Movie("Movie4444444", "Description4", Arrays.asList("Animation", "Comedy"), 1980, 5.5, Arrays.asList("Tarantino2"), Arrays.asList("Actor1") )));

        assertEquals(1, filteredMovies.size());

    }


    @Test
    void testSortMoviesAscending() {
        //when
        controller.sortMovies(true);
        //then
        SortedList<Movie> sortedMovies = new SortedList<>(controller.getObservableMovies());
        assertEquals("Movie1", sortedMovies.get(0).getTitle());
        assertEquals("Movie222", sortedMovies.get(1).getTitle());
        assertEquals("Movie33333", sortedMovies.get(2).getTitle());
        assertEquals("Movie4444444", sortedMovies.get(3).getTitle());
        assertEquals("Movie55555555", sortedMovies.get(4).getTitle());
        assertEquals("Movie6666666666", sortedMovies.get(5).getTitle());
        assertEquals("Movie777777777777", sortedMovies.get(6).getTitle());

    }
    @Test
    void testSortMoviesDescending() {
        //when
        controller.sortMovies(false);
        //then
        SortedList<Movie> sortedMovies = new SortedList<>(controller.getObservableMovies());
        assertEquals("Movie777777777777", sortedMovies.get(0).getTitle());
        assertEquals("Movie6666666666", sortedMovies.get(1).getTitle());
        assertEquals("Movie55555555", sortedMovies.get(2).getTitle());
        assertEquals("Movie4444444", sortedMovies.get(3).getTitle());
        assertEquals("Movie33333", sortedMovies.get(4).getTitle());
        assertEquals("Movie222", sortedMovies.get(5).getTitle());
        assertEquals("Movie1", sortedMovies.get(6).getTitle());

    }

    @Test
    void testGetMostPopularActor() {
        // given
        Movie movie1 = new Movie ("Movie33333", "Description3", Arrays.asList("Biography", "Drama"), 1995, 5.0, Arrays.asList("Tarantino1"), Arrays.asList("Actor1") );
        Movie movie2 = new Movie("Movie4444444", "Description4", Arrays.asList("Animation", "Comedy"), 1980, 5.5, Arrays.asList("Tarantino2"), Arrays.asList("Actor1") );
        Movie movie3 = new Movie("Movie55555555", "Description5", Arrays.asList("Action", "Horror"), 1975, 7.0, Arrays.asList("Tarantino2"), Arrays.asList("Actor2") );


        List<Movie> movies = Arrays.asList(movie1, movie2, movie3);

        // when
        String mostPopularActor = controller.getMostPopularActor(movies);

        // then
        assertEquals("Actor1", mostPopularActor);
    }

    @Test
    void testGetMostPopularActor_NoMovies() {
        // given
        //when
        String mostPopularActor = controller.getMostPopularActor(Collections.emptyList());

        // then
        assertEquals(null, mostPopularActor);
    }

    @Test
    void testGetMostPopularActor_AllActorsEqual() {
        // given
        Movie movie1 = new Movie ("Movie33333", "Description3", Arrays.asList("Biography", "Drama"), 1995, 5.0, Arrays.asList("Tarantino1"), Arrays.asList("Actor1", "Actor2") );


        Movie movie2 = new Movie("Movie4444444", "Description4", Arrays.asList("Animation", "Comedy"), 1980, 5.5, Arrays.asList("Tarantino2"), Arrays.asList("Actor1", "Actor2") );


        List<Movie> movies = Arrays.asList(movie1, movie2);

        // when
        String mostPopularActor = controller.getMostPopularActor(movies);

       //then
        assertTrue(mostPopularActor.equals("Actor1") || mostPopularActor.equals("Actor2"));
    }
    @Test
    void testGetLongestMovieTitle() {
        // given
        Movie movie1 = new Movie("Short", "Description3", Arrays.asList("Biography", "Drama"), 1995, 5.0, Arrays.asList("Tarantino1"), Arrays.asList("Actor1") );
        Movie movie2 = new Movie("LongerTitle", "Description4", Arrays.asList("Animation", "Comedy"), 1980, 5.5, Arrays.asList("Tarantino2"), Arrays.asList("Actor1") );
        Movie movie3 = new Movie("VeryLongTitleIndeed", "Description5", Arrays.asList("Action", "Horror"), 1975, 7.0, Arrays.asList("Tarantino2"), Arrays.asList("Actor2") );

        List<Movie> movies = Arrays.asList(movie1, movie2, movie3);

        // when
        int longestTitleLength = controller.getLongestMovieTitle(movies);

        // then
        assertEquals(19, longestTitleLength);
    }

    @Test
    void testGetLongestMovieTitle_NoMovies() {
        // given
        // when
        int longestTitleLength = controller.getLongestMovieTitle(Collections.emptyList());

        // then
        assertEquals(0, longestTitleLength);
    }

    @Test
    void testGetLongestMovieTitle_AllTitlesEqual() {
        // given
        Movie movie1 = new Movie("Title", "Description5", Arrays.asList("Action", "Horror"), 1975, 7.0, Arrays.asList("Tarantino2"), Arrays.asList("Actor2") );
        Movie movie2 = new Movie("Title", "description6", Arrays.asList("History", "Drama"), 1960, 8.7, Arrays.asList("Tarantino3"), Arrays.asList("Actor3") );

        List<Movie> movies = Arrays.asList(movie1, movie2);

        // when
        int longestTitleLength = controller.getLongestMovieTitle(movies);

        // then
        assertEquals(5, longestTitleLength);
    }
    @Test
    void testCountMoviesFrom() {
        // given
        Movie movie1 = new Movie("Movie1", "Description5", Arrays.asList("Action", "Horror"), 1975, 7.0, Arrays.asList("Tarantino2"), Arrays.asList("Actor2") );
        Movie movie2 = new Movie("Movie2", "description6", Arrays.asList("History", "Drama"), 1960, 8.7, Arrays.asList("Tarantino3"), Arrays.asList("Actor3") );
        Movie movie3 = new Movie("Movie3", "description7", Arrays.asList("Thriller", "Drama"), 1945, 4.5, Arrays.asList("Tarantino3"), Arrays.asList("Actor4") );

        List<Movie> movies = Arrays.asList(movie1, movie2, movie3);

        // when
        long count = controller.countMoviesFrom(movies, Arrays.asList("Tarantino3"));

        // then
        assertEquals(2, count);
    }

    @Test
    void testCountMoviesFrom_NoMovies() {
        //given
        //when
        long count = controller.countMoviesFrom(Collections.emptyList(), Arrays.asList("DirectorA"));

        // then
        assertEquals(0, count);
    }

    @Test
    void testCountMoviesFrom_AllMoviesByDirector() {
        // given
        Movie movie1 = new Movie("Movie1", "description6", Arrays.asList("History", "Drama"), 1960, 8.7, Arrays.asList("Tarantino3"), Arrays.asList("Actor3") );
        Movie movie2 = new Movie("Movie2", "description7", Arrays.asList("Thriller", "Drama"), 1945, 4.5, Arrays.asList("Tarantino3"), Arrays.asList("Actor4") );

        List<Movie> movies = Arrays.asList(movie1, movie2);

        // when
        long count = controller.countMoviesFrom(movies, Arrays.asList("Tarantino3"));

        // then
        assertEquals(2, count);
    }
    @Test
    void testGetMoviesBetweenYears() {
        // given
        Movie movie1 = new Movie("Movie1", "Description5", Arrays.asList("Action", "Horror"), 1975, 7.0, Arrays.asList("Tarantino2"), Arrays.asList("Actor2") );
        Movie movie2 = new Movie("Movie2", "description6", Arrays.asList("History", "Drama"), 1960, 8.7, Arrays.asList("Tarantino3"), Arrays.asList("Actor3") );
        Movie movie3 = new Movie("Movie3", "description7", Arrays.asList("Thriller", "Drama"), 1945, 4.5, Arrays.asList("Tarantino3"), Arrays.asList("Actor4") );
        Movie movie4 = new Movie("Movie4", "description8", Arrays.asList("Thriller", "Drama"), 2000, 4.5, Arrays.asList("Tarantino3"), Arrays.asList("Actor4") );


        List<Movie> movies = Arrays.asList(movie1, movie2, movie3, movie4);

        // when
        List<Movie> filteredMovies = controller.getMoviesBetweenYears(movies, 1955, 1980);

        // then
        assertEquals(2, filteredMovies.size()); // Two movies are within the specified range
        assertTrue(filteredMovies.contains(movie1));
        assertTrue(filteredMovies.contains(movie2));
        assertEquals(movie1, filteredMovies.get(0));
        assertEquals(movie2, filteredMovies.get(1));
    }

    @Test
    void testGetMoviesBetweenYears_NoMovies() {
        // given
        // when
        List<Movie> filteredMovies = controller.getMoviesBetweenYears(Collections.emptyList(), 2000, 2015);

        // then
        assertEquals(0, filteredMovies.size());
    }

    @Test
    void testGetMoviesBetweenYears_AllMoviesWithinRange() {
        // given
        Movie movie1 = new Movie("Movie1", "Description5", Arrays.asList("Action", "Horror"), 1975, 7.0, Arrays.asList("Tarantino2"), Arrays.asList("Actor2") );
        Movie movie2 = new Movie("Movie2", "description6", Arrays.asList("History", "Drama"), 1960, 8.7, Arrays.asList("Tarantino3"), Arrays.asList("Actor3") );
        Movie movie3 = new Movie("Movie3", "description7", Arrays.asList("Thriller", "Drama"), 1945, 4.5, Arrays.asList("Tarantino3"), Arrays.asList("Actor4") );
        Movie movie4 = new Movie("Movie4", "description8", Arrays.asList("Thriller", "Drama"), 2000, 4.5, Arrays.asList("Tarantino3"), Arrays.asList("Actor4") );

        List<Movie> movies = Arrays.asList(movie1, movie2, movie3, movie4);

        // when
        List<Movie> filteredMovies = controller.getMoviesBetweenYears(movies, 1945, 2000);

        // then
        assertEquals(4, filteredMovies.size()); // All movies are within the specified range
        assertTrue(filteredMovies.contains(movie1));
        assertTrue(filteredMovies.contains(movie2));
        assertTrue(filteredMovies.contains(movie3));
        assertTrue(filteredMovies.contains(movie4));
    }

}*/

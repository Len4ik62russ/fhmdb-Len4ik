package at.ac.fhcampuswien.fhmdb.database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class WatchlistRepository {
    private Connection connection;


    public WatchlistRepository() throws SQLException {
        try {
            Class.forName("org.h2.Driver");
            createTableIfNotExists();
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
    }
    public void createTableIfNotExists() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String createTableSQL = "CREATE TABLE IF NOT EXISTS WATCHLIST_MOVIES("
                + "ID INT PRIMARY KEY, "
                + "APIID VARCHAR(255)"
                + ")";
        statement.execute(createTableSQL);
    }
    private Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        }
        return connection;
    }

    public List<WatchlistMovieEntity> getAllWatchlistMovies() throws SQLException {
        List<WatchlistMovieEntity> watchlistMovies = new ArrayList<>();
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM watchlist_movies");

        while (resultSet.next()) {
            WatchlistMovieEntity watchlistMovie = new WatchlistMovieEntity();
            // Set watchlistMovie properties from resultSet
            watchlistMovies.add(watchlistMovie);
        }

        return watchlistMovies;
    }

    public void deleteWatchlistMovie(long id) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("DELETE FROM watchlist_movies WHERE id = ?");
        statement.setLong(1, id);
        statement.executeUpdate();
    }

    public void deleteAllWatchlistMovies() throws SQLException {
        Statement statement = getConnection().createStatement();
        statement.executeUpdate("DELETE FROM watchlist_movies");
    }

    public void addWatchlistMovie(WatchlistMovieEntity watchlistMovie) throws SQLException {
        MovieRepository movieRepository = new MovieRepository();
        MovieEntity movieEntity = movieRepository.findById(watchlistMovie.getId());

        // If the movie does not exist in the MovieEntity table, add it
        if (movieEntity == null) {
            movieEntity = new MovieEntity();
            // Set the properties of movieEntity from watchlistMovie
            movieRepository.addMovie(movieEntity);
        }
        PreparedStatement statement = getConnection().prepareStatement("INSERT INTO watchlist_movies (apiId) VALUES (?)");
        statement.setString(1, watchlistMovie.getApiId());
        statement.executeUpdate();
    }

}

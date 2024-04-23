package at.ac.fhcampuswien.fhmdb.database;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class MovieRepository {
    private Connection connection;
    private static long idCounter = 0;

    public MovieRepository() throws SQLException {
        try {
            Class.forName("org.h2.Driver");
            createTableIfNotExists();
            idCounter = getMaxId() + 1;
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
    }
    private long getMaxId() throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM watchlist_movies");
        return resultSet.next() ? resultSet.getLong(1) : 0;
    }
    public void createTableIfNotExists() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String createTableSQL = "CREATE TABLE IF NOT EXISTS MOVIES("
                + "ID INT PRIMARY KEY, "
                + "TITLE VARCHAR(255), "
                + "GENRES VARCHAR(255), "
                + "DESCRIPTION VARCHAR(255), "
                + "RELEASEYEAR INT, "
                + "RATING DOUBLE"
                + ")";
        Statement stmt = connection.createStatement();
        stmt.execute(createTableSQL);
    }
    private Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        }
        return connection;
    }

    public List<MovieEntity> getAllMovies() throws SQLException {
        List<MovieEntity> movies = new ArrayList<>();
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM movies");

        while (resultSet.next()) {
            MovieEntity movie = new MovieEntity();
            // Set movie properties from resultSet
            movies.add(movie);
        }

        return movies;
    }

    public void deleteMovie(long id) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("DELETE FROM movies WHERE id = ?");
        statement.setLong(1, id);
        statement.executeUpdate();
    }

    public void deleteAllMovies() throws SQLException {
        Statement statement = getConnection().createStatement();
        statement.executeUpdate("DELETE FROM movies");
    }

    public void addMovieToWatchlist(MovieEntity movieEntity) throws SQLException {
        long uniqueId = generateUniqueId(); // Stellen Sie sicher, dass Sie eine eindeutige ID generieren
        movieEntity.setId(uniqueId);
        // Überprüfen Sie, ob der Film bereits in der MovieEntity Tabelle existiert
        MovieEntity existingMovie = findById(movieEntity.getId());

        // Wenn der Film nicht existiert, fügen Sie ihn zur MovieEntity Tabelle hinzu
        if (existingMovie == null) {
            addMovie(movieEntity);
        }

        PreparedStatement statement = getConnection().prepareStatement("INSERT INTO watchlist_movies (apiId) VALUES (?)");

        statement.setString(1, movieEntity.getApiId());
        statement.executeUpdate();


    }
    private long generateUniqueId() {

            return idCounter++;
        }

    public void addMovie(MovieEntity movieEntity) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("INSERT INTO movies (id, apiId, title, genres, description, releaseYear, imgUrl, lengthInMinutes, rating) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

        statement.setString(2, movieEntity.getApiId());
        statement.setString(3, movieEntity.getTitle());
        statement.setString(4, movieEntity.getGenres());
        statement.setString(5, movieEntity.getDescription());
        statement.setInt(6, movieEntity.getReleaseYear());
        statement.setString(7, movieEntity.getImgUrl());

        statement.setInt(8, movieEntity.getLengthInMinutes());
        statement.setDouble(9, movieEntity.getRatingFrom());
        statement.executeUpdate();
    }

    public MovieEntity findById(long id) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM movies WHERE id = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            MovieEntity movie = new MovieEntity();
            movie.setId(resultSet.getLong("id"));
            movie.setApiId(resultSet.getString("apiId"));
            movie.setTitle(resultSet.getString("title"));
            movie.setGenres(resultSet.getString("genres"));
            movie.setDescription(resultSet.getString("description"));
            movie.setReleaseYear(resultSet.getInt("releaseYear"));
            movie.setImgUrl(resultSet.getString("imgUrl"));
            movie.setLengthInMinutes(resultSet.getInt("lengthInMinutes"));
            movie.setRatingFrom(resultSet.getDouble("rating"));
            return movie;
        } else {
            return null;
        }
    }
}

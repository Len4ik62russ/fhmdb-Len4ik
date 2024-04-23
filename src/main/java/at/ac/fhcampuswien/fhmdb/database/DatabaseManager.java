package at.ac.fhcampuswien.fhmdb.database;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private String DB_Url = "jdbc:h2:file: ./src/main/resources/movies.db";
    private String username;
    private String password;
    private ConnectionSource conn;
    private Dao<MovieEntity, Long> movieDao;
    private Dao<WatchlistMovieEntity, Long> watchlistDao;
    private static DatabaseManager instance;


    /*public DatabaseManager(String DB_Url, String username, String password) {
        this.DB_Url = DB_Url;
        this.username = username;
        this.password = password;
    }*/
    private DatabaseManager() {
        try {
            createConnectionSource();
            movieDao = DaoManager.createDao(conn, MovieEntity.class);
            watchlistDao = DaoManager.createDao(conn, WatchlistMovieEntity.class);
            createTables();

        } catch (SQLException e) {
            System.out.println("Error creating connection source: " + e.getMessage());
        }
    }
    public static DatabaseManager getDatabase() {
        if(instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    /*public void testDB() throws SQLException {
        MovieEntity movie = new MovieEntity();
        movieDao.create(movie);

    }*/
    public void createConnectionSource() throws SQLException {
        conn = new JdbcConnectionSource(DB_Url, username, password);
        watchlistDao = DaoManager.createDao(conn, WatchlistMovieEntity.class);
    }

    public void disconnect() throws Exception {
        if (conn != null) {
            conn.close();
        }
    }

    public Dao<WatchlistMovieEntity, Long> getWatchlistMovieEntityDao() {
        return watchlistDao;
    }
    public ConnectionSource getConnectionSource() {
        return conn;
    }
    public void createTables() throws SQLException {
        TableUtils.createTableIfNotExists(conn, MovieEntity.class);
        TableUtils.createTableIfNotExists(conn, WatchlistMovieEntity.class);
        WatchlistRepository watchlistRepository = new WatchlistRepository();
            try (Connection conn = DriverManager.getConnection(DB_Url, username, password);
                 Statement stmt = conn.createStatement()) {

                // SQL statement for creating a new table
                String createMoviesTableSQL = "CREATE TABLE IF NOT EXISTS movies("
                        + "id LONG PRIMARY KEY, "
                        + "apiId VARCHAR(255), "
                        + "title VARCHAR(255), "
                        + "genres VARCHAR(255), "
                        + "description VARCHAR(255), "
                        + "releaseYear INT, "
                        + "ratingFrom DOUBLE,"
                        + "imgUrl VARCHAR(255), "
                        + "lengthInMinutes INT"
                        + ")";
                stmt.execute(createMoviesTableSQL);
                String createWatchlistMoviesTableSQL = "CREATE TABLE IF NOT EXISTS watchlist_movies("
                        + "id LONG PRIMARY KEY, "
                        + "apiId VARCHAR(255) "



                        + ")";
                stmt.execute(createWatchlistMoviesTableSQL);
                String alterMoviesTableSQL = "ALTER TABLE movies ADD COLUMN IF NOT EXISTS apiId VARCHAR(255)";
                stmt.execute(alterMoviesTableSQL);



            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw e;
            }
        }


    /*public  WatchlistDao getWatchlistDao() {
        return new WatchlistDao(watchlistDao);
    }
    public MovieDao getMovieDao() {
        return new MovieDao(movieDao);
    }*/

}


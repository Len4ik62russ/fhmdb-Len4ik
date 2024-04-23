package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.database.DatabaseManager;
import at.ac.fhcampuswien.fhmdb.database.MovieEntity;
import at.ac.fhcampuswien.fhmdb.database.MovieRepository;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class MovieAPI {
    private static final String BASE_URL = "https://prog2.fh-campuswien.ac.at/movies";
    private static final String USER_AGENT = "User-Agent http.agent";
    private DatabaseManager dbManager;
    private MovieRepository movieRepository;

    private OkHttpClient client;
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(Movie.class, new Movie())
            .create();

    public MovieAPI() throws SQLException {
        this.client = new OkHttpClient();
        this.gson = new Gson();

        movieRepository = new MovieRepository();
    }

    public List<Movie> getMovies(String apiId, String imgUrl, int lengthInMinutes, String query, String description, String genres, int releaseYear, double ratingFrom) throws IOException, SQLException {

        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL).newBuilder();

        /*if (movieEntities.isEmpty()) {
            HttpUrl url = HttpUrl.parse(BASE_URL).newBuilder().build();*/


            if (query != null) {
                urlBuilder.addQueryParameter("query", query);
            }
            if (genres != null) {
                urlBuilder.addQueryParameter("genres", genres);
            }
            if (releaseYear != 0) {
                urlBuilder.addQueryParameter("releaseYear", String.valueOf(releaseYear));
            }
            if (ratingFrom != 0) {
                urlBuilder.addQueryParameter("ratingFrom", String.valueOf(ratingFrom));
            }
            HttpUrl url = urlBuilder.build();

            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("User-Agent", USER_AGENT)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Type listType = new TypeToken<List<Movie>>() {}.getType();
                List<Movie> movies = gson.fromJson(response.body().string(), listType);

                        //gson.fromJson(response.body().string(), listType);
                /*for (Movie movie : movies) {
                    MovieEntity movieEntity = new MovieEntity();
                    movieEntity.setApiId(movie.getId());
                    movieEntity.setTitle(movie.getTitle());
                    movieEntity.setGenres(movie.getGenre().stream().collect(Collectors.joining(", ")));
                    movieEntity.setDescription(movie.getDescription());
                    movieEntity.setReleaseYear(movie.getReleaseYear());
                    movieEntity.setImgUrl(movie.getImgUrl());
                    movieEntity.setLengthInMinutes(movie.getLengthInMinutes());
                    movieEntity.setRatingFrom(movie.getRating());

                    movieRepository.addMovie(movieEntity);
                    List<MovieEntity> movieEntities = movieRepository.getAllMovies();
                }*/
                return movies;
            /* } else {
            // Convert MovieEntity to Movie and return
            List<Movie> movies = new ArrayList<>();
            for (MovieEntity movieEntity : movieEntities) {
                Movie movie = new Movie();
                // Set movie properties from movieEntity
                movies.add(movie);
            }
            return movies;*/

        }
    }

    public Movie getMovieById(String id) throws IOException {
        HttpUrl url = HttpUrl.parse(BASE_URL + "/" + id).newBuilder().build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", USER_AGENT)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            return gson.fromJson(response.body().string(), Movie.class);
        }
    }
}


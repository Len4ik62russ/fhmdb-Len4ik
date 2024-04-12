package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class MovieAPI {
    private static final String BASE_URL = "https://prog2.fh-campuswien.ac.at/movies";
    private static final String USER_AGENT = "User-Agent http.agent";

    private OkHttpClient client;
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(Movie.class, new Movie())
            .create();

    public MovieAPI() {
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }

    public List<Movie> getMovies(String query, String description, String genres, int releaseYear, double ratingFrom, String directors, List<String> mainCast) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL).newBuilder();
        if (query != null) {
            urlBuilder.addQueryParameter("query", query);
        }
        if (genres != null) {
            urlBuilder.addQueryParameter("genres", genres);
        }
        if(releaseYear != 0) {
            urlBuilder.addQueryParameter("releaseYear", String.valueOf(releaseYear));
        }
        if(ratingFrom != 0) {
            urlBuilder.addQueryParameter("ratingFrom", String.valueOf(ratingFrom));
        }
        HttpUrl url = urlBuilder.build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", USER_AGENT)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Type listType = new TypeToken<List<Movie>>(){}.getType();

            return gson.fromJson(response.body().string(), listType);

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


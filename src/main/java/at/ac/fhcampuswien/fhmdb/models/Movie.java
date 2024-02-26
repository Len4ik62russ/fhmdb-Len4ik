package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;



    public void setGenre(String genre) {
        this.genre = genre;
    }

    // TODO add more properties here
    private String genre;



    public Movie(String title, String description, String genre) {
        this.title = title;
        this.description = description;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        // TODO add some dummy data here
        movies.add(new Movie("The Shawshank Redemption", "Two imprisoned", "Drama"));
        movies.add(new Movie("The Godfather", "The aging patriarch of an organized crime dynasty " +
                "transfers control of his clandestine empire to his reluctant son.", "Drama"));
        movies.add(new Movie("The Dark Knight", "When the menace known as the Joker wreaks havoc " +
                "and chaos on the people of Gotham", "Action"));
        movies.add(new Movie("The Lord of the Rings: The Return of the King", "Gandalf and Aragorn lead", "Fantasy"));
        movies.add(new Movie("Pulp Fiction", "The lives of two mob hitmen, a boxer, a gangster and his wife, " +
                "and a pair of diner bandits intertwine in four tales of violence and redemption.", "Crime"));
        movies.add(new Movie("Forrest Gump", "The presidencies of Kennedy and Johnson, the events of Vietnam, ", "Drama"));
        movies.add(new Movie("Inception", "A thief who steals corporate secrets through the use of dream-sharing technology", "Action"));
        movies.add(new Movie("The Matrix", "A computer hacker learns from mysterious rebels about" +
                "the true nature of his reality and his role in the war against its controllers.", "Action"));
        movies.add(new Movie("The Silence of the Lambs", "A young F.B.I. cadet must " +
                "receive the help of an incarcerated and manipulative cannibal killer", "Crime"));
        movies.add(new Movie("The Departed", "An undercover cop and a mole in the " +
                "police attempt to identify each other", "Crime"));
        movies.add(new Movie("The Prestige", "After a tragic accident, two stage magicians" +
                "engage in a battle to create the ultimate illusion", "Drama"));
        movies.add(new Movie("The Green Mile", "The lives of guards on Death" +
                "Row are affected by one of their charges", "Drama"));
        movies.add(new Movie("The Pianist", "A Polish Jewish musician struggles to survive" +
                "the destruction of the Warsaw ghetto of World War II", "Biography"));
        movies.add(new Movie("The Lion King", "Lion prince Simba and his father are" +
                "targeted by his bitter uncle", "Animation"));
        movies.add(new Movie("The Godfather: Part II", "The early life and" +
                " career of Vito Corleone in 1920s New York City", "Drama"));
        movies.add(new Movie("The Lord of the Rings: The Fellowship of the Ring", "A meek Hobbit " +
                "from the Shire and eight companions set out on a journey to destroy the powerful One Ring", "Fantasy"));
        movies.add(new Movie("The Lord of the Rings: The Two Towers", "While Frodo and Sam edge ", "Fantasy"));
        movies.add(new Movie("The Good, the Bad and the Ugly", "A bounty hunting", "Western"));
        movies.add(new Movie("The Shining", "A family heads to an isolated hotel " +
                "for the winter where a sinister presence influences the father into violence", "Drama"));
        movies.add(new Movie("The Usual Suspects", "A sole survivor tells of the twisty events ", "Crime"));
        movies.add(new Movie("The Terminator", "A human soldier is sent from 2029 to 1984 " +
                "to stop an almost indestructible cyborg", "Action"));
        movies.add(new Movie("The Sixth Sense", "A", "Drama"));
        movies.add(new Movie("The Thing", "A research team in Antarctica is hunted by a shape-shifting alien", "Horror"));
        movies.add(new Movie("The Big Lebowski", "Jeff 'The Dude' Leboswki is mistaken for Jeffrey Lebowski", "Comedy"));
        movies.add(new Movie("The Truman Show", "An insurance salesman discovers his whole " +
                "life is actually a reality TV show", "Comedy"));
        movies.add(new Movie("The Exorcist", "When a 12-year", "Horror"));












        return movies;
    }
}

package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        /*
        GitHubCopilot
        */
        movies.add(new Movie("The Shawshank Redemption", "Two imprisoned", "Drama"));
        movies.add(new Movie("The Godfather", "The aging patriarch of an organized crime dynasty " +
                "transfers control of his clandestine empire to his reluctant son.", "Drama"));

        movies.add(new Movie("The Lord of the Rings: The Return of the King", "Gandalf and Aragorn lead", "Fantasy"));
        movies.add(new Movie("Pulp Fiction", "The lives of two mob hitmen, a boxer, a gangster and his wife, " +
                "and a pair of diner bandits intertwine in four tales of violence and redemption.", "Crime"));
        movies.add(new Movie("Forrest Gump", "The presidencies of Kennedy and Johnson, the events of Vietnam, ", "Drama"));
        movies.add(new Movie("Inception", "A thief who steals corporate secrets through the use of dream-sharing technology", "Action"));
        movies.add(new Movie("The Matrix", "A computer hacker learns from mysterious rebels about" +
                "the true nature of his reality and his role in the war against its controllers.", "Action"));
        movies.add(new Movie("The Silence of the Lambs", "A young F.B.I. cadet must " +
                "receive the help of an incarcerated and manipulative cannibal killer", "Crime"));

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
        movies.add(new Movie("Misery", "After a famous author is rescued from a car crash by a fan of his novels", "Thriller"));
        movies.add(new Movie("Basquiat", "The brief life of Jean Michel Basquiat, a world renowned New York street artist", "Biography"));
        movies.add(new Movie("The Untouchables", "Federal Agent Eliot Ness sets out to stop Al Capone", "Crime"));
        movies.add(new Movie("The Blues Brothers", "Jake Blues, just out from prison, puts together his old band to save the Catholic home", "Comedy"));
        movies.add(new Movie("The Breakfast Club", "Five high school students meet in Saturday detention and discover how they have a lot more in common", "Comedy"));
        movies.add(new Movie("The Goonies", "A group of young misfits who call themselves The Goonies discover an ancient map", "Adventure"));
        movies.add(new Movie("Princess Mononoke", "On a journey to find the cure for a Tatarigami's curse", "Animation"));
        movies.add(new Movie("The Incredibles", "A family of undercover superheroes, while trying to live the quiet suburban life", "Animation"));
        movies.add(new Movie("The Platform", "A vertical prison with one cell per level", "Horror"));
        movies.add(new Movie("Vivarium", "A young couple looking for the perfect home find themselves trapped in a mysterious labyrinth-like neighborhood", "Horror"));
        movies.add(new Movie("Ex machina", "A young programmer is selected to participate in a ground-breaking experiment in synthetic intelligence", "Sci-Fi"));
        movies.add(new Movie("Interstellar", "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival", "Sci-Fi"));
        movies.add(new Movie("The Martian", "An astronaut becomes stranded on Mars after his team assume him dead", "Sci-Fi"));
        movies.add(new Movie("Into the Wild", "After graduating from Emory University, top student and athlete Christopher McCandless abandons his possessions", "Adventure"));
        movies.add(new Movie("The Revenant", "A frontiersman on a fur trading expedition in the 1820s fights for survival after being mauled by a bear", "Adventure"));
        movies.add(new Movie("The Grand Budapest Hotel", "A writer encounters the owner of an aging high-class hotel", "Comedy"));
        movies.add(new Movie("The Hangover", "Three buddies wake up from a bachelor party in Las Vegas", "Comedy"));
        movies.add(new Movie("The Wolf of Wall Street", "Based on the true story of Jordan Belfort", "Biography"));
        movies.add(new Movie("The Social Network", "As Harvard student Mark Zuckerberg creates the social networking site", "Biography"));
        movies.add(new Movie("The Pursuit of Happyness", "A struggling salesman takes custody of his son as he's poised to begin a life-changing professional career", "Biography"));
        movies.add(new Movie("The Intouchables", "After he becomes a quadriplegic from a paragliding accident", "Biography"));
        movies.add(new Movie("The Help", "An aspiring author during the civil rights movement of the 1960s", "Drama"));
        movies.add(new Movie("The King's Speech", "The story of King George VI, his impromptu ascension to the throne", "Biography"));
        movies.add(new Movie("The Theory of Everything", "A look at the relationship between the famous physicist Stephen Hawking and his wife", "Biography"));
        movies.add(new Movie("The Imitation Game", "During World War II, the English mathematical genius Alan Turing tries to crack the German Enigma code", "Biography"));
        movies.add(new Movie("The Great Gatsby", "A writer and wall street trader, Nick, finds himself drawn to the past and lifestyle of his millionaire neighbor", "Drama"));
        movies.add(new Movie("The Fault in Our Stars", "Two teenage cancer patients begin a life-affirming journey to visit a reclusive author in Amsterdam", "Drama"));
        movies.add(new Movie("The Perks of Being a Wallflower", "An introvert freshman is taken under the wings of two seniors who welcome him to the real world", "Drama"));
        movies.add(new Movie("Enter the void", "A U.S. drug dealer living in Tokyo is betrayed by his best friend and killed in a drug deal", "Drama"));
        movies.add(new Movie("The Neon Demon", "An aspiring model, Jesse, is new to Los Angeles", "Horror"));
        movies.add(new Movie("The Witch", "A family in 1630s New England is torn apart by the forces of witchcraft", "Horror"));
        movies.add(new Movie("The Babadook", "A single mother and her child fall into a deep well of paranoia when an eerie children's book titled", "Horror"));
        movies.add(new Movie("The Conjuring", "Paranormal investigators Ed and Lorraine Warren work to help a family terrorized by a dark presence in their farmhouse", "Horror"));
        movies.add(new Movie("The Stand", "After a deadly plague kills most of the world's population", "Horror"));
        movies.add(new Movie("The Mist", "A freak storm unleashes a species of bloodthirsty creatures on a small town", "Horror"));
        movies.add(new Movie("The Gangs of New York", "In 1862, Amsterdam Vallon returns to the Five Points area of New York City seeking revenge against Bill the Butcher", "Crime"));
        movies.add(new Movie("The Departed", "An undercover cop and a mole in the police attempt to identify each other while infiltrating an Irish gang in South Boston", "Crime"));
        movies.add(new Movie("The Town", "As he plans his next job, a longtime thief tries to balance his feelings for a bank manager connected to one of his earlier heists", "Crime"));
        movies.add(new Movie("The Drop", "Bob Saginowski finds himself at the center of a robbery gone awry and entwined in an investigation that digs deep into the neighborhood's past", "Crime"));
        movies.add(new Movie("American Syndicate", "The story of the rise and fall of the infamous Chicago gangster Al Capone and the control he exhibited over the city during the prohibition years", "Crime"));
        movies.add(new Movie("Don't Breathe", "Hoping to walk away with a massive fortune", "Crime"));
        movies.add(new Movie("The Purge", "A wealthy family is held hostage for harboring the target of a murderous syndicate during the Purge", "Crime"));
        movies.add(new Movie("The War of the Worlds", "As Earth is invaded by alien tripod fighting machines", "Action"));
        movies.add(new Movie("The Day After Tomorrow", "Jack Hall, paleoclimatologist, must make a daring trek from Washington, D.C. to New York City", "Action"));
        movies.add(new Movie("The Core", "The only way to save Earth from catastrophe is to drill down to the core and set it spinning again", "Action"));
        movies.add(new Movie("The Day the Earth Stood Still", "A remake of the 1951 classic science fiction film about an alien visitor and his giant robot counterpart", "Sci-Fi"));
        movies.add(new Movie("The War Game", "The history of warfare as it relates to global Black society", "Documentary"));
        movies.add(new Movie("The Fog of War", "The story of America as seen through the eyes of the former Secretary of Defense under President John F. Kennedy and President Lyndon B. Johnson", "Documentary"));
        movies.add(new Movie("The Act of Killing", "A documentary that challenges former Indonesian death squad leaders to reenact their real-life mass-killings in whichever cinematic genres they wish", "Documentary"));
        movies.add(new Movie("Roger & Me", "Director Michael Moore pursues GM CEO Roger B. Smith to confront him about the harm he did to Flint", "Documentary"));
        movies.add(new Movie("The Corporation", "Documentary that looks at the concept of the corporation throughout recent history up to its present-day dominance", "Documentary"));
        movies.add(new Movie("The Cove", "Using state-of-the-art equipment, a group of activists, led by renowned dolphin trainer Ric O'Barry", "Documentary"));
        movies.add(new Movie("Man on Wire", "A look at tightrope walker Philippe Petit's daring", "Documentary"));
        movies.add(new Movie("Salesman", "Four relentless door-to-door salesmen deal with constant rejection", "Documentary"));
        movies.add(new Movie("Grizzly Man", "A devastating and heartrending take on grizzly bear activists Timothy Treadwell and Amie Huguenard", "Documentary"));
        movies.add(new Movie("Django Unchained", "With the help of a German bounty hunter", "Western"));
        movies.add(new Movie("The Hateful Eight", "In the dead of a Wyoming winter", "Western"));
        movies.add(new Movie("The Magnificent Seven", "Seven gunmen from a variety of backgrounds are brought together", "Western"));
        movies.add(new Movie("Unforgiven", "Retired Old West gunslinger William Munny reluctantly takes on one last job", "Western"));
        movies.add(new Movie("Rio Bravo", "A small-town sheriff in the American West enlists the help of a cripple", "Western"));
        movies.add(new Movie("The Wild Bunch", "An aging group of outlaws look for one last big score", "Western"));
        movies.add(new Movie("The Searchers", "An American Civil War veteran embarks on a journey to rescue his niece from the Comanches", "Western"));
        movies.add(new Movie("Dunkirk", "Allied soldiers from Belgium", "War"));
        movies.add(new Movie("The Thin Red Line", "Adaptation of James Jones' autobiographical 1962 novel", "War"));
        movies.add(new Movie("The Deer Hunter", "An in-depth examination of the ways in which the U.S. Vietnam War impacts and disrupts the lives of people in a small industrial town in Pennsylvania", "War"));
        movies.add(new Movie("The Hurt Locker", "During the Iraq War", "War"));
        movies.add(new Movie("The Bridge on the River Kwai", "After settling his differences with a Japanese PoW camp commander", "War"));
        movies.add(new Movie("The Great Escape", "Allied prisoners of war plan for several hundred of their number to escape from a German camp during World War II", "War"));
        movies.add(new Movie("The Longest Day", "The events of D-Day", "War"));
        movies.add(new Movie("War and Peace", "Napoleon's tumultuous relations with Russia including his disastrous 1812 invasion", "War"));
        movies.add(new Movie("The Battle of Algiers", "In the 1950s", "War"));
        movies.add(new Movie("The Battle of Britain", "In 1940", "War"));
        movies.add(new Movie("The Battle of Midway", "A dramatization of the battle that was widely heralded as a turning point of the Pacific Theatre of World War II", "War"));
        movies.add(new Movie("The Battle of Stalingrad", "A depiction of the brutal battle of Stalingrad", "War"));
        movies.add(new Movie("The Dark Knight Rises", "Eight years after the Joker's reign of anarchy", "Action"));
        movies.add(new Movie("The Dark Knight", "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham", "Action"));
        movies.add(new Movie("The Dark Crystal", "On another planet in the distant past", "Adventure"));
        movies.add(new Movie("The Wizard of Oz", "Dorothy Gale is swept away from a farm in Kansas to a magical land of Oz", "Adventure"));
        movies.add(new Movie("Spirit: Stallion of the Cimarron", "A wild stallion is captured by humans and slowly loses the will to resist training", "Animation"));
        movies.add(new Movie("Spirited Away", "During her family's move to the suburbs", "Animation"));
        movies.add(new Movie("The Secret World of Arrietty", "The Clock family are four-inch-tall people who live anonymously in another family's residence", "Animation"));
        movies.add(new Movie("The Secret Life of Pets", "The quiet life of a terrier named Max is upended when his owner takes in Duke", "Animation"));
        movies.add(new Movie("The Secret Life of Walter Mitty", "When both he and a colleague are about to lose their job", "Adventure"));
        movies.add(new Movie("Alice in Wonderland", "Nineteen-year-old Alice returns to the magical world from her childhood adventure", "Fantasy"));
        movies.add(new Movie("The Chronicles of Narnia: The Lion, the Witch and the Wardrobe", "Four kids travel through a wardrobe to the land of Narnia and learn of their destiny to free it with the guidance of a mystical lion", "Fantasy"));
        movies.add(new Movie("Willow", "A reluctant dwarf must play a critical role in protecting a special baby from an evil queen", "Fantasy"));
        movies.add(new Movie("Ladyhawk", "Philipe Gastone, a thief, escapes from the dungeon at Aquila", "Fantasy"));
        movies.add(new Movie("Gone Girl", "With his wife's disappearance having become the focus of an intense media circus", "Drama"));
        movies.add(new Movie("Psycho", "A Phoenix secretary embezzles $40,000 from her employer's client", "Thriller"));
        movies.add(new Movie("The Birds", "A wealthy San Francisco socialite pursues a potential boyfriend to a small Northern California town", "Horror"));
        movies.add(new Movie("Rear Window", "A wheelchair-bound photographer spies on his neighbors from his apartment window", "Mystery"));
        movies.add(new Movie("Nowhere to Run", "Escaped convict Sam Gillen single-handedly takes on ruthless developers", "Thriller"));
        movies.add(new Movie("The Fugitive", "Dr. Richard Kimble, unjustly accused of murdering his wife", "Thriller"));
        movies.add(new Movie("The Negotiator", "In a desperate attempt to prove his innocence", "Thriller"));
        movies.add(new Movie("The Game", "After a wealthy banker is given an opportunity to participate in a mysterious game", "Thriller"));
        movies.add(new Movie("The Bone Collector", "A quadriplegic ex-homicide detective and his partner try to track down a serial killer", "Thriller"));
        movies.add(new Movie("The Pelican Brief", "A law student uncovers a conspiracy", "Thriller"));
        movies.add(new Movie("Deep Blue Sea", "Searching for a cure to Alzheimer's disease", "Thriller"));
        movies.add(new Movie("The Perfect Storm", "An unusually intense storm pattern catches some commercial fishermen unaware", "Adventure"));
        movies.add(new Movie("The Poseidon Adventure", "A group of passengers struggle to survive and escape", "Adventure"));
        movies.add(new Movie("The Towering Inferno", "At the opening party of a colossal", "Adventure"));
        movies.add(new Movie("The Greatest Showman", "Get into your comfy clothes and bring out the popcorn because this family-friendly musical will keep everyone entertained", "Family"));
        movies.add(new Movie("E.T. The Extra-Terrestrial", "Steven Spielberg's classic sci-fi story of an extraterrestrial stranded on planet Earth is pure movie magic.", "Family"));
/*
End of GitHubCopilot
 */




















        return movies;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) &&
                Objects.equals(genre, movie.genre) &&
                Objects.equals(description, movie.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genre, description);
    }



}

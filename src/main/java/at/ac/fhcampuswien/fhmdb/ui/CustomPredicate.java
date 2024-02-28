package at.ac.fhcampuswien.fhmdb.ui;

import java.util.function.Predicate;

public class CustomPredicate implements Predicate<String> {

        @Override
        public boolean test(String item) {
            // Hier kannst du deine Filterlogik implementieren
            // Beispiel: Nur Elemente, die mit dem Buchstaben 'A' beginnen, zulassen
            return item.startsWith("A");
        }
}

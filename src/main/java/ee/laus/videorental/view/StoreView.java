package ee.laus.videorental.view;

import ee.laus.videorental.entity.inventory.Inventory;
import ee.laus.videorental.entity.movie.Movie;
import ee.laus.videorental.entity.movie.MovieMatcher;
import ee.laus.videorental.entity.movie.MovieType;
import ee.laus.videorental.entity.movie.RentableMovie;
import ee.laus.videorental.io.output.InventoryLogger;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StoreView implements View {
    private final Inventory inventory;

    public void rent(MovieMatcher matcher, int days) {
        RentableMovie movie = inventory.rent(matcher, days);
        try {
            InventoryLogger.printRented(movie.getTitle(), days);
        } catch (IllegalArgumentException ex) {
            InventoryLogger.printNotFound(movie.getTitle());
        }
    }

    public void remove(Movie movie) {
        try {
            inventory.remove(movie);
            InventoryLogger.printChanged(movie);
        } catch (IllegalArgumentException ex) {
            InventoryLogger.printNotFound(movie.getTitle());
        }
    }

    public void save(RentableMovie movie, int amount) {
        this.save(movie, movie.getType(), amount);
    }

    public void save(Movie movie, MovieType type, int amount) {
        RentableMovie rentableMovie = inventory.save(movie, type, amount);

        if (rentableMovie.isChanged()) {
            InventoryLogger.printChanged(rentableMovie, amount);
        } else {
            InventoryLogger.printAdded(rentableMovie, amount);
        }
    }

}

package ee.laus.videorental.io.output;

import ee.laus.videorental.entity.inventory.Inventory;
import ee.laus.videorental.entity.inventory.InventoryItem;
import ee.laus.videorental.entity.movie.Movie;
import ee.laus.videorental.entity.movie.RentableMovie;

import java.util.Collection;

public class InventoryLogger {
    public static void printAll(Inventory inventory) {
        System.out.println("All movies in inventory:");
        printItems(inventory.getItems());
    }

    public static void printAllAvailable(Inventory inventory) {
        System.out.println("Available movies in inventory:");
        printItems(inventory.getAvailableItems());
    }

    public static void printNotFound(String title) {
        System.out.printf("Requested movie with title %s not found.%n",
                title);
    }

    public static void printRented(String title, int days) {
        System.out.printf("Requested movie %s successfully rented for %d days.%n",
                title,
                days);
    }

    public static void printAdded(RentableMovie movie, int amount) {
        System.out.printf("Movie %s with type %s with amount %d was successfully added to inventory.%n",
                movie.getTitle(),
                movie.getType().toString(),
                amount);
    }

    public static void printChanged(RentableMovie movie, int amount) {
        System.out.printf("Movie %s with type %s with amount %d was successfully changed.%n",
                movie.getTitle(),
                movie.getType().toString(),
                amount);
    }

    public static void printChanged(Movie movie) {
        System.out.printf("Movie %s was successfully removed.%n",
                movie.getTitle());
    }

    private static void printItems(Collection<InventoryItem> items) {
        items.forEach(item -> System.out.println(item.toString()));
    }
}

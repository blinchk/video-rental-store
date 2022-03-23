package ee.laus.videorental.util;

import ee.laus.videorental.entity.inventory.InventoryItem;
import ee.laus.videorental.entity.movie.Movie;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class InventoryItemUtil {
    public static Optional<InventoryItem> findFirst(Collection<InventoryItem> items, Movie movie) {
        return items.stream().filter(item -> Objects.equals(item.getMovie().getTitle(), movie.getTitle())).findFirst();
    }

    public static boolean anyMatch(Collection<InventoryItem> items, Movie movie) {
        return items.stream().anyMatch(item -> Objects.equals(item.getMovie().getTitle(), movie.getTitle()));
    }
}

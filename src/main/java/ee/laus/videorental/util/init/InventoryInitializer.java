package ee.laus.videorental.util.init;

import ee.laus.videorental.entity.inventory.Inventory;
import ee.laus.videorental.entity.inventory.InventoryItem;
import ee.laus.videorental.entity.movie.RentableMovie;

import java.util.Collection;

public class InventoryInitializer {
    public static Inventory create() {
        Collection<RentableMovie> movies = MovieCollectionInitializer.create();
        return new Inventory(movies.stream().map(InventoryItem::new).toList());
    }
}

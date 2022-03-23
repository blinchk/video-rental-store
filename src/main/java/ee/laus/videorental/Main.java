package ee.laus.videorental;

import ee.laus.videorental.entity.inventory.Inventory;
import ee.laus.videorental.entity.movie.MovieMatcher;
import ee.laus.videorental.io.output.RentLogger;
import ee.laus.videorental.io.output.ViewLogger;
import ee.laus.videorental.util.init.InventoryInitializer;
import ee.laus.videorental.view.StoreView;
import ee.laus.videorental.view.View;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = InventoryInitializer.create();
        View view = new StoreView(inventory);
        ViewLogger.printAll(view);
        ViewLogger.printAllAvailable(view);
        MovieMatcher matcher = new MovieMatcher("Green Book");
        view.rent(matcher, 5);
        RentLogger.printTotal(view);
    }
}

package ee.laus.videorental.view;

import ee.laus.videorental.entity.inventory.Inventory;
import ee.laus.videorental.entity.movie.Movie;
import ee.laus.videorental.entity.movie.MovieMatcher;
import ee.laus.videorental.entity.movie.MovieType;

public interface View {
    void rent(MovieMatcher matcher, int days);
    void save(Movie movie, MovieType type, int amount);
    Inventory getInventory();
}

package ee.laus.videorental.entity.inventory;

import ee.laus.videorental.entity.movie.Movie;
import ee.laus.videorental.entity.movie.MovieMatcher;
import ee.laus.videorental.entity.movie.MovieType;
import ee.laus.videorental.entity.movie.RentableMovie;
import ee.laus.videorental.entity.rent.Rental;
import ee.laus.videorental.util.InventoryItemUtil;
import ee.laus.videorental.util.summary.InventorySummary;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Inventory {
    private List<InventoryItem> items;
    private List<Rental> rentals;
    private double bonusPoints = 0;

    public Inventory() {
        this(new ArrayList<>());
    }

    public Inventory(List<InventoryItem> items) {
        this(items, new ArrayList<>());
    }

    public Inventory(List<InventoryItem> items, List<Rental> rentals) {
        this.items = items;
        this.rentals = rentals;
    }

    public List<InventoryItem> getAvailableItems() {
        return items.stream().peek(item -> {
            var rented = rentals.stream().takeWhile(rental -> rental.getMovie() == item.getMovie()).count();
            item.setAmount(item.getAmount() - (int) rented);
        }).filter(item -> item.getAmount() > 0).toList();
    }

    public InventorySummary buildSummary() {
        return new InventorySummary(this);
    }

    public RentableMovie save(RentableMovie movie, int amount) {
        return this.save(movie, movie.getType(), amount);
    }

    public RentableMovie save(Movie movie, MovieType type, int amount) {
        boolean exists = containsMovie(movie);
        if (exists) {
            var found = InventoryItemUtil.findFirst(items, movie);
            if (found.isPresent()) amount += found.get().getAmount();
            submitRemove(movie);
        }
        RentableMovie rentableMovie = new RentableMovie(movie.getTitle(), type);
        if (exists) rentableMovie.setChanged(true);
        InventoryItem item = new InventoryItem(rentableMovie, amount);
        items.add(item);
        return rentableMovie;
    }

    public void remove(Movie movie) {
        MovieMatcher matcher = MovieMatcher.of(movie);
        submitRemove(matcher);
    }

    public RentableMovie rent(MovieMatcher movie, int days) {
        InventoryItem available = findAvailableMovie(movie);
        submitRent(available.getMovie(), days);
        return available.getMovie();
    }

    private boolean containsMovie(Movie movie) {
        return containsMovie(movie, false);
    }

    private boolean containsAvailableMovie(Movie movie) {
        return containsMovie(movie, true);
    }

    private boolean containsMovie(Movie movie, boolean onlyAvailable) {
        return InventoryItemUtil.anyMatch(onlyAvailable ? getAvailableItems() : getItems(), movie);
    }

    private InventoryItem findAvailableMovie(Movie movie) {
        boolean isAvailable = containsAvailableMovie(movie);
        var found = InventoryItemUtil.findFirst(items, movie);
        if (isAvailable && found.isPresent()) {
            return found.get();
        } else {
            throw new IllegalArgumentException("Available movie with title " + movie.getTitle() + " is not found");
        }
    }

    private void submitRemove(Movie movie) {
        if (containsMovie(movie)) {
            items = items.stream().dropWhile(item -> item.getMovie().getTitle().equals(movie.getTitle())).collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("Movie with title " + movie.getTitle() + " is not found");
        }

    }

    private void submitRent(RentableMovie movie, int days) {
        Rental rental = new Rental(movie, days);
        rentals.add(rental);
    }
}

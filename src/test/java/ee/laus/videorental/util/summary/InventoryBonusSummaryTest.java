package ee.laus.videorental.util.summary;

import ee.laus.videorental.entity.inventory.Inventory;
import ee.laus.videorental.entity.movie.Movie;
import ee.laus.videorental.entity.movie.MovieMatcher;
import ee.laus.videorental.entity.movie.MovieType;
import ee.laus.videorental.entity.movie.RentableMovie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InventoryBonusSummaryTest {
    private Inventory inventory;
    private InventorySummary summary;

    private final RentableMovie oldMovie = new RentableMovie("Out of africa", MovieType.OLD);
    private final RentableMovie regularMovie = new RentableMovie("Spider man", MovieType.REGULAR);
    private final RentableMovie newReleaseMovie = new RentableMovie("Matrix 11", MovieType.NEW_RELEASE);;

    @BeforeEach
    void beforeEach() {
        inventory = new Inventory();
        inventory.save(oldMovie, 1);
        inventory.save(regularMovie, 1);
        inventory.save(newReleaseMovie, 1);
        summary = new InventorySummary(inventory);
    }

    @Test
    void getBonusPoints_oldMovie_Test() {
        inventory.rent(MovieMatcher.of(oldMovie), 5);
        final int expected = 1;
        final int actual = summary.getBonusSummary().getBonusPoints();
        assertEquals(expected, actual);
    }

    @Test
    void getBonusPoints_regularMovie_Test() {
        inventory.rent(MovieMatcher.of(regularMovie), 5);
        final int expected = 1;
        final int actual = summary.getBonusSummary().getBonusPoints();
        assertEquals(expected, actual);
    }

    @Test
    void getBonusPoints_newReleaseMovie_Test() {
        inventory.rent(MovieMatcher.of(newReleaseMovie), 5);
        final int expected = 2;
        final int actual = summary.getBonusSummary().getBonusPoints();
        assertEquals(expected, actual);
    }

    @Test
    void getFreeDays_noFreeDays_Test() {
        inventory.rent(MovieMatcher.of(oldMovie), 5);
        inventory.rent(MovieMatcher.of(regularMovie), 5);
        inventory.rent(MovieMatcher.of(newReleaseMovie), 5);
        final int expected = 0;
        final int actual = summary.getBonusSummary().getFreeDays();
        assertEquals(expected, actual);
    }

    @Test
    void getFreeDays_oneFreeDay_Test() {
        inventory.save(newReleaseMovie, 12);
        rentMultiply(newReleaseMovie, 5, 13);
        final int expected = 1;
        final int actual = summary.getBonusSummary().getFreeDays();
        assertEquals(expected, actual);
    }

    void rentMultiply(Movie movie, int days,  int times) {
        for (int i = 0; i < times; i++) {
            inventory.rent(MovieMatcher.of(movie), days);
        }
    }
}
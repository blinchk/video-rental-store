package ee.laus.videorental.entity.rental;

import ee.laus.videorental.entity.movie.MovieType;
import ee.laus.videorental.entity.movie.RentableMovie;
import ee.laus.videorental.entity.rent.Rental;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OldMovieRentalTest {
    private final RentableMovie movie = new RentableMovie("Out of africa", MovieType.OLD);

    @Test
    void toString_oldMovieRental_Test() {
        final String expected = "Out of africa (Old movie) 5+5 days 18,00 EUR (extra days fee +13,00 EUR included)";
        final int days = 10;
        Rental rental = new Rental(movie, days);
        assertEquals(expected, rental.toString());
    }

    @Test
    void getPrice_oldMovie1Days_Test() {
        final double expectedPrice = 3;
        final int days = 1;
        Rental rental = new Rental(movie, days);
        assertEquals(expectedPrice, rental.getPrice());
    }

    @Test
    void getPrice_oldMovie5Days_Test() {
        final double expectedPrice = 3;
        final int days = 5;
        Rental rental = new Rental(movie, days);
        assertEquals(expectedPrice, rental.getPrice());
    }

    @Test
    void getPrice_oldMovie10Days_Test() {
        final double expectedPrice = 18;
        final int days = 10;
        Rental rental = new Rental(movie, days);
        assertEquals(expectedPrice, rental.getPrice());
    }
}

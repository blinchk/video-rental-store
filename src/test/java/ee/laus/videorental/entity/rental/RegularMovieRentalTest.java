package ee.laus.videorental.entity.rental;

import ee.laus.videorental.entity.movie.MovieType;
import ee.laus.videorental.entity.movie.RentableMovie;
import ee.laus.videorental.entity.rent.Rental;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegularMovieRentalTest {
    private final RentableMovie movie = new RentableMovie("Spider man", MovieType.REGULAR);

    @Test
    void toString_regularRental_Test() {
        final String expected = "Spider man (Regular movie) 3+7 days 24,00 EUR (extra days fee +21,00 EUR included)";
        final int days = 10;
        Rental rental = new Rental(movie, days);
        assertEquals(expected, rental.toString());
    }

    @Test
    void getPrice_regularFilm1Day_Test() {
        final double expectedPrice = 3;
        final int days = 1;
        Rental rental = new Rental(movie, days);
        assertEquals(expectedPrice, rental.getPrice());
    }


    @Test
    void getPrice_regularFilm3Days_Test() {
        final double expectedPrice = 3;
        final int days = 3;
        Rental rental = new Rental(movie, days);
        assertEquals(expectedPrice, rental.getPrice());
    }

    @Test
    void getPrice_regularFilm5Days_Test() {
        final double expectedPrice = 9;
        final int days = 5;
        Rental rental = new Rental(movie, days);
        assertEquals(expectedPrice, rental.getPrice());
    }
}

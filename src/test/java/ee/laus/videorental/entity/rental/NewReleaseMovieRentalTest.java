package ee.laus.videorental.entity.rental;

import ee.laus.videorental.entity.movie.MovieType;
import ee.laus.videorental.entity.movie.RentableMovie;
import ee.laus.videorental.entity.rent.Rental;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewReleaseMovieRentalTest {
    private final RentableMovie movie = new RentableMovie("Matrix 11", MovieType.NEW_RELEASE);;

    @Test
    void getPrice_newRelease3Days_Test() {
        final double expectedPrice = 12;
        final int days = 3;
        Rental rental = new Rental(movie, days);
        assertEquals(expectedPrice, rental.getPrice());
    }


    @Test
    void toString_newReleaseRental_Test() {
        final String expected = "Matrix 11 (New release) 10 days 40.0 EUR";
        final int days = 10;
        Rental rental = new Rental(movie, days);
        assertEquals(expected, rental.toString());
    }
}

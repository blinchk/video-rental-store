package ee.laus.videorental.util.init;

import ee.laus.videorental.entity.movie.MovieType;
import ee.laus.videorental.entity.movie.RentableMovie;

import java.util.Collection;
import java.util.List;

public class MovieCollectionInitializer {
    public static Collection<RentableMovie> create() {
        return List.of(
                new RentableMovie("The Green Mile", MovieType.OLD),
                new RentableMovie("The Shawshank Redemption", MovieType.OLD),
                new RentableMovie("Schindler's List", MovieType.OLD),
                new RentableMovie("Intouchables", MovieType.REGULAR),
                new RentableMovie("Interstellar", MovieType.REGULAR),
                new RentableMovie("Green Book", MovieType.REGULAR),
                new RentableMovie("Spider-Man: No Way Home", MovieType.NEW_RELEASE),
                new RentableMovie("Dune", MovieType.NEW_RELEASE),
                new RentableMovie("Nomadland", MovieType.NEW_RELEASE)
        );
    }
}

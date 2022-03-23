package ee.laus.videorental.entity.movie;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class MovieMatcher implements Movie {
    private String title;

    public static MovieMatcher of(Movie movie) {
        return new MovieMatcher(movie.getTitle());
    }
}

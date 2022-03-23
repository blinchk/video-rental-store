package ee.laus.videorental.entity.movie;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import static ee.laus.videorental.util.BonusPointUtil.NEW_RELEASE_POINTS;
import static ee.laus.videorental.util.BonusPointUtil.REGULAR_POINTS;

@Getter
@Setter
@EqualsAndHashCode
public class RentableMovie implements Movie {
    private final String title;
    private MovieType type;
    private boolean changed = false;

    public RentableMovie(String title, MovieType type) {
        this.title = title;
        this.type = type;
    }

    public int getBonusPoints() {
        return type == MovieType.NEW_RELEASE ? NEW_RELEASE_POINTS : REGULAR_POINTS;
    }
}

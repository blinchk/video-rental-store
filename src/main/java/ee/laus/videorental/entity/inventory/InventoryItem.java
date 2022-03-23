package ee.laus.videorental.entity.inventory;

import ee.laus.videorental.entity.movie.RentableMovie;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class InventoryItem {
    private RentableMovie movie;
    private int amount = 1;

    public InventoryItem(RentableMovie movie) {
        this.movie = movie;
    }

    public static InventoryItem of(RentableMovie movie) {
        return new InventoryItem(movie);
    }

    @Override
    public String toString() {
        return "- %s (%d) | %s".formatted(movie.getTitle(), amount, movie.getType().toString());
    }
}

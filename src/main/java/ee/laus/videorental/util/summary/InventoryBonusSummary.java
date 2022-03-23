package ee.laus.videorental.util.summary;


import ee.laus.videorental.entity.inventory.Inventory;
import ee.laus.videorental.entity.movie.MovieType;
import ee.laus.videorental.entity.rent.Rental;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

import static ee.laus.videorental.util.BonusPointUtil.NEW_RELEASE_PRICE;

@AllArgsConstructor
public class InventoryBonusSummary {
    private final Inventory inventory;

    private Stream<Rental> getStream() {
        return inventory.getRentals().stream();
    }

    public int getBonusPoints() {
        return getStream().mapToInt(Rental::getBonusPoints).sum();
    }

    public int getFreeDays() {
        int points = getBonusPoints();
        if (points > 25) {
            int required = getMaximumRequiredBonusPointsCount();
            int freeDays = points / NEW_RELEASE_PRICE;
            return Math.min(freeDays, required);
        }
        return 0;
    }

    private int getMaximumRequiredBonusPointsCount() {
        return (int) getStream().filter(rental -> rental.getMovie().getType() == MovieType.NEW_RELEASE).count() * NEW_RELEASE_PRICE;
    }
}

package ee.laus.videorental.util.summary;

import ee.laus.videorental.entity.inventory.Inventory;
import ee.laus.videorental.entity.rent.Rental;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class InventoryRentSummary extends AbstractInventorySummary {
    public InventoryRentSummary(Inventory inventory) {
        super(inventory);
    }

    private Stream<Rental> getStream() {
        return getInventory().getRentals().stream();
    }

    public double getTotal() {
        return getStream().flatMapToDouble(rental -> DoubleStream.of(rental.getPrice())).sum();
    }

    public int getExtraDaysTotal() {
        return getStream().flatMapToInt(rental -> IntStream.of(rental.getExtraDays())).sum();
    }

    public double getExtraDaysFeeTotal() {
        return getStream().flatMapToDouble(rental -> DoubleStream.of(rental.getExtraDaysFee())).sum();
    }

    public int getFlatPriceDaysTotal() {
        return getStream().flatMapToInt(rental -> IntStream.of(rental.getFlatPriceDays())).sum();
    }

    public double getFlatPriceDaysFeeTotal() {
        return getTotal() - getExtraDaysFeeTotal();
    }
}

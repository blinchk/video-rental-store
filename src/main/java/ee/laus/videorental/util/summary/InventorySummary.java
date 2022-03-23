package ee.laus.videorental.util.summary;

import ee.laus.videorental.entity.inventory.Inventory;
import lombok.Getter;

@Getter
public class InventorySummary extends AbstractInventorySummary {
    private final InventoryBonusSummary bonusSummary;
    private final InventoryRentSummary rentSummary;

    public InventorySummary(Inventory inventory) {
        super(inventory);
        this.bonusSummary = new InventoryBonusSummary(inventory);
        this.rentSummary = new InventoryRentSummary(inventory);
    }
}

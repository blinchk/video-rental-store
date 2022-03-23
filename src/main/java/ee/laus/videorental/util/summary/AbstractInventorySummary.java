package ee.laus.videorental.util.summary;

import ee.laus.videorental.entity.inventory.Inventory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class AbstractInventorySummary {
    private final Inventory inventory;
}

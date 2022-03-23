package ee.laus.videorental.entity.rent;

import ee.laus.videorental.util.PriceUtil;
import ee.laus.videorental.util.summary.InventorySummary;
import lombok.Getter;

@Getter
public class RentReceipt {
    private final int freeDays;
    private final double discount;
    private final double total;

    public RentReceipt(InventorySummary summary) {
        this.freeDays = summary.getBonusSummary().getFreeDays();
        this.discount = freeDays * PriceUtil.PREMIUM_PRICE;
        this.total = summary.getRentSummary().getTotal() - discount;
    }
}

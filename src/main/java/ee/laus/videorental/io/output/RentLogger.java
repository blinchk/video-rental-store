package ee.laus.videorental.io.output;

import ee.laus.videorental.entity.inventory.Inventory;
import ee.laus.videorental.entity.rent.RentReceipt;
import ee.laus.videorental.util.summary.InventorySummary;
import ee.laus.videorental.view.View;

public class RentLogger {
    public static void printTotal(Inventory inventory) {
        InventorySummary summary = inventory.buildSummary();
        RentReceipt receipt = new RentReceipt(summary);
        System.out.println("------ RENTALS ------");
        inventory.getRentals().forEach(System.out::println);
        System.out.println("------ SUMMARY ------");
        System.out.printf("Submitted %d bonus points%n",
                summary.getBonusSummary().getBonusPoints());
        System.out.printf("Flat price for %d days is %.2f EUR%n",
                summary.getRentSummary().getFlatPriceDaysTotal(),
                summary.getRentSummary().getFlatPriceDaysFeeTotal());
        System.out.printf("Extra fee for %d days is %.2f EUR%n",
                summary.getRentSummary().getExtraDaysTotal(),
                summary.getRentSummary().getExtraDaysFeeTotal());
        if (receipt.getDiscount() > 0) System.out.printf("DISCOUNT: %.2f EUR (%d free days) for new releases%n",
                receipt.getDiscount(),
                receipt.getFreeDays());
        System.out.printf("SUBTOTAL: %.2f EUR%n", receipt.getTotal());
    }

    public static void printTotal(View view) {
        RentLogger.printTotal(view.getInventory());
    }
}

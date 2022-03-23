package ee.laus.videorental.io.output;

import ee.laus.videorental.view.View;

public class ViewLogger {
    public static void printAll(View view) {
        InventoryLogger.printAll(view.getInventory());
    }

    public static void printAllAvailable(View view) {
        InventoryLogger.printAllAvailable(view.getInventory());
    }
}

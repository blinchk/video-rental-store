package ee.laus.videorental.entity.rent;

public interface CalculablePrice {
    double calculate(int days);
    int getFlatPriceDays();
}

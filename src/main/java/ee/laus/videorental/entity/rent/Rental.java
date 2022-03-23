package ee.laus.videorental.entity.rent;

import ee.laus.videorental.entity.movie.RentableMovie;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Rental {
    private RentableMovie movie;
    private int days;

    public double getPrice() {
        return movie.getType().calculate(days);
    }

    public boolean hasExtraDays() { return days > getFlatPriceDays() && getFlatPriceDays() > 0; }
    public int getFlatPriceDays() { return movie.getType().getFlatPriceDays(); }
    public int getExtraDays() { return days - getFlatPriceDays(); }
    public double getExtraDaysFee() { return getPrice() - getFlatPriceDays(); }
    public int getBonusPoints() { return movie.getBonusPoints(); }

    @Override
    public String toString() {
        if (hasExtraDays()) {
            return "%s (%s) %d+%d days %.2f EUR (extra days fee +%.2f EUR included)".formatted(movie.getTitle(), movie.getType(), getFlatPriceDays(), getExtraDays(), getPrice(), getExtraDaysFee());
        } else {
            return "%s (%s) %d days %s EUR".formatted(movie.getTitle(), movie.getType(), days, getPrice());
        }
    }
}

package ee.laus.videorental.entity.movie;

import ee.laus.videorental.entity.rent.CalculablePrice;

import static ee.laus.videorental.util.PriceUtil.*;

public enum MovieType implements CalculablePrice {
    NEW_RELEASE {
        private final double price = PREMIUM_PRICE;

        @Override
        public String toString() {
            return "New release";
        }

        @Override
        public double calculate(int days) {
            return price * days;
        }

        @Override
        public int getFlatPriceDays() {
            return 0;
        }

    },
    REGULAR {
        private final int flatPriceDays = REGULAR_FILM_FLAT_PRICE_DAYS;
        private final double price = BASIC_PRICE;

        @Override
        public String toString() {
            return "Regular movie";
        }

        @Override
        public double calculate(int days) {
            if (days > flatPriceDays) {
                double extraDaysTotal = (days - flatPriceDays) * price;
                return BASIC_PRICE + extraDaysTotal;
            }
            return BASIC_PRICE;
        }

        @Override
        public int getFlatPriceDays() {
            return flatPriceDays;
        }

    },
    OLD {
        private final int flatPriceDays = OLD_FILM_FLAT_PRICE_DAYS;
        private final double price = BASIC_PRICE;

        @Override
        public String toString() {
            return "Old movie";
        }

        @Override
        public double calculate(int days) {
            if (days > flatPriceDays) {
                double extraDaysTotal = (days - flatPriceDays) * price;
                return price + extraDaysTotal;
            }
            return price;
        }

        @Override
        public int getFlatPriceDays() {
            return flatPriceDays;
        }

    }
}

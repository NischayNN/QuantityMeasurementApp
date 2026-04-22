public class QuantityMeasurementApp {

    // ================== ENUM ==================
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETER(0.0328084);

        private final double toFeetFactor;

        LengthUnit(double factor) {
            this.toFeetFactor = factor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double valueInFeet) {
            return valueInFeet / toFeetFactor;
        }
    }

    // ================== GENERIC CLASS ==================
    static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid number");

            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toFeet(value);
        }

        // INSTANCE METHOD (convert this object)
        public QuantityLength convertTo(LengthUnit targetUnit) {
            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double feetValue = this.toFeet();
            double converted = targetUnit.fromFeet(feetValue);

            return new QuantityLength(converted, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // ================== STATIC API ==================

    public static double convert(double value,
                                 LengthUnit source,
                                 LengthUnit target) {

        if (source == null || target == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid number");

        double valueInFeet = source.toFeet(value);
        return target.fromFeet(valueInFeet);
    }

    // ================== DEMO METHODS ==================

    public static void demonstrateLengthConversion(double value,
                                                   LengthUnit from,
                                                   LengthUnit to) {

        double result = convert(value, from, to);
        System.out.println("Convert " + value + " " + from + " to " + to + " = " + result);
    }

    // OVERLOADED METHOD
    public static void demonstrateLengthConversion(QuantityLength q,
                                                   LengthUnit to) {

        QuantityLength result = q.convertTo(to);
        System.out.println("Convert " + q + " to " + to + " = " + result);
    }

    public static void demonstrateLengthEquality(QuantityLength q1,
                                                 QuantityLength q2) {

        System.out.println(q1 + " equals " + q2 + " ? " + q1.equals(q2));
    }

    // ================== MAIN ==================

    public static void main(String[] args) {

        // BASIC CONVERSIONS
        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCH);
        demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET);
        demonstrateLengthConversion(36.0, LengthUnit.INCH, LengthUnit.YARDS);
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH);

        // INSTANCE METHOD
        QuantityLength q = new QuantityLength(2.0, LengthUnit.YARDS);
        demonstrateLengthConversion(q, LengthUnit.FEET);

        // EQUALITY
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        demonstrateLengthEquality(a, b);
    }
}
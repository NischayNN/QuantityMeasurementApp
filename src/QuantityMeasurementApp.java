public class QuantityMeasurementApp {

    // ================== ENUM FOR ALL UNITS ==================
    enum LengthUnit {

        FEET(1.0),                  // base unit
        INCH(1.0 / 12.0),           // 1 inch = 1/12 feet
        YARDS(3.0),                 // 1 yard = 3 feet
        CENTIMETER(0.0328084);      // 1 cm = 0.0328084 feet

        private final double toFeetFactor;

        LengthUnit(double factor) {
            this.toFeetFactor = factor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    // ================== GENERIC CLASS ==================
    static class QuantityLength {

        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        // Convert to base unit (feet)
        private double toFeet() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {

            // Same reference
            if (this == obj) return true;

            // Null or different type
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            // Compare after conversion
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }
    }

    // ================== MAIN METHOD ==================
    public static void main(String[] args) {

        // UC1 & UC2 style checks
        QuantityLength f1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength f2 = new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength i1 = new QuantityLength(12.0, LengthUnit.INCH);

        // UC3 cross-unit
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);

        // UC4 new units
        QuantityLength y1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength y2 = new QuantityLength(3.0, LengthUnit.FEET);

        QuantityLength c1 = new QuantityLength(1.0, LengthUnit.CENTIMETER);
        QuantityLength c2 = new QuantityLength(0.393701, LengthUnit.INCH);

        // OUTPUTS
        System.out.println("Feet vs Feet: " + f1.equals(f2));          // true
        System.out.println("Feet vs Inch: " + q1.equals(q2));          // true
        System.out.println("Yard vs Feet: " + y1.equals(y2));          // true
        System.out.println("CM vs Inch: " + c1.equals(c2));            // true
        System.out.println("Feet vs Inch (12 in): " + f1.equals(i1));  // true
    }
}
@Test
void testYardToFeet() {
    var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
            QuantityMeasurementApp.LengthUnit.YARDS);
    var q2 = new QuantityMeasurementApp.QuantityLength(3.0,
            QuantityMeasurementApp.LengthUnit.FEET);

    assertTrue(q1.equals(q2));
}

@Test
void testYardToInches() {
    var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
            QuantityMeasurementApp.LengthUnit.YARDS);
    var q2 = new QuantityMeasurementApp.QuantityLength(36.0,
            QuantityMeasurementApp.LengthUnit.INCH);

    assertTrue(q1.equals(q2));
}

@Test
void testCentimeterToInch() {
    var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
            QuantityMeasurementApp.LengthUnit.CENTIMETER);
    var q2 = new QuantityMeasurementApp.QuantityLength(0.393701,
            QuantityMeasurementApp.LengthUnit.INCH);

    assertTrue(q1.equals(q2));
}

@Test
void testDifferentUnitsNotEqual() {
    var q1 = new QuantityMeasurementApp.QuantityLength(1.0,
            QuantityMeasurementApp.LengthUnit.CENTIMETER);
    var q2 = new QuantityMeasurementApp.QuantityLength(1.0,
            QuantityMeasurementApp.LengthUnit.FEET);

    assertFalse(q1.equals(q2));
}
public enum CurrencyValue {
    DOLLAR(1.00),
    EURO(1.06),
    FRANC(0.90),
    PESO(0.05),
    POUND(1.20),
    RUBE(0.013);
    final double value;
    CurrencyValue(double value) {
        this.value = value;
    }
    public double getValue() {
        return this.value;
    }
}

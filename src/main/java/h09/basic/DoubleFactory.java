package h09.basic;

public class DoubleFactory implements BasicFactory<Double> {
    private final double step;
    private double current;

    public DoubleFactory(double start, double step) {
        this.step = step;
        current = start;
    }

    @Override
    public Double create() {
        double tmp = current;
        current += step;
        return tmp;
    }
}
// a different solution would be to initialise current with start - step and then return current += step

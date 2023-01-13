package h09.basic;

public class DoubleBasicBinaryOperations implements BasicBinaryOperations<Double, Double> {

    public Double add(Double x1, Double x2) {
        return x1 + x2;
    }

    @Override
    public Double mul(Double x, Double y) {
        return x * y;
    }
}

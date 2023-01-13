package h09.basic;

public class IntegerBasicBinaryOperations implements BasicBinaryOperations<Integer, Integer> {

    public Integer add(Integer x1, Integer x2) {
        return x1 + x2;
    }

    @Override
    public Integer mul(Integer x, Integer y) {
        return x * y;
    }
}

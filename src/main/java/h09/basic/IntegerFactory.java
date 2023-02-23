package h09.basic;

public class IntegerFactory implements BasicFactory<Integer> {
    private final int step;
    private int current;

    public IntegerFactory(int start, int step) {
        this.step = step;
        current = start;
    }

    @Override
    public Integer create() {
        int tmp = current;
        current += step;
        return tmp;
    }
}
// a different solution would be to initialise current with start - step and then return current += step

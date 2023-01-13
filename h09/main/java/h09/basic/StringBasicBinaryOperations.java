package h09.basic;

public class StringBasicBinaryOperations implements BasicBinaryOperations<String, Integer> {
    @Override
    public String add(String x1, String x2) {
        // x1.concat(x2) would also be a valid solution here
        return x1 + x2;
    }

    @Override
    public String mul(String x, Integer y) {
        // it doesn't get much simpler than this.
        return x.repeat(Math.max(y,0));
    }
}
/* here is a different solution that doesn't use String or Math methods:
if (y <= 0) {
    return "";
} else {
    String result = "";
    for (int i = 0; i < y; i++) {
        result += x;
    }
    return result;
}
 */

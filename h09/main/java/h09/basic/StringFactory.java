package h09.basic;

public class StringFactory implements BasicFactory<String> {
    private final String[] text;
    private int current;

    public StringFactory(int start, String[] text) {
        this.text = text;
        current = start;
    }

    @Override
    public String create() {
        // set current to 0 if it is out of bounds, do nothing otherwise
        current = current >= text.length ? 0 : current;
        // increment current by one after returning the current String
        return text[current++];
    }
}

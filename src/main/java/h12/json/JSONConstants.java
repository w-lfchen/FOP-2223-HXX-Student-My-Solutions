package h12.json;

/**
 * An enum containing all constants a JSON file can contain and their spelling.
 */
public enum JSONConstants {

    TRUE("true"),
    FALSE("false"),
    NULL("null");

    public final String name;

    JSONConstants(String name) {
        this.name = name;
    }

    /**
     * return the correct spelling for this constant in a JSON file.
     *
     * @return the spelling of this constant.
     */
    public String getSpelling() {
        return name;
    }

    @Override
    public String toString() {
        return "JSONConstants{" +
            "name='" + name + '\'' +
            '}';
    }
}

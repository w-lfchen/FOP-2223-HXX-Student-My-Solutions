package h12.gui.shapes;

import h12.gui.components.ContentPanel;

/**
 * An enum containing all types of {@link MyShape} a {@link ContentPanel} can contain and their spelling used in a JSON file.
 */
public enum ShapeType {
    RECTANGLE("rectangle"),
    CIRCLE("circle"),
    TRIANGLE("triangle"),
    CUSTOM_LINE("custom_line"),
    POLYGON("polygon"),
    STRAIGHT_LINE("straight_line");

    private final String name;

    ShapeType(String name) {
        this.name = name;
    }

    /**
     * returns the correct spelling of this {@link ShapeType}.
     *
     * @return the spelling of this {@link ShapeType}.
     */
    public String getSpelling() {
        return name;
    }

    /**
     * The {@link ShapeType} whose spelling is equal to the given spelling.
     *
     * @param spelling The spelling to look for.
     * @return the corresponding {@link ShapeType} or null if there is no such {@link ShapeType}.
     */
    public static ShapeType fromString(String spelling) {
        for (ShapeType shape : ShapeType.values()) {
            if (shape.name.equals(spelling)) {
                return shape;
            }
        }
        return null;
    }
}

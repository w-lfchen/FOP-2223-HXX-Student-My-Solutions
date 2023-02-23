package h12.gui.shapes;

import h12.json.*;
import h12.json.implementation.node.JSONArrayNode;
import h12.json.implementation.node.JSONNumberNode;
import h12.json.implementation.node.JSONObjectNode;
import h12.json.implementation.node.JSONStringNode;

import java.awt.*;
import java.util.List;
import java.util.Objects;

/**
 * A class representing a custom line.
 *
 * @see MyShape
 */
public class CustomLine extends MyShape {

    public static final ShapeType TYPE = ShapeType.CUSTOM_LINE;

    private final List<Integer> x;
    private final List<Integer> y;

    /**
     * Creates a new {@link CustomLine}-Instance.
     *
     * @param x     A list containing all x-coordinates.
     * @param y     A list containing all y-coordinates.
     * @param color The {@link Color} of the line.
     */
    public CustomLine(List<Integer> x, List<Integer> y, Color color) {
        this.x = x;
        this.y = y;
        this.borderColor = color;
    }

    /**
     * Converts this {@link CustomLine} to a {@link JSONObjectNode}. The {@link JSONObjectNode} contains the following entries:
     * <p> name: The {@link ShapeType} as a {@link JSONStringNode}.
     * <p> x: The x-coordinates of the line as a {@link JSONArrayNode} of {@link JSONNumberNode}.
     * <p> y: The y-coordinates of the line as a {@link JSONArrayNode} of {@link JSONNumberNode}.
     * <p> borderColor: The color used to draw the line as a {@link JSONArrayNode}.
     *
     * @return A {@link JSONObjectNode} containing the entries listed above.
     * @see ColorHelper#toJSON(Color)
     * @see ShapeType#getSpelling()
     */
    @Override
    public JSONObject toJSON() {
        return JSONObject.of(
            JSONObject.JSONObjectEntry.of("name", JSONString.of(TYPE.getSpelling())),
            JSONObject.JSONObjectEntry.of("x", JSONArray.of(x.stream().map(JSONNumber::of).toArray(JSONElement[]::new))),
            JSONObject.JSONObjectEntry.of("y", JSONArray.of(y.stream().map(JSONNumber::of).toArray(JSONElement[]::new))),
            JSONObject.JSONObjectEntry.of("borderColor", ColorHelper.toJSON(borderColor))
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param g2d The {@link Graphics2D} object used to draw this {@link MyShape} object.
     */
    @Override
    public void draw(Graphics2D g2d) {

        g2d.setColor(borderColor);
        g2d.drawPolyline(x.stream().mapToInt(i -> i).toArray(), y.stream().mapToInt(i -> i).toArray(), x.size());

    }

    /**
     * {@inheritDoc}
     *
     * @param x     The new x-coordinate.
     * @param y     The new y-coordinate.
     * @param phase The current creation phase.
     */
    @Override
    public void update(int x, int y, int phase) {
        this.x.add(x);
        this.y.add(y);
    }

    /**
     * {@inheritDoc}
     *
     * @param x     The new x-coordinate.
     * @param y     The new y-coordinate.
     * @param phase The next creation phase.
     * @return
     */
    @Override
    public boolean nextPhase(int x, int y, int phase) {
        update(x, y, phase);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomLine that = (CustomLine) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

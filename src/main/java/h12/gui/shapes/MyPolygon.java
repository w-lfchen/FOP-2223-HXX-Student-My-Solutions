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
 * A class representing a polygon.
 *
 * @see MyShape
 */
public class MyPolygon extends MyShape {

    public static final ShapeType TYPE = ShapeType.POLYGON;

    private static int defaultEdgeAmount = 5;

    protected final List<Integer> x;
    protected final List<Integer> y;

    private final int edges;

    /**
     * Creates a new {@link MyPolygon}-Instance.
     *
     * @param x           The x-coordinates of the edges. Does not have to contain all coordinates.
     * @param y           The y-coordinates of the edges. Does not have to contain all coordinates.
     * @param fillColor   The {@link Color} used to fill the polygon.
     * @param borderColor The {@link Color} used to draw the border of the polygon.
     * @param edges       The amount of edges this polygon has.
     */
    public MyPolygon(List<Integer> x, List<Integer> y, Color fillColor, Color borderColor, int edges) {
        this.x = x;
        this.y = y;
        this.borderColor = borderColor;
        this.fillColor = fillColor;
        this.edges = edges;
    }

    /**
     * Creates a new {@link MyPolygon}-Instance. The amount of edges will default to {@link MyPolygon#defaultEdgeAmount}.
     *
     * @param x           The x-coordinates of the edges. Does not have to contain all coordinates.
     * @param y           The y-coordinates of the edges. Does not have to contain all coordinates.
     * @param fillColor   The {@link Color} used to fill the polygon.
     * @param borderColor The {@link Color} used to draw the border of the polygon.
     */
    public MyPolygon(List<Integer> x, List<Integer> y, Color fillColor, Color borderColor) {
        this(x, y, fillColor, borderColor, defaultEdgeAmount);
    }

    /**
     * Converts this {@link MyPolygon} to a {@link JSONObjectNode}. The {@link JSONObjectNode} contains the following entries:
     * <p> name: The {@link ShapeType} as a {@link JSONStringNode}.
     * <p> edges: The amount of edges as a {@link JSONNumberNode}.
     * <p> x: The x-coordinates of the polygon as a {@link JSONArrayNode} of {@link JSONNumberNode}.
     * <p> y: The y-coordinates of the polygon as a {@link JSONArrayNode} of {@link JSONNumberNode}.
     * <p> fillColor: The color used to fill the polygon as a {@link JSONArrayNode}.
     * <p> borderColor: The color used to draw the border of the polygon as a {@link JSONArrayNode}.
     *
     * @return A {@link JSONObjectNode} containing the entries listed above.
     * @see ColorHelper#toJSON(Color)
     * @see ShapeType#getSpelling()
     */
    @Override
    public JSONObject toJSON() {
        return JSONObject.of(
            // use the respective methods to get the entries
            JSONObject.JSONObjectEntry.of("name", JSONString.of(TYPE.getSpelling())),
            JSONObject.JSONObjectEntry.of("edges", JSONNumber.of(edges)),
            // stream, create new NumberNodes from the entries of the stream, then collect them in a new array, out of which an arrayNode is then made
            JSONObject.JSONObjectEntry.of("x", JSONArray.of(x.stream().map(JSONNumberNode::new).toArray(JSONElement[]::new))),
            JSONObject.JSONObjectEntry.of("y", JSONArray.of(y.stream().map(JSONNumberNode::new).toArray(JSONElement[]::new))),
            JSONObject.JSONObjectEntry.of("fillColor", ColorHelper.toJSON(fillColor)),
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
        Polygon polygon = new Polygon(x.stream().mapToInt(i -> i).toArray(), y.stream().mapToInt(i -> i).toArray(), x.size());

        g2d.setColor(fillColor);
        g2d.fillPolygon(polygon);

        g2d.setColor(borderColor);
        g2d.drawPolygon(polygon);

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
        if (this.x.size() > phase) {
            replaceLastX(x);
            replaceLastY(y);
        } else {
            this.x.add(x);
            this.y.add(y);
        }
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
        this.x.add(x);
        this.y.add(y);
        return phase >= edges;
    }

    /**
     * Sets the default edge amount used for creating new {@link MyPolygon} objects.
     *
     * @param defaultEdgeAmount The new default edge amount.
     */
    public static void setDefaultEdgeAmount(int defaultEdgeAmount) {
        MyPolygon.defaultEdgeAmount = defaultEdgeAmount;
    }

    private void replaceLastX(int newX) {
        if (x.size() == 0) x.add(newX);
        else x.set(x.size() - 1, newX);
    }

    private void replaceLastY(int newY) {
        if (y.size() == 0) y.add(newY);
        else y.set(y.size() - 1, newY);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyPolygon myPolygon = (MyPolygon) o;
        return edges == myPolygon.edges && Objects.equals(x, myPolygon.x) && Objects.equals(y, myPolygon.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, edges);
    }
}

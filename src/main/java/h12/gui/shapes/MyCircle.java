package h12.gui.shapes;

import h12.json.JSONNumber;
import h12.json.JSONObject;
import h12.json.JSONString;
import h12.json.implementation.node.JSONArrayNode;
import h12.json.implementation.node.JSONNumberNode;
import h12.json.implementation.node.JSONObjectNode;
import h12.json.implementation.node.JSONStringNode;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Objects;

import static h12.json.JSONObject.JSONObjectEntry;

/**
 * A class representing a circle.
 *
 * @see MyShape
 */
public class MyCircle extends MyShape {

    public static final ShapeType TYPE = ShapeType.CIRCLE;

    private int radius;
    private final int x;
    private final int y;

    /**
     * Creates a new {@link MyCircle}-Instance.
     *
     * @param x           The x-coordinate of the center.
     * @param y           The y-coordinate of the center.
     * @param radius      The radius of the circle.
     * @param fillColor   The {@link Color} used to fill the circle.
     * @param borderColor The {@link Color} used to draw the border of the circle.
     */
    public MyCircle(int x, int y, int radius, Color fillColor, Color borderColor) {
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.borderColor = borderColor;
        this.fillColor = fillColor;
    }

    /**
     * Converts this {@link MyCircle} to a {@link JSONObjectNode}. The {@link JSONObjectNode} contains the following entries:
     * <p> name: The {@link ShapeType} as a {@link JSONStringNode}.
     * <p> x: The x-coordinate of the center as a {@link JSONNumberNode}.
     * <p> y: The y-coordinate of the center as a {@link JSONNumberNode}.
     * <p> radius: The radius as a {@link JSONNumberNode}.
     * <p> fillColor: The color used to fill the circle as a {@link JSONArrayNode}.
     * <p> borderColor: The color used to draw the border of the circle as a {@link JSONArrayNode}.
     *
     * @return A {@link JSONObjectNode} containing the entries listed above.
     * @see ColorHelper#toJSON(Color)
     * @see ShapeType#getSpelling()
     */
    @Override
    public JSONObject toJSON() {
        return JSONObject.of(
            JSONObjectEntry.of("name", JSONString.of(TYPE.getSpelling())),
            JSONObjectEntry.of("x", JSONNumber.of(x)),
            JSONObjectEntry.of("y", JSONNumber.of(y)),
            JSONObjectEntry.of("radius", JSONNumber.of(radius)),
            JSONObjectEntry.of("fillColor", ColorHelper.toJSON(fillColor)),
            JSONObjectEntry.of("borderColor", ColorHelper.toJSON(borderColor))
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param g2d The {@link Graphics2D} object used to draw this {@link MyShape} object.
     */
    @Override
    public void draw(Graphics2D g2d) {
        Shape circle = new Ellipse2D.Double(x - radius, y - radius, 2.0 * radius, 2.0 * radius);
        g2d.setColor(fillColor);
        g2d.fill(circle);
        g2d.setColor(borderColor);
        g2d.draw(circle);
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
        radius = (int) Math.sqrt(Math.pow(Math.abs(this.x - x), 2) + Math.pow(Math.abs(this.y - y), 2));
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
        MyCircle circle = (MyCircle) o;
        return radius == circle.radius && x == circle.x && y == circle.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius, x, y);
    }
}

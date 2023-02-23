package h12.gui.shapes;

import h12.json.JSONNumber;
import h12.json.JSONObject;
import h12.json.JSONString;
import h12.json.implementation.node.JSONArrayNode;
import h12.json.implementation.node.JSONNumberNode;
import h12.json.implementation.node.JSONObjectNode;
import h12.json.implementation.node.JSONStringNode;

import java.awt.*;
import java.util.Objects;

/**
 * A class representing a rectangle.
 *
 * @see MyShape
 */
public class MyRectangle extends MyShape {

    public static final ShapeType TYPE = ShapeType.RECTANGLE;

    private final int x;
    private final int y;
    private int height;
    private int width;

    /**
     * Creates a new {@link MyRectangle}-Instance.
     *
     * @param x           The x-coordinate of the upper left corner.
     * @param y           The y-coordinate of the upper left corner.
     * @param height      the height of the rectangle.
     * @param width       the width of the rectangle
     * @param fillColor   The {@link Color} used to fill the rectangle.
     * @param borderColor The {@link Color} used to draw the border of the rectangle.
     */
    public MyRectangle(int x, int y, int height, int width, Color fillColor, Color borderColor) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    /**
     * Converts this {@link MyRectangle} to a {@link JSONObjectNode}. The {@link JSONObjectNode} contains the following entries:
     * <p> name: The {@link ShapeType} as a {@link JSONStringNode}.
     * <p> x: The x-coordinate of the upper left corner as a {@link JSONNumberNode}.
     * <p> y: The y-coordinate of the upper left corner as a {@link JSONNumberNode}.
     * <p> width: The width of the rectangle as a {@link JSONNumberNode}.
     * <p> height: The height of the rectangle as a {@link JSONNumberNode}.
     * <p> fillColor: The color used to fill the rectangle as a {@link JSONArrayNode}.
     * <p> borderColor: The color used to draw the border of the rectangle as a {@link JSONArrayNode}.
     *
     * @return A {@link JSONObjectNode} containing the entries listed above.
     * @see ColorHelper#toJSON(Color)
     * @see ShapeType#getSpelling()
     */
    @Override
    public JSONObject toJSON() {
        return JSONObject.of(
            JSONObject.JSONObjectEntry.of("name", JSONString.of(TYPE.getSpelling())),
            JSONObject.JSONObjectEntry.of("x", JSONNumber.of(x)),
            JSONObject.JSONObjectEntry.of("y", JSONNumber.of(y)),
            JSONObject.JSONObjectEntry.of("height", JSONNumber.of(height)),
            JSONObject.JSONObjectEntry.of("width", JSONNumber.of(width)),
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

        int actualX = x;
        int actualY = y;
        int actualWidth = width;
        int actualHeight = height;

        if (width < 0) {
            actualWidth = -width;
            actualX -= actualWidth;
        }

        if (height < 0) {
            actualHeight = -height;
            actualY -= actualHeight;
        }

        g2d.setColor(fillColor);
        g2d.fillRect(actualX, actualY, actualWidth, actualHeight);
        g2d.setColor(borderColor);
        g2d.drawRect(actualX, actualY, actualWidth, actualHeight);
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
        width = x - this.x;
        height = y - this.y;
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
        MyRectangle that = (MyRectangle) o;
        return x == that.x && y == that.y && height == that.height && width == that.width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, height, width);
    }
}

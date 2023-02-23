package h12.gui.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a triangle.
 *
 * @see MyShape
 */
public class MyTriangle extends MyPolygon {

    /**
     * Creates a new {@link MyTriangle}-Instance.
     *
     * @param ax          The x-coordinate of the first corner.
     * @param ay          The y-coordinate of the first corner.
     * @param bx          The x-coordinate of the second corner.
     * @param by          The y-coordinate of the second corner.
     * @param cx          The x-coordinate of the third corner.
     * @param cy          The y-coordinate of the third corner.
     * @param fillColor   The {@link Color} used to fill the {@link MyTriangle}.
     * @param borderColor The {@link Color} used to draw the border of the triangle.
     */
    public MyTriangle(int ax, int ay, int bx, int by, int cx, int cy, Color fillColor, Color borderColor) {
        super(new ArrayList<>(List.of(ax, bx, cx)), new ArrayList<>(List.of(ay, by, cy)), fillColor, borderColor, 3);
    }

}

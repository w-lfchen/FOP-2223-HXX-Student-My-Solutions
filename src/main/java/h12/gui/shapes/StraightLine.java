package h12.gui.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a straight line.
 *
 * @see MyShape
 */
public class StraightLine extends MyPolygon {

    /**
     * Creates a new {@link StraightLine}-Instance.
     *
     * @param ax    The x-coordinate of the first end of the line.
     * @param ay    The y-coordinate of the first end of the line.
     * @param bx    The x-coordinate of the second end of the line.
     * @param by    The y-coordinate of the second end of the line.
     * @param color The color of the line.
     */
    public StraightLine(int ax, int ay, int bx, int by, Color color) {
        super(new ArrayList<>(List.of(ax, bx)), new ArrayList<>(List.of(ay, by)), Color.WHITE, color, 2);
    }
}

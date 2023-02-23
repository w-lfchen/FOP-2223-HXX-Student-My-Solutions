package h12.gui;

import h12.gui.shapes.MyShape;
import h12.gui.shapes.ShapeType;

import java.awt.*;

/**
 * A class for handling the current creation process of an {@link MyShape} object.
 */
public class Interaction {

    private ShapeType type;
    private MyShape shape;
    private Color fillColor = Color.WHITE;
    private Color borderColor = Color.BLACK;
    private boolean running = false;
    private int phase = 1;

    /**
     * Creates a new {@link Interaction}-Instance
     */
    public Interaction() {
    }

    /**
     * Sets the {@link ShapeType} of the next {@link MyShape} to create.
     *
     * @param type The next {@link ShapeType}.
     */
    public void setType(ShapeType type) {
        if (!running) {
            this.type = type;
        }
    }

    /**
     * Starts the creation process by creating the drawn {@link MyShape} of type {@link ShapeType}.
     *
     * @param x The start x-coordinate.
     * @param y The start y-coordinate.
     * @return The created {@link MyShape}.
     */
    public MyShape start(int x, int y) {
        if (!running) {
            running = true;
            phase = 1;

            return shape = MyShape.of(type, x, y, fillColor, borderColor);
        }

        return null;
    }

    /**
     * Updates the current position of the object at the current creation phase.
     *
     * @param x The new x-coordinate.
     * @param y The new y-coordinate.
     */
    public void update(int x, int y) {
        if (running) {
            shape.update(x, y, phase);
        }
    }

    /**
     * Continues with the next creation phase at the given position.
     * <p> Stops the current creation process if the created {@link MyShape} is finished.
     *
     * @param x The new x-coordinate.
     * @param y The new y-coordinate.
     */
    public void nextPhase(int x, int y) {
        if (running) {
            phase++;
            if (shape.nextPhase(x, y, phase)) {
                stop();
            }
        }
    }

    /**
     * Stops the current creation process.
     */
    public void stop() {
        running = false;
        shape = null;
    }

    /**
     * Checks if the type for the next creation is set.
     *
     * @return {@code true} if the type for the next creation is set.
     */
    public boolean isTypeSet() {
        return type != null;
    }

    /**
     * Checks if a creation process is currently running.
     *
     * @return {@code true} if a creation process is currently running.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Sets the Color the created {@link MyShape} should be filled with.
     *
     * @param fillColor The {@link Color} to fill the created {@link MyShape} with.
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
        if (shape != null) shape.setFillColor(fillColor);
    }

    /**
     * Sets the Color the border of the created {@link MyShape} should be drawn with.
     *
     * @param borderColor The {@link Color} to draw the border the created {@link MyShape} with.
     */
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        if (shape != null) shape.setBorderColor(borderColor);
    }

}

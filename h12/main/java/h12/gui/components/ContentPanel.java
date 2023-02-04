package h12.gui.components;

import h12.gui.Interaction;
import h12.gui.shapes.MyShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

/**
 * The drawing panel of the painting program.
 */
public class ContentPanel extends JPanel {

    private final MainFrame mf;
    private final List<MyShape> shapes = new ArrayList<>();

    private Shape coordinates;

    /**
     * Creates a new {@link ContentPanel}-Instance.
     *
     * @param mf The {@link MainFrame} the created {@link ContentPanel} belongs to.
     */
    public ContentPanel(MainFrame mf) {

        this.mf = mf;

        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEtchedBorder());

        addMouseListener(new MouseInteractionHandler());
        addMouseMotionListener(new MouseMotionHandler());

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                coordinates = createTextShape("x: --  y: --");
                ContentPanel.this.revalidate();
                ContentPanel.this.repaint();
            }
        });

        coordinates = createTextShape("x: --  y: --");
    }

    /**
     * Renders all {@link MyShape} objects on this {@link ContentPanel} using {@link RenderingHints#KEY_ANTIALIASING}
     * and {@link RenderingHints#VALUE_ANTIALIAS_ON}.
     *
     * @param g The {@link Graphics} object used to render the {@link MyShape} objects.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (MyShape shape : shapes) {
            shape.draw(g2d);
        }

        g2d.setColor(Color.black);
        g2d.fill(coordinates);

    }

    /**
     * Removes the last shape that was added to this {@link ContentPanel}.
     */
    public void removeLastShape() {
        if (shapes.size() == 0) return;
        shapes.remove(shapes.size() - 1);

        revalidate();
        repaint();
    }

    /**
     * Adds a new {@link MyShape} to this {@link ContentPanel}.
     *
     * @param shape The {@link MyShape} to add.
     */
    public void addShape(MyShape shape) {
        shapes.add(shape);

        revalidate();
        repaint();
    }

    /**
     * Adds all given {@link MyShape} objects to this {@link ContentPanel}.
     *
     * @param shapes A {@link List} containing all {@link MyShape} objects to add.
     */
    public void addAll(List<MyShape> shapes) {
        this.shapes.addAll(shapes);

        revalidate();
        repaint();
    }

    /**
     * Removes all {@link MyShape} objects from this {@link ContentPanel} and resets the background color to {@link Color#WHITE}.
     */
    public void clear() {
        setBackground(Color.WHITE);
        shapes.clear();
        revalidate();
        repaint();
    }

    /**
     * Returns the {@link MainFrame} this {@link ContentPanel} belongs to.
     *
     * @return The {@link MainFrame} this {@link ContentPanel} belongs to.
     */
    public MainFrame getMainFrame() {
        return mf;
    }

    /**
     * Returns the contents of this {@link ContentPanel}.
     *
     * @return The contents of this {@link ContentPanel}.
     */
    public List<MyShape> getShapes() {
        return shapes;
    }

    /**
     * Converts the given text into a {@link Shape}.
     * By default, the created {@link Shape} will be positioned in the bottom left corner of this {@link JPanel}.
     *
     * @param text The text to convert.
     * @return A {@link Shape} in the form of the outline of the given text.
     */
    private Shape createTextShape(String text) {
        Font font = getFont().deriveFont(Font.PLAIN, 13);
        GlyphVector glyphVector = font.createGlyphVector(getFontMetrics(font).getFontRenderContext(), text);
        Shape shape = glyphVector.getOutline();
        Rectangle bounds = shape.getBounds();
        AffineTransform transformation = new AffineTransform();
        transformation.translate(getWidth() - bounds.width - 10, getHeight() - bounds.height);
        return transformation.createTransformedShape(shape);
    }

    /**
     * An inner class for handling a movement of the mouse.
     */
    private class MouseMotionHandler extends MouseMotionAdapter {

        /**
         * Updates the current {@link Interaction} if the mouse was moved on this {@link ContentPanel}.
         *
         * @param e The {@link MouseEvent} to be handled.
         */
        @Override
        public void mouseMoved(MouseEvent e) {
            mf.getInteraction().update(e.getX(), e.getY());
            coordinates = createTextShape("x: %d y: %d".formatted(e.getX(), e.getY()));

            revalidate();
            repaint();
        }

        /**
         * Updates the current {@link Interaction} if the mouse was dragged on this {@link ContentPanel}.
         *
         * @param e The {@link MouseEvent} to be handled.
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            mouseMoved(e);
        }
    }

    /**
     * An inner class for handling an interaction with the mouse, such as pressing the mouse or leaving and entering this {@link ContentPanel}.
     */
    private class MouseInteractionHandler extends MouseAdapter {

        /**
         * Handle a mouse press on this {@link ContentPanel}
         * <p> Starts if possible a new {@link Interaction} or updates the currently running one.
         *
         * @param e The {@link MouseEvent} to be handled.
         */
        @Override
        public void mousePressed(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                if (!mf.getInteraction().isRunning() && mf.getInteraction().isTypeSet()) {
                    addShape(mf.getInteraction().start(e.getX(), e.getY()));
                } else if (mf.getInteraction().isRunning()) {
                    mf.getInteraction().nextPhase(e.getX(), e.getY());
                }
                revalidate();
                repaint();
            }
        }

        /**
         * Updates the look of the mouse cursor if the mouse enters this {@link ContentPanel}.
         *
         * @param e The {@link MouseEvent} to be handled.
         */
        @Override
        public void mouseEntered(MouseEvent e) {
            if (mf.getInteraction().isTypeSet())
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        }

        /**
         * Resets the look of the mouse cursor if the mouse leaves this {@link ContentPanel}.
         *
         * @param e The {@link MouseEvent} to be handled.
         */
        @Override
        public void mouseExited(MouseEvent e) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }

    }
}

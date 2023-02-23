package h12.gui.components;

import h12.gui.Interaction;
import h12.gui.shapes.MyShape;

import javax.swing.*;
import java.awt.*;

/**
 * The main frame of the painting program.
 */
public class MainFrame extends JFrame {

    private final ContentPanel contentPanel;
    private final ControlPanel controlPanel;
    private final Interaction interaction;

    /**
     * Creates a new {@link MainFrame}-Instance of the painting program and adds all contents.
     */
    public MainFrame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Painting Program");
        setMinimumSize(new Dimension(500, 500));

        setLayout(new BorderLayout());

        contentPanel = new ContentPanel(this);
        add(contentPanel, BorderLayout.CENTER);

        controlPanel = new ControlPanel(this);
        add(controlPanel, BorderLayout.NORTH);

        interaction = new Interaction();

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Returns the related {@link ContentPanel}.
     *
     * @return the related {@link ContentPanel}.
     */
    public ContentPanel getContentPanel() {
        return contentPanel;
    }

    /**
     * Returns the related {@link ControlPanel}.
     *
     * @return the related {@link ControlPanel}.
     */
    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    /**
     * Returns the {@link Interaction} object handling the creation process of a {@link MyShape}.
     *
     * @return the related {@link Interaction}.
     */
    public Interaction getInteraction() {
        return interaction;
    }


}

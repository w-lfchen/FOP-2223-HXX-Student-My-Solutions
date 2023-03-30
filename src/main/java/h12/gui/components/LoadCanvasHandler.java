package h12.gui.components;

import h12.exceptions.JSONParseException;
import h12.gui.shapes.ColorHelper;
import h12.gui.shapes.MyShape;
import h12.ioFactory.FileSystemIOFactory;
import h12.json.JSON;
import h12.json.JSONElement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A class handling the loading of a canvas from a JSON file.
 */
public class LoadCanvasHandler extends FileOperationHandler {

    private List<MyShape> shapes;
    private Color backgroundColor;

    /**
     * Creates a new {@link LoadCanvasHandler}-Instance.
     *
     * @param controlPanel The {@link ControlPanel} this {@link LoadCanvasHandler} belongs to.
     * @param json         The {@link JSON} object used to parse the contents of the selected file.
     */
    public LoadCanvasHandler(ControlPanel controlPanel, JSON json) {
        super(controlPanel);
        this.json = json;
    }

    /**
     * Shows a {@link JFileChooser} dialog to the user and tries to load the canvas stored in the selected file.
     */
    public void load() {
        // get path
        String selectedPath = selectFile(System.getProperty("user.dir"));
        // check legality of path
        if (checkFileName(selectedPath)) {
            try {
                // try loading the canvas
                json.setIOFactory(new FileSystemIOFactory());
                canvasFromJSONElement(json.parse(selectedPath));
            	setupNewFrame();
            } catch (JSONParseException exc) {
                // show error message if loading fails
                showErrorDialog(exc.getMessage());
            }
        }
        // do nothing otherwise
    }

    /**
     * Reads the content of the given {@link JSONElement} and saves them in the attributes {@link #shapes} and {@link #backgroundColor}.
     *
     * @param element The {@link JSONElement} to convert to a canvas.
     * @throws JSONParseException If the json file does not describe a valid canvas.
     */
    public void canvasFromJSONElement(JSONElement element) throws JSONParseException {
        // catch empty file
        if (element == null) {
            throw new JSONParseException("The given File is empty!");
        } else {
            try {
                // set the attributes
                backgroundColor = ColorHelper.fromJSON(element.getValueOf("background"));
                shapes = new ArrayList<>();
                // add all converted shapes to shapes
                for (JSONElement shape : element.getValueOf("shapes").getArray()) {
                    shapes.add(MyShape.fromJSON(shape));
                }
            } catch (UnsupportedOperationException | NoSuchElementException exc) {
                throw new JSONParseException("Invalid MyShape format!");
            }
        }
    }

    /**
     * Creates and shows a new {@link MainFrame} with the contents of the attributes {@link #shapes} and {@link #backgroundColor}.
     */
    public void setupNewFrame() {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setLocation(mainFrame.getX() + 25, mainFrame.getY() + 25);
        mainFrame.getContentPanel().addAll(shapes);
        mainFrame.getContentPanel().setBackground(backgroundColor);
        mainFrame.setVisible(true);
    }

}

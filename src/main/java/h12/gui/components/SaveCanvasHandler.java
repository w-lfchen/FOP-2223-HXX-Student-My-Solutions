package h12.gui.components;

import h12.exceptions.JSONWriteException;
import h12.gui.shapes.ColorHelper;
import h12.gui.shapes.MyShape;
import h12.ioFactory.FileSystemIOFactory;
import h12.json.JSONArray;
import h12.json.JSONElement;
import h12.json.JSONObject;
import h12.json.implementation.node.JSONArrayNode;
import h12.json.implementation.node.JSONObjectNode;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * A class handling the saving of a canvas to a JSON file.
 */
public class SaveCanvasHandler extends FileOperationHandler {

    private final List<MyShape> contents;
    private final Color background;

    /**
     * Creates a new {@link SaveCanvasHandler}-Instance.
     *
     * @param controlPanel The {@link ControlPanel} this {@link SaveCanvasHandler} belongs to.
     */
    public SaveCanvasHandler(ControlPanel controlPanel) {
        super(controlPanel);
        contents = controlPanel.getMainFrame().getContentPanel().getShapes();
        background = controlPanel.getMainFrame().getContentPanel().getBackground();
    }

    /**
     * Shows a {@link JFileChooser} dialog to the user and tries to save the current canvas to the selected file.
     */
    public void save() {
        // get the file that will be saved to
        String theFile = selectFile(System.getProperty("user.dir"));
        // check for validity
        if (checkFileName(theFile)) {
            try {
                json.setIOFactory(new FileSystemIOFactory());
                json.write(theFile, canvasToJSONObject());
            	showSuccessDialog(theFile);
            } catch (JSONWriteException exc) {
                // catch and show error dialog
                showErrorDialog(exc.getMessage());
            }
            // if nothing went wrong, show the success dialog
        }
        // otherwise do nothing
    }

    /**
     * Converts the contents of the associated {@link ContentPanel} to a {@link JSONObject}. The {@link JSONObject} contains the following entries:
     * <p> background: The background color of the canvas as a {@link JSONArrayNode}.
     * <p> shapes: The shapes on the canvas as a {@link JSONArrayNode}.
     *
     * @return A {@link JSONObjectNode} containing the contents of the associated {@link ContentPanel}.
     * @see ColorHelper#toJSON(Color)
     * @see MyShape#toJSON()
     */
    public JSONObject canvasToJSONObject() {
        return JSONObject.of(
            JSONObject.JSONObjectEntry.of("background", ColorHelper.toJSON(background)),
            // stream the contents of the canvas, convert them to JSON, then collect them and make an objectEntry out of all of it
            JSONObject.JSONObjectEntry.of("shapes", JSONArray.of(contents.stream().map(MyShape::toJSON).toArray(JSONElement[]::new)))
        );
    }
}

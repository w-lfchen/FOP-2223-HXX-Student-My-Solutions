package h12.gui.components;

import h12.json.JSON;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * An abstract class for handling an interaction with a file.
 */
public abstract class FileOperationHandler {

    protected final ControlPanel controlPanel;
    protected JSON json = new JSON();

    /**
     * Creates a new {@link FileOperationHandler}-Instance.
     *
     * @param controlPanel The {@link ControlPanel} this {@link FileOperationHandler} belongs to.
     */
    public FileOperationHandler(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    /**
     * Checks if the given file name is valid. A file name is valid if it is not null and ends with {@code ".json"}.
     *
     * @param fileName The file name to check.
     * @return {@code true}, if this file name is valid. Otherwise {@code false}.
     */
    public boolean checkFileName(String fileName) {
        if (fileName == null) {
            showErrorDialog("No file selected!");
            return false;
        } else if (!fileName.endsWith(".json")) {
            // if the file doesn't end with the correct type, it is invalid
            showErrorDialog("Invalid file type!");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Shows a {@link JFileChooser} dialog to the user.
     * This method blocks until the user has selected a file or closed the dialog.
     *
     * @return The selected file or {@code null} if no file was selected.
     */
    public String selectFile(String defaultPath) {
        JFileChooser jfc = new JFileChooser(defaultPath);
        jfc.setFileFilter(new FileNameExtensionFilter("json files", "json"));

        int returnValue = jfc.showDialog(controlPanel, "Select");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return jfc.getSelectedFile().getPath();
        }

        return null;
    }

    /**
     * Shows a message to the user that the file was saved successfully.
     *
     * @param path The path to the saved file.
     */
    public void showSuccessDialog(String path) {
        JOptionPane.showMessageDialog(controlPanel.getMainFrame(), "The canvas was saved successfully to %s.".formatted(path));
    }

    /**
     * Shows a message to the user that the contents were not saved successfully.
     *
     * @param errorMessage The shown error message.
     */
    public void showErrorDialog(String errorMessage) {
        JOptionPane.showMessageDialog(controlPanel.getMainFrame(), "Unable to save the canvas to the file.\n%s".formatted(errorMessage),
            "Error", JOptionPane.ERROR_MESSAGE);
    }


    /**
     * Sets the {@link JSON} Object used to save or load a canvas to the given {@link JSON} Object.
     *
     * @param json The new {@link JSON} Object.
     */
    public void setJson(JSON json) {
        this.json = json;
    }
}

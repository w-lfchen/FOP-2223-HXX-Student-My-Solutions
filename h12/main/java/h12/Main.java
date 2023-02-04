package h12;

import h12.gui.components.MainFrame;

import javax.swing.*;

/**
 * Main entry point in executing the program.
 */
public class Main {

    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {

        //start the GUI.
        startGUI();
    }

    /**
     * Starts the GUI.
     */
    private static void startGUI() {
        JFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}

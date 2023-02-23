package h13.view.gui;

import h13.controller.scene.menu.AboutController;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

/**
 * The {@link AboutScene} is a {@link SubMenuScene} that displays information about the project.
 */
public class AboutScene extends SubMenuScene<AboutController, TextArea> {

    /**
     * Creates a Scene for a specific root Node.
     */
    public AboutScene() {
        super(new TextArea(), new AboutController(), "About Project");
        init();
    }

    /**
     * Initialize the content of the scene.
     */
    private void init() {
        final var textArea = getContentRoot();
        textArea.setEditable(false);
        textArea.setFont(Font.font(18));
        textArea.setPrefSize(200, 200);
        textArea.setText("Exercise 13 of the subject Functional and Object-oriented programming concepts\nAuthor: Ruben Deisenroth\nBased on the game Space Invaders");
        textArea.setWrapText(true);
    }
}

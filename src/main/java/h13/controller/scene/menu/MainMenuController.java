package h13.controller.scene.menu;

import h13.controller.scene.SceneController;
import javafx.event.ActionEvent;

/**
 * A {@link SceneController} that manages the "Main Menu" scene.
 */
public class MainMenuController extends SceneController {

    @Override
    public String getTitle() {
        return "Space Invaders - Main Menu";
    }

    /**
     * Called when the user clicks the "Quit" button.
     *
     * @param e The {@link ActionEvent} that triggered this method.
     */
    public void quit(final ActionEvent e) {
        getStage().close();
    }
}

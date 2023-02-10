package h13.controller.scene;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.Nullable;

/**
 * A {@link SceneController} is responsible for dynamically managing a {@link Scene} and its {@link Stage}.
 */
public abstract class SceneController {
    // --Variables-- //

    /**
     * The {@link Stage} that is managed by this {@link SceneController}.
     */
    private Stage stage;

    /**
     * The {@link Stage} that is managed by this {@link SceneController}.
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Specifies the title of the {@link Stage}.
     * This is used in {@link #initStage(Stage)} to set the title of the {@link Stage}.
     *
     * @return The title of the {@link Stage}.
     */
    public abstract String getTitle();

    // --Setup Methods-- //

    /**
     * Initializes the {@link Stage} of this {@link SceneController}.
     * This default implementation sets the title of the {@link Stage} to {@link #getTitle()}.
     *
     * @param stage The {@link Stage} to initialize.
     */
    public void initStage(final Stage stage) {
        (this.stage = stage).setTitle(getTitle());
    }

    // --Button Actions-- //

    /**
     * Called when the user clicks the "Main Menu" button.
     *
     * @param e The {@link ActionEvent} that triggered this method.
     */
    public Scene loadMainMenuScene(final @Nullable ActionEvent e) {
        return SceneSwitcher.loadScene(SceneSwitcher.SceneType.MAIN_MENU, getStage());
    }

    /**
     * Called when the user clicks the "About" button.
     *
     * @param e The {@link ActionEvent} that triggered this method.
     */
    public Scene loadAboutScene(final @Nullable ActionEvent e) {
        return SceneSwitcher.loadScene(SceneSwitcher.SceneType.ABOUT, getStage());
    }

    /**
     * Called when the user clicks the "Settings" button.
     *
     * @param e The {@link ActionEvent} that triggered this method.
     */
    public Scene loadSettingsScene(final @Nullable ActionEvent e) {
        return SceneSwitcher.loadScene(SceneSwitcher.SceneType.SETTINGS, getStage());
    }

    /**
     * Called when the user clicks the "High Scores" button.
     *
     * @param e The {@link ActionEvent} that triggered this method.
     */
    public Scene loadHighscoreScene(final @Nullable ActionEvent e) {
        return SceneSwitcher.loadScene(SceneSwitcher.SceneType.HIGHSCORE, getStage());
    }

    /**
     * Called when the user clicks the "Start Game" button.
     *
     * @param e The {@link ActionEvent} that triggered this method.
     */
    public Scene loadGameScene(final @Nullable ActionEvent e) {
        return SceneSwitcher.loadScene(SceneSwitcher.SceneType.GAME, getStage());
    }
}
